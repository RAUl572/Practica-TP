import java.io.*;
import java.util.Random;
import java.util.Scanner;

/**
 * Description of the class
 *
 * @author Raúl Fernández Iglesias.
 * @author Noel López Losada.
 * @version     1.0
 */
public class Envio {

    private String localizador;
    private Porte porte;
    private Cliente cliente;
    private int fila;
    private int columna;
    private double precio;

    /**
     * Constructor of the class
     *
     * @param localizador
     * @param porte
     * @param cliente
     * @param fila
     * @param columna
     * @param precio
     */
    public Envio(String localizador, Porte porte, Cliente cliente, int fila, int columna, double precio) {
        this.localizador = localizador;
        this.porte = porte;
        this.cliente = cliente;
        this.fila = fila;
        this.columna = columna;
        this.precio = precio;
    }
    public String getLocalizador() {
        return localizador;
    }
    public Porte getPorte() {
        return porte;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public int getFila() {
        return fila;
    }
    public int getColumna() {
        return columna;
    }
    // TODO: Ejemplos: "1A" para el hueco con fila 1 y columna 1, "3D" para el hueco con fila 3 y columna 4
    public String getHueco() {
        return (Integer.toString(getFila())+(char)(64+getColumna()));
    }
    public double getPrecio() {
        return precio;
    }
    //TODO: Texto que debe generar: Envío PM1111AAAABBBBC para Porte PM0066 de GGT M5 (01/01/2023 08:15:00) a CID M1 (01/01/2024 11:00:05) en hueco 6C por 13424,56 SSD
    public String toString() {
        return ("Envío "+getLocalizador()+
                " para Porte "+getPorte().toStringSimple()
                //...
                );
    }

    /**
     * @return String con los datos del envio en formato CSV
     */
    public String toStringCSV(){
        return getLocalizador()+";"+getPorte().getID()+";"+getCliente().getEmail()+";"+getFila()+";"+getColumna()+";"+getPrecio();
    }
    // TODO: Cancela este envío, eliminándolo de la lista de envíos del porte y del cliente correspondiente
    public boolean cancelar() {
        return getPorte().desocuparHueco(getLocalizador()) && getCliente().cancelarEnvio(getLocalizador());
    }

    /**
     * TODO: Método para imprimir la información de este envío en un fichero que respecta el formato descrito en el
     *  enunciado
     * @param fichero
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
                        "\nOrigen: "+getPorte().getMuelleOrigen()+
                        "\nDestino: "+getPorte().getMuelleDestino()+
                        "\nSalida: "+getPorte().getSalida()+
                        "\nLlegada: "+getPorte().getLlegada()+
                        "\nPasajero: "+getCliente().toString()+
                        "\nAsiento: "+getHueco()+
                        "\nPrecio: "+getPrecio()+
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
     *	TODO: Genera un localizador de envío. Este consistirá en una cadena de 15 caracteres, de los cuales los seis
	 *   primeros será el ID del porte asociado y los 9 siguientes serán letras mayúsculas aleatorias. Ejemplo: PM0123ABCD
     *   NOTA: Usar el objeto rand pasado como argumento para la parte aleatoria.
     * @param rand
     * @param idPorte
     * @return
     */
    public static String generarLocalizador(Random rand, String idPorte) {
        StringBuilder localizador = new StringBuilder(idPorte);
        for (int i=1;i<=3;i++){
            localizador.append((char)(65+rand.nextInt(25)));
        }
        return localizador.toString();
    }


    /**
     * TODO: Método para crear un nuevo envío para un porte y cliente específico, pidiendo por teclado los datos
     *  necesarios al usuario en el orden y con los textos indicados en los ejemplos de ejecución del enunciado
     *  La función solicita repetidamente los parámetros hasta que sean correctos
     * @param teclado
     * @param rand
     * @param porte
     * @param cliente
     * @return Envio para el porte y cliente especificados
     */
    public static Envio altaEnvio(Scanner teclado, Random rand, Porte porte, Cliente cliente) {
        int fila, columna;
        Envio nuevo = null;
        if ((!porte.porteLleno())){
            porte.imprimirMatrizHuecos();
            do {
                fila = Utilidades.leerNumero(teclado,"Seleccione una fila: ",1,porte.getNave().getFilas());
                columna = Utilidades.leerLetra(teclado,"Seleccione una columna: ",'A',((char)(64+porte.getNave().getColumnas())));
            } while (porte.huecoOcupado(fila, (Integer.parseInt(String.valueOf(columna)))));
            nuevo = new Envio(Envio.generarLocalizador(rand,porte.getID()),porte,cliente,fila,(Integer.parseInt(String.valueOf(columna))),porte.getPrecio());
        }else {
            System.out.println("Porte lleno");
        }
        return nuevo;
    }
}