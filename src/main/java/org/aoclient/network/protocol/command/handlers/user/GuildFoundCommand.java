package org.aoclient.network.protocol.command.handlers.user;

import org.aoclient.network.protocol.command.core.CommandContext;
import org.aoclient.network.protocol.command.core.CommandException;
import org.aoclient.network.protocol.command.handlers.BaseCommandHandler;

import static org.aoclient.network.protocol.Protocol.guildFound;

public class GuildFoundCommand extends BaseCommandHandler {

    private static final int MIN_LEVEL_REQUIRED = 25;

    @Override
    public void handle(CommandContext commandContext) throws CommandException {
        if (user.getUserLvl() < MIN_LEVEL_REQUIRED)
            showError("To found a clan you must be level 25 and have 90 leadership skills.");
        guildFound(); // TODO No falta el argumento del nombre del clan?
    }

}
