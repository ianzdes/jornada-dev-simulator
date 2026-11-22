
package br.com.devlife.systems;

import br.com.devlife.core.Jogador;
import br.com.devlife.domain.Vaga;
import br.com.devlife.domain.enums.AreaAtuacao;
import br.com.devlife.domain.enums.NivelCargo;

import org.junit.jupiter.api.Test;
import br.com.devlife.domain.Curso;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class GerenciadorDeAcoesIT {

    @Test
    void jogadorDeveAtenderRequisitosAposAprenderHabilidades() {

        // Arrange - Preparar
        Jogador jogador = new Jogador(
            "Teste",
            AreaAtuacao.BACKEND,
            NivelCargo.ESTAGIARIO_INICIO
        );

        GerenciadorDeAcoes gerenciador =
            new GerenciadorDeAcoes();

        List<Vaga> vagas =
            gerenciador.getVagasDisponiveis(jogador, 1);

        Vaga vaga = vagas.stream()
            .filter(v -> v.getTituloVaga()
                .equals("Estágio em Suporte"))
            .findFirst()
            .orElseThrow();

        // Assert - Antes de aprender
        assertFalse(
            gerenciador.jogadorTemRequisitosParaVaga(
                jogador, vaga
            )
        );

        // Act - Desenvolver habilidades
        jogador.aprenderOuMelhorarHabilidade("SO");
        jogador.aprenderOuMelhorarHabilidade("Pacote Office");

        // Assert - Depois de aprender
        assertTrue(
            gerenciador.jogadorTemRequisitosParaVaga(
                jogador, vaga
            )
        );
    }


    @Test
    void cursoComDescontoDeveFicarDisponivel() {

        // Arrange - Preparar
        Jogador jogador = new Jogador(
            "Teste",
            AreaAtuacao.BACKEND,
            NivelCargo.ESTAGIARIO_INICIO
        );

        GerenciadorDeAcoes gerenciador =
            new GerenciadorDeAcoes();

        // O jogador fica com R$ 200
        jogador.gastarDinheiro(300);

        // Ativa o desconto de 50%
        jogador.setTemDescontoCursos(true);

        // Act - Executar
        List<Curso> cursos =
            gerenciador.getCursosDisponiveis(jogador);

        boolean cursoDisponivel = cursos.stream()
            .anyMatch(curso -> curso.getNome()
                .equals("Técnicas de Negociação"));

        // Assert - Verificar
        assertTrue(
            cursoDisponivel,
            "O curso deveria estar disponível com desconto de 50%"
        );
    }

}
