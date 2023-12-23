import java.io.*;
import java.util.Random;
import java.util.Scanner;

/**
 * Envío es una clase que tiene todos los datos para identificar los envíos y ver donde se sitúan.
 * Además, tiene la información del porte al que pertenece y del cliente que lo contrata.
 *
 * @author Raúl Fernández Iglesias.
 * @author Noel López Losada.
 * @version     1.0
 */
public class Envio {

    /**
     * Código que identifica al envío.
     */
    private String localizador;
    /**
     * Porte al que pertenece el envío.
     */
    private Porte porte;
    /**
     * Cliente que ha contratado el envío.
     */
    private Cliente cliente;
    /**
     * Fila en la que se encuentra el envío dentro de la nave.
     */
    private int fila;
    /**
     * Columna en la que se encuentra el envío dentro de la nave.
     */
    private int columna;
    /**
     * Precio del envío.
     */
    private double precio;

    /**
     * Constructor de la clase que crea un envío con los parámetros que se le da para saber donde está ubicado,
     * su dueño y como localizarlo.
     *
     * @param localizador Localizador que identifica al envío.
     * @param porte Porte al que pertenece el envío.
     * @param cliente Cliente que ha contratado el envío.
     * @param fila Fila en la que se encuentra el envío dentro de la nave.
     * @param columna Columna en la que se encuentra el envío dentro de la nave.
     * @param precio Precio del envío.
     */
    public Envio(String localizador, Porte porte, Cliente cliente, int fila, int columna, double precio) {
        this.localizador = localizador;
        this.porte = porte;
        this.cliente = cliente;
        this.fila = fila;
        this.columna = columna;
        this.precio = precio;
    }

    /**
     * Getter del atributo localizador.
     * @return El localizador del envío.
     */
    public String getLocalizador() {
        return localizador;
    }

    /**
     * Getter del atributo porte.
     * @return El porte del envío.
     */
    public Porte getPorte() {
        return porte;
    }

    /**
     * Getter del atributo cliente.
     * @return El cliente del envío.
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Getter del atributo fila.
     * @return La fila del envío.
     */
    public int getFila() {
        return fila;
    }

    /**
     * Getter del atributo columna.
     * @return La columna del envío.
     */
    public int getColumna() {
        return columna;
    }

    /**
     * Método que indica donde se ubica un envío.
     * @return La ubicación del envío con la fila como número y la columna como letra.
     */
    // TODO: Ejemplos: "1A" para el hueco con fila 1 y columna 1, "3D" para el hueco con fila 3 y columna 4
    public String getHueco() {
        return String.format("%d%c",(getFila()),((char)(64+getColumna())));
    }

    /**
     * Getter del atributo precio.
     * @return Precio del envío.
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Método que devuelve un string con los datos del envío.
     * @return String con el formato indicado.
     */
    //TODO: Texto que debe generar: Envío PM1111AAAABBBBC para Porte PM0066 de GGT M5 (01/01/2023 08:15:00) a CID M1 (01/01/2024 11:00:05) en hueco 6C por 13424,56 SSD
    public String toString() {
        return String.format("Envío %s para %s en hueco %s por %f SSD",getLocalizador(),getPorte().toStringSimple(),getHueco(),getPrecio());
    }

    /**
     * @return String con los datos del envio en formato CSV
     */
    public String toStringCSV(){
        return getLocalizador()+";"+getPorte().getID()+";"+getCliente().getEmail()+";"+getFila()+";"+getColumna()+";"+getPrecio();
    }

    /**
     * Booleano que indica si se ha eliminado correctamente un envío del porte y del cliente que le corresponden.
     * @return True si se elimina correctamente, false si hay algún error.
     */
    // TODO: Cancela este envío, eliminándolo de la lista de envíos del porte y del cliente correspondiente
    public boolean cancelar() {
        return getPorte().desocuparHueco(getLocalizador()) && getCliente().cancelarEnvio(getLocalizador());
    }

