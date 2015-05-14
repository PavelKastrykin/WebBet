package com.pavel.webbet.dao.mysql.connectionpool;

import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;

public final class ConnectionPool {

    public static final Logger logger = Logger.getLogger(ConnectionPool.class);

    private static ConnectionPool instance = new ConnectionPool();

    public static ConnectionPool getInstance() {
         return instance;
    }

    private BlockingQueue<Connection> connectionQueue;
    private BlockingQueue<Connection> givenAwayConQueue;

    private static final String PROPERTY_USER = "user";
    private static final String PROPERTY_PASSWORD = "password";
    private static final String PROPERTY_USE_UNICODE = "useUnicode";
    private static final String PROPERTY_ENCODING = "characterEncoding";

    private String driverName;
    private String url;
    private String user;
    private String password;
    private int poolSize;
    private String encoding;

    private ConnectionPool() {
        DBResourceManager dbResourceManager = DBResourceManager.getInstance();
        this.driverName = dbResourceManager.getValue(DBParameter.DB_DRIVER);
        this.url = dbResourceManager.getValue(DBParameter.DB_URL);
        this.user = dbResourceManager.getValue(DBParameter.DB_USER);
        this.password = dbResourceManager.getValue(DBParameter.DB_PASSWORD);
        this.encoding = dbResourceManager.getValue(DBParameter.DB_ENCODING);
        try {
            this.poolSize = Integer.parseInt(dbResourceManager.getValue(DBParameter.DB_POOL_SIZE));
        }
        catch (NumberFormatException e){
            poolSize = 5;
            logger.info("Connection pool size properties corrupted, size set to 5");
        }
        try{
            initPoolData();
        }
        catch (ConnectionPoolException e){
            logger.error("Connection pool was not initialized", e);
        }

    }

    public void initPoolData() throws ConnectionPoolException {
        Locale.setDefault(Locale.ENGLISH);

        try{
            Class.forName(driverName);
            givenAwayConQueue = new ArrayBlockingQueue<>(poolSize);
            connectionQueue = new ArrayBlockingQueue<>(poolSize);

            Properties properties = new Properties();
            properties.setProperty(PROPERTY_USER, user);
            properties.setProperty(PROPERTY_PASSWORD, password);
            properties.setProperty(PROPERTY_USE_UNICODE, "true");
            properties.setProperty(PROPERTY_ENCODING, encoding);
            for (int i = 0; i < poolSize; i++){
                Connection connection = DriverManager.getConnection(url, properties);
                PooledConnection pooledConnection = new PooledConnection(connection);
                connectionQueue.add(pooledConnection);
            }
        }
        catch (SQLException e){
            logger.error("SQLException in Connection pool", e);
            throw new ConnectionPoolException("No connection to database. Try later");
        }
        catch (ClassNotFoundException e){
            logger.error("Can't find database driver class", e);
            throw new ConnectionPoolException("No connection to database. Try later");
        }
    }

    public void dispose() throws ConnectionPoolException {
        clearConnectionQueue();
    }

    public void clearConnectionQueue() throws ConnectionPoolException{
        try{
            closeConnectionsQueue(givenAwayConQueue);
            closeConnectionsQueue(connectionQueue);
        }
        catch (SQLException e){
            logger.error("Unable to clear connection queue", e);
            throw new ConnectionPoolException("WebBet works unstable, please reopen it");
        }
    }

    public Connection takeConnection() throws ConnectionPoolException {
        Connection connection;
        try{
            connection = connectionQueue.take();
            givenAwayConQueue.add(connection);
        }
        catch (InterruptedException e){
            logger.error("Error connecting to the data source", e);
            throw new ConnectionPoolException("No connection to database. Try later");
        }
        return connection;
    }

    public void closeConnection(Connection con, Statement st, ResultSet rs) throws ConnectionPoolException{
        try{
            con.close();
        }
        catch (SQLException e){
            logger.error("Failed to close connection", e);
            throw new ConnectionPoolException("WebBet works unstable, please reopen it");
        }
        try{
            rs.close();
        }
        catch (SQLException e){
            logger.error("Failed to close resultset", e);
            throw new ConnectionPoolException("");
        }
        try{
            st.close();
        }
        catch (SQLException e){
            logger.error("Failed to close statement", e);
            throw new ConnectionPoolException("");
        }
    }

    public void closeConnection(Connection con, Statement st) throws ConnectionPoolException{
        try{
            con.close();
        }
        catch (SQLException e){
            logger.error("Failed to close connection", e);
            throw new ConnectionPoolException("WebBet works unstable, please reopen it");
        }
        try{
            st.close();
        }
        catch (SQLException e){
            logger.error("Failed to close statement", e);
            throw new ConnectionPoolException("");
        }
    }

    public void closeConnectionsQueue(BlockingQueue<Connection> queue) throws SQLException {
        Connection connection;
        while ((connection = queue.poll()) != null){
            if (!connection.getAutoCommit()){
                connection.commit();
            }
            ((PooledConnection)connection).reallyClose();
        }
    }

    private class PooledConnection implements Connection{
        private Connection connection;

        public PooledConnection(Connection c) throws SQLException {
            this.connection = c;
            this.connection.setAutoCommit(true);
        }

