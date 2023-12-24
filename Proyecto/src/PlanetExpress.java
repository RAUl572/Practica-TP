import java.util.Random;
import java.util.Scanner;

/**
 * Clase principal de Planet Express App, la práctica de Taller de Programación.
 *
 * @author Raúl Fernández Iglesias
 * @author Noel López Losada
 * @version     1.0
 */
public class PlanetExpress {
    /**
     * Máximo de portes que va a tener la app.
     */
    private final int maxPortes;
    /**
     * Máximo de naves que va a tener la app.
     */
    private final int maxNaves;
    /**
     * Máximo de clientes que va a tener la app.
     */
    private final int maxClientes;
    /**
     * Máximo de envíos por cada cliente que va a tener la app.
     */
    private final int maxEnviosPorCliente;
    /**
     * Lista en que se van a guardar los puertos espaciales que maneje la app.
     */
    private ListaPuertosEspaciales listaPuertosEspaciales;
    /**
     * Máximo de puertos espaciales que va a tener la app.
     */
    private final int maxPuertosEspaciales;
    /**
     * Lista en que se van a guardar las naves que maneje la app.
     */
    private ListaNaves listaNaves;
    /**
     * Lista en que se van a guardar los clientes que maneje la app.
     */
    private ListaClientes listaClientes;
    /**
     * Lista en que se van a guardar los portes que maneje la app.
     */
    private ListaPortes listaPortes;


    /**
     * Constructor de la clase que recibe las capacidades máximas de las listas necesarias para que Planet Express App funcione correctamente.
     * @param maxPuertosEspaciales Máximo número de puertos espaciales que tendrá la lista de puertos espaciales de PlanetExpress App.
     * @param maxNaves Máximo número de naves que tendrá la lista de naves de PlanetExpress App.
     * @param maxPortes Máximo número de portes que tendrá la lista de portes de PlanetExpress App.
     * @param maxClientes Máximo número de clientes que tendrá la lista de clientes de PlanetExpress App.
     * @param maxEnviosPorCliente Máximo número de envíos por cliente.
     */
    //TODO: Rellene el constructor de la clase
    public PlanetExpress(int maxPuertosEspaciales, int maxNaves, int maxPortes, int maxClientes, int maxEnviosPorCliente) {
        this.maxPuertosEspaciales = maxPuertosEspaciales;
        this.maxNaves = maxNaves;
        this.maxPortes = maxPortes;
        this.maxClientes = maxClientes;
        this.maxEnviosPorCliente = maxEnviosPorCliente;
    }


    /**
     * Método que lee los ficheros CSV y guarda su información en listas.
     * @param ficheroPuertos Fichero en el que se guarda la información de los puertos espaciales.
     * @param ficheroNaves Fichero en el que se guarda la información de las naves.
     * @param ficheroPortes Fichero en el que se guarda la información de los portes.
     * @param ficheroClientes Fichero en el que se guarda la información de los clientes.
     * @param ficheroEnvios Fichero en el que se guarda la información de los envíos.
     */
    //TODO: Metodo para leer los datos de los ficheros específicados en el enunciado y los agrega a
    // la información de PlanetExpress (listaPuertosEspaciales, listaNaves, listaPortes, listaClientes)
    public void cargarDatos(String ficheroPuertos, String ficheroNaves, String ficheroPortes, String ficheroClientes, String ficheroEnvios) {
        listaPuertosEspaciales = ListaPuertosEspaciales.leerPuertosEspacialesCsv(ficheroPuertos,maxPuertosEspaciales);
        listaNaves = ListaNaves.leerNavesCsv(ficheroNaves,maxNaves);
        listaPortes = ListaPortes.leerPortesCsv(ficheroPortes,maxPortes,listaPuertosEspaciales,listaNaves);
        listaClientes = ListaClientes.leerClientesCsv(ficheroClientes,maxClientes,maxEnviosPorCliente);
        ListaEnvios.leerEnviosCsv(ficheroEnvios,listaPortes,listaClientes);

    }


