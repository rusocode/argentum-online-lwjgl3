package org.aoclient.network.protocol.handlers;

import org.aoclient.network.PacketBuffer;
import org.tinylog.Logger;

public class UserCommerceEndHandler implements PacketHandler {

    @Override
    public void handle(PacketBuffer buffer) {
        buffer.readByte();

        //Set InvComUsu = Nothing
        //    Set InvOroComUsu(0) = Nothing
        //    Set InvOroComUsu(1) = Nothing
        //    Set InvOroComUsu(2) = Nothing
        //    Set InvOfferComUsu(0) = Nothing
        //    Set InvOfferComUsu(1) = Nothing
        //
        //    'Destroy the form and reset the state
        //    Unload frmComerciarUsu
        //    Comerciando = False

        Logger.debug("handleUserCommerceEnd CARGADO - FALTA TERMINAR!");
    }

}
