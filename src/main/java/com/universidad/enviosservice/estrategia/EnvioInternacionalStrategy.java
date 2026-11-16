package com.universidad.enviosservice.estrategia;

public class EnvioInternacionalStrategy implements EstrategiaEnvio {

    private static final TarifaPorPeso TARIFA = new TarifaPorPeso(1.8, 50, 3.0, 4.5);
    private static final double RECARGO_INTERCONTINENTAL = 100;

    @Override
    public double calcular(double pesoKg, double distanciaKm) {
        double costo = TARIFA.costoBase(pesoKg, distanciaKm);
        return distanciaKm > 5000 ? costo + RECARGO_INTERCONTINENTAL : costo;
    }
}
