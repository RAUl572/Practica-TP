import java.io.*;
import java.util.Scanner;

/**
 * La clase ListaEnvios sirve para tener organizados los envios que se hacen con la aplicación.
 * Estos envios se pueden ver por el porte o por el cliente que los haga.
 *
 * @author Raúl Fernández Iglesias.
 * @author Noel López Losada.
 * @version     1.0
 */
public class ListaEnvios {
    /**
     * Array que contiene los envios de la lista.
     */
    private Envio[] envios;
    /**
     * Número de envios que contiene la lista.
     */
    private int ocupacion;

    /**
     * EL constructor de la clase, la inicia dada una capacidad.
     * Además inicia la ocupación de la lista a 0 ya que está vacía.
     *
     * @param capacidad Número máximo de envios de la lista.
     */
    //TODO: Constructor de la clase para inicializar la lista a una capacidad determinada
    public ListaEnvios(int capacidad) {
		envios = new Envio[capacidad];
        ocupacion = 0;
    }

    /**
     * Getter del atributo ocupación.
     * @return Devuelve los envíos que hay en la lista.
     */
    // TODO: Devuelve el número de envíos que hay en la lista
    public int getOcupacion() {
        return ocupacion;
    }

    /**
     * Boolean que comprueba si la lista está llena.
     * @return Devuelve true si el último espacio de la lista contiene un envío y false si no lo hace.
     */
    // TODO: ¿Está llena la lista de envíos?
    public boolean estaLlena() {
        return envios[envios.length-1]!=null;
    }

    /**
     * Da un envío del que se conoce su índice en la lista.
     * @param i Índice del envio en la lista.
     * @return EL envio al que le corresponde el índice.
     */
	//TODO: Devuelve el envio dado un indice
    public Envio getEnvio(int i) {
        return envios[i];
    }

    /**
     * Método para introducir envíos a la lista.
     * @param envio El envío que sde va a introducir.
     * @return true en caso de que se añada correctamente, false en caso de lista llena o error
     */
    //TODO: insertamos un nuevo envío en la lista
    public boolean insertarEnvio(Envio envio) {
        boolean insertar = !estaLlena();
        if (insertar){
            envios[ocupacion] = envio;
            ocupacion++;
        }
        return insertar;
    }

    /**
     * Busca un envío dado su localizador.
     * @param localizador El localizador del envío quye se busca.
     * @return el envio que encontramos o null si no existe
     */
    //TODO: Buscamos el envio a partir del localizador pasado como parámetro
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
     * Busca un envío sabiendo el id de su porte y la fila y columna donde está.
     * @param idPorte Id del porte del envío que se busca.
     * @param fila Fila en la que se encuentra el envío.
     * @param columna Columna en la que se encuentra el envío.
     * @return el envio que encontramos o null si no existe
     */
    //TODO: Buscamos el envio a partir del idPorte, fila y columna pasados como parámetros
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

    /**
     * Dado el localizador busca un envío en la lista y da su posición.
     * @param localizador Localizador del envío que se busca.
     * @return Posición del envío en la lista o -1 si no se encuentra un envío con ese localizador en la lista.
     */
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
     * Borra un envío de la lista sabuendo su localizador.
     * @param localizador Localizador del envío que se va a eliminar.
     * @return True si se ha borrado correctamente, false en cualquier otro caso.
     */
    //TODO: Eliminamos un envio a través del localizador pasado por parámetro
    public boolean eliminarEnvio (String localizador) {
        boolean existe = buscarEnvio(localizador)!= null;
        if (existe) {
            int posicion = posicionEnvio(localizador);
            if (posicion!=-1) {
                for (int i = posicion; i < ocupacion - 1; i++) {
                    envios[i] = envios[i + 1];
                }
                envios[ocupacion]=null;
                ocupacion--;
            }
        }
        return existe;
    }

    /**
     * Muestra por pantalla los envíos de la lista.
     *
     */
    /*TODO: Muestra por pantalla los Envios de la lista, con el formato que aparece
       en el enunciado
     */
    public void listarEnvios() {
        if (ocupacion>0) {
            for (int i = 0; i < ocupacion; i++) {
                System.out.println(envios[i].toString());
            }
        }else System.out.println("No hay envios");
    }

