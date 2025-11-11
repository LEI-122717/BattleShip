package iscteiul.ista.battleship;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes da classe Position")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PositionTest {

    private Position posA;
    private Position posB;

    @BeforeEach
    void setUp() {
        posA = new Position(2, 3);
        posB = new Position(2, 4);
    }

    @AfterEach
    void tearDown() {
        posA = null;
        posB = null;
    }

    @Test
    @DisplayName("Obter linha da posição")
    void getRow() {
        assertEquals(2, posA.getRow(),
                "A linha deve corresponder ao valor passado no construtor.");
    }

    @Test
    @DisplayName("Obter coluna da posição")
    void getColumn() {
        assertEquals(3, posA.getColumn(),
                "A coluna deve corresponder ao valor passado no construtor.");
    }

    @Test
    @DisplayName("Verificar hashCode consistente para posições iguais")
    void testHashCode() {
        Position copy = new Position(2, 3);
        assertEquals(posA.hashCode(), copy.hashCode(),
                "Posições iguais devem ter o mesmo hashCode.");
    }

    @Test
    @DisplayName("Verificar igualdade entre posições")
    void testEquals() {
        Position same = new Position(2, 3);
        Position different = new Position(5, 5);

        assertEquals(posA, same, "Posições com mesmas coordenadas devem ser iguais.");
        assertNotEquals(posA, different, "Posições diferentes não devem ser iguais.");
        assertNotEquals(posA, null, "Uma posição não deve ser igual a null.");
        assertNotEquals(posA, "texto", "Uma posição não deve ser igual a um objeto de outro tipo.");
    }

    @Test
    @DisplayName("Verificar posições adjacentes — incluindo diagonais")
    void isAdjacentTo() {
        Position above = new Position(1, 3);
        Position below = new Position(3, 3);
        Position left = new Position(2, 2);
        Position right = new Position(2, 4);
        Position diagonal = new Position(3, 4);
        Position distant = new Position(4, 5);

        assertTrue(posA.isAdjacentTo(above), "A posição acima deve ser adjacente.");
        assertTrue(posA.isAdjacentTo(below), "A posição abaixo deve ser adjacente.");
        assertTrue(posA.isAdjacentTo(left), "A posição à esquerda deve ser adjacente.");
        assertTrue(posA.isAdjacentTo(right), "A posição à direita deve ser adjacente.");
        assertTrue(posA.isAdjacentTo(diagonal), "A posição diagonal também é adjacente segundo a implementação.");
        assertFalse(posA.isAdjacentTo(distant), "Uma posição distante não deve ser adjacente.");
    }

    @Test
    @DisplayName("Ocupar a posição")
    void occupy() {
        assertFalse(posA.isOccupied(), "A posição deve começar livre.");
        posA.occupy();
        assertTrue(posA.isOccupied(), "Após ocupar, a posição deve estar ocupada.");
    }

    @Test
    @DisplayName("Disparar sobre a posição")
    void shoot() {
        assertFalse(posA.isHit(), "A posição deve começar sem estar atingida.");
        posA.shoot();
        assertTrue(posA.isHit(), "A posição deve ser marcada como atingida após disparo.");

        Position occupied = new Position(1, 1);
        occupied.occupy();
        occupied.shoot();
        assertTrue(occupied.isHit(), "Uma posição ocupada também deve ficar marcada como atingida.");
    }

    @Test
    @DisplayName("Verificar se a posição está ocupada")
    void isOccupied() {
        assertFalse(posA.isOccupied(), "Inicialmente, a posição deve estar livre.");
        posA.occupy();
        assertTrue(posA.isOccupied(), "Depois de ocupar, deve indicar estado ocupado.");
    }

    @Test
    @DisplayName("Verificar se a posição foi atingida")
    void isHit() {
        assertFalse(posA.isHit(), "Inicialmente, a posição não deve estar atingida.");
        posA.shoot();
        assertTrue(posA.isHit(), "Após disparo, deve estar marcada como atingida.");
    }

    @Test
    @DisplayName("Verificar formato de toString()")
    void testToString() {
        String result = posA.toString();
        assertTrue(result.contains("Linha") && result.contains("Coluna"),
                "O método toString deve conter as palavras 'Linha' e 'Coluna'.");
        assertTrue(result.contains("2") && result.contains("3"),
                "O método toString deve incluir os valores da linha e coluna.");
    }
}
