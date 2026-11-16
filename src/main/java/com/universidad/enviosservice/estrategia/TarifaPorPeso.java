package com.universidad.enviosservice.estrategia;

/**
 * Value Object con la estructura comun de tramos de peso (hasta 5 kg, de 5 a 20 kg y mas de 20 kg)
 * que las cuatro estrategias repetian. Extraerlo elimino la duplicacion que hacia fallar
 * la condicion "Duplicated Lines (%) &gt; 5" del Quality Gate, sin cambiar ninguna formula.
 */
public record TarifaPorPeso(double tarifaKm, double cargoFijo, double tarifaKgMedio, double tarifaKgPesado) {

    private static final double LIMITE_LIVIANO_KG = 5;
    private static final double LIMITE_MEDIO_KG = 20;

    public double costoBase(double pesoKg, double distanciaKm) {
        double costo = distanciaKm * tarifaKm + cargoFijo;
        if (pesoKg > LIMITE_MEDIO_KG) {
            return costo + (LIMITE_MEDIO_KG - LIMITE_LIVIANO_KG) * tarifaKgMedio
                    + (pesoKg - LIMITE_MEDIO_KG) * tarifaKgPesado;
        }
        if (pesoKg > LIMITE_LIVIANO_KG) {
            return costo + (pesoKg - LIMITE_LIVIANO_KG) * tarifaKgMedio;
        }
        return costo;
    }
}
