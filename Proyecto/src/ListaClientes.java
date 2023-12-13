import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * La clase lista clientes almacena varios clientes. Es de gran utilidad a la hora de ver los clientes que hay en el sistema
 * y poder guardar sus datos con gran facilidad
 *
 * @author Raúl Fernández Iglesias.
 * @author Noel López Losada.
 * @version     1.0
 */
public class ListaClientes {
    /**
     * Array que contiene los clientes de la lista.
     */
    private Cliente[] clientes;
    /**
     * Número de clientes que contiene la lista.
     */
    private int ocupacion;

    /**
     * Constructor de la clase, inicia la lista a una capacidad determinada e inicia la ocupación a 0.
     * @param capacidad Capacidad de clientes que va a tener la lista.
     */
    //TODO: Constructor de la clase para inicializar la lista a una capacidad determinada
    public ListaClientes(int capacidad) {
        clientes = new Cliente[capacidad];
        ocupacion = 0;
    }

    /**
     * Getter del atributo ocupacion
     * @return Devuelve el número de clientes de la lista
     */
    // TODO: Devuelve el número de clientes que hay en la lista de clientes
    public int getOcupacion() {
        return ocupacion;
    }

    /**
     * Comprueba si la lista está llena.
     * @return Devuelve si el último espacio posible de la lista es nulo(false) o contiene un cliente(true).
     */
    // TODO: ¿Está llena la lista de clientes?
    public boolean estaLlena() {
        return clientes[clientes.length-1]!=null;
    }

    /**
     * Getter de un cliente de la lista.
     * @param i Indice que se recibe por parámetros.
     * @return Devuelve el cliente que le corresponde a ese indice en la lista.
     */
	// TODO: Devuelve el cliente dada el indice
    public Cliente getCliente(int i) {
        return clientes[i];
    }

    /**
     * Introduce un cliente en la lista.
     * @param cliente cliente que se va a introducir.
     * @return true si se introduce correctamente false en cualquier otro caso.
     */
    // TODO: Inserta el cliente en la lista de clientes
    public boolean insertarCliente(Cliente cliente) {
        boolean insertar = !estaLlena();
        if (insertar){
            clientes[ocupacion] = cliente;
            ocupacion++;
        }
        return insertar;
    }

    /**
     * Busca un cliente dado su email.
     * @param email Email del cliente que se va a buscar.
     * @return El cliente dueño de ese email o nulo si no existe un cliente con ese email.
     */
    // TODO: Devuelve el cliente que coincida con el email, o null en caso de no encontrarlo
    public Cliente buscarClienteEmail(String email) {
        int indice = 0;
        boolean encontrado = false;
        Cliente cliente = null;
        while (indice<ocupacion && !encontrado){
            if (email.equals(clientes[indice].getEmail())){
                cliente = clientes[indice];
                encontrado = true;
            }
            indice++;
        }
        return cliente;
    }

    /**
     * Se busca un cliente con su email el cual introducirá el usuario hasta que encuentre un email válido.
     * @param teclado Scanner que lee lo que introduce el usuario.
     * @param mensaje Mensaje que se le muestra al usuario.
     * @return El cliente cuyo email coincide con el que pone el usuario.
     */
    /*TODO: Método para seleccionar un Cliente existente a partir de su email, usando el mensaje pasado como argumento
       para la solicitud y, siguiendo el orden y los textos mostrados en el enunciado.
       La función debe solicitar repetidamente hasta que se introduzca un email correcto*/
    public Cliente seleccionarCliente(Scanner teclado, String mensaje) {
        Cliente cliente = null;
        String email;
        do {
            System.out.println(mensaje);
            email = teclado.nextLine();
            cliente = buscarClienteEmail(email);
            if (cliente==null){
                System.out.println("El cliente con email : "+ email +"no existe.");
            }
        }while (cliente==null);
        return cliente;
    }

    /**
     * Imprime la lista de clientes en un fichero .csv
     * @param fichero Fichero en el que se va a imprimir la lista.
     * @return True si el proceso concluye exitosamente, false si hay algún error.
     */
    /*TODO: Método para guardar la lista de clientes en un fichero .csv, sobreescribiendo la información del mismo
       fichero*/
    public boolean escribirClientesCsv(String fichero) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(fichero);
            for (int i =0 ; i<ocupacion;i++){
                pw.println(clientes[i].toStringCSV());
            }
            return true;
        } catch (FileNotFoundException e) {
            return false;
        } finally {
            if (pw!=null){
                pw.close();
            }
        }
    }

    /**
     * Lee un fichero csv y crea una lista de clientes a partir de él.
     * @param fichero Fichero csv que se va a leer.
     * @param capacidad Número de clientes en el fichero.
     * @param maxEnviosPorCliente Máximo de envios que puede tener el cliente.
     * @return Lista de clientes si no hay ningún error, sino devuelve nulo.
     */
    /* TODO: Genera una lista de Clientes a partir del fichero CSV, usando los límites especificados como argumentos
        para la capacidad de la lista y el número de billetes máximo por pasajero*/
    public static ListaClientes leerClientesCsv(String fichero, int capacidad, int maxEnviosPorCliente) {
        ListaClientes listaClientes = null;
        Scanner sc = null;
        try {
            sc = new Scanner(new FileReader(fichero));
            listaClientes = new ListaClientes(capacidad);
            String [] cliente;
            Cliente nuevo;
            while (sc.hasNextLine()){
                cliente = sc.nextLine().split(";");
                nuevo = new Cliente(cliente[0],cliente[1],cliente[2],maxEnviosPorCliente);
                listaClientes.insertarCliente(nuevo);
            }
        } catch (FileNotFoundException e) {
            return null;
        } finally {
            if (sc!=null){
                sc.close();
            }
        }
        return listaClientes;
    }
}