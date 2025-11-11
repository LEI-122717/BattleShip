package iscteiul.ista.battleship;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes da enum Compass")
class CompassTest {

    @Test
    @DisplayName("Verifica se o método getDirection() devolve o carácter correto para cada direção")
    void getDirection() {
        assertEquals('n', Compass.NORTH.getDirection());
        assertEquals('s', Compass.SOUTH.getDirection());
        assertEquals('e', Compass.EAST.getDirection());
        assertEquals('o', Compass.WEST.getDirection());
        assertEquals('u', Compass.UNKNOWN.getDirection());
    }

    @Test
    @DisplayName("Verifica se o método toString() devolve o nome da direção corretamente")
    void testToString() {
        assertEquals("n", Compass.NORTH.toString());
        assertEquals("s", Compass.SOUTH.toString());
        assertEquals("e", Compass.EAST.toString());
        assertEquals("o", Compass.WEST.toString());
        assertEquals("u", Compass.UNKNOWN.toString());
    }

    @Test
    @DisplayName("Valida se o método charToCompass() converte caracteres corretamente para a enumeração")
    void charToCompass() {
        assertEquals(Compass.NORTH, Compass.charToCompass('n'));
        assertEquals(Compass.SOUTH, Compass.charToCompass('s'));
        assertEquals(Compass.EAST, Compass.charToCompass('e'));
        assertEquals(Compass.WEST, Compass.charToCompass('o'));
        assertEquals(Compass.UNKNOWN, Compass.charToCompass('x'),
                "Caracteres inválidos devem retornar UNKNOWN");
    }

    @Test
    @DisplayName("Verifica se o método values() devolve todas as direções esperadas")
    void values() {
        Compass[] directions = Compass.values();
        assertEquals(5, directions.length, "A enum deve conter 5 valores (incluindo UNKNOWN).");
        assertArrayEquals(
                new Compass[]{Compass.NORTH, Compass.SOUTH, Compass.EAST, Compass.WEST, Compass.UNKNOWN},
                directions,
                "A enumeração deve conter todas as direções conhecidas e UNKNOWN."
        );
    }

    @Test
    @DisplayName("Verifica se o método valueOf() devolve corretamente o valor do enum")
    void valueOf() {
        assertEquals(Compass.NORTH, Compass.valueOf("NORTH"));
        assertEquals(Compass.SOUTH, Compass.valueOf("SOUTH"));
        assertEquals(Compass.EAST, Compass.valueOf("EAST"));
        assertEquals(Compass.WEST, Compass.valueOf("WEST"));
        assertEquals(Compass.UNKNOWN, Compass.valueOf("UNKNOWN"));
    }
}
