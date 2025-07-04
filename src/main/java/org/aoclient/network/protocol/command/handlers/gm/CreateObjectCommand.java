package org.aoclient.network.protocol.command.handlers.gm;

import org.aoclient.network.protocol.command.BaseCommandHandler;
import org.aoclient.network.protocol.command.CommandContext;
import org.aoclient.network.protocol.command.CommandException;

import static org.aoclient.network.protocol.Protocol.writeCreateObject;
import static org.aoclient.network.protocol.command.GameCommand.CREATE_OBJ;

/**
 * Al crear el hacha (objeto #3 del obj.dat del servidor VB6), se indica GrhIndex=505 pero el grafico real es el 16037 en
 * graphics-descompressed del cliente Java.
 */

public class CreateObjectCommand extends BaseCommandHandler {

    @Override
    public void handle(CommandContext commandContext) throws CommandException {
        requireArguments(commandContext, 1, CREATE_OBJ.getCommand() + " <object_id>");
        requireInteger(commandContext, 0, "object_id");
        int objectId = Integer.parseInt(commandContext.getArgument(0));
        writeCreateObject(objectId);
    }

}
