import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

/**
 * Porte es una clase que permite controlan donde se llevan los envíos los lugares entre los que se mueven y sus fechas
 * a parte de poder identificarlos. Además de permitir imprimir la matriz de los envíos y de imprimir la lista de
 * envíos del porte en un fichero.
 *
 * @author Raúl Fernández Iglesias.
 * @author Noel López Losada.
 * @version     1.0
 */
public class Porte {
    /**
     * Matriz qeu muestra los almacenamientos de las naves con los huecos ocupados.
     */
    private boolean[][] huecos;
    /**
     * Número de huecos ocupados.
     */
    private int numHuecos;
    /**
     * Identificador del porte.
     */
    private String id;
    /**
     * Nave que lleva el porte.
     */
    private Nave nave;
    /**
     * Puerto del que sale el porte.
     */
    private PuertoEspacial origen;
    /**
     * Muelle del que sale el porte.
     */
    private int muelleOrigen;
    /**
     * Fecha en la que sale el porte.
     */
    private Fecha salida;
    /**
     * Puerto al que llega el porte.
     */
    private PuertoEspacial destino;
    /**
     * Muelle al que llega el porte.
     */
    private int muelleDestino;
    /**
     * Fecha en la que llega el porte a su destino.
     */
    private Fecha llegada;
    /**
     * Precio del porte.
     */
    private double precio;
    /**
     * Lista con los envíos que lleva el porte.
     */
    private ListaEnvios listaEnvios;

    /**
     * Constructor de la clase que crea un porte con sus datos además de la matriz de los huecos que tiene
     * y la lista de los envíos que lleva.
     * 
     * @param id Identificador del porte.
     * @param nave Nave que lleva el porte.
     * @param origen Puerto del que sale el porte.
     * @param muelleOrigen Muelle desde el que sale el porte.
     * @param salida Fecha en la que sale el porte.
     * @param destino Puerto al que llega el porte.
     * @param muelleDestino Muelle al que llega el porte.
     * @param llegada Fecha en la que llega el porte.
     * @param precio Precio del porte.
     */
    //TODO: Completa el constructo de la clase
    public Porte(String id, Nave nave, PuertoEspacial origen, int muelleOrigen, Fecha salida, PuertoEspacial destino, int muelleDestino, Fecha llegada, double precio) {
        this.id = id;
        this.nave = nave;
        this.origen = origen;
        this.muelleOrigen = muelleOrigen;
        this.salida = salida;
        this.destino = destino;
        this.muelleDestino = muelleDestino;
        this.llegada = llegada;
        this.precio = precio;
        huecos = new boolean[nave.getFilas()][nave.getColumnas()];
        listaEnvios = new ListaEnvios(nave.getFilas()* nave.getColumnas());
        numHuecos=0;
    }

    /**
     * Getter de la lista de envíos.
     * @return Lista de envíos del porte.
     */
    public ListaEnvios getListaEnvios() {
        return listaEnvios;
    }

    /**
     * Getter del atributo id.
     * @return ID del porte.
     */
    public String getID() {
        return id;
    }

    /**
     * Getter del atributo nave.
     * @return Nave del porte.
     */
    public Nave getNave(){
        return nave;
    }

    /**
     * Getter del atributo origen.
     * @return El puerto de origen del porte.
     */
    public PuertoEspacial getOrigen() {
        return origen;
    }
    /**
     * Getter atributo muelleOrigen
     * @return El muelle del que sale el porte.
     */
    public int getMuelleOrigen() {
        return muelleOrigen;
    }

    /**
     * Getter del atributo salida.
     * @return Fecha de salida del porte.
     */
    public Fecha getSalida(){
        return salida;
    }

    /**
     * Getter del atributo destino.
     * @return Puerto espacial al que llega el porte.
     */
    public PuertoEspacial getDestino() {
        return destino;
    }

    /**
     * Getter del atributo muelleDestino.
     * @return Muelle al que llega el porte.
     */
    public int getMuelleDestino() {
        return muelleDestino;
    }

