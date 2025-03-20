package org.iesalandalus.programacion.tallermecanico.vista;

import java.util.HashMap;
import java.util.Map;

public enum Opcion {

    INSERTAR_CLIENTE(1,"Inserta el cliente"),
    BUSCAR_CLIENTE(2,"Busca el cliente"),
    BORRAR_CLIENTE(3,"Borra el cliente"),
    LISTAR_CLIENTES(4,"Lista los clientes"),
    MODIFICAR_CLIENTE(5,"Modifica el cliente"),
    INSERTAR_VEHICULO(6,"Inserta el vehiculo"),
    BUSCAR_VEHICULO(7,"Busca el vehiculo"),
    BORRAR_VEHICULO(8, "Borra el vehiculo"),
    LISTAR_VEHICULOS(9,"Lista los vehiculos"),
    INSERTAR_REVISION(10, "Inserta una revision"),
    BUSCAR_REVISION(11,"Busca una revision"),
    BORRAR_REVISION(12,"Borra una revision"),
    LISTAR_REVISIONES(13,"Lista las revisiones"),
    LISTAR_REVISIONES_CLIENTE(14,"Listar las revisiones de un cliente"),
    LISTAR_REVISIONES_VEHICULO(15,"Listar las revisiones de un vehiculo"),
    ANADIR_HORAS_REVISION(16,"Añade horas a una revision"),
    ANADIR_PRECIO_MATERIAL_REVISION(17,"Añade precio de material a una revision"),
    CERRAR_REVISION(18, "Cierra una revision"),
    SALIR(19,"Salir del programa");

    private int numeroOpcion;
    private String mensaje;
    private static Map<Integer, Opcion> opciones;

    static {
        opciones = new HashMap<>();
        for (Opcion opcion : Opcion.values()) {
            opciones.put(opcion.numeroOpcion, opcion);
        }
    }

    private Opcion(int numeroOpcion, String mensaje) {
        this.numeroOpcion = numeroOpcion;
        this.mensaje = mensaje;
    }

    public static boolean esValida(int numeroOpcion) {
        return opciones.containsKey(numeroOpcion);
    }

    public static Opcion get(int numeroOpcion) {
        if (esValida(numeroOpcion)) {
            return opciones.get(numeroOpcion);
        } else {
            throw new IllegalArgumentException("No existe ninguna opción con ese número.");
        }
    }

    @Override
    public String toString() {
        return numeroOpcion + ".- " + mensaje;
    }
}
