package org.aoclient.engine.gui.forms;

import imgui.ImGui;
import imgui.flag.ImGuiCond;
import imgui.flag.ImGuiInputTextFlags;
import imgui.flag.ImGuiWindowFlags;
import imgui.type.ImString;
import org.aoclient.engine.game.User;

import java.io.IOException;

import static org.aoclient.engine.game.inventory.UserInventory.FLAGORO;
import static org.aoclient.network.Protocol.writeDrop;

public final class FCantidad extends Form {

    private final ImString cant = new ImString("1");
    private final boolean dropOro;

    public FCantidad() {
        this.dropOro = false;

        try {
            this.backgroundImage = loadTexture("VentanaTirarOro");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public FCantidad(boolean dropOro) {
        this.dropOro = dropOro;
        try {
            this.backgroundImage = loadTexture("VentanaTirarOro");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void render() {
        ImGui.setNextWindowSize(225, 100, ImGuiCond.Always);
        ImGui.begin(this.getClass().getSimpleName(), ImGuiWindowFlags.NoTitleBar | ImGuiWindowFlags.NoResize | ImGuiWindowFlags.NoDecoration |
                ImGuiWindowFlags.NoBackground);

        ImGui.setCursorPos(5, 0);
        ImGui.image(backgroundImage, 216, 100);

        ImGui.setCursorPos(35, 30);
        ImGui.pushItemWidth(150);
        ImGui.pushID("cantidad");
        ImGui.inputText("", cant, ImGuiInputTextFlags.CharsDecimal | ImGuiInputTextFlags.CallbackResize);
        ImGui.popID();
        ImGui.popItemWidth();

        // buttons
        ImGui.setCursorPos(20, 65);
        if (ImGui.button("Tirar", 89, 25)) {
            if (!cant.get().isEmpty()) {
                final int cantToDrop = Integer.parseInt(cant.get());
                if (cantToDrop > 0 && cantToDrop <= 10000) {
                    if (dropOro) writeDrop(FLAGORO, cantToDrop);
                    else writeDrop(User.get().getUserInventory().getSlotSelected() + 1, cantToDrop);
                }
            }
            this.close();
        }

        ImGui.setCursorPos(118, 65);
        if (ImGui.button("Tirar todo", 89, 25)) {
            if (!cant.get().isEmpty()) {
                final int cantToDrop = Integer.parseInt(cant.get());
                if (cantToDrop > 0 && cantToDrop <= 10000) {
                    if (dropOro) writeDrop(FLAGORO, Math.min(User.get().getUserGLD(), 10000));
                    else
                        writeDrop(User.get().getUserInventory().getSlotSelected() + 1, User.get().getUserInventory().getAmountSlotSelected());
                }
            }
            this.close();
        }
        ImGui.end();
    }

}
