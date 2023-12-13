import java.util.Random;
import java.util.Scanner;

/**
 * Clase principal de Planet Express App, la práctica de Taller de Programación
 *
 * @author      Taller de Progamación
 * @version     1.0
 */
public class PlanetExpress {
    private final int maxPortes;
    private final int maxNaves;
    private final int maxClientes;
    private final int maxEnviosPorCliente;
    private ListaPuertosEspaciales listaPuertosEspaciales;
    private final int maxPuertosEspaciales;
    private ListaNaves listaNaves;
    private ListaClientes listaClientes;
    private ListaPortes listaPortes;


    /**
     * TODO: Rellene el constructor de la clase
     *
     * @param maxPuertosEspaciales Máximo número de puertos espaciales que tendrá la lista de puertos espaciales de PlanetExpress App.
     * @param maxNaves Máximo número de naves que tendrá la lista de naves de PlanetExpress App.
     * @param maxPortes Máximo número de portes que tendrá la lista de portes de PlanetExpress App.
     * @param maxClientes Máximo número de clientes que tendrá la lista de clientes de PlanetExpress App.
     * @param maxEnviosPorCliente Máximo número de envíos por cliente.
     */
    public PlanetExpress(int maxPuertosEspaciales, int maxNaves, int maxPortes, int maxClientes, int maxEnviosPorCliente) {
        this.maxPuertosEspaciales = maxPuertosEspaciales;
        this.maxNaves = maxNaves;
        this.maxPortes = maxPortes;
        this.maxClientes = maxClientes;
        this.maxEnviosPorCliente = maxEnviosPorCliente;
    }


    /**
     * TODO: Metodo para leer los datos de los ficheros específicados en el enunciado y los agrega a
     *  la información de PlanetExpress (listaPuertosEspaciales, listaNaves, listaPortes, listaClientes)
     * @param ficheroPuertos
     * @param ficheroNaves
     * @param ficheroPortes
     * @param ficheroClientes
     * @param ficheroEnvios
     */
    public void cargarDatos(String ficheroPuertos, String ficheroNaves, String ficheroPortes, String ficheroClientes, String ficheroEnvios) {
        listaPuertosEspaciales = ListaPuertosEspaciales.leerPuertosEspacialesCsv(ficheroPuertos,maxPuertosEspaciales);
        listaNaves = ListaNaves.leerNavesCsv(ficheroNaves,maxNaves);
        listaPortes = ListaPortes.leerPortesCsv(ficheroPortes,maxPortes,listaPuertosEspaciales,listaNaves);
        listaClientes = ListaClientes.leerClientesCsv(ficheroClientes,maxClientes,maxEnviosPorCliente);
        ListaEnvios.leerEnviosCsv(ficheroEnvios,listaPortes,listaClientes);

    }


    /**
     * TODO: Metodo para almacenar los datos de PlanetExpress en los ficheros .csv especificados
     *  en el enunciado de la práctica
     * @param ficheroPuertos
     * @param ficheroNaves
     * @param ficheroPortes
     * @param ficheroClientes
     * @param ficheroEnvios
     */
    public void guardarDatos(String ficheroPuertos, String ficheroNaves, String ficheroPortes, String ficheroClientes, String ficheroEnvios) {
        listaClientes.escribirClientesCsv(ficheroClientes);
        listaPuertosEspaciales.escribirPuertosEspacialesCsv(ficheroPuertos);
        listaNaves.escribirNavesCsv(ficheroNaves);
        listaPortes.escribirPortesCsv(ficheroPortes);
        ListaEnvios listaEnvios;
        for (int i=0; i<listaClientes.getOcupacion();i++) {
             listaEnvios = listaClientes.getCliente(i).getListaEnvios();
             listaEnvios.aniadirEnviosCsv(ficheroEnvios);
        }
    }
    public boolean maxPortesAlcanzado() {
        return listaPortes.estaLlena();
    }
    public boolean insertarPorte (Porte porte) {
        return listaPortes.insertarPorte(porte);
    }
    public boolean maxClientesAlcanzado() {
        return listaClientes.estaLlena();
    }
    public boolean insertarCliente (Cliente cliente) {
        return listaClientes.insertarCliente(cliente);
    }

    /**
     * TODO: Metodo para buscar los portes especificados tal y como aparece en el enunciado de la práctica,
     *  Devuelve una lista de los portes entre dos puertos espaciales con una fecha de salida solicitados por teclado
     *  al usuario en el orden y con los textos establecidos (tomar como referencia los ejemplos de ejecución en el
     *  enunciado de la prática)
     * @param teclado
     * @return
     */
    public ListaPortes buscarPorte(Scanner teclado) {
            String codigoOrigen = listaPuertosEspaciales.seleccionarPuertoEspacial(teclado,"Ingrese código de puerto Origen: ").getCodigo();
            String codigoDestino = listaPuertosEspaciales.seleccionarPuertoEspacial(teclado,"Ingrese código de puerto Destino: ").getCodigo();
            Fecha fecha = Utilidades.leerFecha(teclado,"Fecha de salida: ");
        return listaPortes.buscarPortes(codigoOrigen, codigoDestino, fecha);
    }


