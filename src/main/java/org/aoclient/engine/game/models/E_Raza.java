package org.aoclient.engine.game.models;

/**
 * Representa las razas que un jugador puede seleccionar al momento de crear su personaje.
 */

public enum E_Raza {

    Humano(1),
    Elfo(2),
    Elfo_Drow(3),
    Gnomo(4),
    Enano(5);

    private final int value;

    E_Raza(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
