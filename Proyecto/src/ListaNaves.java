import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * La clase ListaNaves almacena un número determinado de naves. Se puede usar para ver las naves que hay en los muelles de los puertos
 * o a la hora de hacer pedidos ver las naves disponibles.
 *
 * @author Raúl Fernández Iglesias.
 * @author Noel López Losada.
 * @version     1.0
 */
public class ListaNaves {
    /**
     * Array que contiene las naves de la lista.
     */
    private Nave[] naves;
    /**
     * Número de naves que tiene la lista.
     */
    private int ocupacion;
    /**
     * Constructor de la clase, inicia la lista para una capacidad e inicia la ocupacion a 0.
     *
     * @param capacidad La capacidad de la lista que se va a iniciar.
     */
    //TODO: Constructor de la clase para inicializar la lista a una capacidad determinada
    public ListaNaves(int capacidad) {
        naves = new Nave[capacidad];
        ocupacion = 0;
    }

    /**
     * @return Devuelve el número de naves de la lista.
     */
    // TODO: Devuelve el número de naves que hay en la lista
    public int getOcupacion() {
        return ocupacion;
    }

    /**
     * Boolean que comprueba si la lista está llena
     * @return Devuelve la última posición de la lista, true si contiene una nave, false si no contiene una nave.
     */
    // TODO: ¿Está llena la lista de naves?
    public boolean estaLlena() {
        return  naves[naves.length-1]!=null;
    }

    /**
     * Da una nave dado un indice.
     * @param posicion Indice de la nave que se quiere recibir.
     * @return Nave que en la lista está en la posición deseada.
     */
    // TODO: Devuelve nave dado un indice
    public Nave getNave(int posicion) {
        return naves[posicion];
    }

    /**
     * Método que inserta una nave al final de la lista.
     * @param nave Nave que se va a introducir en la lista.
     * @return True en caso de que se añada correctamente, false en caso de lista llena o error.
     */
    //TODO: insertamos una nueva nave en la lista
    public boolean insertarNave(Nave nave) {
        boolean espacios = !estaLlena();
        if (espacios){
            naves[ocupacion] = nave;
            ocupacion++;
        }
        return espacios;
    }
    /**
     * Método que busca una nave a partir de su matrícula.
     * @param matricula Matricula que se va a buscar en las naves de la lista.
     * @return La nave que encontramos o null si no existe.
     */
    //TODO: Buscamos la nave a partir de la matricula pasada como parámetro
    public Nave buscarNave(String matricula) {
        int indice=0;
        boolean encontrada = false;
        Nave buscada = null;
        while (indice<ocupacion && !encontrada){
            if (naves[indice].getMatricula().equals(matricula)){
                encontrada = true;
                buscada = naves[indice];
            }
            indice ++;
        }
        return buscada;
    }

    /**
     * Muestra las naves que ha en la lista si no está vacía.
     */
    // TODO: Muestra por pantalla las naves de la lista con el formato indicado en el enunciado
    public void mostrarNaves() {
        if (ocupacion>0) {
            for (int i = 0; i < ocupacion; i++) {
                System.out.println(naves[i].toString());
            }
        }
    }
    /**
     * Método que permite seleccionar una nave con su matrícula siempre y cuando tenga un cierto alcance.
     * @param teclado Scanner que lee lo que introduce el usuario.
     * @param mensaje Instrucciones de lo que se quiere que se introduzca.
     * @param alcance Alcance que se necesita que tenga la nave.
     * @return La nave con la matricula introducida y que tiene un alcance igual o superior al necesario.
     */
    //TODO: Permite seleccionar una nave existente a partir de su matrícula, y comprueba si dispone de un alcance
    // mayor o igual que el pasado como argumento, usando el mensaje pasado como argumento para la solicitud y
    // siguiendo el orden y los textos mostrados en el enunciado.
    // La función solicita repetidamente la matrícula de la nave hasta que se introduzca una con alcance suficiente
    public Nave seleccionarNave(Scanner teclado, String mensaje, double alcance) {
        Nave nave = null;
        do {
            String matricula = Utilidades.leerCadena(teclado,mensaje);
            nave = buscarNave(matricula);
            if (nave != null && nave.getAlcance()<alcance){
                System.out.println("La nave no tiene suficiente alcance");
            }
        }while (nave == null || nave.getAlcance()<alcance);
        return nave;
    }


    /**
     * Método que escribe las naves de la lista siguiendo el formato CSV en un fichero.
     * @param nombre Nombre del fichero en el que se va a generar el fichero CSV de la lista.
     * @return True si el proceso se ejecuta correctamente, false si hay algún error.
     */
    //TODO: Genera un fichero CSV con la lista de Naves, SOBREESCRIBIÉNDOLO
    public boolean escribirNavesCsv(String nombre) {
        PrintWriter pw = null;
        boolean correcto;
        try {
            pw = new PrintWriter(nombre);
            for (int i=0;i<ocupacion;i++){
                pw.println(naves[i].toStringCsv());
            }
            correcto = true;
        } catch (FileNotFoundException e) {
            System.out.println("No se ha encontrado el fichero "+nombre);
            correcto = false;
        } catch (Exception e) {
            System.out.println("Error de escritura en el fichero.");
            correcto = false;
        }finally {
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
     * Método que lee un fichero CSV y crea una lista de naves a partir de su contenido.
     * @param fichero Nombre del fichero CSV del que se van a leer las naves.
     * @param capacidad Número de naves en el fichero.
     * @return True si el proceso se ejecuta correctamente, false si hay algún error.
     */
    //TODO: Genera una lista de naves a partir del fichero CSV, usando el argumento como capacidad máxima de la lista
    public static ListaNaves leerNavesCsv(String fichero, int capacidad) {
        ListaNaves listaNaves = null;
        Scanner sc = null;
        try {
            sc = new Scanner(new FileReader(fichero));
            listaNaves = new ListaNaves(capacidad);
            String [] nave;
            Nave nueva;
            while (sc.hasNextLine()){
                nave = sc.nextLine().split(";");
                nueva = new Nave(nave[0],nave[1],nave[2],Integer.parseInt(nave[4]), Integer.parseInt(nave[3]),Double.parseDouble(nave[5]));
                listaNaves.insertarNave(nueva);
            }

        } catch (FileNotFoundException e) {
            System.out.println("No se ha encontrado el fichero "+fichero);
        }catch (Exception e){
            System.out.println("Error de lectura");
            listaNaves = null;
        } finally {
            try {
                if (sc!=null){
                    sc.close();
                }
            }catch (Exception e){
                System.out.println("Error de cierre de fichero");
            }

        }
        return listaNaves;
    }
}