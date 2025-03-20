package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import java.util.Objects;

public class Cliente {

    private static final String ER_NOMBRE = "^[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+(\\s[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+)*$";
    private static final String ER_DNI = "^[0-9]{8}[A-Z]$";
    private static final String ER_TELEFONO = "^[0-9]{9}$";
    private String nombre;
    private String dni;
    private String telefono;

    public Cliente(String nombre, String dni, String telefono) {

        setNombre(nombre);
        setDni(dni);
        setTelefono(telefono);
    }

    public Cliente(Cliente cliente) {
        Objects.requireNonNull(cliente, "No es posible copiar un cliente nulo.");
        nombre = cliente.getNombre();
        dni = cliente.getDni();
        telefono = cliente.getTelefono();
    }

    public String getNombre() {
        return nombre;

    }

    public void setNombre(String nombre) {
        Objects.requireNonNull(nombre, "El nombre no puede ser nulo.");
        if (nombre.isEmpty() || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no tiene un formato válido.");
        }
        if (!nombre.matches(ER_NOMBRE)) {
            throw new IllegalArgumentException("El nombre no tiene un formato válido.");
        }
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        Objects.requireNonNull(dni, "El DNI no puede ser nulo.");
        String dniMayus = dni.toUpperCase();
        if (!dniMayus.matches(ER_DNI)) {
            throw new IllegalArgumentException("El DNI no tiene un formato válido.");
        }
        if (!comprobarLetraDni(dniMayus)) {
            throw new IllegalArgumentException("La letra del DNI no es correcta.");
        }
        this.dni = dniMayus;
    }

    private boolean comprobarLetraDni(String dni) {
        char[] LETRAS = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};
        int numeros = Integer.parseInt(dni.substring(0, 8));
        char letraCorrecta = LETRAS[numeros % 23];
        return dni.charAt(8) == letraCorrecta;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        Objects.requireNonNull(telefono, "El teléfono no puede ser nulo.");
        if (!telefono.matches(ER_TELEFONO)) {
            throw new IllegalArgumentException("El teléfono no tiene un formato válido.");
        }
        this.telefono = telefono;
    }

    public static Cliente get(String dni) {
        return new Cliente("Jose", dni, "640090405");
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(dni, cliente.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(dni);
    }

    @Override
    public String toString() {
        return String.format("%s - %s (%s)", nombre, dni, telefono);
    }
}
