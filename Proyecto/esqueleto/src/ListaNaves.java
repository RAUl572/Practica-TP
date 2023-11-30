import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Description of the class
 *
 * @author
 * @author
 * @version     1.0
 */
public class ListaNaves {
    private Nave[] naves;
    private int ocupacion;
    private final int CAPACIDAD;

    /**
     * TODO: Constructor de la clase para inicializar la lista a una capacidad determinada
     *
     * @param capacidad
     */
    public ListaNaves(int capacidad) {
        naves = new Nave[capacidad];
		ocupacion = 0;
        CAPACIDAD = capacidad;
    }
    // TODO: Devuelve el número de naves que hay en la lista
    public int getOcupacion() {
        return ocupacion;
    }
    // TODO: ¿Está llena la lista de naves?
    public boolean estaLlena() {
        return  naves[CAPACIDAD-1]!=null;
    }
	// TODO: Devuelve nave dado un indice
    public Nave getNave(int posicion) {
        return naves[posicion];
    }

    /**
     * TODO: insertamos una nueva nave en la lista
     * @param nave
     * @return true en caso de que se añada correctamente, false en caso de lista llena o error
     */
    public boolean insertarNave(Nave nave) {
        boolean espacios = !estaLlena();
        if (espacios){
            naves[ocupacion] = nave;
            ocupacion++;
        }
        return espacios;
    }
    /**
     * TODO: Buscamos la nave a partir de la matricula pasada como parámetro
     * @param matricula
     * @return la nave que encontramos o null si no existe
     */
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
        if (indice==ocupacion){
            System.out.println("La nave con matrícula " + matricula + " no ha sido encontrada");
        }
        return buscada;
    }
    // TODO: Muestra por pantalla las naves de la lista con el formato indicado en el enunciado
    public void mostrarNaves() {
        if (ocupacion>0) {
            for (int i = 0; i < ocupacion; i++) {
                System.out.println(naves[i].toString());
            }
        }else System.out.println("No hay naves");
    }
    /**
     * TODO: Permite seleccionar una nave existente a partir de su matrícula, y comprueba si dispone de un alcance
     *  mayor o igual que el pasado como argumento, usando el mensaje pasado como argumento para la solicitud y
     *  siguiendo el orden y los textos mostrados en el enunciado.
     *  La función solicita repetidamente la matrícula de la nave hasta que se introduzca una con alcance suficiente
     * @param teclado
     * @param mensaje
     * @param alcance
     * @return
     */
    public Nave seleccionarNave(Scanner teclado, String mensaje, double alcance) {
        Nave nave = null;
        do {
            System.out.println(mensaje);
            String matricula = teclado.nextLine();
            nave = buscarNave(matricula);
            if (nave.getAlcance()<alcance){
                System.out.println("La nave no tiene suficiente alcance");
            }
        }while (nave.getAlcance()<alcance);
        return nave;
    }


    /**
     * TODO: Genera un fichero CSV con la lista de Naves, SOBREESCRIBIÉNDOLO
     * @param nombre
     * @return
     */
    public boolean escribirNavesCsv(String nombre) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(nombre);
            for (int i=0;i<ocupacion;i++){
                pw.println(naves[i].toStringCsv());
            }
            return true;
        } catch (Exception e) {
            return false;
        } finally {
                if (pw!=null){
                    pw.close();
                }
        }
    }


    /**
     * TODO: Genera una lista de naves a partir del fichero CSV, usando el argumento como capacidad máxima de la lista
     * @param fichero
     * @param capacidad
     * @return
     */
    public static ListaNaves leerNavesCsv(String fichero, int capacidad) {
        ListaNaves listaNaves = new ListaNaves(capacidad);
        Scanner sc = null;
        try {
            sc = new Scanner(new FileReader(fichero));
            String [] nave;
            Nave nueva;
            while (sc.hasNextLine()){
                nave = sc.nextLine().split(";");
                nueva = new Nave(nave[0],nave[1],nave[2],Integer.parseInt(nave[4]), Integer.parseInt(nave[3]),Integer.parseInt(nave[5]));
                listaNaves.insertarNave(nueva);
            }

        } catch (Exception e) {
            return null;
        } finally {
            if (sc!=null){
                sc.close();
            }
        }
        return listaNaves;
    }
}
