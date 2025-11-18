package iscteiul.ista.battleship;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Caravel (size 2)")
class CaravelTest {

    @Nested
    @DisplayName("Metadados")
    class Metadata {
        @Test
        @DisplayName("getSize() returns 2")
        void getSize_returnsTwo() {
            Caravel c = new Caravel(Compass.NORTH, new Position(1, 1));
            assertEquals(2, c.getSize());
        }
    }

    @Nested
    @DisplayName("Validação do construtor")
    class ConstructorValidation {
        @Test
        @DisplayName("Null bearing throws NullPointerException (lançado por Ship)")
        void nullBearing_throwsNullPointerException() {
            // Ship.<init> lança NullPointerException quando bearing == null
            assertThrows(NullPointerException.class,
                    () -> new Caravel(null, new Position(0, 0)));
        }
    }

    @Nested
    @DisplayName("Posições ocupadas")
    class OccupiedPositions {

        @ParameterizedTest(name = "bearing={0} -> positions are contiguous and size=2")
        @EnumSource(
                value = Compass.class,
                names = { "NORTH", "SOUTH", "EAST", "WEST" } // apenas os suportados
        )
        @DisplayName("Occupied positions are contiguous and match bearing")
        void occupiedPositions_areContiguous(Compass bearing) {
            Position anchor = new Position(2, 3);
            Caravel caravel = new Caravel(bearing, anchor);

            List<IPosition> cells = caravel.getPositions();
            assertEquals(2, cells.size(), "Caravel must occupy exactly two cells");

            // unique cells
            Set<IPosition> set = new HashSet<>(cells);
            assertEquals(2, set.size(), "Occupied cells must be unique");

            // expected contiguous cells (apenas direções suportadas)
            List<IPosition> expected = switch (bearing) {
                case NORTH, SOUTH -> List.of(
                        new Position(anchor.getRow(),     anchor.getColumn()),
                        new Position(anchor.getRow() + 1, anchor.getColumn())
                );
                case EAST, WEST -> List.of(
                        new Position(anchor.getRow(), anchor.getColumn()),
                        new Position(anchor.getRow(), anchor.getColumn() + 1)
                );
                default -> throw new IllegalArgumentException("Unsupported bearing: " + bearing);
            };

            assertIterableEquals(expected, cells,
                    "Cells must be contiguous from the anchor following bearing rules");
        }
    }

    @Nested
    @DisplayName("Extremos")
    class Extremes {
        private Caravel caravel;
        private List<IPosition> cells;

        @BeforeEach
        void setUp() {
            // Escolhemos SOUTH para gerar vertical (2 células)
            caravel = new Caravel(Compass.SOUTH, new Position(2, 3));
            cells = caravel.getPositions();
        }

        @Test
        @DisplayName("Extremos (top/bottom/left/right) condizem com as posições ocupadas")
        void extremes_matchOccupiedCells() {
            int top = cells.stream().mapToInt(IPosition::getRow).min().orElseThrow();
            int bottom = cells.stream().mapToInt(IPosition::getRow).max().orElseThrow();
            int left = cells.stream().mapToInt(IPosition::getColumn).min().orElseThrow();
            int right = cells.stream().mapToInt(IPosition::getColumn).max().orElseThrow();

            assertAll(
                    () -> assertEquals(top,    caravel.getTopMostPos()),
                    () -> assertEquals(bottom, caravel.getBottomMostPos()),
                    () -> assertEquals(left,   caravel.getLeftMostPos()),
                    () -> assertEquals(right,  caravel.getRightMostPos())
            );
        }
    }
}
