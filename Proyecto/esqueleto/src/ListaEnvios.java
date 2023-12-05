import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Description of the class
 *
 * @author
 * @author
 * @version     1.0
 */
public class ListaEnvios {
    private Envio[] envios;
    private int ocupacion;
    private final int CAPACIDAD;
    /**
     * TODO: Constructor de la clase para inicializar la lista a una capacidad determinada
     *
     * @param capacidad
     */
    public ListaEnvios(int capacidad) {
		envios = new Envio[capacidad];
        ocupacion = 0;
        CAPACIDAD = capacidad;
    }
    // TODO: Devuelve el número de envíos que hay en la lista
    public int getOcupacion() {
        return ocupacion;
    }
    // TODO: ¿Está llena la lista de envíos?
    public boolean estaLlena() {
        return envios[CAPACIDAD-1]!=null;
    }
	//TODO: Devuelve el envio dado un indice
    public Envio getEnvio(int i) {
        return envios[i];
    }

    /**
     * TODO: insertamos un nuevo envío en la lista
     * @param envio
     * @return true en caso de que se añada correctamente, false en caso de lista llena o error
     */
    public boolean insertarEnvio(Envio envio) {
        boolean insertar = !estaLlena();
        if (insertar){
            envios[ocupacion] = envio;
            ocupacion++;
        }
        return insertar;
    }

    /**
     * TODO: Buscamos el envio a partir del localizador pasado como parámetro
     * @param localizador
     * @return el envio que encontramos o null si no existe
     */
    public Envio buscarEnvio(String localizador) {
        int indice = 0;
        boolean encontrado = false;
        Envio envio = null;
        while (indice<ocupacion && !encontrado){
            if (localizador.equals(envios[indice].getLocalizador())){
                envio = envios[indice];
                encontrado = true;
            }
            indice++;
        }
        return envio;
    }

    /**
     * TODO: Buscamos el envio a partir del idPorte, fila y columna pasados como parámetros
     * @param idPorte
     * @param fila
     * @param columna
     * @return el envio que encontramos o null si no existe
     */
    public Envio buscarEnvio(String idPorte, int fila, int columna) {
        int indice = 0;
        boolean encontrado = false;
        Envio envio = null;
        while (indice<ocupacion && !encontrado){
            if (idPorte.equals(envios[indice].getPorte().getID()) && fila == envios[indice].getFila() && columna == envios[indice].getColumna()){
                envio = envios[indice];
                encontrado = true;
            }
            indice++;
        }
        return envio;
    }
    public int posicionEnvio(String localizador){
        int posicion=0, devolver= -1;
        boolean encontrado = false;
        while (posicion<ocupacion && !encontrado){
            if (localizador.equals(envios[posicion].getLocalizador())){
                devolver = posicion;
                encontrado = true;
            }
            posicion++;
        }
        return devolver;
    }
    /**
     * TODO: Eliminamos un envio a través del localizador pasado por parámetro
     * @param localizador
     * @return True si se ha borrado correctamente, false en cualquier otro caso
     */
    public boolean eliminarEnvio (String localizador) {
        boolean existe = buscarEnvio(localizador)!= null;
        if (existe) {
            int posicion = posicionEnvio(localizador);
            for (int i = posicion; i<ocupacion-1; i++){
                envios[i] = envios[i+1];
            }

        }
        return existe;
    }

    /**
     * TODO: Muestra por pantalla los Envios de la lista, con el formato que aparece
     * en el enunciado
     */
    public void listarEnvios() {
        if (ocupacion>0) {
            for (int i = 0; i < ocupacion; i++) {
                System.out.println(envios[i].toString());
            }
        }else System.out.println("No hay envios");
    }

    /**
     * TODO: Permite seleccionar un Envio existente a partir de su localizador, usando el mensaje pasado como argumento
     *  para la solicitud y siguiendo el orden y los textos mostrados en el enunciado.
     *  La función solicita repetidamente hasta que se introduzca un localizador correcto
     * @param teclado
     * @param mensaje
     * @return
     */
    public Envio seleccionarEnvio(Scanner teclado, String mensaje) {
        Envio envio = null;
        String localizador;
        do {
            System.out.println(mensaje);
            localizador = teclado.nextLine();
            envio = buscarEnvio(localizador);
            if (envio==null){
                System.out.println("El envío con localizador: "+ localizador +"no existe.");
            }
        }while (envio==null);
        return envio;
    }



    /**
     * TODO: Añade los Envios al final de un fichero CSV, SIN SOBREESCRIBIR la información
     * @param fichero
     * @return
     */
    public boolean aniadirEnviosCsv(String fichero) {
        PrintWriter pw = null;
        try {

            return true;
        } catch (Exception e) {
            return false;
        } finally {

        }
    }

    /**
     * TODO: Lee los Envios del fichero CSV y los añade a las listas de sus respectivos Portes y Clientes
     * @param ficheroEnvios
     * @param portes
     * @param clientes
     */
    public static void leerEnviosCsv(String ficheroEnvios, ListaPortes portes, ListaClientes clientes) {
        Scanner sc = null;
        try {

        } catch (FileNotFoundException e) {
            System.out.println("No se ha encontrado el fichero de envíos");
        } finally {

        }
    }
}
