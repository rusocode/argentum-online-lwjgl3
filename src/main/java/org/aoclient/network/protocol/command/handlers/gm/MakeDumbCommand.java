package org.aoclient.network.protocol.command.handlers.gm;

import org.aoclient.network.protocol.command.BaseCommandHandler;
import org.aoclient.network.protocol.command.CommandContext;
import org.aoclient.network.protocol.command.CommandException;

import static org.aoclient.network.protocol.Protocol.writeMakeDumb;
import static org.aoclient.network.protocol.command.GameCommand.ESTUPID;

public class MakeDumbCommand extends BaseCommandHandler {

    @Override
    public void handle(CommandContext commandContext) throws CommandException {
        requireArguments(commandContext, 1, ESTUPID.getCommand() + " <nick>");
        String nick = commandContext.getArgument(0);
        writeMakeDumb(nick);
    }

}
