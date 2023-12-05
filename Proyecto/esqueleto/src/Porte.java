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
        numHuecos=0;
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
    public int numHuecosLibres() {return (huecos.length-numHuecos);}
    // TODO: ¿Están llenos todos los huecos?
    public boolean porteLleno() {return (huecos.length==numHuecos);}
    // TODO: ¿Está ocupado el hueco consultado?
    public boolean huecoOcupado(int fila, int columna) {
        return huecos[fila-1][columna-1];
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
        numHuecos++;
        return huecos[envio.getFila()][envio.getColumna()]=true;
    }


    /**
     * TODO: A través del localizador del envio, se desocupará el hueco
     * @param localizador
     * @return
     */
    public boolean desocuparHueco(String localizador) {
        return huecos[buscarEnvio(localizador).getFila()][buscarEnvio(localizador).getColumna()]=false;
    }

    /**
     * TODO: Devuelve una cadena con información completa del porte
     * @return ejemplo del formato -> "Porte PM0066 de Gaia Galactic Terminal(GGT) M5 (01/01/2023 08:15:00) a
     *  Cidonia(CID) M1 (01/01/2024 11:00:05) en Planet Express One(EP-245732X) por 13424,56 SSD, huecos libres: 10"
     */
    public String toString() {
        return "";
    }

    /**
     * TODO: Devuelve una cadena con información abreviada del vuelo
     * @return ejemplo del formato -> "Porte PM0066 de GGT M5 (01/01/2023 08:15:00) a CID M1 (01/01/2024 11:00:05)"
     */
    public String toStringSimple() {
        return ("Porte"+getID()+
                "de "
                );
    }


    /**
     * TODO: Devuelve true si el código origen, destino y fecha son los mismos que el porte
     * @param codigoOrigen
     * @param codigoDestino
     * @param fecha
     * @return
     */
    public boolean coincide(String codigoOrigen, String codigoDestino, Fecha fecha) {
        return ;
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
        System.out.print("  ");

    }

    /**
     * TODO: Devuelve true si ha podido escribir en un fichero la lista de envíos del porte, siguiendo las indicaciones
     *  del enunciado
     * @param fichero
     * @return
     */
    public boolean generarListaEnvios(String fichero) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(fichero);
            for (int i = 0;i< listaEnvios.getOcupacion();i++) {
                pw.write(listaEnvios.getEnvio(i).getLocalizador()+";"+
                        getID()+";" +
                        listaEnvios.getEnvio(i).getCliente().getEmail()+";"+
                        listaEnvios.getEnvio(i).getFila()+";"+
                        listaEnvios.getEnvio(i).getColumna()+";"+
                        listaEnvios.getEnvio(i).getPrecio()+"\n"
                );
            }
            return true;
        } catch (FileNotFoundException e) {
            return false;
        }finally {
                if (pw!=null){
                    pw.flush();
                    pw.close();
                }
        }
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
        String matricula, codigoOrigen, codigoDestino, muelle, terminal;
        boolean continuar;
        while(puertosEspaciales.buscarPuertoEspacial(codigoOrigen=Utilidades.leerCadena(teclado,"Ingrese código de puerto Origen:"))==null){
            System.out.println("Código de puerto no encontrado.");
        }
        Utilidades.leerNumero(teclado,"Ingrese el muelle de Origen (1 - 4):",1,4);
        while(puertosEspaciales.buscarPuertoEspacial(codigoDestino=Utilidades.leerCadena(teclado,"Ingrese código de puerto Destino:"))==null){
            System.out.println("Código de puerto no encontrado.");
        }
        Utilidades.leerNumero(teclado,"Ingrese Terminal Destino (1 - 6): ",1,6);
        do {
            continuar = false;
            matricula = Utilidades.leerCadena(teclado, "Ingrese matrícula de la nave: ");
            if (naves.buscarNave(matricula)==null){System.out.println("Matrícula de avión no encontrada");continuar = true;}
            if (naves.buscarNave(matricula).getAlcance()<puertosEspaciales.buscarPuertoEspacial(codigoOrigen).distancia(puertosEspaciales.buscarPuertoEspacial(codigoDestino))){
                System.out.println("Avión seleccionado con alcance insuficiente.");
                continuar=true;
            }
        }while (continuar);
        //Falta inicializar fecha
        return = new Porte();
    }
}