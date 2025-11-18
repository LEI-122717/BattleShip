package iscteiul.ista.battleship;

import org.junit.jupiter.api.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Teste para a classe Barge")
class BargeTest {

    // campo partilhado entre os nested tests
    private IPosition startPos;

    @BeforeEach
    void globalSetUp() {
        // posição base para os testes
        startPos = new Position(3, 3);
    }

    @Nested
    @DisplayName("Metadados")
    class MetadataTests {
        private Barge barge;

        @BeforeEach
        void setUp() {
            barge = new Barge(Compass.NORTH, startPos);
        }

        @Test
        @DisplayName("Verificar metadados da Barca (Tamanho)")
        void testBargeMetadata() {
            assertEquals(1, barge.getSize(), "O tamanho da Barca deve ser sempre 1");
        }
    }

    @Nested
    @DisplayName("Posição e inicialização")
    class PositionTests {
        private Barge barge;

        @BeforeEach
        void setUp() {
            barge = new Barge(Compass.NORTH, startPos);
        }

        @Test
        @DisplayName("Verificar inicialização de posição única")
        void testBargePosition() {
            // Verifica se a orientação foi guardada corretamente
            assertEquals(Compass.NORTH, barge.getBearing(), "A orientação inicial deve ser preservada");

            // Verifica as posições ocupadas
            List<IPosition> positions = barge.getPositions();
            assertNotNull(positions, "A lista de posições não deve ser nula");
            assertEquals(1, positions.size(), "A Barca deve ocupar exatamente 1 posição");

            // Verifica se a posição ocupada é exatamente a que foi passada no construtor
            IPosition occupiedPos = positions.get(0);
            assertEquals(startPos, occupiedPos, "A posição ocupada deve ser exatamente a passada no construtor");
        }
    }

    @Nested
    @DisplayName("Independência de orientação")
    class OrientationTests {

        @ParameterizedTest(name = "orientation = {0}")
        @EnumSource(value = Compass.class, names = {"NORTH", "EAST", "SOUTH", "WEST"})
        @DisplayName("Verificar independência da orientação para tamanho 1 (parametrizado)")
        void testOrientationIndependence(Compass orientation) {
            // Como a Barca tem tamanho 1, a posição ocupada deve ser a mesma independentemente da orientação
            IPosition pos = new Position(5, 5);

            Barge b = new Barge(orientation, pos);

            assertAll("Verificar que para cada orientação a posição é exactamente a passada",
                    () -> assertNotNull(b.getPositions(), "Lista de posições não deve ser nula"),
                    () -> assertEquals(1, b.getPositions().size(), "Deve ocupar exactamente 1 posição"),
                    () -> assertEquals(pos, b.getPositions().get(0), "A posição ocupada deve ser igual à passada no construtor")
            );
        }
    }

    @Nested
    @DisplayName("Validação do construtor")
    class ConstructorValidation {
        @Test
        @DisplayName("Construtor com bearing null deve lançar NullPointerException (lançado por Ship)")
        void testNullBearingThrowsNullPointerException() {
            assertThrows(NullPointerException.class,
                    () -> new Barge(null, new Position(0, 0)),
                    "Deve lançar NullPointerException quando o bearing é null (comportamento actual do Ship)");
        }
    }
}
