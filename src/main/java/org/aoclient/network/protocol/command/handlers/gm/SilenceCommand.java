package org.aoclient.network.protocol.command.handlers.gm;

import org.aoclient.network.protocol.command.BaseCommandHandler;
import org.aoclient.network.protocol.command.CommandContext;
import org.aoclient.network.protocol.command.CommandException;

import static org.aoclient.network.protocol.Protocol.writeSilence;
import static org.aoclient.network.protocol.command.GameCommand.SILENT;

public class SilenceCommand extends BaseCommandHandler {

    @Override
    public void handle(CommandContext commandContext) throws CommandException {
        requireArguments(commandContext, 1, SILENT.getCommand() + " <nick>");
        String nick = commandContext.getArgument(0);
        writeSilence(nick);
    }

}
