package iscteiul.ista.battleship;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Barge (size = 1).
 */
@DisplayName("Barge (size 1)")
class BargeTest {

    @Test
    @DisplayName("getSize() returns 1")
    void getSize_returnsOne() {
        IPosition anchor = new Position(2, 3);
        Barge barge = new Barge(Compass.NORTH, anchor);

        assertEquals(1, barge.getSize());
    }

    @Test
    @DisplayName("Occupied positions contains exactly the anchor cell")
    void occupiedPositions_hasOnlyAnchor() {
        Position anchor = new Position(4, 5);
        Barge barge = new Barge(Compass.EAST, anchor);

        List<Position> cells = barge.getPositions();
        assertEquals(1, cells.size(), "Barge must occupy exactly one cell");
        assertEquals(anchor, cells.get(0), "Single occupied cell must be the anchor");
    }

    @ParameterizedTest(name = "bearing={0}")
    @EnumSource(Compass.class)
    @DisplayName("Bearing does not change occupied cells for size-1 ship")
    void bearingDoesNotAffectCells(Compass bearing) {
        Position anchor = new Position(0, 0);
        Barge barge = new Barge(bearing, anchor);

        List<Position> cells = barge.getPositions();
        assertAll(
                () -> assertEquals(1, barge.getSize()),
                () -> assertEquals(1, cells.size()),
                () -> assertEquals(anchor, cells.get(0))
        );
    }
}
