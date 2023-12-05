import java.io.FileNotFoundException;
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
public class ListaClientes {
    private Cliente[] clientes;
    private int ocupacion;
    private final int CAPACIDAD;

    /**
     * TODO: Constructor de la clase para inicializar la lista a una capacidad determinada
     *
     * @param capacidad
     */
    public ListaClientes(int capacidad) {
        clientes = new Cliente[capacidad];
        ocupacion = 0;
        CAPACIDAD = capacidad;
    }
    // TODO: Devuelve el número de clientes que hay en la lista de clientes
    public int getOcupacion() {
        return ocupacion;
    }
    // TODO: ¿Está llena la lista de clientes?
    public boolean estaLlena() {
        return clientes[CAPACIDAD-1]!=null;
    }
	// TODO: Devuelve el cliente dada el indice
    public Cliente getCliente(int i) {
        return clientes[i];
    }
    // TODO: Inserta el cliente en la lista de clientes
    public boolean insertarCliente(Cliente cliente) {
        boolean insertar = !estaLlena();
        if (insertar){
            clientes[ocupacion] = cliente;
            ocupacion++;
        }
        return insertar;
    }
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
     * TODO: Método para seleccionar un Cliente existente a partir de su email, usando el mensaje pasado como argumento
     *  para la solicitud y, siguiendo el orden y los textos mostrados en el enunciado.
     *  La función debe solicitar repetidamente hasta que se introduzca un email correcto
     * @param teclado
     * @param mensaje
     * @return
     */
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
     * TODO: Método para guardar la lista de clientes en un fichero .csv, sobreescribiendo la información del mismo
     *  fichero
     * @param fichero
     * @return
     */
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
     * TODO: Genera una lista de Clientes a partir del fichero CSV, usando los límites especificados como argumentos
     *  para la capacidad de la lista y el número de billetes máximo por pasajero
     * @param fichero
     * @param capacidad
     * @param maxEnviosPorCliente
     * @return lista de clientes
     */
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