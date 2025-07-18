package org.aoclient.network.protocol.command.handlers.gm;

import org.aoclient.network.protocol.command.core.CommandContext;
import org.aoclient.network.protocol.command.core.CommandException;
import org.aoclient.network.protocol.command.core.CommandValidator;
import org.aoclient.network.protocol.command.handlers.BaseCommandHandler;

import static org.aoclient.network.protocol.Protocol.banIP;
import static org.aoclient.network.protocol.command.metadata.GameCommand.BAN_IP;

public class BanIpCommand extends BaseCommandHandler {

    @Override
    public void handle(CommandContext commandContext) throws CommandException {
        requireArguments(commandContext, 2, getCommandUsage(BAN_IP));
        requireString(commandContext, 0, "ip|nick");
        requireString(commandContext, 1, "reason");

        String ipOrNick = commandContext.getArgument(0);
        String reason = commandContext.getArgument(1);

        if (CommandValidator.isValidIPv4(ipOrNick))
            banIP(true, CommandValidator.parseIPv4ToArray(ipOrNick), "", reason); // Banea por IP
        else
            banIP(false, CommandValidator.parseIPv4ToArray("0.0.0.0"), ipOrNick, reason); // Banea por nick (buscar IP del jugador)

    }

}
