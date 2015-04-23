package com.pavel.webbet.service;

import com.pavel.webbet.service.impl.*;

import java.util.HashMap;
import java.util.Map;

public class CommandDispatcher {
    private static final CommandDispatcher instance = new CommandDispatcher();
    private Map<CommandType, ICommand> commands = new HashMap<CommandType, ICommand>();

    public CommandDispatcher(){
        commands.put(CommandType.LOGIN_COMMAND, new DoLoginCommand());
        commands.put(CommandType.LOCALE_COMMAND, new DoLocaleCommand());
        commands.put(CommandType.REGISTER_COMMAND, new DoRegisterCommand());
        commands.put(CommandType.LOGOUT_COMMAND, new DoLogoutCommand());
        commands.put(CommandType.DISPLAY_MATCHES_COMMAND, new DoDisplayMatchesCommand());
        commands.put(CommandType.ADD_MATCH_COMMAND, new DoAddMatchCommand());
        commands.put(CommandType.CREATE_BET_FORM, new DoCreateBetForm());
    }

    public static CommandDispatcher getInstance(){
        return instance;
    }

    public ICommand getCommand(String commandName){
        CommandType commandType = CommandType.valueOf(commandName);
        ICommand command;
        if (null != commandType){
            command = commands.get(commandType);
        }
        else {
            command = commands.get(CommandType.NO_SUCH_COMMAND);
        }
        return command;
    }
}
