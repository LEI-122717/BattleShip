package iscteiul.ista.battleship;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

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

    @Nested
    @DisplayName("Metadados")
    class Metadata {
        @Test
        @DisplayName("getSize() deve retornar 3")
        void testGetSize() {
            Carrack carrack = new Carrack(Compass.NORTH, start);
            assertEquals(3, carrack.getSize());
        }
    }

    @Nested
    @DisplayName("Posições por orientação")
    class PositionsByBearing {

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

        @ParameterizedTest(name = "bearing={0} -> positions contiguous and size=3")
        @EnumSource(value = Compass.class, names = {"NORTH", "SOUTH", "EAST", "WEST"})
        @DisplayName("Posições contínuas e tamanho = 3 (parametrizado)")
        void positions_are_contiguous(Compass bearing) {
            Carrack carrack = new Carrack(bearing, start);
            List<IPosition> positions = carrack.getPositions();

            assertEquals(3, positions.size(), "Carrack must occupy exactly three cells");

            // validar contiguidade básica para cada orientação
            switch (bearing) {
                case NORTH, SOUTH -> {
                    assertEquals(new Position(0, 0), positions.get(0));
                    assertEquals(new Position(1, 0), positions.get(1));
                    assertEquals(new Position(2, 0), positions.get(2));
                }
                case EAST, WEST -> {
                    assertEquals(new Position(0, 0), positions.get(0));
                    assertEquals(new Position(0, 1), positions.get(1));
                    assertEquals(new Position(0, 2), positions.get(2));
                }
                default -> fail("Unsupported bearing: " + bearing);
            }
        }
    }

    @Nested
    @DisplayName("Validação do construtor e erros")
    class ConstructorValidation {

        @Test
        @DisplayName("Carrack com bearing null deve lançar NullPointerException (lançado pelo Ship)")
        void testCarrackInvalidBearing_null() {
            // O Ship.<init> lança NullPointerException quando bearing == null
            assertThrows(NullPointerException.class,
                    () -> new Carrack(null, start),
                    "Deve lançar NullPointerException quando o bearing é null (comportamento atual do Ship)");
        }

        @Test
        @DisplayName("Direção inválida (valueOf) deve lançar IllegalArgumentException")
        void testCarrackInvalidBearing_valueOf() {
            assertThrows(IllegalArgumentException.class,
                    () -> Compass.valueOf("INVALID"),
                    "valueOf com direção inválida deve lançar IllegalArgumentException");
        }
    }
}
