package org.aoclient.engine.renderer;

import org.aoclient.engine.utils.BinaryDataReader;

import java.nio.ByteOrder;

import static org.aoclient.engine.renderer.Drawn.drawGrhIndex;
import static org.aoclient.engine.utils.GameData.grhData;
import static org.aoclient.scripts.Compressor.readResource;

/**
 * Clase utilitaria que maneja la carga y renderizado de fuentes de texto.
 * <p>
 * Esta clase estatica proporciona funcionalidad para cargar distintos tipos de fuentes desde archivos, renderizar texto en
 * pantalla con diferentes estilos, y calcular las dimensiones del texto para su correcta ubicacion.
 * <p>
 * Contiene constantes predefinidas para los diferentes tipos de fuentes utilizados (como fuente normal y fuente para mostrar
 * golpes), y metodos para dibujar texto con diferentes colores, alineaciones y formatos. Tambien ofrece funciones para medir el
 * ancho y alto del texto renderizado.
 * <p>
 * La clase utiliza un sistema de codigos ASCII para mapear caracteres a sus representaciones graficas, permitiendo renderizar
 * texto de manera eficiente mediante el sistema de graficos.
 * <p>
 * Es fundamental para mostrar dialogos, mensajes del sistema, nombres de personajes y cualquier otro texto que aparece en el
 * juego.
 */

public class FontTypes {

    public static final int NORMAL_FONT = 0;
    public static final int HIT_FONT = 1;
    private static final BinaryDataReader reader = new BinaryDataReader();
    private static Font[] font_types;

    public static void loadFontTypes() {
        byte[] data = readResource("resources/inits.ao", "fonts");
        if (data == null) {
            System.err.println("Could not load fonts data!");
            return;
        }

        reader.init(data, ByteOrder.BIG_ENDIAN);

        int cantFontTypes = (int) reader.readInt();
        font_types = new Font[cantFontTypes];

        for (int i = 0; i < cantFontTypes; i++) {
            font_types[i] = new Font();
            font_types[i].fontSize = reader.readInt();
            for (int k = 0; k < 256; k++)
                font_types[i].ascii_code[k] = reader.readInt();
        }

    }

    public static void drawText(String text, int x, int y, RGBColor color, int fontIndex, boolean multiLine) {
        int a, b, d = 0, e, f;
        if (text.isEmpty()) return;
        if (!multiLine) {
            for (a = 0; a < text.length(); a++) {
                b = text.charAt(a);
                if (b > 255) b = 0;
                if (b != 32) {
                    if (font_types[fontIndex].ascii_code[b] != 0) {
                        drawGrhIndex(font_types[fontIndex].ascii_code[b], (x + d) + 1, y, color);
                        d += grhData[font_types[fontIndex].ascii_code[b]].getPixelWidth();
                    }
                } else d += 4;
            }
        } else {
            e = 0;
            f = 0;
            for (a = 0; a < text.length(); a++) {
                b = text.charAt(a);
                if (b > 255) b = 0;
                if (b == 32 || b == 13) {
                    if (e >= 20) {
                        f++;
                        e = 0;
                        d = 0;
                    } else if (b == 32) d = d + 4;
                } else {
                    if (font_types[fontIndex].ascii_code[b] > 12) {
                        drawGrhIndex(font_types[fontIndex].ascii_code[b], (x + d) + 1, y + f * 14, color);
                        d += grhData[font_types[fontIndex].ascii_code[b]].getPixelWidth();
                    }
                }
                e++;
            }
        }
    }

    public static int getTextWidth(String text, boolean multi) {
        int retVal = 0;
        int b, e, d = 0;
        if (!multi) {
            for (int a = 0; a < text.length(); a++) {
                b = text.charAt(a);
                if ((b != 32) && (b != 5) && (b != 129) && (b != 9) && (b != 4) && (b != 255) && (b != 2) && (b != 151) && (b != 152)) {
                    retVal = retVal + grhData[font_types[NORMAL_FONT].ascii_code[b]].getPixelWidth();
                } else retVal = retVal + 4;
            }
        } else {
            e = 0;
            for (int a = 0; a < text.length(); a++) {
                b = text.charAt(a);
                if (b == 32 || b == 13) {
                    if (e >= 20) { // reemplazar por lo que quieran
                        e = 0;
                        d = 0;
                    } else if (b == 32) d += 4;
                } else {
                    if (font_types[NORMAL_FONT].ascii_code[b] > 12) {
                        d += grhData[font_types[NORMAL_FONT].ascii_code[b]].getPixelWidth();
                        if (d > retVal) retVal = d;
                    }
                }
                e++;
            }
        }
        return retVal;
    }

    public static int getTextHeight(String text, boolean multi) {
        int retVal = 0;
        int b, e, f;
        if (!multi) return retVal;
        else {
            e = 0;
            f = 0;
            for (int a = 0; a < text.length(); a++) {
                b = text.charAt(a);
                if (b == 32 || b == 13) {
                    if (e >= 20) {
                        f++;
                        e = 0;
                    }
                }
                e++;
            }
            retVal = f * 14;
        }
        return retVal;
    }

    static class Font {
        int fontSize;
        int[] ascii_code = new int[256];
    }

}