    /**
     * TODO: Metodo para contratar un envio tal y como se indica en el enunciado de la práctica. Se contrata un envio para un porte
     *  especificado, pidiendo por teclado los datos necesarios al usuario en el orden y con los textos (tomar como referencia los
     *  ejemplos de ejecución en el enunciado de la prática)
     * @param teclado
     * @param rand
     * @param porte
     */
    public void contratarEnvio(Scanner teclado, Random rand, Porte porte) {
        int opcion;
        Cliente cliente = null;
        String email;
        Envio contratado;
        if (porte != null) {
            System.out.println("1. Cliente ya registrado");
            System.out.println("2. Nuevo cliente");
            opcion = Utilidades.leerNumero(teclado,"Selecciona la opcion",1,2);
            if (opcion==2){
                cliente = Cliente.altaCliente(teclado,listaClientes,maxEnviosPorCliente);
                listaClientes.insertarCliente(cliente);
            } else {
                do {
                    email = Utilidades.leerCadena(teclado,"Introduzca su email");
                }while (!Cliente.correctoEmail(email));
                cliente = listaClientes.buscarClienteEmail("email");
            }
            contratado = Envio.altaEnvio(teclado,rand,porte,cliente);
            cliente.aniadirEnvio(contratado);
        }
    }


    /**
     * TODO Metodo statico con la interfaz del menú de entrada a la App.
     * Tiene que coincidir con las trazas de ejecución que se muestran en el enunciado
     * @param teclado
     * @return opción seleccionada
     */
    public static int menu(Scanner teclado) {
        int opcion;
        System.out.println("1. Alta de Porte");
        System.out.println("2. Alta de Cliente");
        System.out.println("3. Buscar Porte");
        System.out.println("4. Mostrar envíos de un cliente");
        System.out.println("5. Generar lista de envíos");
        System.out.println("0. Salir");
        opcion = Utilidades.leerNumero(teclado,"Seleccione opción:",0,5);
        return opcion;
    }

    /**
     * TODO: Método Main que carga los datos de los ficheros CSV pasados por argumento (consola) en PlanetExpress,
     *  llama iterativamente al menú y realiza la opción especificada hasta que se indique la opción Salir. Al finalizar
     *  guarda los datos de PlanetExpress en los mismos ficheros CSV.
     * @param args argumentos de la línea de comandos, recibe **10 argumentos** estrictamente en el siguiente orden:
     * 1. Número máximo de puertos espaciales que tendrá la lista de puertos espaciales de PlanetExpress App.
     * 2. Número máximo de naves que tendrá la lista de naves de PlanetExpress App.
     * 3. Número máximo de portes que tendrá la lita de portes de PlanetExpress App.
     * 4. Número máximo de clientes que tendrá la lista de clientes de PlanetExpress App.
     * 5. Número máximo de envíos por pasajero.
     * 6. Nombre del fichero CSV que contiene la lista de puertos espaciales de PlanetExpress App (tanto para lectura como escritura).
     * 7. Nombre del fichero CSV que contiene la lista de naves de PlanetExpress App (tanto para lectura como escritura).
     * 8. Nombre del fichero CSV que contiene la lista de portes de PlanetExpress App (tanto para lectura como escritura).
     * 9. Nombre del fichero CSV que contiene la lista de clientes de PlanetExpress App (tanto para lectura como escritura).
     * 10. Nombre del fichero CSV que contiene los envíos adquiridos en PlanetExpress App (tanto para lectura como escritura).
     * En el caso de que no se reciban exactamente estos argumentos, el programa mostrará el siguiente mensaje
     * y concluirá la ejecución del mismo: `Número de argumentos incorrecto`.
     */
    public static void main(String[] args) {
        PlanetExpress app;
        Scanner teclado = new Scanner(System.in);
        Cliente cliente;
        Porte porte;
        int opcion;
        if (args.length != 10) {
            System.out.println("Número de argumentos incorrecto");
            return;
        }else {
            app = new PlanetExpress(Integer.parseInt(args[0]),Integer.parseInt(args[1]),Integer.parseInt(args[2]),Integer.parseInt(args[3]),Integer.parseInt(args[4]));
            app.cargarDatos(args[5],args[6],args[7],args[8],args[9]);
        }


        do {
            opcion = menu(teclado);
            switch (opcion) {
                case 1:     // TODO: Alta de Porte


                    break;
                case 2:     // TODO: Alta de Cliente
                    cliente = Cliente.altaCliente(teclado,app.listaClientes,app.maxEnviosPorCliente);
                    app.listaClientes.insertarCliente(cliente);
                    break;
                case 3:     // TODO: Buscar Porte
                    app.buscarPorte(teclado);
                    break;
                case 4:     // TODO: Listado de envíos de un cliente
                    cliente = app.listaClientes.seleccionarCliente(teclado,"Introduzca el email del cliente del que desea ver los envios");
                    cliente.listarEnvios();
                    break;
                case 5:     // TODO: Lista de envíos de un porte
                    porte = app.listaPortes.seleccionarPorte(teclado,"Introduzca el id del porte que quiere ver","CANCELAR");
                    porte.getListaEnvios().listarEnvios();

                    break;
            }
        } while (opcion != 0);

        app.guardarDatos(args[5],args[6],args[7],args[8],args[9]);

    }
}
