package com.universidad.enviosservice.estrategia;

/** Strategy: cada metodo de envio encapsula unicamente su formula de costo base. */
public interface EstrategiaEnvio {

    double calcular(double pesoKg, double distanciaKm);
}
