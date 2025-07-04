package org.aoclient.network.protocol.command.handlers.gm;

import org.aoclient.network.protocol.command.BaseCommandHandler;
import org.aoclient.network.protocol.command.CommandContext;
import org.aoclient.network.protocol.command.CommandException;

import static org.aoclient.network.protocol.Protocol.writeComment;
import static org.aoclient.network.protocol.command.GameCommand.REM;

public class CommentCommand extends BaseCommandHandler {

    @Override
    public void handle(CommandContext commandContext) throws CommandException {
        requireArguments(commandContext, -1, REM.getCommand() + " <comment>");
        requireValidString(commandContext, "comment", REGEX);
        String comment = commandContext.argumentsRaw().trim();
        writeComment(comment);
    }

}
