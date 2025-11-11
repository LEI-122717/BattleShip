package iscteiul.ista.battleship;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Caravel (size = 2).
 * Notes:
 *  - Implementation builds two contiguous cells from the anchor depending on bearing:
 *      NORTH/SOUTH -> vertical (row, row+1)
 *      EAST/WEST   -> horizontal (col, col+1)
 */
@DisplayName("Caravel (size 2)")
class CaravelTest {

    @Test
    @DisplayName("getSize() returns 2")
    void getSize_returnsTwo() {
        Caravel c = new Caravel(Compass.NORTH, new Position(1, 1));
        assertEquals(2, c.getSize());
    }

    @Test
    @DisplayName("Null bearing throws NullPointerException")
    void nullBearing_throwsNPE() {
        assertThrows(NullPointerException.class,
                () -> new Caravel(null, new Position(0, 0)));
    }

    @ParameterizedTest(name = "bearing={0} -> positions are contiguous and size=2")
    @EnumSource(value = Compass.class, names = {"NORTH", "SOUTH", "EAST", "WEST"})
    @DisplayName("Occupied positions are contiguous and match bearing")
    void occupiedPositions_areContiguous(Compass bearing) {
        Position anchor = new Position(2, 3);
        Caravel caravel = new Caravel(bearing, anchor);

        List<IPosition> cells = caravel.getPositions();
        assertEquals(2, cells.size(), "Caravel must occupy exactly two cells");

        // unique cells
        Set<IPosition> set = new HashSet<>(cells);  
        assertEquals(2, set.size(), "Occupied cells must be unique");

        // expected contiguous cells according to current implementation
        List<Position> expected = switch (bearing) {
            case NORTH, SOUTH -> List.of(
                    new Position(anchor.getRow(), anchor.getColumn()),
                    new Position(anchor.getRow() + 1, anchor.getColumn())
            );
            case EAST, WEST -> List.of(
                    new Position(anchor.getRow(), anchor.getColumn()),
                    new Position(anchor.getRow(), anchor.getColumn() + 1)
            );
            default -> throw new IllegalArgumentException("Unexpected bearing value: " + bearing);
        };

        assertIterableEquals(expected, cells,
                "Cells must be contiguous from the anchor following bearing rules");
    }

}
