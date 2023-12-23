import java.util.Scanner;

/**
 * Cliente es una clase usada para alamcenar los datos de todos los clientes que usen
 * el sistema y sus respectivos envíos.
 *
 * @author Raúl Fernández Iglesias.
 * @author Noel López Losada.
 * @version     1.0
 */
public class Cliente {
    /**
     * Lista que contiene los envios del cliente.
     */
    private final ListaEnvios listaEnvios;
    /**
     * Nombre del cliente.
     */
    private final String nombre;
    /**
     * Apellidos del cliente.
     */
    private final String apellidos;
    /**
     * Email del cliente.
     */
    private final String email;

    /**
     * Constructor de la clase que crea un cliente basándose en los parámetros.
     *
     * @param nombre Nombre del cliente.
     * @param apellidos Apellidos del cliente.
     * @param email Email del cliente.
     * @param maxEnvios Número máximo de envíos que puede tener el cliente.
     */
    public Cliente(String nombre, String apellidos, String email, int maxEnvios) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.listaEnvios = new ListaEnvios(maxEnvios);
    }

    /**
     * Getter del atributo nombre.
     * @return Nombre del cliente.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Getter del atributo apellidos.
     * @return Apellidos del cliente.
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Getter del atributo email.
     * @return Email del cliente.
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return Datos del cliente con el formato Zapp Brannigan, zapp.brannigan@dop.gov.
     */
    public String toString() {
        return (getNombre()+" "+getApellidos()+", "+getEmail());
    }

    /**
     * @return String con formato de fichero .CSV
     */
    public String toStringCSV() {
        return (getNombre()+";"+getApellidos()+";"+getEmail());
    }

    /**
     * Método booleano que indica si se ha alcanzado el número máximo de envios.
     * @return True si la lista se ha llenado, false si no.
     */
    // TODO: Devuelve un booleano que indica si se ha alcanzado el número máximo de envíos
    public boolean maxEnviosAlcanzado() {
        return getListaEnvios().estaLlena();
    }

    /**
     * Getter de los envios del cliente.
     * @param i Indice del envio en la lista.
     * @return El envio que se encuentra en esa posición.
     */
    // TODO: Devuelve un envío en función de su posición
    public Envio getEnvio(int i) {
        return getListaEnvios().getEnvio(i);
    }

    /**
     * Getter del atributo listaEnvios.
     * @return La lista de envios del cliente.
     */
    public ListaEnvios getListaEnvios() {
        return listaEnvios;
    }

    /**
     * @param envio Envio que se quiere añadir a la lista de envios del cliente.
     * @return True si se añade correctamente, false si hay errores.
     */
    // TODO: Añade un envío al cliente
    public boolean aniadirEnvio(Envio envio) {
        return getListaEnvios().insertarEnvio(envio);
    }

    /**
     * Busca un envío a partir de su localizador.
     * @param localizador Localizador que se va a buscar.
     * @return El envío que corresponde al localizador.
     */
    public Envio buscarEnvio(String localizador) {
        return getListaEnvios().buscarEnvio(localizador);
    }

    /**
     * Cancela un envío a partir de su localizador.
     * @param localizador Localizador del envío que se va a eliminar.
     * @return True si se elimina correctamente, false si hay errores.
     */
    // TODO: Elimina el envío de la lista de envíos del pasajero
    public boolean cancelarEnvio(String localizador) {
        return getListaEnvios().eliminarEnvio(localizador);
    }

    /**
     * Método que muestra por pantalla los envíos del cliente.
     */
    public void listarEnvios() {
        listaEnvios.listarEnvios();
    }

    /**
     * Método que llama a la lista del
     * cliente para mostrar los envíos
     * @return devuelve true logra mostrar los envios y
     * false si no hay envios que mostrar
     */
    public boolean listarEnviosBoolean() {
        return listaEnvios.listarEnviosBoolean();
    }

    /**
     * Método que ncapsula la funcionalidad seleccionarEnvio de ListaEnvios
      */
    public Envio seleccionarEnvio(Scanner teclado, String mensaje) {
        return listaEnvios.seleccionarEnvio(teclado, mensaje);
    }


    /**
     * Método para crear un cliente que no exista previamente
     * Los datos se piden al usuario hasta que son correctos.
     * @param teclado Scanner que lee los datos que proporciona el usuario.
     * @param clientes Lista de clientes a la que se añade el nuevo.
     * @param maxEnvios Máximo de envíos que tiene cada cliente.
     * @return Cliente que se ha sido dado de alta.
     */
    //TODO: Método estático para crear un nuevo cliente "no repetido", se pide por teclado los datos necesarios
    // al usuario en el orden y con los textos indicados en los ejemplos de ejecución del enunciado
    // La función tiene que solicitar repetidamente los parámetros hasta que sean correctos

    public static Cliente altaCliente(Scanner teclado, ListaClientes clientes, int maxEnvios) {
        String nombre, apellidos, email = null;
        Cliente nuevo = null;
        if (!clientes.estaLlena()){
            do {
                if (!(nombre = (Utilidades.leerCadena(teclado, "Nombre: "))).equalsIgnoreCase("cancelar")){
                    apellidos = (Utilidades.leerCadena(teclado, "Apellidos: "));
                    email = (Utilidades.leerCadena(teclado, "Email: "));
                    if (email!=null&&correctoEmail(email))nuevo = new Cliente(nombre, apellidos, email, maxEnvios);
                }
            } while (email==null&&(clientes.buscarClienteEmail(email)!=null)&&!(nombre.equalsIgnoreCase("cancelar")));
        }else System.out.println("   Aumente la capacidad de clientes.");
        return nuevo;
    }


    /**
     * Método que comprueba si un email es correcto.
     * @param email Email que se va a comprobar.
     * @return True si el formato es correcto, false si es incorrecto.
     */
    //TODO: Metodo para comprobar que el formato de email introducido sea correcto
    public static boolean correctoEmail(String email){
        return ((email.contains("@"))&&(email.split("@")[1].contains(".")));
    }
}
