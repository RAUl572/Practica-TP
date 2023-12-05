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
        DataOutputStream out = null;
        try {
            out = new DataOutputStream(new FileOutputStream(fichero,false));
            out.writeUTF(
                    "-----------------------------------------------------\n"+
                        "--------- Factura del envío"+getLocalizador()+"--------\n-"+
                        "-----------------------------------------------------\n"+
                        "\nPorte: "+getPorte().getID()+
                        "\nOrigen: "+getPorte().getMuelleOrigen()+
                        "\nDestino: "+getPorte().getMuelleDestino()+
                        "\nSalida: "+getPorte().getSalida()+
                        "\nLlegada: "+getPorte().getLlegada()+
                        "\nPasajero: "+getCliente().toString()+
                        "\ntipo de billete: "+
                        "\nAsiento: "+getHueco()+
                        "\nPrecio: "+getPrecio()+
                        "\n"
            );
        }catch (FileNotFoundException e){System.out.println(e.getMessage());return false;}
        catch (IOException ex){System.out.println(ex.getMessage());}
        finally {
            try {
                if (out != null) {
                    out.flush();
                    out.close();
                }
            }catch (IOException ex){System.out.println(ex.getMessage());}
        }
        return true;
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
        boolean repetir;
        int x,fila, columna;
        try {
            do {
                repetir=false;
                x = Utilidades.leerNumero(teclado,"Contaratar envío como cliente ya registrado (1) o como nuevo cliente (2): ",1,2);
                //Apañar ListaClientes
                if (x==2){
                    cliente = Cliente.altaCliente(teclado,new ListaClientes(1),((porte.getNave().getFilas())*(porte.getNave().getColumnas())));
                }
                if ((!porte.porteLleno())){
                    porte.imprimirMatrizHuecos();
                    do {
                        fila = Utilidades.leerNumero(teclado,"Seleccione una fila: ",1,porte.getNave().getFilas());
                        columna = Utilidades.leerNumero(teclado,"Seleccione una columna: ",1,porte.getNave().getColumnas());
                    } while (porte.huecoOcupado(fila, columna));
                    return new Envio(Envio.generarLocalizador(rand,porte.getID()),porte,cliente,fila,columna,porte.getPrecio());
                }else {System.out.println("Porte lleno");return null;}
            }while (repetir);
        }catch(IOException ex){repetir = true;}
        return null;
    }
}