package com.universidad.enviosservice.estrategia;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EnvioOvernightStrategyTest {

    private final EnvioOvernightStrategy estrategia = new EnvioOvernightStrategy();

    @ParameterizedTest(name = "peso {0} kg, distancia {1} km -> {2}")
    @CsvSource({
        "3.0,  100.0, 220.0",   // pesoKg <= 5
        "8.0,  100.0, 227.5",   // 5 < pesoKg <= 20
        "25.0, 100.0, 272.5",   // pesoKg > 20: 200 + 20 + 15*2.5 + 5*3.0
        "3.0,  300.0, 620.0"    // distancia = 300 aun permitida
    })
    void calculaCostoPorTramoDePeso(double pesoKg, double distanciaKm, double esperado) {
        assertEquals(esperado, estrategia.calcular(pesoKg, distanciaKm), 0.01);
    }

    @Test
    void rechazaDistanciasMayoresA300Km() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> estrategia.calcular(3.0, 301.0));
        assertEquals("OVERNIGHT no disponible para mas de 300 km", ex.getMessage());
    }
}
