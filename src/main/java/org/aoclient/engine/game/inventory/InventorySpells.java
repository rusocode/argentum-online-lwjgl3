package org.aoclient.engine.game.inventory;

import imgui.ImGui;
import imgui.flag.ImGuiCond;
import imgui.flag.ImGuiWindowFlags;
import imgui.type.ImInt;
import org.aoclient.engine.game.models.Skill;

import static org.aoclient.network.protocol.Protocol.*;

/**
 * <p>
 * Proporciona la funcionalidad para manejar y mostrar la lista de hechizos. Contiene los metodos necesarios para renderizar la
 * interfaz grafica de los hechizos, agregar nuevos hechizos al inventario, y ejecutar acciones como lanzar un hechizo o solicitar
 * informacion detallada sobre el mismo.
 * <p>
 * El inventario de hechizos se presenta como una lista interactiva donde el jugador puede seleccionar cualquier hechizo
 * disponible y realizar acciones con el mismo mediante los botones de la interfaz.
 */

public class InventorySpells {
    private static final int MAX_INVENTORY_SPELLS = 35;
    private final ImInt currentItemSpell = new ImInt(0);
    private final String[] spells;

    public InventorySpells() {
        this.spells = new String[MAX_INVENTORY_SPELLS];
        for (int i = 0; i < MAX_INVENTORY_SPELLS; i++) spells[i] = "";
    }

    public void draw() {
        ImGui.setNextWindowPos(595, 159);
        ImGui.setNextWindowSize(250, 171, ImGuiCond.Once);

        ImGui.begin("InventorySpells", ImGuiWindowFlags.NoTitleBar
                | ImGuiWindowFlags.NoBackground
                | ImGuiWindowFlags.NoResize
                | ImGuiWindowFlags.NoSavedSettings);

        ImGui.setCursorPos(5, 1);
        ImGui.pushID("spells");
        ImGui.listBox("", currentItemSpell, spells);
        ImGui.popID();

        ImGui.end();

        ImGui.setCursorPos(589, 353);
        if (ImGui.button("Throw Spell", 77, 25)) this.buttonThrowSpell();

        ImGui.setCursorPos(717, 353);
        if (ImGui.button("Info Spell", 57, 27)) this.buttonInfoSpell();

    }

    public void addSpell(final int slot, final String spell) {
        spells[slot] = spell;
    }

    public void buttonThrowSpell() {
        if (!spells[currentItemSpell.get()].equals("(None)")) {
            castSpell(currentItemSpell.get() + 1);
            work(Skill.MAGIC.getId());
        }
    }

    public void buttonInfoSpell() {
        spellInfo(currentItemSpell.get() + 1);
    }

}
