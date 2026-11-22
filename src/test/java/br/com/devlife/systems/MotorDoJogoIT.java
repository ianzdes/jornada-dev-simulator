package br.com.devlife.systems;

import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;

import java.io.PrintStream;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

import java.io.ByteArrayOutputStream;

import static org.junit.jupiter.api.Assertions.*;

public class MotorDoJogoIT {

    @Test
    void devePagarSalarioNoDia30AposContratacao() {

        // arrange 
        String entrada =
            "\n" + // enter na tela de boas vindas
            "Ana\n" + // nick do personagem
            "2\n" + // dev back-end
            "2\n1\n" + // estudar fundamentos sistemas operacionais. passa dois dias
            "2\n1\n" + // estudar pacote office passa dois dias tb
            "3\n1\n" + // pega a vaga estadio em suporte no dia 5
            "7\n".repeat(25) + // finalizar o dia varias vezes ate chegar no dia 30
            "\n"; // recebeu o salario e da enter na tela 

        // simula o teclado + captura o que o jogo ta escrevendo no terminal
        ByteArrayOutputStream saidaCapturada = new ByteArrayOutputStream();
        System.setIn(new ByteArrayInputStream(entrada.getBytes(StandardCharsets.UTF_8)));
        System.setOut(new PrintStream(saidaCapturada, true, StandardCharsets.UTF_8));

        // o motor tem q ser criado depois do System.setIn
        MotorDoJogo motor = new MotorDoJogo();

        // act
        // o jogo n tem opcao de sair e para quando a entrada acaba
        assertThrows(NoSuchElementException.class, motor::iniciar);
        String saida = saidaCapturada.toString(StandardCharsets.UTF_8);
        saida = saida.replaceAll("\u001B\\[[0-9;]*m", "");

        // assert
        assertTrue(
            saida.contains("Parabéns! Você foi contratado como Estágio em Suporte na Consultores Consultorias!"),
            "o jogador deveria ter sido contratado"
        );

        assertTrue(
            saida.contains("VOCÊ RECEBEU SEU SALÁRIO DE R$ 1200.00!"),
            "o salario deveria cair no dia 30"
        );
        System.err.println("Saldo encontrado? " +
            saida.contains("Dinheiro: R$ 1520.00"));
        assertTrue(
            saida.contains("Dinheiro: R$ 1520.00"),
            "o saldo deveria ser 1520 (500 - 100 - 80 + salario de 1200)"
        );
    }

}