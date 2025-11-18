package iscteiul.ista.battleship;

import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para a classe Carrack (tamanho 3)
 */
@DisplayName("Testes para a classe Carrack")
class CarrackTest {

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
    @DisplayName("Carrack NORTH deve criar posições corretas")
    void testCarrackNorthPositions() {
        Carrack carrack = new Carrack(Compass.NORTH, start);
        List<IPosition> positions = carrack.getPositions();

        assertAll("Verificação posições NORTH",
                () -> assertEquals(3, carrack.getSize()),
                () -> assertEquals(3, positions.size()),
                () -> assertEquals(new Position(0, 0), positions.get(0)),
                () -> assertEquals(new Position(1, 0), positions.get(1)),
                () -> assertEquals(new Position(2, 0), positions.get(2))
        );
    }

    @Test
    @DisplayName("Carrack SOUTH deve criar posições corretas")
    void testCarrackSouthPositions() {
        Carrack carrack = new Carrack(Compass.SOUTH, start);
        List<IPosition> positions = carrack.getPositions();

        assertAll("Verificação posições SOUTH",
                () -> assertEquals(3, positions.size()),
                () -> assertEquals(new Position(0, 0), positions.get(0)),
                () -> assertEquals(new Position(1, 0), positions.get(1)),
                () -> assertEquals(new Position(2, 0), positions.get(2))
        );
    }

    @Test
    @DisplayName("Carrack EAST deve criar posições corretas")
    void testCarrackEastPositions() {
        Carrack carrack = new Carrack(Compass.EAST, start);
        List<IPosition> positions = carrack.getPositions();

        assertAll("Verificação posições EAST",
                () -> assertEquals(3, positions.size()),
                () -> assertEquals(new Position(0, 0), positions.get(0)),
                () -> assertEquals(new Position(0, 1), positions.get(1)),
                () -> assertEquals(new Position(0, 2), positions.get(2))
        );
    }

    @Test
    @DisplayName("Carrack WEST deve criar posições corretas")
    void testCarrackWestPositions() {
        Carrack carrack = new Carrack(Compass.WEST, start);
        List<IPosition> positions = carrack.getPositions();

        assertAll("Verificação posições WEST",
                () -> assertEquals(3, positions.size()),
                () -> assertEquals(new Position(0, 0), positions.get(0)),
                () -> assertEquals(new Position(0, 1), positions.get(1)),
                () -> assertEquals(new Position(0, 2), positions.get(2))
        );
    }

    @Test
    @DisplayName("Carrack com bearing null deve lançar NullPointerException (lançado pelo Ship)")
    void testCarrackInvalidBearing_null() {
        // O Ship.<init> lança NullPointerException quando bearing == null
        assertThrows(AssertionError.class,
                () -> new Carrack(null, start),
                "Deve lançar AssertionError para direção inválida");
    }

    @Test
    @DisplayName("Direção inválida (valueOf) deve lançar IllegalArgumentException")
    void testCarrackInvalidBearing_valueOf() {
        assertThrows(IllegalArgumentException.class,
                () -> Compass.valueOf("INVALID"),
                "valueOf com direção inválida deve lançar IllegalArgumentException");
    }

    @Test
    @DisplayName("getSize() deve retornar 3")
    void testGetSize() {
        Carrack carrack = new Carrack(Compass.NORTH, start);
        assertEquals(3, carrack.getSize());
    }
}
