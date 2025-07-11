package org.aoclient.network.protocol.command.handlers.gm;

import org.aoclient.network.protocol.command.handlers.BaseCommandHandler;
import org.aoclient.network.protocol.command.core.CommandContext;
import org.aoclient.network.protocol.command.core.CommandException;
import org.aoclient.network.protocol.command.core.CommandValidator;

import static org.aoclient.network.protocol.Protocol.IPToNick;
import static org.aoclient.network.protocol.command.metadata.GameCommand.IP2NICK;

/**
 * Comando para obtener el nickname asociado a una dirección IP.
 * <p>
 * Uso: {@code /ip2nick <ip>}
 * <p>
 * Parametros:
 * <ul>
 * <li>ip: Dirección IP en formato IPv4 (ej: 192.168.1.1)
 * </ul>
 * <p>
 * Ejemplo: {@code /ip2nick 192.168.1.100} - Buscar nickname asociado a esta IP
 */

public class IpToNickCommand extends BaseCommandHandler {

    @Override
    public void handle(CommandContext commandContext) throws CommandException {
        requireArguments(commandContext, 1, IP2NICK.getCommand() + " <ip>");

        String ip = commandContext.argumentsRaw();

        if (!CommandValidator.isValidIPv4(ip)) showError("Invalid IP address, must be a valid IPv4 address (e.g., 192.168.1.1).");

        int[] ipArray = CommandValidator.parseIPv4ToArray(ip);
        if (ipArray == null) showError("Error parsing IP address.");

        IPToNick(ipArray);
    }

}

