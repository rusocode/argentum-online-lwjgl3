package org.aoclient.network.protocol.command.handlers.gm;

import org.aoclient.network.protocol.command.handlers.BaseCommandHandler;
import org.aoclient.network.protocol.command.core.CommandContext;
import org.aoclient.network.protocol.command.core.CommandException;

import static org.aoclient.network.protocol.Protocol.banChar;
import static org.aoclient.network.protocol.command.metadata.GameCommand.BAN;

public class BanCharCommand extends BaseCommandHandler {

    @Override
    public void handle(CommandContext commandContext) throws CommandException {
        requireArguments(commandContext, 2, BAN.getCommand() + " <nick> <reason>");
        requireString(commandContext, 0, "nick");
        requireString(commandContext, 1, "reason");

        String nick = commandContext.getArgument(0);
        String reason = commandContext.getArgument(1);

        banChar(nick, reason);
    }

}
