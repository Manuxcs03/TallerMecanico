package org.iesalandalus.programacion.tallermecanico.vista;
import java.util.List;

import org.iesalandalus.programacion.tallermecanico.controlador.Controlador;
import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;


public class Vista {

    private Controlador controlador;

    public void setControlador(Controlador controlador){
        if (controlador != null){
            this.controlador = controlador;
        }
    }

    public void comenzar(){
        Opcion opcion = null;
        Consola.mostrarCabecera("GESTIÓN TALLER MECÁNICO");
        do {
            Consola.mostrarMenu();
            opcion = Consola.elegirOpcion();
            ejecutar(opcion);
        } while (opcion != Opcion.SALIR);
    }

    public void terminar(){
        System.out.println("Fin del programa");
    }

    private void ejecutar(Opcion opcion) {
        switch (opcion) {
            case INSERTAR_CLIENTE: insertarCliente(); break;
            case INSERTAR_VEHICULO: insertarVehiculo(); break;
            case INSERTAR_REVISION : insertarRevision(); break;
            case BUSCAR_CLIENTE : buscarCliente(); break;
            case BUSCAR_VEHICULO : buscarVehiculo(); break;
            case BUSCAR_REVISION : buscarRevision(); break;
            case MODIFICAR_CLIENTE : modificarCliente(); break;
            case ANADIR_HORAS_REVISION : anadirHoras(); break;
            case ANADIR_PRECIO_MATERIAL_REVISION : anadirPrecioMaterial(); break;
            case CERRAR_REVISION : cerrarRevision(); break;
            case BORRAR_CLIENTE : borrarCliente(); break;
            case BORRAR_VEHICULO : borrarVehiculo(); break;
            case BORRAR_REVISION : borrarRevision(); break;
            case LISTAR_CLIENTES : listarClientes(); break;
            case LISTAR_VEHICULOS : listarVehiculos(); break;
            case LISTAR_REVISIONES : listarRevisiones(); break;
            case LISTAR_REVISIONES_CLIENTE : listarRevisionesCliente(); break;
            case LISTAR_REVISIONES_VEHICULO : listarRevisionesVehiculo(); break;
            case SALIR : salir(); break;
            default:
                System.out.println("Opción no válida");
                break;
        }

    }

    private void insertarCliente(){
        try {
            System.out.println("Insertar cliente");
            controlador.insertar(Consola.leerCliente());
            System.out.println("Cliente insertado correctamente");
        }catch (TallerMecanicoExcepcion e){
            System.out.println(e.getMessage());
        }
    }

    private void insertarVehiculo() {
        try {
            System.out.println("INSERTAR VEHICULO");
            controlador.insertar(Consola.leerVehiculo());
            System.out.println("Vehiculo insertado correctamente");
        } catch (TallerMecanicoExcepcion e) {
            System.out.println(e.getMessage());
        }
    }

    private void insertarRevision() {
        try {
            System.out.println("INSERTAR REVISION");
            controlador.insertar(Consola.leerRevision());
            System.out.println("Revision insertada correctamente");
        } catch (TallerMecanicoExcepcion e) {
            System.out.println(e.getMessage());
        }
    }

    private void buscarCliente() {
        System.out.println("BUSCAR CLIENTE");
        Cliente c = controlador.buscar(Consola.leerClienteDni());
        if(c == null) {
            System.out.println("No se encontró el cliente buscado");
        }else {
            System.out.println(c);
        }
    }

    private void buscarVehiculo() {
        System.out.println("BUSCAR VEHICULO");
        Vehiculo v = controlador.buscar(Consola.leerVehiculoMatricula());
        if(v == null) {
            System.out.println("No se encontró el vehiculo buscado");
        }else {
            System.out.println(v);
        }
    }

    private void buscarRevision() {
        System.out.println("BUSCAR REVISION");
        Revision r = controlador.buscar(Consola.leerRevision());
        if(r == null) {
            System.out.println("No se encontró la revisión buscada");
        }else {
            System.out.println(r);
        }
    }

    private void modificarCliente() {
        System.out.println("MODIFICAR CLIENTE");
        try {
            Cliente clienteModificado = controlador.modificar(Consola.leerCliente(), Consola.leerNuevoNombre(), Consola.leerNuevoTelefono());
            if (clienteModificado != null) {
                System.out.println("Se ha modificado correctamente el cliente");
            } else {
                System.out.println("No ha sido posible modificar el cliente");
            }
        } catch (TallerMecanicoExcepcion e) {
            System.out.println(e.getMessage());
        }
    }

