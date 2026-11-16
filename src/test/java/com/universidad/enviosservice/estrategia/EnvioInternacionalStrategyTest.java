package com.universidad.enviosservice.estrategia;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EnvioInternacionalStrategyTest {

    private final EnvioInternacionalStrategy estrategia = new EnvioInternacionalStrategy();

    @ParameterizedTest(name = "{3}")
    @CsvSource({
        "3.0,  100.0,   230.0,   peso <= 5 con tarifa fija de 50",
        "8.0,  100.0,   239.0,   tramo 5-20 kg",
        "25.0, 100.0,   297.5,   tramo > 20 kg",
        "3.0,  6000.0,  10950.0, recargo de 100 por distancia > 5000",
        "3.0,  5000.0,  9050.0,  distancia = 5000 no recarga"
    })
    void calculaCostoInternacional(double pesoKg, double distanciaKm, double esperado, String caso) {
        assertEquals(esperado, estrategia.calcular(pesoKg, distanciaKm), 0.01);
    }
}
