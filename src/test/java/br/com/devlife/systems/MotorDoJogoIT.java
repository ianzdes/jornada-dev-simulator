package br.com.devlife.systems;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class MotorDoJogoIT {

    @Test
    void devePagarSalarioNoDia30AposContratacao() {

        // arrange
        String entrada =
            "\n" +          // enter na tela de boas vindas
            "Ana\n" +       // nick do personagem
            "2\n";          // dev back-end
            // selecionar dps cursos, vaga e avançar varios dias

        // simula o teclado
        System.setIn(new ByteArrayInputStream(entrada.getBytes(StandardCharsets.UTF_8)));

        MotorDoJogo motor = new MotorDoJogo();

        // act 
        motor.iniciar();

        // assert
        // verificar contratacao e salário
    }

}