
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

        
        assertFalse(
            gerenciador.jogadorTemRequisitosParaVaga(
                jogador, vaga
            )
        );


        jogador.aprenderOuMelhorarHabilidade("SO");
        jogador.aprenderOuMelhorarHabilidade("Pacote Office");
        assertTrue(
            gerenciador.jogadorTemRequisitosParaVaga(
                jogador, vaga
            )
        );
    }


    @Test
    void cursoComDescontoDeveFicarDisponivel() {

    
        Jogador jogador = new Jogador(
            "Teste",
            AreaAtuacao.BACKEND,
            NivelCargo.ESTAGIARIO_INICIO
        );

        GerenciadorDeAcoes gerenciador =
            new GerenciadorDeAcoes();

        
        jogador.gastarDinheiro(300);

    
        jogador.setTemDescontoCursos(true);

        List<Curso> cursos =
            gerenciador.getCursosDisponiveis(jogador);

        boolean cursoDisponivel = cursos.stream()
            .anyMatch(curso -> curso.getNome()
                .equals("Técnicas de Negociação"));

    
        assertTrue(
            cursoDisponivel,
            "O curso deveria estar disponível com desconto de 50%"
        );
    }

}
