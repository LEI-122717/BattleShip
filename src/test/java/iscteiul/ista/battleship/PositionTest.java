package iscteiul.ista.battleship;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes da classe Position")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PositionTest {

    private Position position;

    @BeforeEach
    void setUp() {
        position = new Position(2, 3);
    }

    @AfterEach
    void tearDown() {
        position = null;
    }

    @Nested
    @DisplayName("Testes de acesso a propriedades")
    class PropertyTests {

        @Test
        @DisplayName("Deve retornar a linha correta")
        void getRow() {
            assertEquals(2, position.getRow());
        }

        @Test
        @DisplayName("Deve retornar a coluna correta")
        void getColumn() {
            assertEquals(3, position.getColumn());
        }
    }

    @Nested
    @DisplayName("Testes de igualdade e hashCode")
    class EqualityTests {

        @Test
        @DisplayName("hashCode deve ser igual para posições idênticas")
        void testHashCode() {
            Position same = new Position(2, 3);
            assertEquals(position.hashCode(), same.hashCode());
        }

        @Test
        @DisplayName("equals deve funcionar corretamente")
        void testEquals() {
            Position same = new Position(2, 3);
            Position differentRow = new Position(1, 3);
            Position differentColumn = new Position(2, 4);

            assertAll(
                    () -> assertTrue(position.equals(same)),
                    () -> assertFalse(position.equals(differentRow)),
                    () -> assertFalse(position.equals(differentColumn)),
                    () -> assertFalse(position.equals(null)),
                    () -> assertFalse(position.equals("texto")),
                    () -> assertTrue(position.equals(position))
            );
        }
    }

    @Nested
    @DisplayName("Testes de adjacência")
    class AdjacencyTests {

        @Test
        @DisplayName("Deve identificar posições adjacentes corretamente")
        void isAdjacentTo() {
            Position adjacent = new Position(3, 3);
            Position diagonal = new Position(1, 2);
            Position farAway = new Position(5, 5);
            Position farRow = new Position(10, 3);
            Position farColumn = new Position(2, 10);

            assertAll(
                    () -> assertTrue(position.isAdjacentTo(adjacent)),
                    () -> assertTrue(position.isAdjacentTo(diagonal)),
                    () -> assertFalse(position.isAdjacentTo(farAway)),
                    () -> assertFalse(position.isAdjacentTo(farRow)),
                    () -> assertFalse(position.isAdjacentTo(farColumn))
            );
        }
    }

    @Nested
    @DisplayName("Testes de ocupação e impacto")
    class StateTests {

        @Test
        void occupy() {
            assertFalse(position.isOccupied());
            position.occupy();
            assertTrue(position.isOccupied());
        }

        @Test
        void shoot() {
            assertFalse(position.isHit());
            position.shoot();
            assertTrue(position.isHit());
        }

        @Test
        void isOccupied() {
            assertFalse(position.isOccupied());
            position.occupy();
            assertTrue(position.isOccupied());
        }

        @Test
        void isHit() {
            assertFalse(position.isHit());
            position.shoot();
            assertTrue(position.isHit());
        }
    }

    @Nested
    @DisplayName("Testes de representação textual")
    class StringTests {
        @Test
        void testToString() {
            assertEquals("Linha = 2 Coluna = 3", position.toString());
        }
    }
}