    /**
     * Método que genera una factura en un fichero con el formato dado.
     * @param fichero Fichero en el que se genra la factura.
     * @return Devuelve la información con el siguiente formato como ejemplo ->
     *     -----------------------------------------------------
     *     --------- Factura del envío PM1111AAAABBBBC ---------
     *     -----------------------------------------------------
     *     Porte: PM0066
     *     Origen: Gaia Galactic Terminal (GGT) M5
     *     Destino: Cidonia (CID) M1
     *     Salida: 01/01/2023 08:15:00
     *     Llegada: 01/01/2024 11:00:05
     *     Cliente: Zapp Brannigan, zapp.brannigan@dop.gov
     *     Hueco: 6C
     *     Precio: 13424,56 SSD
     */
    //TODO: Método para imprimir la información de este envío en un fichero que respecta el formato descrito en el
    // enunciado
    public boolean generarFactura(String fichero) {
        PrintWriter out = null;
        boolean correcto;
        try {
            out = new PrintWriter(new FileWriter(fichero,false));
            out.print(
                    "-----------------------------------------------------\n"+
                        "-----------Factura del envío "+getLocalizador()+"----------\n"+
                        "-----------------------------------------------------\n"+
                        "\nPorte: "+getPorte().getID()+
                        "\nOrigen: "+getPorte().getOrigen().toStringSimple()+
                        "\nDestino: "+getPorte().getDestino().toStringSimple()+
                        "\nSalida: "+getPorte().getSalida()+
                        "\nLlegada: "+getPorte().getLlegada()+
                        "\nPasajero: "+getCliente().toString()+
                        "\nAsiento: "+getHueco()+
                        "\nPrecio: "+getPrecio()+" SSD"+
                        "\n"
            );
            correcto = true;
        }catch (FileNotFoundException e){
            System.out.println("Fichero "+fichero+" no encontrado");
            correcto = false;
        }
        catch (Exception ex){
            System.out.println("Error al generar la factura");
            correcto = false;
        }
        finally {
            try {
                if (out != null) {
                    out.flush();
                    out.close();
                }
            }catch (Exception ex){
                System.out.println("Error al cerrar el fichero.");}
        }
        return correcto;
    }



    /**
     * Método que genera un nuevo localizador para cada envío, que consiste en el id del porte al que pertenece
     * y 9 letras mayúsculas aleatorias.
     * @param rand Random que elige las letras aleatorias.
     * @param idPorte ID del porte al que pertenece el envío.
     * @return Localizador para el envío.
     */
    //TODO: Genera un localizador de envío. Este consistirá en una cadena de 15 caracteres, de los cuales los seis
    // primeros será el ID del porte asociado y los 9 siguientes serán letras mayúsculas aleatorias. Ejemplo: PM0123ABCD
    // NOTA: Usar el objeto rand pasado como argumento para la parte aleatoria.
    public static String generarLocalizador(Random rand, String idPorte) {
        StringBuilder localizador = new StringBuilder(idPorte);
        for (int i=1;i<=9;i++){
            localizador.append((char)(65+rand.nextInt(25)));
        }
        return localizador.toString();
    }


    /**
     * Método que da de alta un nuevo envío en un porte y un cliente dados,
     * Se solicitan los datos del usuario hasta que son correctos.
     * @param teclado Scanner que le lo que introduce el usuario.
     * @param rand Random para el localizador del envío.
     * @param porte Porte en el que se incluye al envío.
     * @param cliente CLiente que ha contratado el envío.
     * @return Envio para el porte y cliente especificados
     */
    //TODO: Método para crear un nuevo envío para un porte y cliente específico, pidiendo por teclado los datos
    // necesarios al usuario en el orden y con los textos indicados en los ejemplos de ejecución del enunciado
    // La función solicita repetidamente los parámetros hasta que sean correctos
    public static Envio altaEnvio(Scanner teclado, Random rand, Porte porte, Cliente cliente) {
        int fila, columnaInt;
        char columna;
        double precio;
        Envio nuevo = null;
        if ((!porte.porteLleno())){
            porte.imprimirMatrizHuecos();
            do {
                fila = (Utilidades.leerNumero(teclado,"Seleccione una fila: ",1,porte.getNave().getFilas()));
                columna = Utilidades.leerLetra(teclado,"Seleccione una columna: ",'A',((char)(64+porte.getNave().getColumnas())));
                columnaInt = (columna-64);
            } while (porte.huecoOcupado(fila, columnaInt));
            precio = Utilidades.leerNumero(teclado,"Precio del envio: ",0,Double.MAX_VALUE);
            nuevo = new Envio(Envio.generarLocalizador(rand,porte.getID()),porte,cliente,(fila),(columnaInt),precio);
            if (porte.ocuparHueco(nuevo)) System.out.println("    Envío "+nuevo.getLocalizador()+" creado correctamente ");
        }else {
            System.out.println("Porte lleno");
        }
        return nuevo;
    }
}