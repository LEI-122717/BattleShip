package iscteiul.ista.battleship;

import org.junit.jupiter.api.*;
import java.util.List;
import static iscteiul.ista.battleship.IFleet.BOARD_SIZE;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes da classe Fleet")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FleetTest {

    private Fleet fleet;
    private IShip frigate;
    private IShip galleon;
    private IShip barge;
    private IShip galleonToCollide;

    @BeforeEach
    void setUp() {
        fleet = new Fleet();
        frigate = new Frigate(Compass.SOUTH, new Position(0, 0));
        galleon = new Galleon(Compass.EAST, new Position(7, 7));
        galleonToCollide = new Galleon(Compass.EAST, new Position(2, 2));
        barge = new Barge(Compass.SOUTH, new Position(4, 5));
    }
    
    @Nested
    @DisplayName("Adicionar navios à frota")
    class AddShipTests {

        @Test
        @DisplayName("Adicionar navios com sucesso e evitar colisões")
        void testAddShipSuccessfully() {
            assertTrue(fleet.addShip(frigate));
            assertTrue(fleet.addShip(galleon));
            assertFalse(fleet.addShip(galleonToCollide));
            assertEquals(2, fleet.getShips().size());

            Fleet fleetToFill = new Fleet();
            Position[] positions = {
                    new Position(0, 0), new Position(0, 2), new Position(0, 4),
                    new Position(2, 0), new Position(2, 2), new Position(2, 4),
                    new Position(4, 0), new Position(4, 2), new Position(4, 4),
                    new Position(6, 0), new Position(6, 2)
            };

            for (Position pos : positions) {
                Barge barge = new Barge(Compass.NORTH, pos);
                fleetToFill.addShip(barge);
            }

            Barge barge = new Barge(Compass.SOUTH, new Position(6, 4));
            assertFalse(fleetToFill.addShip(barge));
        }

        @Test
        @DisplayName("Detectar colisões entre navios")
        void testAddShipCollision() {
            fleet.addShip(frigate);
            IShip collidingShip = new Frigate(Compass.NORTH, new Position(0, 0));
            assertFalse(fleet.addShip(collidingShip));
            assertEquals(1, fleet.getShips().size());
        }

        @Test
        @DisplayName("Verificar posições dentro e fora do tabuleiro")
        void testIsInsideBoardBranches() {
            Fleet fleet = new Fleet();
            IShip left = new Frigate(Compass.NORTH, new Position(0, -1));
            assertFalse(fleet.addShip(left));

            IShip right = new Frigate(Compass.NORTH, new Position(0, BOARD_SIZE));
            assertFalse(fleet.addShip(right));

            IShip top = new Frigate(Compass.NORTH, new Position(-1, 0));
            assertFalse(fleet.addShip(top));

            IShip bottom = new Frigate(Compass.NORTH, new Position(BOARD_SIZE, 0));
            assertFalse(fleet.addShip(bottom));

            IShip valid = new Frigate(Compass.NORTH, new Position(5, 5));
            assertTrue(fleet.addShip(valid));
        }
    }

    @Nested
    @DisplayName("Consultar informação da frota")
    class QueryFleetTests {

        @Test
        @DisplayName("Obter navios de um tipo específico")
        void testGetShipsLike() {
            fleet.addShip(frigate);
            fleet.addShip(galleon);
            fleet.addShip(barge);

            List<IShip> frigates = fleet.getShipsLike("Fragata");
            assertEquals(1, frigates.size());
            assertEquals(frigate, frigates.get(0));

            List<IShip> galleons = fleet.getShipsLike("Galeao");
            assertEquals(1, galleons.size());
            assertEquals(galleon, galleons.get(0));
        }

        @Test
        @DisplayName("Obter navios ainda a flutuar")
        void testGetFloatingShips() {
            fleet.addShip(frigate);
            fleet.addShip(barge);

            barge.getPositions().get(0).shoot();

            List<IShip> floatingShips = fleet.getFloatingShips();
            assertTrue(floatingShips.contains(frigate));
        }

        @Test
        @DisplayName("Identificar navio numa posição específica")
        void testShipAt() {
            fleet.addShip(frigate);
            fleet.addShip(galleon);

            IPosition pos = frigate.getPositions().get(0);
            assertEquals(frigate, fleet.shipAt(pos));

            IPosition emptyPos = new Position(9, 9);
            assertNull(fleet.shipAt(emptyPos));
        }
    }

    @Nested
    @DisplayName("Impressão e estado da frota")
    class PrintAndStatusTests {

        @Test
        @DisplayName("Imprimir estado da frota e categorias")
        void testPrintStatus() {
            fleet.addShip(frigate);
            fleet.addShip(galleon);
            fleet.addShip(barge);

            assertDoesNotThrow(() -> fleet.printStatus());
            assertThrows(AssertionError.class, () -> {
                fleet.printShipsByCategory(null);
            });
        }
    }
}