    /**
     * Método que guarda los datos de las listas de Planet Express App a sus respectivos ficheros.
     * @param ficheroPuertos Fichero en el que se guarda la información de los puertos espaciales.
     * @param ficheroNaves Fichero en el que se guarda la información de las naves.
     * @param ficheroPortes Fichero en el que se guarda la información de los portes.
     * @param ficheroClientes Fichero en el que se guarda la información de los clientes.
     * @param ficheroEnvios Fichero en el que se guarda la información de los envíos.
     */
    //TODO: Metodo para almacenar los datos de PlanetExpress en los ficheros .csv especificados
    // en el enunciado de la práctica
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

    /**
     * Booleano que indica si se ha alcanzado el máximo de portes en la lista.
     * @return True si la lista se ha llenado, false si no.
     */
    public boolean maxPortesAlcanzado() {
        return listaPortes.estaLlena();
    }

    /**
     * Booleano que mete un porte a la lista de portes.
     * @param porte Porte que se va a meter a la lista.
     * @return True si la inserción se realiza correctamente, false si hay errores.
     */
    public boolean insertarPorte (Porte porte) {
        return listaPortes.insertarPorte(porte);
    }

    /**
     * Booleano que indica si se ha llenado la lista de clientes.
     * @return True si esta llena, false si no.
     */
    public boolean maxClientesAlcanzado() {
        return listaClientes.estaLlena();
    }

    /**
     * Booleano que inserta un cliente a la lista de envíos.
     * @param cliente Cliente que se inserta a la lista
     * @return True si se inserta correctamente, false en otro caso.
     */
    public boolean insertarCliente (Cliente cliente) {
        return listaClientes.insertarCliente(cliente);
    }

    /**
     * Método que busca todos los portes que van de un puerto a otro en una determinada fecha.
     * @param teclado Scanner que lee los datos del teclado.
     * @return La lista de portes con los portes que se llevan a cabo entre los puertos esa fecha.
     */
    //TODO: Metodo para buscar los portes especificados tal y como aparece en el enunciado de la práctica,
    // Devuelve una lista de los portes entre dos puertos espaciales con una fecha de salida solicitados por teclado
    // al usuario en el orden y con los textos establecidos (tomar como referencia los ejemplos de ejecución en el
    // enunciado de la prática)
    public ListaPortes buscarPorte(Scanner teclado) {
        ListaPortes listaPortes1;
        while((listaPortes1 = listaPortes.buscarPortes(
                listaPuertosEspaciales.seleccionarPuertoEspacial(teclado,"Ingrese código de puerto Origen: ").getCodigo(),
                listaPuertosEspaciales.seleccionarPuertoEspacial(teclado,"Ingrese código de puerto Destino: ").getCodigo(),
                Utilidades.leerFecha(teclado, "Fecha de salida: ")))==null){
            System.out.println("Datos sin coincidencia");
        }
        return listaPortes1;
    }


    /**
     * Método que contrata envíos con los datos que introduce el usuario.
     * @param teclado Scanner que lee los datos del usuario.
     * @param rand Random que permite generar el localizador del envío.
     * @param porte Porte al que pertenece el envío.
     */
    //TODO: Metodo para contratar un envio tal y como se indica en el enunciado de la práctica. Se contrata un envio para un porte
    // especificado, pidiendo por teclado los datos necesarios al usuario en el orden y con los textos (tomar como referencia los
    // ejemplos de ejecución en el enunciado de la prática)
    public void contratarEnvio(Scanner teclado, Random rand, Porte porte) {
        char opcion;
        Cliente cliente = null;
        String email;
        Envio contratado;
        if (porte != null) {
            opcion = Utilidades.leerLetraOpciones(teclado,"¿Comprar billete para un nuevo cliente(n), o para uno ya existente (e)? ",'n','e');
            if (opcion=='n'){
                do {
                    cliente = Cliente.altaCliente(teclado,listaClientes,maxEnviosPorCliente);
                    listaClientes.insertarCliente(cliente);
                }while (listaClientes.buscarClienteEmail(cliente.getEmail())==null);

            } else {
                while (cliente==null) {
                    do {
                        email = Utilidades.leerCadena(teclado, "Introduzca su email: ");
                    } while (!Cliente.correctoEmail(email));
                    cliente = listaClientes.buscarClienteEmail(email);
                }
            }
            contratado = Envio.altaEnvio(teclado,rand,porte,cliente);
            cliente.aniadirEnvio(contratado);
        }
    }


