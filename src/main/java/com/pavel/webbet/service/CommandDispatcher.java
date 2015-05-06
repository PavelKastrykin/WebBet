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
        commands.put(CommandType.CREATE_BET_FORM_COMMAND, new DoCreateBetFormCommand());
        commands.put(CommandType.ADD_BET_COMMAND, new DoAddBetCommand());
        commands.put(CommandType.SHOW_MY_BETS_COMMAND, new DoShowMyBetsCommand());
        commands.put(CommandType.CREATE_MATCH_EDIT_FORM_COMMAND, new DoCreateMatchEditCommand());
        commands.put(CommandType.EDIT_MATCH_COMMAND, new DoEditMatchCommand());
        commands.put(CommandType.CREATE_USER_LIST, new DoCreateUserListCommand());
        commands.put(CommandType.CREATE_BET_LIST, new DoCreateBetListCommand());
        commands.put(CommandType.EDIT_BET_COMMAND, new DoCreateBetEditCommand());
        commands.put(CommandType.CONFIRM_EDIT_BET_COMMAND, new DoConfirmEditBetCommand());
        commands.put(CommandType.EDIT_USER_COMMAND, new DoCreateUserEditCommand());
        commands.put(CommandType.CONFIRM_EDIT_USER_COMMAND, new DoConfirmEditUserCommand());
        commands.put(CommandType.DELETE_MATCH_COMMAND, new DoDeleteMatchCommand());
    }

    public static CommandDispatcher getInstance(){
        return instance;
    }

    public ICommand getCommand(String commandName){
        CommandType commandType = CommandType.valueOf(commandName);
        ICommand command;
        if (null != commandType){
            try {
                command = commands.get(commandType);
            }
            catch (IllegalArgumentException e){
                command = commands.get(CommandType.NO_SUCH_COMMAND);
            }

        }
        else {
            command = commands.get(CommandType.NO_SUCH_COMMAND);
        }
        return command;
    }
}
