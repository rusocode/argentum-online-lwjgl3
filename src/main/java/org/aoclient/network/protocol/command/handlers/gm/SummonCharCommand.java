package org.aoclient.network.protocol.command.handlers.gm;

import org.aoclient.network.protocol.command.BaseCommandHandler;
import org.aoclient.network.protocol.command.CommandContext;
import org.aoclient.network.protocol.command.CommandException;

import static org.aoclient.network.protocol.Protocol.writeSummonChar;
import static org.aoclient.network.protocol.command.GameCommand.SUM;

public class SummonCharCommand extends BaseCommandHandler {

    @Override
    public void handle(CommandContext commandContext) throws CommandException {
        requireArguments(commandContext, 1, SUM.getCommand() + " <nick>");
        writeSummonChar(); // TODO Raro que no se pase nada
    }

}
