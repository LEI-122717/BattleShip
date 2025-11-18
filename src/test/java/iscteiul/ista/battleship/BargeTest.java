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

        // getPositions() -> List<IPosition>
        List<IPosition> cells = barge.getPositions();
        assertEquals(1, cells.size(), "Barge must occupy exactly one cell");
        assertEquals(anchor, cells.get(0), "Single occupied cell must be the anchor");
    }

    @ParameterizedTest(name = "bearing={0}")
    @EnumSource(Compass.class)
    @DisplayName("Bearing does not change occupied cells for size-1 ship")
    void bearingDoesNotAffectCells(Compass bearing) {
        Position anchor = new Position(0, 0);
        Barge barge = new Barge(bearing, anchor);

        List<IPosition> cells = barge.getPositions();
        assertAll(
                () -> assertEquals(1, barge.getSize()),
                () -> assertEquals(1, cells.size()),
                () -> assertEquals(anchor, cells.get(0))
        );
    }

    @Test
    @DisplayName("occupies(anchor) == true e não ocupa vizinhos")
    void occupies_anchor_only() {
        Position anchor = new Position(4, 5);
        Barge barge = new Barge(Compass.WEST, anchor);

        assertAll(
                () -> assertTrue(barge.occupies(anchor)),
                () -> assertFalse(barge.occupies(new Position(4, 4))),
                () -> assertFalse(barge.occupies(new Position(4, 6))),
                () -> assertFalse(barge.occupies(new Position(3, 5))),
                () -> assertFalse(barge.occupies(new Position(5, 5)))
        );
    }

    @Test
    @DisplayName("Extremos (top/bottom/left/right) coincidem com a âncora")
    void extremes_equal_to_anchor() {
        Position anchor = new Position(7, 2);
        Barge barge = new Barge(Compass.SOUTH, anchor);

        assertAll(
                () -> assertEquals(anchor.getRow(),    barge.getTopMostPos()),
                () -> assertEquals(anchor.getRow(),    barge.getBottomMostPos()),
                () -> assertEquals(anchor.getColumn(), barge.getLeftMostPos()),
                () -> assertEquals(anchor.getColumn(), barge.getRightMostPos())
        );
    }

}
