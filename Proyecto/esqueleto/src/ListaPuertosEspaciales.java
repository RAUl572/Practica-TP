import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Description of the class
 *
 * @author
 * @author
 * @version     1.0
 */
public class ListaPuertosEspaciales {
    private PuertoEspacial[] lista;
    private int ocupacion;

    private final int CAPACIDAD;

    /**
     * TODO: Constructor de la clase para inicializar la lista a una capacidad determinada
     *
     * @param capacidad indica el número de puertos espaciales que hay.
     */
    public ListaPuertosEspaciales(int capacidad) {
		lista = new PuertoEspacial[capacidad];
        ocupacion = 0;
        CAPACIDAD = capacidad;
    }
    // TODO: Devuelve el número de puertos espaciales que hay en la lista
    public int getOcupacion() {
        return ocupacion;
    }
    // TODO: ¿Está llena la lista?
    public boolean estaLlena() {
        return lista[CAPACIDAD-1]!=null;
    }
	// TODO: Devuelve un puerto espacial dado un indice
    public PuertoEspacial getPuertoEspacial(int i) {
        return lista[i];
    }

    /**
     * TODO: insertamos un Puerto espacial nuevo en la lista
     * @param puertoEspacial
     * @return true en caso de que se añada correctamente, false en caso de lista llena o error
     */
    public boolean insertarPuertoEspacial(PuertoEspacial puertoEspacial) {
        boolean espacios = !estaLlena();
        if (espacios) {
            lista[ocupacion] = puertoEspacial;
            ocupacion++;
        }
        return espacios;
    }

    /**
     * TODO: Buscamos un Puerto espacial a partir del codigo pasado como parámetro
     * @param codigo
     * @return Puerto espacial que encontramos o null si no existe
     */
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
        if (indice==ocupacion){
            System.out.println("El puerto con código " + codigo + " no ha sido encontrado");
        }
        return buscado;
    }

    /**
     * TODO: Permite seleccionar un puerto espacial existente a partir de su código, usando el mensaje pasado como
     *  argumento para la solicitud y siguiendo el orden y los textos mostrados en el enunciado.
     *  La función solicita repetidamente el código hasta que se introduzca uno correcto
     * @param teclado
     * @param mensaje
     * @return
     */
    public PuertoEspacial seleccionarPuertoEspacial(Scanner teclado, String mensaje) {
        PuertoEspacial puertoEspacial = null;
        do {
            System.out.println(mensaje);
            String codigo = teclado.nextLine();
            puertoEspacial = buscarPuertoEspacial(codigo);
        }while (puertoEspacial==null);
        return puertoEspacial;
    }

    /**
     * TODO: Genera un fichero CSV con la lista de puertos espaciales, SOBREESCRIBIENDOLO
     * @param nombre
     * @return
     */
    public boolean escribirPuertosEspacialesCsv(String nombre) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(nombre);
            for (int i=0;i<ocupacion;i++){
                pw.println(lista[i].toStringCsv());
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
     * TODO: Genera una lista de PuertosEspaciales a partir del fichero CSV, usando el argumento como capacidad máxima
     *  de la lista
     * @param fichero
     * @param capacidad
     * @return
     */
    public static ListaPuertosEspaciales leerPuertosEspacialesCsv(String fichero, int capacidad) {
        ListaPuertosEspaciales listaPuertosEspaciales = new ListaPuertosEspaciales(capacidad);
        Scanner sc = null;
        try {
            sc = new Scanner(new FileReader(fichero));
            String[] puerto;
            PuertoEspacial nuevo;
            while (sc.hasNext()){
                puerto = sc.nextLine().split(";");
                nuevo = new PuertoEspacial(puerto[0],puerto[1],Double.parseDouble(puerto[2]),Double.parseDouble(puerto[3]),Double.parseDouble(puerto[4]),Integer.parseInt(puerto[5]));
                listaPuertosEspaciales.insertarPuertoEspacial(nuevo);
            }

        } catch (Exception e) {
            return null;
        } finally {
            if (sc!=null){
                sc.close();
            }
        }
        return listaPuertosEspaciales;
    }
}
