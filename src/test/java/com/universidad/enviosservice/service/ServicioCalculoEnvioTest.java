package com.universidad.enviosservice.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ServicioCalculoEnvioTest {

    private final ServicioCalculoEnvio servicio = new ServicioCalculoEnvio();

    @Test
    void calculaEnvioEstandarPesoBajo() {
        double costo = servicio.calcularCosto("ESTANDAR", 3.0, 100.0, "REGULAR", false);
        assertEquals(50.0, costo, 0.01);
    }

    @Test
    void aplicaDescuentoClientePremium() {
        double costo = servicio.calcularCosto("ESTANDAR", 3.0, 100.0, "PREMIUM", false);
        assertEquals(45.0, costo, 0.01);
    }

    @Test
    void aplicaDescuentoClienteCorporativo() {
        assertEquals(42.5, servicio.calcularCosto("ESTANDAR", 3.0, 100.0, "CORPORATIVO", false), 0.01);
    }

    @Test
    void aplicaRecargoPorFragilidad() {
        assertEquals(57.5, servicio.calcularCosto("ESTANDAR", 3.0, 100.0, "REGULAR", true), 0.01);
    }

    @Test
    void fragilidadSeAplicaAntesDelDescuentoDelCliente() {
        // 50 * 1.15 = 57.5 ; 57.5 * 0.9 = 51.75 (el orden de las reglas transversales importa)
        assertEquals(51.75, servicio.calcularCosto("ESTANDAR", 3.0, 100.0, "PREMIUM", true), 0.01);
    }

    @ParameterizedTest(name = "{0} delega en su estrategia")
    @CsvSource({
        "EXPRESS,       156.0",
        "OVERNIGHT,     220.0",
        "INTERNACIONAL, 230.0"
    })
    void delegaEnLaEstrategiaDelMetodo(String metodo, double esperado) {
        assertEquals(esperado, servicio.calcularCosto(metodo, 3.0, 100.0, "REGULAR", false), 0.01);
    }

    @Test
    void rechazaMetodoDeEnvioNoSoportado() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> servicio.calcularCosto("DRON", 3.0, 100.0, "REGULAR", false));
        assertEquals("Metodo de envio no soportado: DRON", ex.getMessage());
    }

    @Test
    void overnightFueraDeRangoPropagaLaExcepcionDeSuEstrategia() {
        assertThrows(IllegalArgumentException.class,
                () -> servicio.calcularCosto("OVERNIGHT", 3.0, 450.0, "REGULAR", false));
    }
}
