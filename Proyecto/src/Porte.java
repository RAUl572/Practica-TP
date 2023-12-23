import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

/**
 * Description of the class
 *
 * @author Raúl Fernández Iglesias.
 * @author Noel López Losada.
 * @version     1.0
 */
public class Porte {
    private boolean[][] huecos;
    /**
     * Número de huecos ocupados
     */
    private int numHuecos;
    private String id;
    private Nave nave;
    private PuertoEspacial origen;
    private int muelleOrigen;
    private Fecha salida;
    private PuertoEspacial destino;
    private int muelleDestino;
    private Fecha llegada;
    private double precio;
    private ListaEnvios listaEnvios;

    /**
     * TODO: Completa el constructo de la clase
     * 
     * @param id
     * @param nave
     * @param origen
     * @param muelleOrigen
     * @param salida
     * @param destino
     * @param muelleDestino
     * @param llegada
     * @param precio
     */
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

    public ListaEnvios getListaEnvios() {
        return listaEnvios;
    }
    public String getID() {
        return id;
    }
    public Nave getNave(){
        return nave;
    }
    public PuertoEspacial getOrigen() {
        return origen;
    }
    public int getMuelleOrigen() {
        return muelleOrigen;
    }
    public Fecha getSalida(){
        return salida;
    }
    public PuertoEspacial getDestino() {
        return destino;
    }
    public int getMuelleDestino() {
        return muelleDestino;
    }
    public Fecha getLlegada() {
        return llegada;
    }
    public double getPrecio() {
        return precio;
    }
    // TODO: Devuelve el número de huecos libres que hay en el porte
    public int numHuecosLibres() {return (nave.getFilas()* nave.getColumnas()-numHuecos);}
    // TODO: ¿Están llenos todos los huecos?
    public boolean porteLleno() {return (nave.getFilas()* nave.getColumnas()==numHuecos);}
    // TODO: ¿Está ocupado el hueco consultado?
    public boolean huecoOcupado(int fila, int columna) {
        return  huecos[fila-1][columna-1] ;
    }
    public Envio buscarEnvio(String localizador) {
        return listaEnvios.buscarEnvio(localizador);
    }


    /**
     * TODO: Devuelve el objeto Envio que corresponde con una fila o columna,
     * @param fila
     * @param columna
     * @return el objeto Envio que corresponde, o null si está libre o se excede en el límite de fila y columna
     */
    public Envio buscarEnvio(int fila, int columna) {return listaEnvios.buscarEnvio(getID(),fila,columna);}


    /**
     * TODO: Método que Si está desocupado el hueco que indica el envio, lo pone ocupado y devuelve true,
     *  si no devuelve false
     * @param envio
     * @return
     */
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
     * TODO: A través del localizador del envio, se desocupará el hueco
     * @param localizador
     * @return
     */
    public boolean desocuparHueco(String localizador) {
        numHuecos--;
        huecos[buscarEnvio(localizador).getFila()-1][buscarEnvio(localizador).getColumna()-1] = false;
        return !huecos[buscarEnvio(localizador).getFila()-1][buscarEnvio(localizador).getColumna()-1];
    }

    /**
     * TODO: Devuelve una cadena con información completa del porte
     * @return ejemplo del formato -> "Porte PM0066 de Gaia Galactic Terminal(GGT) M5 (01/01/2023 08:15:00) a
     *  Cidonia(CID) M1 (01/01/2024 11:00:05) en Planet Express One(EP-245732X) por 13424,56 SSD, huecos libres: 10"
     */
    public String toString() {
        return "Porte "+getID()+" de "+getOrigen().toStringSimple()+" M"+getMuelleOrigen()+" ("+getSalida()+") a "
                +getDestino().toStringSimple()+" M"+getMuelleDestino()+" ("+getLlegada()+") en "+getNave().toStringSimple()+" por "
                +getPrecio()+" SSD, huecos libres: "+numHuecosLibres();
    }

    /**
     * TODO: Devuelve una cadena con información abreviada del vuelo
     * @return ejemplo del formato -> "Porte PM0066 de GGT M5 (01/01/2023 08:15:00) a CID M1 (01/01/2024 11:00:05)"
     */
    public String toStringSimple() {
        return "Porte "+getID()+" de "+getOrigen().getCodigo()+" M"+getMuelleOrigen()+" ("+getSalida()+") a "+getDestino().getCodigo()+" M"+getMuelleDestino()+" ("+getLlegada()+")";
    }


    /**
     * TODO: Devuelve true si el código origen, destino y fecha son los mismos que el porte
     * @param codigoOrigen
     * @param codigoDestino
     * @param fecha
     * @return
     */
    public boolean coincide(String codigoOrigen, String codigoDestino, Fecha fecha) {
        return ((codigoOrigen.equals(this.getOrigen().getCodigo()))&&(codigoDestino.equals(this.getDestino().getCodigo()))&&(fecha==this.getLlegada()));
    }


    /**
     * TODO: Muestra la matriz de huecos del porte, ejemplo:
     *        A  B  C
     *      1[ ][ ][ ]
     *      2[X][X][X]
     *      3[ ][ ][ ]
     *      4[ ][X][ ]
     *     10[ ][ ][ ]
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
     * TODO: Devuelve true si ha podido escribir en un fichero la lista de envíos del porte, siguiendo las indicaciones
     *  del enunciado
     * @param fichero
     * @return
     */
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
     * TODO: Genera un ID de porte. Este consistirá en una cadena de 6 caracteres, de los cuales los dos primeros
     *  serán PM y los 4 siguientes serán números aleatorios.
     *  NOTA: Usar el objeto rand pasado como argumento para la parte aleatoria.
     * @param rand
     * @return ejemplo -> "PM0123"
     */
    public static String generarID(Random rand) {
        String id="PM";
        for (int i=1;i<=4;i++){id=id.concat(Integer.toString(rand.nextInt(9)));}
        return id;
    }

    /**
     * TODO: Crea y devuelve un objeto Porte de los datos que selecciona el usuario de puertos espaciales
     *  y naves y la restricción de que no puede estar repetido el identificador, siguiendo las indicaciones
     *  del enunciado.
     *  La función solicita repetidamente los parametros hasta que sean correctos
     * @param teclado
     * @param rand
     * @param puertosEspaciales
     * @param naves
     * @param portes
     * @return
     */

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