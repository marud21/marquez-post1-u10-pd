package com.universidad.enviosservice.estrategia;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TarifaPorPesoTest {

    private final TarifaPorPeso tarifa = new TarifaPorPeso(1.0, 10, 2.0, 3.0);

    @ParameterizedTest(name = "peso {0} kg -> {2}")
    @CsvSource({
        "5.0,  100.0, 110.0",   // limite del tramo liviano: solo km + cargo fijo
        "5.5,  100.0, 111.0",   // apenas entra al tramo medio
        "20.0, 100.0, 140.0",   // limite del tramo medio: 15 kg x 2.0
        "21.0, 100.0, 143.0"    // tramo pesado: 15 x 2.0 + 1 x 3.0
    })
    void costoBaseRespetaLosLimitesDeLosTramos(double pesoKg, double distanciaKm, double esperado) {
        assertEquals(esperado, tarifa.costoBase(pesoKg, distanciaKm), 0.001);
    }
}
