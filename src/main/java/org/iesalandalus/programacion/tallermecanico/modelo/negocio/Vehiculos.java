package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Vehiculos {

    private final List<Vehiculo> vehiculos;

    public Vehiculos() {
        vehiculos = new ArrayList<>();
    }

    public List<Vehiculo> get() {
        return new ArrayList<>(vehiculos);
    }

    public void insertar(Vehiculo vehiculo) {
        Objects.requireNonNull(vehiculo, "No se puede insertar un vehículo nulo.");

        if (buscar(vehiculo) != null) {
            throw new TallerMecanicoExcepcion("Ya existe un vehículo con esa matrícula.");
        }

        vehiculos.add(vehiculo);
    }

    public Vehiculo borrar(Vehiculo vehiculo) {
        Objects.requireNonNull(vehiculo, "No se puede borrar un vehículo nulo.");

        Vehiculo vehiculoEncontrado = buscar(vehiculo);
        if (vehiculoEncontrado == null) {
            throw new TallerMecanicoExcepcion("No existe ningún vehículo con esa matrícula.");
        }

        vehiculos.remove(vehiculoEncontrado);
        return vehiculoEncontrado;
    }

    public Vehiculo buscar(Vehiculo vehiculo) {
        Objects.requireNonNull(vehiculo, "No se puede buscar un vehículo nulo.");

        for (Vehiculo value : vehiculos) {
            if (value.matricula().equals(vehiculo.matricula())) {
                {
                    return value;
                }
            }
            return null;
        }
        return null;
    }
}