    /**
     * Getter del atributo llegada.
     * @return Fecha de llegada del porte.
     */
    public Fecha getLlegada() {
        return llegada;
    }

    /**
     * Getter del atributo precio.
     * @return Precio del porte.
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Método que da los huecos libres del porte.
     * @return Los huecos que hay sin ocupar.
     */
    // TODO: Devuelve el número de huecos libres que hay en el porte
    public int numHuecosLibres() {return (nave.getFilas()* nave.getColumnas()-numHuecos);}

    /**
     * Booleano que indica si el porte está lleno.
     * @return True si está lleno, false si no.
     */
    // TODO: ¿Están llenos todos los huecos?
    public boolean porteLleno() {return (nave.getFilas()* nave.getColumnas()==numHuecos);}

    /**
     * Booleano que indica si un hueco está ocupado.
     * @param fila Fila del hueco.
     * @param columna Columna del hueco.
     * @return True si está ocupado, false si no.
     */
    // TODO: ¿Está ocupado el hueco consultado?
    public boolean huecoOcupado(int fila, int columna) {
        return  huecos[fila-1][columna-1] ;
    }

    /**
     * Método que busca un envío dado su localizador.
     * @param localizador Localizador del envío que se busca.
     * @return El envío que se ha buscado o null si no se encuentra.
     */
    public Envio buscarEnvio(String localizador) {
        return listaEnvios.buscarEnvio(localizador);
    }


    /**
     * Método que busca un envío dada su fila y columna.
     * @param fila Fila del envío.
     * @param columna Columna del envío.
     * @return El objeto Envio que corresponde, o null si está libre o se excede en el límite de fila y columna
     */
    //TODO: Devuelve el objeto Envio que corresponde con una fila o columna,
    public Envio buscarEnvio(int fila, int columna) {return listaEnvios.buscarEnvio(getID(),fila,columna);}


    /**
     * Booleano que intenta ocupar un hueco en la matriz e insertar un envío en la lista.
     * @param envio Envío que se inserta.
     * @return True si se completa correctamente, false si hay algún error.
     */
    //TODO: Método que Si está desocupado el hueco que indica el envio, lo pone ocupado y devuelve true,
    // si no devuelve false
    public boolean ocuparHueco(Envio envio) {
        boolean libre = !huecoOcupado(envio.getFila(),envio.getColumna());
        if (libre){
            listaEnvios.insertarEnvio(envio);
            numHuecos++;
            huecos[envio.getFila()-1][envio.getColumna()-1]=true;
        }
        return libre;
    }


    /**
     * Booleano que intenta desocupar un hueco.
     * @param localizador Localizador del envío que se va a desocupar.
     * @return True si se completa correctamente, false si no.
     */
    //
    public boolean desocuparHueco(String localizador) {
        numHuecos--;
        huecos[buscarEnvio(localizador).getFila()-1][buscarEnvio(localizador).getColumna()-1] = false;
        return !huecos[buscarEnvio(localizador).getFila()-1][buscarEnvio(localizador).getColumna()-1];
    }

    /**
     * Método que crea un String con toda la información del porte.
     * @return ejemplo del formato -> "Porte PM0066 de Gaia Galactic Terminal(GGT) M5 (01/01/2023 08:15:00) a
     *  Cidonia(CID) M1 (01/01/2024 11:00:05) en Planet Express One(EP-245732X) por 13424,56 SSD, huecos libres: 10"
     */
    //TODO: Devuelve una cadena con información completa del porte
    public String toString() {
        return "Porte "+getID()+" de "+getOrigen().toStringSimple()+" M"+getMuelleOrigen()+" ("+getSalida()+") a "
                +getDestino().toStringSimple()+" M"+getMuelleDestino()+" ("+getLlegada()+") en "+getNave().toStringSimple()+" por "
                +getPrecio()+" SSD, huecos libres: "+numHuecosLibres();
    }