    /**
     * Método con la interfaz del menú que permite seleccionar una de sus opciones.
     * Tiene que coincidir con las trazas de ejecución que se muestran en el enunciado
     * @param teclado
     * @return opción seleccionada
     */
    //TODO Metodo statico con la interfaz del menú de entrada a la App.
    public static int menu(Scanner teclado) {
        int opcion;
        System.out.println("1. Alta de Porte");
        System.out.println("2. Alta de Cliente");
        System.out.println("3. Buscar Porte");
        System.out.println("4. Mostrar envíos de un cliente");
        System.out.println("5. Generar lista de envíos");
        System.out.println("0. Salir");
        opcion = Utilidades.leerNumero(teclado,"Seleccione opción: ",0,5);
        return opcion;
    }

    /**
     * Método main de Planet Express App que carga los datos de los ficheros CSV al inicio y los introduce en los ficheros al salir de la app,
     * Permite usar todas las opciones que ofrece la app.
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
    //TODO: Método Main que carga los datos de los ficheros CSV pasados por argumento (consola) en PlanetExpress,
    // llama iterativamente al menú y realiza la opción especificada hasta que se indique la opción Salir. Al finalizar
    // guarda los datos de PlanetExpress en los mismos ficheros CSV.
    public static void main(String[] args) {
        PlanetExpress app;
        Scanner teclado = new Scanner(System.in);
        Cliente cliente;
        Porte porte;
        ListaPortes listaPortes1;
        Envio envio;
        String nombre;
        char eleccion;
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
                    if((!app.maxPortesAlcanzado())&&(app.insertarPorte(porte=Porte.altaPorte(teclado,new Random(),app.listaPuertosEspaciales,app.listaNaves,app.listaPortes)))) {
                        System.out.printf("Porte %s creado correctamente\n", porte.getID());
                    }
                    break;
                case 2:     // TODO: Alta de Cliente
                    if ((cliente = Cliente.altaCliente(teclado, app.listaClientes, app.maxEnviosPorCliente)) != null) {
                        if (app.insertarCliente(cliente)){
                            System.out.println("Cliente con email "+cliente.getEmail()+" ha sido registrado");
                        }
                    }
                    break;
                case 3:     // TODO: Buscar Porte
                    listaPortes1 = app.buscarPorte(teclado);
                    app.contratarEnvio(teclado,new Random(),listaPortes1.seleccionarPorte(teclado,"Introduzca el id del porte que quiere ver: ","CANCELAR"));
                    break;
                case 4:     // TODO: Listado de envíos de un cliente
                    if ((cliente = app.listaClientes.seleccionarCliente(teclado,"Introduzca el email del cliente del que desea ver los envíos: "))!=null) {
                        if (cliente.listarEnviosBoolean()) {
                            if ((envio = cliente.getListaEnvios().seleccionarEnvio(teclado, "Seleccione un envío: ")) != null) {
                                eleccion = Utilidades.leerLetraOpciones(teclado, "¿Cancelar envío (c), o generar factura (f)? ", 'c', 'f');
                                if (eleccion == 'c') {
                                    if (envio.cancelar()) {
                                        System.out.println("   Envio cancelado");
                                    } else System.out.println("   Error al cancelar el envío");
                                } else {
                                    nombre = Utilidades.leerCadena(teclado, "Nombre del fichero: ");
                                    if (!nombre.equalsIgnoreCase("cancelar") && envio.generarFactura(nombre)) {
                                        System.out.println("   Factura generada");
                                    }

                                }
                            }
                        }
                    }
                    break;
                case 5:     // TODO: Lista de envíos de un porte
                    porte = app.listaPortes.seleccionarPorte(teclado,"Selecciones el porte: ","CANCELAR");
                    if (porte!=null) {
                        nombre = Utilidades.leerCadena(teclado, "Nombre del fichero: ");
                        if (!nombre.equalsIgnoreCase("cancelar")&&porte.generarListaEnvios(nombre)) {
                            System.out.println("   Fichero creado correctamente");
                        }
                    }
                    break;
            }
            System.out.println();
        } while (opcion != 0);
        app.guardarDatos(args[5],args[6],args[7],args[8],args[9]);
    }
}
