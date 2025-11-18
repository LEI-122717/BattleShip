package iscteiul.ista.battleship;

import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes unitários da classe Galleon")
class GalleonTest {

    private IPosition startPos;

    @BeforeEach
    void setUp() {
        startPos = new Position(5, 5);
    }

    @AfterEach
    void tearDown() {
        startPos = null;
    }

    @Nested
    @DisplayName("Testes de posições e tamanho")
    class PositionAndSizeTests {

        @Test
        @DisplayName("NORTH cria posições corretas")
        void testNorthPositions() {
            Galleon galleon = new Galleon(Compass.NORTH, startPos);
            List<IPosition> expected = List.of(
                    new Position(5, 5),
                    new Position(5, 6),
                    new Position(5, 7),
                    new Position(6, 6),
                    new Position(7, 6)
            );
            assertEquals(5, galleon.getPositions().size());
            assertTrue(galleon.getPositions().containsAll(expected));
        }

        @Test
        @DisplayName("SOUTH cria posições corretas")
        void testSouthPositions() {
            Galleon galleon = new Galleon(Compass.SOUTH, startPos);
            List<IPosition> expected = List.of(
                    new Position(5, 5),
                    new Position(6, 5),
                    new Position(7, 4),
                    new Position(7, 5),
                    new Position(7, 6)
            );
            assertEquals(5, galleon.getPositions().size());
            assertTrue(galleon.getPositions().containsAll(expected));
        }

        @Test
        @DisplayName("EAST cria posições corretas")
        void testEastPositions() {
            Galleon galleon = new Galleon(Compass.EAST, startPos);
            List<IPosition> expected = List.of(
                    new Position(5, 5),
                    new Position(6, 3),
                    new Position(6, 4),
                    new Position(6, 5),
                    new Position(7, 5)
            );
            assertEquals(5, galleon.getPositions().size());
            assertTrue(galleon.getPositions().containsAll(expected));
        }

        @Test
        @DisplayName("WEST cria posições corretas")
        void testWestPositions() {
            Galleon galleon = new Galleon(Compass.WEST, startPos);
            List<IPosition> expected = List.of(
                    new Position(5, 5),
                    new Position(6, 5),
                    new Position(6, 6),
                    new Position(6, 7),
                    new Position(7, 5)
            );
            assertEquals(5, galleon.getPositions().size());
            assertTrue(galleon.getPositions().containsAll(expected));
        }

        @Test
        @DisplayName("getSize() deve retornar 5")
        void testSize() {
            Galleon galleon = new Galleon(Compass.NORTH, startPos);
            assertEquals(5, galleon.getSize());
        }
    }

    @Nested
    @DisplayName("Testes de exceção")
    class ExceptionTests {

        @Test
        @DisplayName("Construtor com bearing nulo deve lançar AssertionError")
        void testNullBearing() {
            assertThrows(AssertionError.class, () -> new Galleon(null, startPos));
        }

        @Test
        @DisplayName("Construtor com position nula deve lançar AssertionError")
        void testNullPosition() {
            assertThrows(AssertionError.class, () -> new Galleon(Compass.NORTH, null));
        }

        @Test
        @DisplayName("Construtor com bearing inválido deve lançar IllegalArgumentException")
        void testUnsupportedBearing() {
            Compass unsupported = Compass.UNKNOWN; // garante branch default
            IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                    () -> new Galleon(unsupported, startPos));
            assertEquals("ERROR! invalid bearing for the galleon", ex.getMessage());
        }
    }
}