    /**
     * Método que crea un String con la información simplificada del porte.
     * @return ejemplo del formato -> "Porte PM0066 de GGT M5 (01/01/2023 08:15:00) a CID M1 (01/01/2024 11:00:05)"
     */
    //TODO: Devuelve una cadena con información abreviada del vuelo
    public String toStringSimple() {
        return "Porte "+getID()+" de "+getOrigen().getCodigo()+" M"+getMuelleOrigen()+" ("+getSalida()+") a "+getDestino().getCodigo()+" M"+getMuelleDestino()+" ("+getLlegada()+")";
    }


    /**
     * Booleano que comprueba si el puerto de origen, destino y la fecha son los del porte.
     * @param codigoOrigen Código del puerto de origen que se busca.
     * @param codigoDestino Código del puerto de destino que se busca.
     * @param fecha Fécha que se busca.
     * @return True ni coincide, false no.
     */
    public boolean coincide(String codigoOrigen, String codigoDestino, Fecha fecha) {
        return ((codigoOrigen.equals(this.getOrigen().getCodigo()))&&(codigoDestino.equals(this.getDestino().getCodigo()))&&(fecha==this.getLlegada()));
    }
    //TODO: Devuelve true si el código origen, destino y fecha son los mismos que el porte


    /**
     * TODO: Muestra la matriz de huecos del porte, ejemplo:
     *        A  B  C
     *      1[ ][ ][ ]
     *      2[X][X][X]
     *      3[ ][ ][ ]
     *      4[ ][X][ ]
     *     10[ ][ ][ ]
     * Método que imprime la matriz de huecos de la manera correspondiente.
     */
    public void imprimirMatrizHuecos() {
        for (int j=0;j<getNave().getColumnas();j++){System.out.print("   "+(char)(65+j));}
        System.out.print("\n");
        for (int i = 0;i<getNave().getFilas();i++){
            System.out.print(" "+(i+1));
            for (int j = 0;j<getNave().getColumnas();j++){
                if (huecos[i][j]){
                    System.out.print("[X] ");
                }else {
                    System.out.print("[ ] " );
                }
            }
            System.out.print("\n");
        }
    }

    /**
     * Booleano que intenta generar una lista de envíos
     * @param fichero Fichero en el que se guarda la lista.
     * @return True si se genera adecuadamente, false si no.
     */
    //TODO: Devuelve true si ha podido escribir en un fichero la lista de envíos del porte, siguiendo las indicaciones
    // del enunciado
    public boolean generarListaEnvios(String fichero) {
        PrintWriter pw = null;
        Envio envio;
        boolean resul;
        try {
            pw = new PrintWriter(fichero);
            pw.print("--------------------------------------------------------------\n" +
                     "--------- Lista de envíos del porte "+getID()+"--------------------\n" +
                     "--------------------------------------------------------------\n");
            pw.println("Hueco  Cliente");
            for (int i = 1;i<=getNave().getFilas();i++) {
                for (int j = 1;j<=getNave().getColumnas();j++) {
                    pw.print(i);
                    pw.print((char)(64+j));
                    if ((envio = buscarEnvio(i,j))!=null){
                        pw.print("       "+envio.getCliente().toString());
                    }
                    pw.print("\n");
                }
            }
            resul = true;
        } catch (FileNotFoundException e) {
            System.out.println("Fichero "+fichero+" no encontrado");
            resul = false;
        }finally {
                if (pw!=null){
                    pw.flush();
                    pw.close();
                }
        }
        return resul;
    }


    /**
     * Método que genera un id aleatorio para el porte.
     * @param rand Random que ayuda a generar el id del porte.
     * @return ejemplo -> "PM0123"
     */
    //TODO: Genera un ID de porte. Este consistirá en una cadena de 6 caracteres, de los cuales los dos primeros
    // serán PM y los 4 siguientes serán números aleatorios.
    // NOTA: Usar el objeto rand pasado como argumento para la parte aleatoria.
    public static String generarID(Random rand) {
        String id="PM";
        for (int i=1;i<=4;i++){id=id.concat(Integer.toString(rand.nextInt(9)));}
        return id;
    }

