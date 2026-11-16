package com.universidad.enviosservice.estrategia;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EnvioExpressStrategyTest {

    private final EnvioExpressStrategy estrategia = new EnvioExpressStrategy();

    @ParameterizedTest(name = "{3}")
    @CsvSource({
        "3.0,  100.0, 156.0,  peso <= 5 con factor express 1.3",
        "8.0,  100.0, 161.85, tramo 5-20 kg",
        "25.0, 100.0, 198.25, tramo > 20 kg",
        "3.0,  600.0, 968.5,  recargo de 25 por distancia > 500, tambien multiplicado por 1.3"
    })
    void calculaCostoConFactorExpress(double pesoKg, double distanciaKm, double esperado, String caso) {
        assertEquals(esperado, estrategia.calcular(pesoKg, distanciaKm), 0.01);
    }
}
