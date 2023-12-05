import java.util.Scanner;

/**
 * Description of the class
 *
 * @author Taller de Programación
 * @version     1.0
 */
public class Cliente {
    private final ListaEnvios listaEnvios;
    private final String nombre;
    private final String apellidos;
    private final String email;

    /**
     * Constructor of the class
     *
     * @param nombre Nombre del cliente
     * @param apellidos Apellidos del cliente
     * @param email Email del cliente
     * @param maxEnvios Número máximo de envíos que puede tener el cliente
     */
    public Cliente(String nombre, String apellidos, String email, int maxEnvios) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.listaEnvios = new ListaEnvios(maxEnvios);
    }
    public String getNombre() {
        return nombre;
    }
    public String getApellidos() {
        return apellidos;
    }
    public String getEmail() {
        return email;
    }
    // TODO: Texto que debe generar: Zapp Brannigan, zapp.brannigan@dop.gov
    public String toString() {
        return (getNombre()+" "+getApellidos()+", "+getEmail());
    }

    /**
     * @return String con formato de fichero .CSV
     */
    public String toStringCSV() {
        return (getNombre()+";"+getApellidos()+";"+getEmail());
    }
    // TODO: Devuelve un booleano que indica si se ha alcanzado el número máximo de envíos
    public boolean maxEnviosAlcanzado() {
        return getListaEnvios().estaLlena();
    }
    // TODO: Devuelve un envío en función de su posición
    public Envio getEnvio(int i) {
        return getListaEnvios().getEnvio(i);
    }
    public ListaEnvios getListaEnvios() {
        return listaEnvios;
    }
    // TODO: Añade un envío al cliente
    public boolean aniadirEnvio(Envio envio) {
        return getListaEnvios().insertarEnvio(envio);
    }
    public Envio buscarEnvio(String localizador) {
        return getListaEnvios().buscarEnvio(localizador);
    }
    // TODO: Elimina el envío de la lista de envíos del pasajero
    public boolean cancelarEnvio(String localizador) {
        return getListaEnvios().eliminarEnvio(localizador);
    }
    public void listarEnvios() {
        listaEnvios.listarEnvios();
    }
    // Encapsula la funcionalidad seleccionarEnvio de ListaEnvios
    public Envio seleccionarEnvio(Scanner teclado, String mensaje) {
        return listaEnvios.seleccionarEnvio(teclado, mensaje);
    }


    /**
     * TODO: Método estático para crear un nuevo cliente "no repetido", se pide por teclado los datos necesarios
     * al usuario en el orden y con los textos indicados en los ejemplos de ejecución del enunciado
     * La función tiene que solicitar repetidamente los parámetros hasta que sean correctos
     * @param teclado
     * @param clientes
     * @param maxEnvios
     * @return Cliente
     */
    public static Cliente altaCliente(Scanner teclado, ListaClientes clientes, int maxEnvios) {
        String nombre, apellidos, email;
        if (!clientes.estaLlena()){
            do {
                nombre = Utilidades.leerCadena(teclado, "Nombre: ");
                apellidos = Utilidades.leerCadena(teclado, "Apellidos: ");
                email = Utilidades.leerCadena(teclado, "Email: ");
            } while (!correctoEmail(email)&&(clientes.buscarClienteEmail(email)==null));
        }else {return null;}
        return new Cliente(nombre, apellidos, email, maxEnvios);
    }


    /**
     * TODO: Metodo para comprobar que el formato de email introducido sea correcto
     * @param email
     * @return
     */
    // La condición es que sea una @, un . y un .
    public static boolean correctoEmail(String email){
        return ((email.contains("@"))&&(email.split("@")[1].contains(".")));
    }
}