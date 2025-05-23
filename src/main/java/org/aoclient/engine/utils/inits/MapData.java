package org.aoclient.engine.utils.inits;

/**
 * Simula el "Type" o la estructura de {@code MapData}, representando los datos de una celda individual en el mapa.
 * <p>
 * Esta clase almacena toda la informacion necesaria para representar una celda especifica del mapa, incluyendo sus capas
 * graficas, personajes, objetos y propiedades de interaccion.
 *
 * @see GrhInfo
 * @see Obj
 */

public final class MapData {

    private final GrhInfo[] layer = new GrhInfo[5];
    private short charIndex;
    private GrhInfo objGrh;
    private short npcIndex;
    private Obj objInfo;
    private boolean blocked;
    private short trigger;

    public MapData() {
        layer[1] = new GrhInfo();
        layer[2] = new GrhInfo();
        layer[3] = new GrhInfo();
        layer[4] = new GrhInfo();
        objGrh = new GrhInfo();
    }

    public GrhInfo getLayer(int index) {
        return layer[index];
    }

    public void setLayer(int index, GrhInfo layer) {
        this.layer[index] = layer;
    }

    public short getCharIndex() {
        return charIndex;
    }

    public void setCharIndex(int charIndex) {
        this.charIndex = (short) charIndex;
    }

    public GrhInfo getObjGrh() {
        return objGrh;
    }

    public void setObjGrh(GrhInfo objGrh) {
        this.objGrh = objGrh;
    }

    public short getNpcIndex() {
        return npcIndex;
    }

    public void setNpcIndex(short npcIndex) {
        this.npcIndex = npcIndex;
    }

    public Obj getObjInfo() {
        return objInfo;
    }

    public void setObjInfo(Obj objInfo) {
        this.objInfo = objInfo;
    }

    public boolean getBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public short getTrigger() {
        return trigger;
    }

    public void setTrigger(int trigger) {
        this.trigger = (short) trigger;
    }

}
