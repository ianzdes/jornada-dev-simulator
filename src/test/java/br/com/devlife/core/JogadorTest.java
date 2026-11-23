
package br.com.devlife.core;

import br.com.devlife.domain.enums.AreaAtuacao;
import br.com.devlife.domain.enums.NivelCargo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JogadorTest {

    @Test
    void energiaNaoDeveUltrapassar100() {

        Jogador jogador = new Jogador(
            "Teste",
            AreaAtuacao.BACKEND,
            NivelCargo.ESTAGIARIO
        );

        jogador.alterarRecursoVital("energia", 50);

        assertEquals(100, jogador.getEnergia());
    }


    @Test
    void energiaNaoDeveFicarNegativa() {

        Jogador jogador = new Jogador(
            "Teste",
            AreaAtuacao.BACKEND,
            NivelCargo.ESTAGIARIO
        );

        jogador.alterarRecursoVital("energia", -150);

        assertEquals(0, jogador.getEnergia());
    }


    @Test
    void naoDeveGastarDinheiroSemSaldoSuficiente() {

        Jogador jogador = new Jogador(
            "Teste",
            AreaAtuacao.BACKEND,
            NivelCargo.ESTAGIARIO
        );

        jogador.gastarDinheiro(600);

        assertEquals(500.0, jogador.getDinheiro(), 0.001);
    }



    @Test
    void naoDeveAdicionarDinheiroNegativo() {

        Jogador jogador = new Jogador(
            "Teste",
            AreaAtuacao.BACKEND,
            NivelCargo.ESTAGIARIO
        );

        jogador.adicionarDinheiro(-100);

        assertEquals(500.0, jogador.getDinheiro(), 0.001);
    }


}
