package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Revisiones {
    private List<Revision> coleccionRevisiones;

    public Revisiones() {
        coleccionRevisiones = new ArrayList<>();
    }

    public List<Revision> get() {
        return new ArrayList<>(coleccionRevisiones);
    }

    public List<Revision> get(Cliente cliente) {
        Objects.requireNonNull(cliente, "El cliente no puede ser nulo.");
        List<Revision> revisionesCliente = new ArrayList<>();
        for (Revision revision : coleccionRevisiones) {
            if (revision.getCliente().equals(cliente)) {
                revisionesCliente.add(revision);
            }
        }
        return revisionesCliente;
    }

    public List<Revision> get(Vehiculo vehiculo) {
        Objects.requireNonNull(vehiculo, "El vehículo no puede ser nulo.");
        List<Revision> revisionesVehiculo = new ArrayList<>();
        for (Revision revision : coleccionRevisiones) {
            if (revision.getVehiculo().equals(vehiculo)) {
                revisionesVehiculo.add(revision);
            }
        }
        return revisionesVehiculo;
    }

    public void insertar(Revision revision) {
        Objects.requireNonNull(revision, "No se puede insertar una revisión nula.");
        comprobarRevision(revision.getCliente(), revision.getVehiculo(), revision.getFechaInicio());
        coleccionRevisiones.add(revision);
    }

    private void comprobarRevision(Cliente cliente, Vehiculo vehiculo, LocalDate fechaRevision) {
        Objects.requireNonNull(cliente, "El cliente no puede ser nulo.");
        Objects.requireNonNull(vehiculo, "El vehículo no puede ser nulo.");
        Objects.requireNonNull(fechaRevision, "La fecha de revisión no puede ser nula.");
        
        for (Revision revision : coleccionRevisiones) {
            if (!revision.estaCerrada() && revision.getCliente().equals(cliente)) {
                throw new TallerMecanicoExcepcion("El cliente tiene otra revisión en curso.");
            }
        }
        
        for (Revision revision : coleccionRevisiones) {
            if (!revision.estaCerrada() && revision.getVehiculo().equals(vehiculo)) {
                throw new TallerMecanicoExcepcion("El vehículo está actualmente en revisión.");
            }
        }

        for (Revision revision : coleccionRevisiones) {
            if (revision.estaCerrada() && revision.getVehiculo().equals(vehiculo) &&
                    (revision.getFechaFin().isAfter(fechaRevision) || revision.getFechaFin().isEqual(fechaRevision))) {
                throw new TallerMecanicoExcepcion("El vehículo tiene una revisión posterior.");
            }
        }

        for (Revision revision : coleccionRevisiones) {
            if (revision.estaCerrada() && revision.getCliente().equals(cliente) &&
                    (revision.getFechaFin().isAfter(fechaRevision) || revision.getFechaFin().isEqual(fechaRevision))) {
                throw new TallerMecanicoExcepcion("El cliente tiene una revisión posterior.");
            }
        }
    }

    public Revision anadirHoras(Revision revision, int horas) {
        Objects.requireNonNull(revision, "No puedo operar sobre una revisión nula.");
        Revision revisionExistente = buscar(revision);
        if (revisionExistente == null) {
            throw new TallerMecanicoExcepcion("No existe ninguna revisión igual.");
        }
        revisionExistente.anadirHoras(horas);
        return revisionExistente;
    }

    public Revision anadirPrecioMaterial(Revision revision, float precioMaterial) {
        Objects.requireNonNull(revision, "No puedo operar sobre una revisión nula.");
        Revision revisionExistente = buscar(revision);
        if (revisionExistente == null) {
            throw new TallerMecanicoExcepcion("No existe ninguna revisión igual.");
        }
        revisionExistente.anadirPrecioMaterial(precioMaterial);
        return revisionExistente;
    }

    public Revision cerrar(Revision revision, LocalDate fechaFin) {
        Objects.requireNonNull(revision, "No puedo operar sobre una revisión nula.");
        Revision revisionExistente = buscar(revision);
        if (revisionExistente == null) {
            throw new TallerMecanicoExcepcion("No existe ninguna revisión igual.");
        }
        revisionExistente.cerrar(fechaFin);
        return revisionExistente;
    }

    public Revision buscar(Revision revision) {
        Objects.requireNonNull(revision, "No se puede buscar una revisión nula.");
        Revision revisionEncontrada = null;
        for (Revision revisionExistente : coleccionRevisiones) {
            if (revisionExistente.equals(revision)) {
                revisionEncontrada = revisionExistente;
            }
        }
        return revisionEncontrada;
    }

    public Revision borrar(Revision revision) {
        Objects.requireNonNull(revision, "No se puede borrar una revisión nula.");
        Revision revisionExistente = buscar(revision);
        if (revisionExistente == null) {
            throw new TallerMecanicoExcepcion("No existe ninguna revisión igual.");
        }
        coleccionRevisiones.remove(revisionExistente);
        return revisionExistente;
    }
}