        public void reallyClose() throws SQLException {
            connection.close();
        }
        @Override
        public Statement createStatement() throws SQLException {
            return connection.createStatement();
        }
        @Override
        public PreparedStatement prepareStatement(String sql) throws SQLException {
            return connection.prepareStatement(sql);
        }
        @Override
        public CallableStatement prepareCall(String sql) throws SQLException {
            return connection.prepareCall(sql);
        }
        @Override
        public String nativeSQL(String sql) throws SQLException {
            return connection.nativeSQL(sql);
        }
        @Override
        public void setAutoCommit(boolean autoCommit) throws SQLException {
            connection.setAutoCommit(autoCommit);
        }
        @Override
        public boolean getAutoCommit() throws SQLException {
            return connection.getAutoCommit();
        }
        @Override
        public void commit() throws SQLException {
            connection.commit();
        }
        @Override
        public void rollback() throws SQLException {
            connection.rollback();
        }
        @Override
        public void close() throws SQLException {
            if(connection.isClosed()){
                logger.info("Attempting to close closed connection.");
                throw new SQLException("");
            }

            if(connection.isReadOnly()){
                connection.setReadOnly(false);
            }

            if (!givenAwayConQueue.remove(this)){
                logger.info("Error deleting connection from the given away connections pool");
                throw new SQLException("");
            }

            if (!connectionQueue.offer(this)){
                logger.info("Error allocating connection in the pool");
                throw new SQLException();
//
            }
        }
        @Override
        public boolean isClosed() throws SQLException {
            return connection.isClosed();
        }
        @Override
        public DatabaseMetaData getMetaData() throws SQLException {
            return connection.getMetaData();
        }
        @Override
        public void setReadOnly(boolean readOnly) throws SQLException {
            connection.setReadOnly(readOnly);
        }
        @Override
        public boolean isReadOnly() throws SQLException {
            return connection.isReadOnly();
        }
        @Override
        public void setCatalog(String catalog) throws SQLException {
            connection.setCatalog(catalog);
        }
        @Override
        public String getCatalog() throws SQLException {
            return connection.getCatalog();
        }
        @Override
        public void setTransactionIsolation(int level) throws SQLException {
            connection.setTransactionIsolation(level);
        }
        @Override
        public int getTransactionIsolation() throws SQLException {
            return connection.getTransactionIsolation();
        }
        @Override
        public SQLWarning getWarnings() throws SQLException {
            return connection.getWarnings();
        }
        @Override
        public void clearWarnings() throws SQLException {
            connection.clearWarnings();
        }
        @Override
        public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
            return connection.createStatement(resultSetType, resultSetConcurrency);
        }
        @Override
        public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
            return connection.prepareStatement(sql, resultSetType, resultSetConcurrency);
        }
        @Override
        public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
            return connection.prepareCall(sql, resultSetType, resultSetConcurrency);
        }
        @Override
        public Map<String, Class<?>> getTypeMap() throws SQLException {
            return connection.getTypeMap();
        }
        @Override
        public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
            connection.setTypeMap(map);
        }
        @Override
        public void setHoldability(int holdability) throws SQLException {
            connection.setHoldability(holdability);
        }
        @Override
        public int getHoldability() throws SQLException {
            return connection.getHoldability();
        }
        @Override
        public Savepoint setSavepoint() throws SQLException {
            return connection.setSavepoint();
        }
        @Override
        public Savepoint setSavepoint(String name) throws SQLException {
            return connection.setSavepoint(name);
        }
        @Override
        public void rollback(Savepoint savepoint) throws SQLException {
            connection.rollback(savepoint);
        }
        @Override
        public void releaseSavepoint(Savepoint savepoint) throws SQLException {
            connection.releaseSavepoint(savepoint);
        }
        @Override
        public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
            return connection.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
        }
        @Override
        public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
            return connection.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
        }
        @Override
        public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
            return connection.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
        }
        @Override
        public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
            return connection.prepareStatement(sql, autoGeneratedKeys);
        }
        @Override
        public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
            return connection.prepareStatement(sql, columnIndexes);
        }
        @Override
        public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
            return connection.prepareStatement(sql, columnNames);
        }
        @Override
        public Clob createClob() throws SQLException {
            return connection.createClob();
        }
        @Override
        public Blob createBlob() throws SQLException {
            return connection.createBlob();
        }
        @Override
        public NClob createNClob() throws SQLException {
            return connection.createNClob();
        }
        @Override
        public SQLXML createSQLXML() throws SQLException {
            return connection.createSQLXML();
        }
        @Override
        public boolean isValid(int timeout) throws SQLException {
            return connection.isValid(timeout);
        }
        @Override
        public void setClientInfo(String name, String value) throws SQLClientInfoException {
            connection.setClientInfo(name, value);
        }
        @Override
        public void setClientInfo(Properties properties) throws SQLClientInfoException {
            connection.setClientInfo(properties);
        }
        @Override
        public String getClientInfo(String name) throws SQLException {
            return connection.getClientInfo(name);
        }
        @Override
        public Properties getClientInfo() throws SQLException {
            return connection.getClientInfo();
        }
        @Override
        public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
            return connection.createArrayOf(typeName, elements);
        }
        @Override
        public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
            return connection.createStruct(typeName, attributes);
        }
        @Override
        public void setSchema(String schema) throws SQLException {
            connection.setSchema(schema);
        }
        @Override
        public String getSchema() throws SQLException {
            return connection.getSchema();
        }
        @Override
        public void abort(Executor executor) throws SQLException {
            connection.abort(executor);
        }
        @Override
        public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
            connection.setNetworkTimeout(executor, milliseconds);
        }
        @Override
        public int getNetworkTimeout() throws SQLException {
            return connection.getNetworkTimeout();
        }
        @Override
        public <T> T unwrap(Class<T> iface) throws SQLException {
            return connection.unwrap(iface);
        }
        @Override
        public boolean isWrapperFor(Class<?> iface) throws SQLException {
            return connection.isWrapperFor(iface);
        }
    }

}