    /**
     * Método que da de alta un porte con la información que se comprueba que escribe el usuario.
     * @param teclado Scanner que lee los datos del usuario.
     * @param rand Random qye genera el id del porte.
     * @param puertosEspaciales Lista de puertos entre los que puede ir el porte.
     * @param naves Lista de naves que pueden llevar el porte.
     * @param portes Lista de portes en los que se meten el porte.
     * @return Porte que se ha dado de alta o null si se cancela.
     */
    //TODO: Crea y devuelve un objeto Porte de los datos que selecciona el usuario de puertos espaciales
    // y naves y la restricción de que no puede estar repetido el identificador, siguiendo las indicaciones
    // del enunciado.
    // La función solicita repetidamente los parametros hasta que sean correctos

    public static Porte altaPorte(Scanner teclado, Random rand,
                                  ListaPuertosEspaciales puertosEspaciales,
                                  ListaNaves naves,
                                  ListaPortes portes) {
        String matricula, codigoOrigen, codigoDestino;
        int muelle, terminal;
        double precio;
        Fecha salida, llegada;
        Porte nuevoPorte=null;
        boolean bucle;
        if (!portes.estaLlena()){
            //entrada de parámetros para los puertos y muelles
            if (!(codigoOrigen = Utilidades.leerCadena(teclado, "Ingrese código de puerto Origen:")).equalsIgnoreCase("cancelar")) {
                while (puertosEspaciales.buscarPuertoEspacial(codigoOrigen) == null) {
                    System.out.println("Código de puerto no encontrado.");
                    codigoOrigen = Utilidades.leerCadena(teclado, "Ingrese código de puerto Origen:");
                }
                muelle = Utilidades.leerNumero(teclado, "Ingrese el muelle de Origen (1 - 4):", 1, 4);
                if (!(codigoDestino = Utilidades.leerCadena(teclado, "Ingrese código de puerto Destino:")).equalsIgnoreCase("cancelar")) {
                    while ((puertosEspaciales.buscarPuertoEspacial(codigoDestino)) == null) {
                        System.out.println("Código de puerto no encontrado.");
                        codigoDestino = Utilidades.leerCadena(teclado, "Ingrese código de puerto Destino:");
                    }
                    terminal = Utilidades.leerNumero(teclado, "Ingrese Terminal Destino (1 - 6): ", 1, 6);

                    //Entrada de parámetros para la nave
                    naves.mostrarNaves();
                    if (!(matricula = Utilidades.leerCadena(teclado, "Ingrese matrícula de la nave:")).equalsIgnoreCase("cancelar")) {
                        do {
                            if (naves.buscarNave(matricula) == null) {
                                System.out.println("Matrícula no encontrada");
                                bucle = true;
                            } else if ((naves.buscarNave(matricula).getAlcance() < puertosEspaciales.buscarPuertoEspacial(codigoOrigen).distancia(puertosEspaciales.buscarPuertoEspacial(codigoDestino)))) {
                                System.out.println("Avión seleccionado con alcance insuficiente.");
                                bucle = true;
                            } else {bucle = false;}
                            if (bucle){matricula= Utilidades.leerCadena(teclado, "Ingrese matrícula de la nave:");}
                        } while (bucle);

                        //Entrada de parámetros para las fechas de salida y llegada
                        salida = Utilidades.leerFechaHora(teclado, "Introduzca la fecha de salida: ");
                        do {
                            llegada = Utilidades.leerFechaHora(teclado, "Introduzca la fecha de llegada: ");
                            if (llegada.anterior(salida)) {
                                System.out.println("La llegada debe ser posterior a la salida");
                            }
                        } while (llegada.anterior(salida));

                        //Entrada de parámetros para el precio
                        precio = Utilidades.leerNumero(teclado, "Ingrese precio del pasaje", 0, Double.MAX_VALUE);

                        //Creación del porte
                        nuevoPorte = new Porte(generarID(rand), naves.buscarNave(matricula),
                                puertosEspaciales.buscarPuertoEspacial(codigoOrigen),
                                muelle, salida, puertosEspaciales.buscarPuertoEspacial(codigoDestino), terminal, llegada, precio);
                    }
                }
            }
            }else{System.out.println("Lista de portes llena");}
            return nuevoPorte;
        }
    }