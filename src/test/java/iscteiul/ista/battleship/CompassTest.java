package iscteiul.ista.battleship;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes da enum Compass")
class CompassTest {

    @Nested
    @DisplayName("Testes de direções e caracteres")
    class DirectionTests {

        @Test
        void getDirection() {
            assertEquals('n', Compass.NORTH.getDirection());
            assertEquals('s', Compass.SOUTH.getDirection());
            assertEquals('e', Compass.EAST.getDirection());
            assertEquals('o', Compass.WEST.getDirection());
            assertEquals('u', Compass.UNKNOWN.getDirection());
        }

        @Test
        void testToString() {
            assertEquals("n", Compass.NORTH.toString());
            assertEquals("s", Compass.SOUTH.toString());
            assertEquals("e", Compass.EAST.toString());
            assertEquals("o", Compass.WEST.toString());
            assertEquals("u", Compass.UNKNOWN.toString());
        }
    }

    @Nested
    @DisplayName("Testes de conversão de caracteres")
    class ConversionTests {

        @Test
        void charToCompass() {
            assertEquals(Compass.NORTH, Compass.charToCompass('n'));
            assertEquals(Compass.SOUTH, Compass.charToCompass('s'));
            assertEquals(Compass.EAST, Compass.charToCompass('e'));
            assertEquals(Compass.WEST, Compass.charToCompass('o'));
            assertEquals(Compass.UNKNOWN, Compass.charToCompass('x'));
        }
    }

    @Nested
    @DisplayName("Testes de enumeração")
    class EnumTests {

        @Test
        void values() {
            Compass[] directions = Compass.values();
            assertEquals(5, directions.length);
            assertArrayEquals(
                    new Compass[]{Compass.NORTH, Compass.SOUTH, Compass.EAST, Compass.WEST, Compass.UNKNOWN},
                    directions
            );
        }

        @Test
        void valueOfTest() {
            assertEquals(Compass.NORTH, Compass.valueOf("NORTH"));
            assertEquals(Compass.SOUTH, Compass.valueOf("SOUTH"));
            assertEquals(Compass.EAST, Compass.valueOf("EAST"));
            assertEquals(Compass.WEST, Compass.valueOf("WEST"));
            assertEquals(Compass.UNKNOWN, Compass.valueOf("UNKNOWN"));
        }
    }
}