
package br.com.devlife.core;

import br.com.devlife.domain.enums.AreaAtuacao;
import br.com.devlife.domain.enums.NivelCargo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JogadorTest {

    @Test
    void energiaNaoDeveUltrapassar100() {

        // Arrange - Preparar
        Jogador jogador = new Jogador(
            "Teste",
            AreaAtuacao.BACKEND,
            NivelCargo.ESTAGIARIO
        );

        // Act - Executar
        jogador.alterarRecursoVital("energia", 50);

        // Assert - Verificar
        assertEquals(100, jogador.getEnergia());
    }


    @Test
    void energiaNaoDeveFicarNegativa() {

        // Arrange - Preparar
        Jogador jogador = new Jogador(
            "Teste",
            AreaAtuacao.BACKEND,
            NivelCargo.ESTAGIARIO
        );

        // Act - Executar
        jogador.alterarRecursoVital("energia", -150);

        // Assert - Verificar
        assertEquals(0, jogador.getEnergia());
    }


    @Test
    void naoDeveGastarDinheiroSemSaldoSuficiente() {

        // Arrange - Preparar
        Jogador jogador = new Jogador(
            "Teste",
            AreaAtuacao.BACKEND,
            NivelCargo.ESTAGIARIO
        );

        // Act - Executar
        jogador.gastarDinheiro(600);

        // Assert - Verificar
        assertEquals(500.0, jogador.getDinheiro(), 0.001);
    }


}
