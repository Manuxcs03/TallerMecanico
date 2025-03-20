package org.iesalandalus.programacion.tallermecanico.modelo;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.Clientes;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.Revisiones;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.Vehiculos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Modelo {

    private Clientes clientes;
    private Revisiones revisiones;
    private Vehiculos vehiculos;

    public Modelo() {
    }

    public void comenzar() {
        clientes = new Clientes();
        revisiones = new Revisiones();
        vehiculos = new Vehiculos();
        System.out.println("Modelo inicializado.");
    }

    public void terminar() {
        System.out.println("El modelo ha terminado.");
    }

    public void insertar(Cliente cliente) {
        Objects.requireNonNull(cliente, "El cliente no puede ser nulo.");
        clientes.insertar(new Cliente(cliente));
    }

    public void insertar(Vehiculo vehiculo) {
        Objects.requireNonNull(vehiculo, "El vehículo no puede ser nulo.");
        vehiculos.insertar(vehiculo);
    }

    public void insertar(Revision revision) {
        Objects.requireNonNull(revision, "La revisión no puede ser nula.");
        
        Cliente cliente = buscar(revision.getCliente());
        
        Vehiculo vehiculo = buscar(revision.getVehiculo());
        
        revisiones.insertar(new Revision(cliente, vehiculo, revision.getFechaInicio()));
    }
    
    public Cliente buscar(Cliente cliente) {
        Objects.requireNonNull(cliente, "El cliente a buscar no puede ser nulo.");
        Cliente clienteEncontrado = clientes.buscar(cliente);
        return clienteEncontrado != null ? new Cliente(clienteEncontrado) : null;
    }
    
    public Vehiculo buscar(Vehiculo vehiculo) {
        Objects.requireNonNull(vehiculo, "El vehículo a buscar no puede ser nulo.");
        return vehiculos.buscar(vehiculo);
    }
    
    public Revision buscar(Revision revision) {
        Objects.requireNonNull(revision, "La revisión a buscar no puede ser nula.");
        Revision revisionEncontrada = revisiones.buscar(revision);
        return new Revision(revisionEncontrada);
    }

    public Cliente modificar(Cliente cliente, String nombre, String telefono) {
        Objects.requireNonNull(cliente, "El cliente no puede ser nulo.");
        Objects.requireNonNull(nombre, "El nombre no puede ser nulo.");
        Objects.requireNonNull(telefono, "El teléfono no puede ser nulo.");
        return clientes.modificar(cliente, nombre, telefono);
    }

    public Revision anadirHoras(Revision revision, int horas) {
        Objects.requireNonNull(revision, "La revision no puede ser nula.");

        if (horas < 0) {
            throw new IllegalArgumentException("La horas no puede ser negativo.");
        }

        return revisiones.anadirHoras(revision, horas);
    }

    public Revision anadirPrecioMaterial(Revision revision, float precioMaterial) {
        Objects.requireNonNull(revision, "La revision no puede ser nula.");
        return revisiones.anadirPrecioMaterial(revision, precioMaterial);
    }

    public Revision cerrar(Revision revision, LocalDate fechaFin) {
        Objects.requireNonNull(revision, "La revision no puede ser nula.");

        return revisiones.cerrar(revision, fechaFin);
    }

    public Cliente borrar(Cliente cliente) {
        Objects.requireNonNull(cliente, "El cliente no puede ser nulo.");
        
        List<Revision> revisionesCliente = revisiones.get(cliente);
        for (Revision revision : revisionesCliente) {
            revisiones.borrar(revision);
        }

        return clientes.borrar(cliente);
    }

    public Vehiculo borrar(Vehiculo vehiculo) {
        Objects.requireNonNull(vehiculo, "El vehiculo no puede ser nulo.");
        
        List<Revision> revisionesVehiculo = revisiones.get(vehiculo);
        for (Revision revision : revisionesVehiculo) {
            revisiones.borrar(revision);
        }

        return vehiculos.borrar(vehiculo);
    }

    public Revision borrar(Revision revision) {
        Objects.requireNonNull(revision, "La revision no puede ser nula.");
        return revisiones.borrar(revision);
    }

    public List<Cliente> getClientes() {
        List<Cliente> listaClientes = clientes.get();
        List<Cliente> copiaClientes = new ArrayList<>();
        
        for (Cliente cliente : listaClientes) {
            copiaClientes.add(new Cliente(cliente));
        }
        
        return copiaClientes;
    }
    
    public List<Vehiculo> getVehiculos() {
        return vehiculos.get();
    }
    
    public List<Revision> getRevisiones() {
        List<Revision> listaRevisiones = revisiones.get();
        List<Revision> copiaRevisiones = new ArrayList<>();
        
        for (Revision revision : listaRevisiones) {
            copiaRevisiones.add(new Revision(revision));
        }
        
        return copiaRevisiones;
    }
    
    public List<Revision> getRevisiones(Cliente cliente) {
        Objects.requireNonNull(cliente, "El cliente no puede ser nulo.");
        List<Revision> listaRevisiones = revisiones.get(cliente);
        List<Revision> copiaRevisiones = new ArrayList<>();
        
        for (Revision revision : listaRevisiones) {
            copiaRevisiones.add(new Revision(revision));
        }
        
        return copiaRevisiones;
    }
    
    public List<Revision> getRevisiones(Vehiculo vehiculo) {
        Objects.requireNonNull(vehiculo, "El vehículo no puede ser nulo.");
        List<Revision> listaRevisiones = revisiones.get(vehiculo);
        List<Revision> copiaRevisiones = new ArrayList<>();
        
        for (Revision revision : listaRevisiones) {
            copiaRevisiones.add(new Revision(revision));
        }
        
        return copiaRevisiones;
    }
}
