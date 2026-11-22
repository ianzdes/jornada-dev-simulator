
package br.com.devlife.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class EventoTest {

    @Test
    void naoDevePermitirEventoComCustoNegativo() {

        // Arrange - Preparar
        double custoInvalido = -100.0;

        // Act e Assert - Executar e verificar
        assertThrows(IllegalArgumentException.class, () -> {
            new Evento(
                "Evento de Tecnologia",
                "Palestras sobre programação",
                "22/11/2026",
                "Recife",
                "Estudantes",
                100,
                20,
                custoInvalido,
                30,
                1,
                false
            );
        });
    }
}