    private void anadirHoras() {
        System.out.println("AÑADIR HORAS");
        try {
            controlador.anadirHoras(Consola.leerRevision(), Consola.leerHoras());
            System.out.println("Horas añadidas correctamente");
        } catch (TallerMecanicoExcepcion e) {
            System.out.println(e.getMessage());
        }
    }

    private void anadirPrecioMaterial() {
        System.out.println("AÑADIR PRECIO MATERIAL");
        try {
            controlador.anadirPrecioMaterial(Consola.leerRevision(), Consola.leerPrecioMaterial());
            System.out.println("Precio material añadido correctamente");
        } catch (TallerMecanicoExcepcion e) {
            System.out.println(e.getMessage());
        }
    }

    private void cerrarRevision() {
        System.out.println("CERRAR REVISION");
        try {
            controlador.cerrar(Consola.leerRevision(), Consola.leerFechaCierre());
            System.out.println("Revision cerrada correctamente");
        } catch (TallerMecanicoExcepcion e) {
            System.out.println(e.getMessage());
        }
    }

    private void borrarCliente() {
        System.out.println("BORRAR CLIENTE");
        try {
            controlador.borrar(Consola.leerClienteDni());
            System.out.println("Cliente borrado correctamente");
        } catch (TallerMecanicoExcepcion e) {
            System.out.println(e.getMessage());
        }
    }

    private void borrarVehiculo() {
        System.out.println("BORRAR VEHICULO");
        try {
            controlador.borrar(Consola.leerVehiculoMatricula());
            System.out.println("Vehiculo borrado correctamente");
        } catch (TallerMecanicoExcepcion e) {
            System.out.println(e.getMessage());
        }
    }

    private void borrarRevision() {
        System.out.println("BORRAR REVISION");
        try {
            controlador.borrar(Consola.leerRevision());
            System.out.println("Revision borrada correctamente");
        } catch (TallerMecanicoExcepcion e) {
            System.out.println(e.getMessage());
        }
    }

    private void listarClientes() {
        List<Cliente> aux = controlador.getClientes();
        System.out.println("LISTADO DE CLIENTES");
        if(aux.isEmpty()) {
            System.out.println(" -- Listado vacio --");
        }else {
            for (int i = 0; i < aux.size(); i++) {
                System.out.println(" - "+(i+1)+".- "+aux.get(i));
            }
        }
    }

    private void listarVehiculos() {
        List<Vehiculo> aux = controlador.getVehiculos();
        System.out.println("LISTADO DE VEHICULOS");
        if(aux.isEmpty()) {
            System.out.println(" -- Listado vacio --");
        }else {
            for (int i = 0; i < aux.size(); i++) {
                System.out.println(" - "+(i+1)+".- "+aux.get(i));
            }
        }
    }

    private void listarRevisiones() {
        List<Revision> aux = controlador.getRevisiones();
        System.out.println("LISTADO DE REVISIONES");
        if(aux.isEmpty()) {
            System.out.println(" -- Listado vacio --");
        }else {
            for (int i = 0; i < aux.size(); i++) {
                System.out.println(" - "+(i+1)+".- "+aux.get(i));
            }
        }
    }

    private void listarRevisionesCliente() {
        List<Revision> aux = controlador.getRevisiones(Consola.leerClienteDni());
        System.out.println("LISTADO DE REVISIONES CLIENTE");
        if(aux.isEmpty()) {
            System.out.println(" -- Listado vacio --");
        }else {
            for (int i = 0; i < aux.size(); i++) {
                System.out.println(" - "+(i+1)+".- "+aux.get(i));
            }
        }
    }

    private void listarRevisionesVehiculo() {
        List<Revision> aux = controlador.getRevisiones(Consola.leerVehiculoMatricula());
        System.out.println("LISTADO DE REVISIONES VEHICULO");
        if(aux.isEmpty()) {
            System.out.println(" -- Listado vacio --");
        }else {
            for (int i = 0; i < aux.size(); i++) {
                System.out.println(" - "+(i+1)+".- "+aux.get(i));
            }
        }
    }

    private void salir() {
        controlador.terminar();
    }
}