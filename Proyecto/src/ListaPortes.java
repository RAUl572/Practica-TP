import java.io.*;
import java.util.Scanner;

/**
 * ListaPortes es una clase que permite almacenar los portes que se usan de manera sencilla y
 * manejarlos adecuadamente.
 *
 * @author Raúl Fernández Iglesias.
 * @author Noel López Losada.
 * @version     1.0
 */
public class ListaPortes {
    /**
     * Lista que almacena los portes de la lista.
     */
    private Porte[] portes;
    /**
     * Número de portes que contiene la lista.
     */
    private int ocupacion;

    /**
     * Constructor de la clase que crea una lista de una capacidad determinada e inicia la ocupación a 0.
     * @param capacidad
     */
    //TODO: Constructor de la clase para inicializar la lista a una capacidad determinada
    public ListaPortes(int capacidad) {
        portes = new Porte[capacidad];
        ocupacion = 0;
    }

    /**
     * Getter del atributo ocupación.
     * @return El valor de la ocupación.
     */
    // TODO: Devuelve el número de portes que hay en la lista
    public int getOcupacion() {
        return ocupacion;
    }

    /**
     * Valor booleano que comprueba si la lista está llena.
     * @return True si el último espacio de la lista contiene un porte, false en cualquier otro caso.
     */
    // TODO: ¿Está llena la lista?
    public boolean estaLlena() {
        return  portes[portes.length-1]!=null;
    }

    /**
     * Método que busca un porte en la lista dado su índice.
     * @param i Índice del porte en la lista.
     * @return Porte que corresponde al índice.
     */
	//TODO: devuelve un porte dado un indice
    public Porte getPorte(int i) {
        return portes[i];
    }


    /**
     * Método que inserta un porte en la lista y devuelve si se ha completado el proceso.
     * @param porte Porte que se va a insertar.
     * @return False en caso de estar llena la lista o de error
     */
    //TODO: Devuelve true si puede insertar el porte
    public boolean insertarPorte(Porte porte) {
        boolean resul = false;
        if ((porte!=null)&&(!estaLlena())){portes[getOcupacion()]=porte;ocupacion++;resul = true;}
        return resul;
    }


    /**
     * Método que busca un porte en la lista con el identificador.
     * @param id ID del porte que se busca.
     * @return el objeto Porte que encontramos o null si no existe
     */
    //TODO: Devuelve el objeto Porte que tenga el identificador igual al parámetro id
    public Porte buscarPorte(String id) {
        Porte porte=null;
        for (int i = 0;i<getOcupacion();i++){
            if (portes[i].getID().equals(id)){porte = portes[i];}
        }
        return porte;
    }

    /**
     * Método que seleccionan los portes que van de un puerto a otro en una determinada fecha.
     * @param codigoOrigen Código del puerto del que sale el porte.
     * @param codigoDestino Código del puerto al que llega el porte.
     * @param fecha Fecha en la que se realiza el viaje.
     * @return Lista que contiene los portes que se mueven ese día entre esos portes.
     */
    //TODO: Devuelve un nuevo objeto ListaPortes conteniendo los Portes que vayan de un puerto espacial a otro
    // en una determinada fecha
    public ListaPortes buscarPortes(String codigoOrigen, String codigoDestino, Fecha fecha) {
        ListaPortes listaPortes=new ListaPortes(this.getOcupacion());
        for (int i = 0;i<getOcupacion();i++){
            if ((portes[i].getOrigen().getCodigo().equals(codigoOrigen))&&
                    (portes[i].getDestino().getCodigo().equals(codigoDestino))&&
                    (portes[i].getSalida().coincide(fecha)))
            {listaPortes.insertarPorte(portes[i]);}
        }
        if (listaPortes.getOcupacion()==0){listaPortes = null;}
        return listaPortes;
    }

    /**
     * Método que imprime los portes de la lista por pantalla.
     */
    //TODO: Muestra por pantalla los Portes siguiendo el formato de los ejemplos del enunciado
    public void listarPortes() {
        for (int i=0;i<getOcupacion();i++) {System.out.println(portes[i].toStringSimple());}
    }


