package org.aoclient.network.protocol.command.handlers.gm;

import org.aoclient.network.protocol.command.core.CommandContext;
import org.aoclient.network.protocol.command.core.CommandException;
import org.aoclient.network.protocol.command.handlers.BaseCommandHandler;

import static org.aoclient.network.protocol.Protocol.removePunishment;
import static org.aoclient.network.protocol.command.metadata.GameCommand.REMOVE_PUNISHMENT;

public class RemovePunishmentCommand extends BaseCommandHandler {

    @Override
    public void handle(CommandContext commandContext) throws CommandException {
        requireArguments(commandContext, 3, getCommandUsage(REMOVE_PUNISHMENT));
        requireString(commandContext, 0, "player");
        requireInteger(commandContext, 1, "minutes");
        requireString(commandContext, 2, "newPunishment");
        String player = commandContext.getArgument(0);
        int minutes = Integer.parseInt(commandContext.getArgument(1));
        String newPunishment = commandContext.getArgument(2);
        removePunishment(player, minutes, newPunishment);
    }

}
