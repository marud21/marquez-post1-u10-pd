package com.universidad.enviosservice.estrategia;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EnvioEstandarStrategyTest {

    private final EnvioEstandarStrategy estrategia = new EnvioEstandarStrategy();

    @ParameterizedTest(name = "{3}")
    @CsvSource({
        "5.0,  100.0,  50.0, limite superior del tramo 1 (peso = 5)",
        "8.0,  100.0,  52.4, tramo 5-20 kg (antes sin cubrir, linea 12)",
        "20.0, 100.0,  62.0, limite superior del tramo 2 (peso = 20)",
        "25.0, 100.0,  68.0, tramo > 20 kg (antes sin cubrir, linea 14)",
        "3.0,  500.0, 250.0, distancia = 500 no recarga",
        "3.0,  600.0, 310.0, recargo por distancia > 500 (antes sin cubrir, linea 17)"
    })
    void calculaCostoPorTramoYDistancia(double pesoKg, double distanciaKm, double esperado, String caso) {
        assertEquals(esperado, estrategia.calcular(pesoKg, distanciaKm), 0.01);
    }
}