    /**
     * Método que busca un porte dado un id que introduce el usuario hasta que se cancela la acción o se encuentra.
     * @param teclado Scanner que lee lo que escribe el usuario.
     * @param mensaje Mensaje con instrucciones que se le muestra al usuario.
     * @param cancelar Mensaje que si se introduce se cancela la acción.
     * @return El porte que se selecciona o nulo si se cancela la acción.
     */
    //TODO: Permite seleccionar un Porte existente a partir de su ID, usando el mensaje pasado como argumento para
    // la solicitud y siguiendo el orden y los textos mostrados en el enunciado, y usando la cadena cancelar para
    // salir devolviendo null.
    // La función solicita repetidamente hasta que se introduzca un ID correcto
    public Porte seleccionarPorte(Scanner teclado, String mensaje, String cancelar) {
        listarPortes();
        Porte porte=null;
        String id;
        boolean encontado = false;
        while (!encontado) {
            id = Utilidades.leerCadena(teclado, mensaje);
            porte = buscarPorte(id);
            if (id.equalsIgnoreCase(cancelar)) {
                encontado = true;
            } else if (porte == null) {
                System.out.println("    Id de porte incorrecto");
            }else {
                encontado = true;
            }
        }
        return porte;
    }

    /**
     * Método que guarda la lista escribiéndola en un fichero con formato CSV.
     * @param fichero Fichero en el que se guarda la lista.
     * @return True si se escribe en el fichero correctamente, false si hay algún error.
     */
    //TODO: Ha de escribir la lista de Portes en la ruta y nombre del fichero pasado como parámetro.
    // Si existe el fichero, SE SOBREESCRIBE, si no existe se crea.
    public boolean escribirPortesCsv(String fichero) {
        BufferedWriter out = null;
        boolean correcto;
        try {
            out = new BufferedWriter(new FileWriter(fichero));
            for (int i=0;i<getOcupacion();i++) {
                Porte aux = portes[i];
                out.write(String.format("%s;%s;%s;%d;%s;%s;%d;%s;%s\n",
                        aux.getID(),aux.getNave().getMatricula(),aux.getOrigen().getCodigo(),aux.getMuelleOrigen(),
                        aux.getSalida().toString(),aux.getDestino().getCodigo(),aux.getMuelleDestino(),
                        aux.getLlegada().toString(),String.valueOf(aux.getPrecio()).replace(',','.'))
                );
            }
            correcto = true;
        } catch (FileNotFoundException e) {
            System.out.println("No se ha encontrado el fichero "+fichero);
            correcto = false;
        }catch (IOException ex){
            System.out.println("Error de escritura en el fichero.");
            correcto = false;
        }finally {
            try{
                if (out!=null) {
                    out.flush();
                    out.close();
                }
            }catch (Exception e){
                System.out.println("Error de cierre de fichero");
            }
        }
        return correcto;
    }

    /**
     * Método que lee un fichero CSV y genera una lista de portes a partir de su contenido.
     * @param fichero Fichero que se lee.
     * @param capacidad Número de puertos del fichero.
     * @param puertosEspaciales Lista de los puertos entre los que se mueven los portes.
     * @param naves Lista de las naves que llevan los portes.
     * @return Lista de portes con los portes que se han leído.
     */
    //TODO: Genera una lista de Portes a partir del fichero CSV, usando los límites especificados como argumentos para
    // la capacidad de la lista
    public static ListaPortes leerPortesCsv(String fichero, int capacidad, ListaPuertosEspaciales puertosEspaciales, ListaNaves naves) {
        ListaPortes listaPortes = null;
        BufferedReader in=null;
        try {
            in = new BufferedReader(new FileReader(fichero));
            listaPortes = new ListaPortes(capacidad);
            String[] porte;
            String linea;
            while((linea = in.readLine())!=null){
                porte = linea.split(";");
                listaPortes.insertarPorte(new Porte(
                                            porte[0],
                                            naves.buscarNave(porte[1]),
                                            puertosEspaciales.buscarPuertoEspacial(porte[2]),
                                            Integer.parseInt(porte[3]),
                                            Fecha.fromString(porte[4]),
                                            puertosEspaciales.buscarPuertoEspacial(porte[5]),
                                            Integer.parseInt(porte[6]),
                                            Fecha.fromString(porte[7]),
                                            Double.parseDouble(porte[8])
                ));
            }
        } catch (FileNotFoundException e) {
            System.out.println("No se ha encontrado el fichero "+fichero);
        }catch (Exception e) {
            System.out.println("Error de lectura");
            listaPortes = null;
        }finally {
            try{
                if (in!=null) {
                    in.close();
                }
            }
            catch (Exception e){
                System.out.println("Error de cierre de fichero");
            }
        }
        return listaPortes;
    }
}
