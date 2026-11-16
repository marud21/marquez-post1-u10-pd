package com.universidad.enviosservice.estrategia;

public class EnvioOvernightStrategy implements EstrategiaEnvio {

    private static final double DISTANCIA_MAXIMA_KM = 300;

    @Override
    public double calcular(double pesoKg, double distanciaKm) {
        if (distanciaKm > DISTANCIA_MAXIMA_KM) {
            throw new IllegalArgumentException("OVERNIGHT no disponible para mas de 300 km");
        }
        if (pesoKg <= 5) {
            return distanciaKm * 2.0 + 20;
        } else if (pesoKg <= 20) {
            return distanciaKm * 2.0 + 20 + (pesoKg - 5) * 2.5;
        }
        return distanciaKm * 2.0 + 20 + 15 * 2.5 + (pesoKg - 20) * 3.0;
    }
}
