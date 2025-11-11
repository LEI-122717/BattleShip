package iscteiul.ista.battleship;

import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para a classe Galleon (tamanho 5).
 */
@DisplayName("Testes para a classe Galleon")
class GalleonTest {

    private IPosition start;

    @BeforeEach
    void setUp() {
        start = new Position(0, 0);
    }

    @AfterEach
    void tearDown() {
        start = null;
    }

    @Test
    @DisplayName("Galleon NORTH deve criar posições corretas")
    void testGalleonNorthPositions() {
        Galleon galleon = new Galleon(Compass.NORTH, start);
        List<IPosition> positions = galleon.getPositions();

        assertAll("Verificação posições NORTH",
                () -> assertEquals(5, galleon.getSize()),
                () -> assertEquals(5, positions.size()),
                () -> assertEquals(new Position(0,0), positions.get(0)),
                () -> assertEquals(new Position(0,1), positions.get(1)),
                () -> assertEquals(new Position(0,2), positions.get(2)),
                () -> assertEquals(new Position(1,1), positions.get(3)),
                () -> assertEquals(new Position(2,1), positions.get(4))
        );
    }

    @Test
    @DisplayName("Galleon SOUTH deve criar posições corretas")
    void testGalleonSouthPositions() {
        Galleon galleon = new Galleon(Compass.SOUTH, start);
        List<IPosition> positions = galleon.getPositions();

        assertAll("Verificação posições SOUTH",
                () -> assertEquals(5, positions.size()),
                () -> assertEquals(new Position(0,0), positions.get(0)),
                () -> assertEquals(new Position(1,0), positions.get(1)),
                () -> assertEquals(new Position(2,-1), positions.get(2)),
                () -> assertEquals(new Position(2,0), positions.get(3)),
                () -> assertEquals(new Position(2,1), positions.get(4))
        );
    }


    @Test
    @DisplayName("Galleon EAST deve criar posições corretas")
    void testGalleonEastPositions() {
        Galleon galleon = new Galleon(Compass.EAST, start);
        List<IPosition> positions = galleon.getPositions();

        assertAll("Verificação posições EAST",
                () -> assertEquals(5, positions.size()),
                () -> assertEquals(new Position(0,0), positions.get(0)),
                () -> assertEquals(new Position(1,-2), positions.get(1)),
                () -> assertEquals(new Position(1,-1), positions.get(2)),
                () -> assertEquals(new Position(1,0), positions.get(3)),
                () -> assertEquals(new Position(2,0), positions.get(4))
        );
    }

    @Test
    @DisplayName("Galleon WEST deve criar posições corretas")
    void testGalleonWestPositions() {
        Galleon galleon = new Galleon(Compass.WEST, start);
        List<IPosition> positions = galleon.getPositions();

        assertAll("Verificação posições WEST",
                () -> assertEquals(5, positions.size()),
                () -> assertEquals(new Position(0,0), positions.get(0)),
                () -> assertEquals(new Position(1,0), positions.get(1)),
                () -> assertEquals(new Position(1,1), positions.get(2)),
                () -> assertEquals(new Position(1,2), positions.get(3)),
                () -> assertEquals(new Position(2,0), positions.get(4))
        );
    }

    @Test
    @DisplayName("Galleon com bearing null deve lançar AssertionError")
    void testGalleonNullBearing() {
        assertThrows(AssertionError.class,
                () -> new Galleon(null, start),
                "Deve lançar AssertionError se o bearing for null");
    }

    @Test
    @DisplayName("Galleon com direção inválida deve lançar IllegalArgumentException")
    void testGalleonInvalidBearing() {
        assertThrows(IllegalArgumentException.class,
                () -> new Galleon(Compass.valueOf("INVALID"), start),
                "Deve lançar IllegalArgumentException para direção inválida");
    }

    @Test
    @DisplayName("getSize() deve retornar 5")
    void testGetSize() {
        Galleon galleon = new Galleon(Compass.NORTH, start);
        assertEquals(5, galleon.getSize());
    }
}
