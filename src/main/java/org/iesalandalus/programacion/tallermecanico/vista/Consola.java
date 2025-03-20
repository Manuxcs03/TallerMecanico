package org.iesalandalus.programacion.tallermecanico.vista;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Consola {

    private static final String CADENA_FORMATO_FECHA = "dd/MM/yyyy";

    private Consola(){}

    public static void mostrarCabecera(String mensaje){
        System.out.println(mensaje);
        for (int i = 0; i < mensaje.length(); i++) {
            System.out.print("_");
        }
        System.out.println();
    }

    public static void mostrarMenu(){
        System.out.println("Listado de opciones disponibles");
        for (int i = 0; i < Opcion.values().length; i++) {
            System.out.print(Opcion.get(i+1));
        }
    }

    public static Opcion elegirOpcion(){
        int opcion = 0;
        do {
            opcion = leerEntero("Seleccione la opción");
            if (!Opcion.esValida(opcion)){
                System.out.println("Opción no válida");
            }
        }while (!Opcion.esValida(opcion));
        return Opcion.get(opcion);
    }

    private static int leerEntero(String mensaje) {
        System.out.print(mensaje+": ");
        return Entrada.entero();
    }

    private static float leerReal(String mensaje){
        System.out.print(mensaje+": ");
        return Entrada.real();
    }

    private static String leerCadena(String mensaje){
        System.out.print(mensaje+": ");
        return Entrada.cadena();
    }

    private static LocalDate leerFecha(String mensaje){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(CADENA_FORMATO_FECHA);
        do {
            System.out.print(mensaje+": ");
            try {
                return LocalDate.parse(Entrada.cadena(), dtf);
            } catch (Exception e){
                System.out.println("Formato de fecha incorrecto");
            }
        }while (true);
    }

    public static Cliente leerCliente(){
        do {
            String nombre = leerCadena("Introduzca el nombre del cliente");
            String dni = leerCadena("Introduzca el dni del cliente");
            String telefono = leerCadena("Introduzca el telefono del cliente");
            try {
                return new Cliente(nombre, dni, telefono);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }while (true);
    }

    public static Cliente leerClienteDni(){
        do {
            String dni = leerCadena("Introduzca el dni del cliente");
            try {
                return Cliente.get(dni);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }while (true);
    }

    public static String leerNuevoNombre(){
        do {
            String nombre = leerCadena("Introduzca el nuevo nombre del cliente");
            String dni = "12345678Z";
            String telefono = "666666666";
            try {
                return new Cliente(nombre, dni, telefono).getNombre();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }while (true);
    }

    public static String leerNuevoTelefono(){
        do {
            String nombre = "Desconocido";
            String dni = "12345678Z";
            String telefono = leerCadena("Introduzca el telefono del cliente");
            try {
                return new Cliente(nombre, dni, telefono).getNombre();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }while (true);
    }

    public static Vehiculo leerVehiculo(){
        do {
            String marca = leerCadena("Introduzca la marca del vehiculo");
            String modelo = leerCadena("Introduzca la modelo del vehiculo");
            String matricula = leerCadena("Introduzca la matricula del vehiculo");
            try {
                return new Vehiculo(marca, modelo, matricula);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }while (true);
    }

    public static Vehiculo leerVehiculoMatricula(){
        do {
            String matricula = leerCadena("Introduzca la matricula del vehiculo");
            try {
                return Vehiculo.get(matricula);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }while (true);
    }

    public static Revision leerRevision(){
        Cliente c = leerCliente();
        Vehiculo v = leerVehiculo();
        LocalDate f = leerFecha("Introduzca la fecha de la revisión");
        return new Revision(c, v, f);
    }

    public static int leerHoras(){
        return leerEntero("Introduzca el número de horas.");
    }

    public static float leerPrecioMaterial(){
        return leerReal("Introduzca el precio del material");
    }

    public static LocalDate leerFechaCierre(){
        return leerFecha("Introduzca la fecha de cierre de la revisión");
    }
}