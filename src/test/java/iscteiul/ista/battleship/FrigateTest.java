package iscteiul.ista.battleship;

import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para a classe Frigate (tamanho 4).
 */
@DisplayName("Testes para a classe Frigate")
class FrigateTest {

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
    @DisplayName("Frigate NORTH deve criar 4 posições verticais corretas")
    void testFrigateNorthPositions() {
        Frigate frigate = new Frigate(Compass.NORTH, start);
        List<IPosition> positions = frigate.getPositions();

        assertAll("Verificação das posições (NORTH)",
                () -> assertEquals(4, frigate.getSize(), "Tamanho deve ser 4"),
                () -> assertEquals(4, positions.size(), "Deve ter 4 posições"),
                () -> assertEquals(new Position(0, 0), positions.get(0)),
                () -> assertEquals(new Position(3, 0), positions.get(3))
        );
    }

    @Test
    @DisplayName("Frigate SOUTH deve criar 4 posições verticais corretas")
    void testFrigateSouthPositions() {
        Frigate frigate = new Frigate(Compass.SOUTH, start);
        List<IPosition> positions = frigate.getPositions();

        assertAll("Verificação das posições (SOUTH)",
                () -> assertEquals(4, positions.size()),
                () -> assertEquals(new Position(0, 0), positions.get(0)),
                () -> assertEquals(new Position(3, 0), positions.get(3))
        );
    }

    @Test
    @DisplayName("Frigate EAST deve criar 4 posições horizontais corretas")
    void testFrigateEastPositions() {
        Frigate frigate = new Frigate(Compass.EAST, start);
        List<IPosition> positions = frigate.getPositions();

        assertAll("Verificação das posições (EAST)",
                () -> assertEquals(4, positions.size()),
                () -> assertEquals(new Position(0, 0), positions.get(0)),
                () -> assertEquals(new Position(0, 3), positions.get(3))
        );
    }

    @Test
    @DisplayName("Frigate WEST deve criar 4 posições horizontais corretas")
    void testFrigateWestPositions() {
        Frigate frigate = new Frigate(Compass.WEST, start);
        List<IPosition> positions = frigate.getPositions();

        assertAll("Verificação das posições (WEST)",
                () -> assertEquals(4, positions.size()),
                () -> assertEquals(new Position(0, 0), positions.get(0)),
                () -> assertEquals(new Position(0, 3), positions.get(3))
        );
    }

    @Test
    @DisplayName("Frigate com direção inválida deve lançar NullPointerException")
    void testFrigateInvalidDirectionThrowsException() {
        assertThrows(AssertionError.class,
                () -> new Frigate(null, start),
                "Deve lançar AssertionError para direção inválida");
    }

    @Test
    @DisplayName("getSize() deve retornar 4")
    void testGetSize() {
        Frigate frigate = new Frigate(Compass.NORTH, start);
        assertEquals(4, frigate.getSize());
    }
}
