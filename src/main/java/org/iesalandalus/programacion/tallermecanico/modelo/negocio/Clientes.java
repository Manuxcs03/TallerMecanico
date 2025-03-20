package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Clientes {
    private final List<Cliente> clientes;

    public Clientes() {
        clientes = new ArrayList<>();
    }

    public List<Cliente> get() {
        return new ArrayList<>(clientes);
    }

    public void insertar(Cliente cliente) {
        Objects.requireNonNull(cliente, "No se puede insertar un cliente nulo.");

        if (buscar(cliente) != null) {
            throw new TallerMecanicoExcepcion("Ya existe un cliente con ese DNI.");
        }

        clientes.add(cliente);
    }

    public Cliente borrar(Cliente cliente) {
        Objects.requireNonNull(cliente, "No se puede borrar un cliente nulo.");

        Cliente clienteEncontrado = buscar(cliente);
        if (clienteEncontrado == null) {
            throw new TallerMecanicoExcepcion("No existe ningún cliente con ese DNI.");
        }

        clientes.remove(clienteEncontrado);
        return clienteEncontrado;
    }

    public Cliente buscar(Cliente cliente) {
        Objects.requireNonNull(cliente, "No se puede buscar un cliente nulo.");

        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getDni().equals(cliente.getDni())) {
                return clientes.get(i);
            }
        }
        return null;
    }

    public Cliente modificar(Cliente cliente, String nombre, String telefono) {
        Objects.requireNonNull(cliente, "No se puede modificar un cliente nulo.");

        Cliente clienteEncontrado = buscar(cliente);
        if (clienteEncontrado == null) {
            throw new TallerMecanicoExcepcion("No existe ningún cliente con ese DNI.");
        }

        if (nombre != null) {
            clienteEncontrado.setNombre(nombre);
        }
        if (telefono != null) {
            clienteEncontrado.setTelefono(telefono);
        }
        return clienteEncontrado;
    }
}