    /**
     * Método que muestra todos los envios de la lista
     * @return devuelve true logra mostrar los envios y
     * false si no hay envios que mostrar
     */
    public boolean listarEnviosBoolean() {
        boolean resul=(ocupacion>0);
        if (resul) {
            for (int i = 0; i < ocupacion; i++) {
                System.out.println(envios[i].toString());
            }
        }else {System.out.println("No hay envios");}
        return resul;
    }

    /**
     * Busca un envío en base a un localizador que se pide al usuario, se repite el proceso hasta que se introduzca uno correcto.
     * @param teclado Scanner que lee los inputs del usuario.
     * @param mensaje Mensaje que se le mustra al usuario.
     * @return EL envío cuyo localizador coincide con el introducido.
     */
    /*
      TODO: Permite seleccionar un Envio existente a partir de su localizador, usando el mensaje pasado como argumento
       para la solicitud y siguiendo el orden y los textos mostrados en el enunciado.
       La función solicita repetidamente hasta que se introduzca un localizador correcto*/
    public Envio seleccionarEnvio(Scanner teclado, String mensaje) {
        Envio envio = null;
        String localizador;
        do {
            localizador = Utilidades.leerCadena(teclado,mensaje);
            envio = buscarEnvio(localizador);
            if (envio==null){
                System.out.println("     Localizador incorrecto");
            }
        }while (envio==null);
        return envio;
    }



    /**
     * Escribe los envíos de la lista en el fichero csv.
     * @param fichero Nombre del fichero csv en el que se van a añadir los envíos.
     * @return True si se completa correctamente la escritura, false en el resto de casos.
     */
    // TODO: Añade los Envios al final de un fichero CSV, SIN SOBREESCRIBIR la información
    public boolean aniadirEnviosCsv(String fichero) {
        PrintWriter pw = null;
        boolean correcto;
        try {
            pw = new PrintWriter(new FileWriter(fichero, true));
            for (int i=0;i<ocupacion;i++){
                pw.println(envios[i].toStringCSV());
            }
            correcto = true;
        } catch (FileNotFoundException e) {
            System.out.println("No se ha encontrado el fichero "+fichero);
            correcto = false;
        } catch (Exception ex){
            System.out.println("Error de lectura");
            correcto = false;
        } finally {
            try {
                if (pw!=null){
                    pw.close();
                }
            }catch (Exception e){
                System.out.println("Error de cierre de fichero");
            }

        }
        return correcto;
    }

    /**
     * Lee los envíos de un fichero csv y los añade a su porte y a su cliente dados las listas de ambos.
     * @param ficheroEnvios Fichero csv que se lee.
     * @param portes Lista de los portes.
     * @param clientes Lista de los clientes.
     */
    //TODO: Lee los Envios del fichero CSV y los añade a las listas de sus respectivos Portes y Clientes
    public static void leerEnviosCsv(String ficheroEnvios, ListaPortes portes, ListaClientes clientes) {
        Scanner sc = null;
        try {
            sc = new Scanner(new FileReader(ficheroEnvios));
            String [] linea;
            Cliente cliente;
            Porte porte;
            Envio envio;
            while (sc.hasNextLine()){
                linea = sc.nextLine().split(";");
                cliente = clientes.buscarClienteEmail(linea[2]);
                porte = portes.buscarPorte(linea[1]);
                envio = new Envio(linea[0],porte,cliente,Integer.parseInt(linea[3]),Integer.parseInt(linea[4]),Double.parseDouble(linea[5]));
                cliente.aniadirEnvio(envio);
                porte.ocuparHueco(envio);
            }
        } catch (FileNotFoundException e) {
            System.out.println("No se ha encontrado el fichero "+ficheroEnvios);

        } catch (Exception ex){
            System.out.println("Error de lectura");
        }finally {
            try {
                if (sc!=null){
                    sc.close();
                }
            }catch (Exception e){
                System.out.println("Error de cierre de fichero");
            }

        }
    }
}
