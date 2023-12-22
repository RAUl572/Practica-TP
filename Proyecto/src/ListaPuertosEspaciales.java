import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * La clase ListaPuertosEspaciales almacena un número determinado de puertos espaciales. Se usa a la hora de hacer un porte ver de que lugares a que
 * otros puede ir.
 *
 * @author Raúl Fernández Iglesias.
 * @author Noel López Losada.
 * @version     1.0
 */
public class ListaPuertosEspaciales {
    /**
     * Array en el que se guardan los puertos espaciales de la lista.
     */
    private PuertoEspacial[] lista;
    /**
     * Número de puertos en la lista.
     */
    private int ocupacion;



    /**
     * Constructor de la clase, inicia la lista para una capacidad e inicia la ocupacion a 0.
     * @param capacidad indica el número de puertos espaciales que hay.
     */
    //TODO: Constructor de la clase para inicializar la lista a una capacidad determinada
    public ListaPuertosEspaciales(int capacidad) {
        lista = new PuertoEspacial[capacidad];
        ocupacion = 0;
    }

    /**
     * Getter del parámetro ocupacion.
     * @return Devuelve el número de purtos espaciales en la lista.
     */
    // TODO: Devuelve el número de puertos espaciales que hay en la lista
    public int getOcupacion() {
        return ocupacion;
    }

    /**
     * Boolean que determina si la lista está llena o no.
     * @return Devuelve si el último espacio de la lista contiene un puerto espacial (devuelve true) o  es nulo (devuelve false).
     */
    // TODO: ¿Está llena la lista?
    public boolean estaLlena() {
        return lista[lista.length-1]!=null;
    }

    /**
     * Dado un índice da el puerto que le corresponde en la lista.
     * @param i Índice que se recibe.
     * @return Puerto espacial correspondiente a ese índice.
     */
    // TODO: Devuelve un puerto espacial dado un indice
    public PuertoEspacial getPuertoEspacial(int i) {
        return lista[i];
    }

    /**
     * Método que inserta un puerto a la lista.
     * @param puertoEspacial Puerto que se quiere añadir a la lista.
     * @return true en caso de que se añada correctamente, false en caso de lista llena o error
     */
    //TODO: insertamos un Puerto espacial nuevo en la lista
    public boolean insertarPuertoEspacial(PuertoEspacial puertoEspacial) {
        boolean espacios = !estaLlena();
        if (espacios) {
            lista[ocupacion] = puertoEspacial;
            ocupacion++;
        }
        return espacios;
    }

    /**
     * Método que busca en la lista a partir de su código.
     * @param codigo Código del puerto que estamos buscando.
     * @return Puerto espacial que encontramos o null si no existe
     */
    //TODO: Buscamos un Puerto espacial a partir del codigo pasado como parámetro
    public PuertoEspacial buscarPuertoEspacial(String codigo) {
        PuertoEspacial buscado = null;
        int indice=0;
        boolean encontrada = false;
        while (indice<ocupacion && !encontrada){
            if (lista[indice].getCodigo().equals(codigo)){
                encontrada = true;
                buscado = lista[indice];
            }
            indice ++;
        }
        return buscado;
    }
    /**
     * Método que permite elegir un puerto espacial de la lista con su código.
     * @param teclado Scanner que lee los códigos que introduce el usuario.
     * @param mensaje Instrucciones sobre lo que se necesita que se introduzca.
     * @return El puerto espacial con código igual al introducido.
     */
    //TODO: Permite seleccionar un puerto espacial existente a partir de su código, usando el mensaje pasado como
    // argumento para la solicitud y siguiendo el orden y los textos mostrados en el enunciado.
    // La función solicita repetidamente el código hasta que se introduzca uno correcto
    public PuertoEspacial seleccionarPuertoEspacial(Scanner teclado, String mensaje) {
        PuertoEspacial puertoEspacial;
        do {
            String codigo = Utilidades.leerCadena(teclado,mensaje);
            puertoEspacial = buscarPuertoEspacial(codigo);
        }while (puertoEspacial==null);
        return puertoEspacial;
    }

    /**
     * Método que genera un fichero con el formato CSV en el fichero indicado.
     * @param nombre Nombre del fichero en el que se va a escribir el fichero CSV de la lista.
     * @return True si el proceso se ejecuta correctamente, false si hay algún error.
     */
    //TODO: Genera un fichero CSV con la lista de puertos espaciales, SOBREESCRIBIENDOLO
    public boolean escribirPuertosEspacialesCsv(String nombre) {
        PrintWriter pw = null;
        boolean correcto;
        try {
            pw = new PrintWriter(nombre);
            for (int i=0;i<ocupacion;i++){
                pw.println(lista[i].toStringCsv());
            }
            correcto = true;
        }catch (FileNotFoundException e) {
            System.out.println("No se ha encontrado el fichero "+nombre);
            correcto = false;
        }catch (Exception ex){
            System.out.println("Error de escritura en el fichero.");
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
     * Método que lee un fichero con formato CSV y crea una lista de puertos espaciales a partir de él.
     * @param fichero Nombre del fichero CSV del que se van a leer los puertos espaciales.
     * @param capacidad Número de puertos del fichero.
     * @return True si el proceso se ejecuta correctamente, false si hay algún error.
     */
    //TODO: Genera una lista de PuertosEspaciales a partir del fichero CSV, usando el argumento como capacidad máxima
    // de la lista
    public static ListaPuertosEspaciales leerPuertosEspacialesCsv(String fichero, int capacidad) {
        ListaPuertosEspaciales listaPuertosEspaciales = null;
        Scanner sc = null;
        try {
            sc = new Scanner(new FileReader(fichero));
            listaPuertosEspaciales = new ListaPuertosEspaciales(capacidad);
            String[] puerto;
            PuertoEspacial nuevo;
            while (sc.hasNextLine()){
                puerto = sc.nextLine().split(";");
                nuevo = new PuertoEspacial(puerto[0],puerto[1],Double.parseDouble(puerto[2]),Double.parseDouble(puerto[3]),Double.parseDouble(puerto[4]),Integer.parseInt(puerto[5]));
                listaPuertosEspaciales.insertarPuertoEspacial(nuevo);
            }

        } catch (FileNotFoundException e) {
            System.out.println("No se ha encontrado el fichero "+fichero);
        }catch (Exception e) {
            System.out.println("Error de lectura");
            listaPuertosEspaciales = null;
        } finally {
            try {
                if (sc!=null){
                    sc.close();
                }
            }catch (Exception e){
                System.out.println("Error de cierre de fichero");
            }

        }
        return listaPuertosEspaciales;
    }
}
