package iscteiul.ista.battleship;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class ShipTest {

    private Ship ship;

    @BeforeEach
    @DisplayName("Configuração Inicial dos Testes")
    void setUp() {
        // Inicializando uma implementação de teste da classe abstrata Ship
        ship = new Ship("fragata", Compass.NORTH, new Position(1, 1)) {
            @Override
            public Integer getSize() {
                return 3; // Exemplo: navio de tamanho 3
            }
        };

        // Adicionando posições de exemplo para a simulação do navio
        ship.getPositions().add(new Position(1, 1));
        ship.getPositions().add(new Position(1, 2));
        ship.getPositions().add(new Position(1, 3));
    }

    @Test
    @DisplayName("Teste buildShip - Instanciar diferentes tipos de embarcações")
    void buildShip() {
        Ship barge = Ship.buildShip("barca", Compass.NORTH, new Position(1, 1));
        assertNotNull(barge);
        assertEquals("Barca", barge.getCategory());

        Ship invalidShip = Ship.buildShip("invalid", Compass.EAST, new Position(2, 2));
        assertNull(invalidShip, "Tipos de navios inválidos devem retornar null");
    }

    @Test
    @DisplayName("Teste getCategory - Retorna a categoria do navio")
    void getCategory() {
        assertEquals("fragata", ship.getCategory());
    }

    @Test
    @DisplayName("Teste getPositions - Retorna todas as posições do navio")
    void getPositions() {
        List<IPosition> positions = ship.getPositions();
        assertEquals(3, positions.size()); // Deve haver 3 posições

        // Verifique todas as linhas e colunas para garantir consistência
        assertAll(
                () -> assertEquals(1, positions.get(0).getRow()),  // Primeira posição, linha 1
                () -> assertEquals(1, positions.get(0).getColumn()), // Primeira posição, coluna 1

                () -> assertEquals(1, positions.get(1).getRow()),  // Segunda posição, linha 1
                () -> assertEquals(2, positions.get(1).getColumn()), // Segunda posição, coluna 2

                () -> assertEquals(1, positions.get(2).getRow()),  // Terceira posição, linha 1
                () -> assertEquals(3, positions.get(2).getColumn()) // Terceira posição, coluna 3
        );
    }

    @Test
    @DisplayName("Teste getPosition - Retorna posição inicial")
    void getPosition() {
        IPosition posInicial = ship.getPosition();
        assertEquals(1, posInicial.getRow());
        assertEquals(1, posInicial.getColumn());
    }

    @Test
    @DisplayName("Teste getBearing - Retorna a direção atual do navio")
    void getBearing() {
        assertEquals(Compass.NORTH, ship.getBearing());
    }

    @Test
    @DisplayName("Teste stillFloating - Verifica se o navio ainda está flutuando")
    void stillFloating() {
        ship.getPositions().get(0).shoot(); // Atinge uma posição
        assertTrue(ship.stillFloating());

        // Atinge todas as posições
        ship.getPositions().forEach(IPosition::shoot);
        assertFalse(ship.stillFloating());
    }

    @Test
    @DisplayName("Teste getTopMostPos - Retorna a linha mais alta")
    void getTopMostPos() {
        assertEquals(1, ship.getTopMostPos());
    }

    @Test
    @DisplayName("Teste getBottomMostPos - Retorna a linha mais baixa")
    void getBottomMostPos() {
        assertEquals(1, ship.getBottomMostPos());
    }

    @Test
    @DisplayName("Teste getLeftMostPos - Retorna a coluna mais à esquerda")
    void getLeftMostPos() {
        assertEquals(1, ship.getLeftMostPos());
    }

    @Test
    @DisplayName("Teste getRightMostPos - Retorna a coluna mais à direita")
    void getRightMostPos() {
        assertEquals(3, ship.getRightMostPos());
    }

    @Test
    @DisplayName("Teste occupies - Verifica se ocupa uma posição")
    void occupies() {
        IPosition position = new Position(1, 2);
        assertTrue(ship.occupies(position));

        IPosition outsidePosition = new Position(2, 2);
        assertFalse(ship.occupies(outsidePosition));
    }

    @Test
    @DisplayName("Teste tooCloseTo - Verifica se está muito próximo de outro navio")
    void tooCloseTo() {
        // Um exemplo com um "navio fictício"
        Ship anotherShip = new Ship("nau", Compass.EAST, new Position(5, 5)) {
            @Override
            public Integer getSize() {
                return 1; // Exemplo de tamanho do outro navio
            }
        };

        anotherShip.getPositions().add(new Position(5, 5));
        assertFalse(ship.tooCloseTo(anotherShip));

        anotherShip.getPositions().add(new Position(1, 4)); // Adiciona posição perto
        assertTrue(ship.tooCloseTo(anotherShip));
    }

    @Test
    @DisplayName("Teste shoot - Marca uma posição como atingida")
    void shoot() {
        ship.shoot(new Position(1, 2));
        assertTrue(ship.getPositions().get(1).isHit());
    }

    @Test
    @DisplayName("Teste toString - Retorna representação como texto")
    void testToString() {
        assertEquals("[fragata n Linha = 1 Coluna = 1]", ship.toString());
    }
}