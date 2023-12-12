import java.io.*;
import java.util.Scanner;

/**
 * Description of the class
 *
 * @author
 * @author
 * @version     1.0
 */
public class ListaPortes {
    private Porte[] portes;
    private int ocupacion;

    /**
     * TODO: Constructor de la clase para inicializar la lista a una capacidad determinada
     *
     * @param capacidad
     */
    public ListaPortes(int capacidad) {
        portes = new Porte[capacidad];
        ocupacion = 0;
    }
    // TODO: Devuelve el número de portes que hay en la lista
    public int getOcupacion() {
        return ocupacion;
    }
    // TODO: ¿Está llena la lista?
    public boolean estaLlena() {
        return  portes[portes.length-1]!=null;
    }

	//TODO: devuelve un porte dado un indice
    public Porte getPorte(int i) {
        return portes[i];
    }


    /**
     * TODO: Devuelve true si puede insertar el porte
     * @param porte
     * @return false en caso de estar llena la lista o de error
     */
    public boolean insertarPorte(Porte porte) {
        if (!estaLlena()){portes[getOcupacion()-1]=porte;ocupacion++;return true;}
        return false;
    }


    /**
     * TODO: Devuelve el objeto Porte que tenga el identificador igual al parámetro id
     * @param id
     * @return el objeto Porte que encontramos o null si no existe
     */
    public Porte buscarPorte(String id) {
        for (int i = 0;i<getOcupacion();i++){
            if (portes[i].getID().equals(id)){return portes[i];}
        }
        return null;
    }

    /**
     * TODO: Devuelve un nuevo objeto ListaPortes conteniendo los Portes que vayan de un puerto espacial a otro
     *  en una determinada fecha
     * @param codigoOrigen
     * @param codigoDestino
     * @param fecha
     * @return
     */
    public ListaPortes buscarPortes(String codigoOrigen, String codigoDestino, Fecha fecha) {
        ListaPortes listaPortes=new ListaPortes(this.getOcupacion());
        for (int i = 0;i<getOcupacion();i++){
            if ((portes[i].getOrigen().getCodigo().equals(codigoOrigen))&&
                    (portes[i].getDestino().getCodigo().equals(codigoDestino))&&
                    (portes[i].getLlegada()==fecha))
            {listaPortes.insertarPorte(portes[i]);}
        }
        return listaPortes;
    }

    /**
     * TODO: Muestra por pantalla los Portes siguiendo el formato de los ejemplos del enunciado
     */
    public void listarPortes() {
        for (int i=0;i<getOcupacion();i++) {System.out.println(portes[i].toString());}
    }


    /**
     * TODO: Permite seleccionar un Porte existente a partir de su ID, usando el mensaje pasado como argumento para
     *  la solicitud y siguiendo el orden y los textos mostrados en el enunciado, y usando la cadena cancelar para
     *  salir devolviendo null.
     *  La función solicita repetidamente hasta que se introduzca un ID correcto
     * @param teclado
     * @param mensaje
     * @param cancelar
     * @return
     */
    public Porte seleccionarPorte(Scanner teclado, String mensaje, String cancelar) {
        listarPortes();
        Porte porte;
        String id;
        if ((id = Utilidades.leerCadena(teclado,mensaje)).equals((porte = buscarPorte(id)).getID())){
            return porte;
        } else if ((id.equals(cancelar))){
            return null;
        }
        return porte;
    }

    /**
     * TODO: Ha de escribir la lista de Portes en la ruta y nombre del fichero pasado como parámetro.
     *  Si existe el fichero, SE SOBREESCRIBE, si no existe se crea.
     * @param fichero
     * @return
     */
    public boolean escribirPortesCsv(String fichero) {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new FileWriter(fichero));
            for (int i=0;i<getOcupacion();i++) {
                Porte aux = portes[i];
                out.write(String.format("%s;%s;%s;%d;%s;%s;%d;%s;%f",
                        aux.getID(),aux.getNave().getMatricula(),aux.getOrigen().getCodigo(),aux.getMuelleOrigen(),
                        aux.getSalida().toString(),aux.getDestino().getCodigo(),aux.getMuelleDestino(),
                        aux.getLlegada().toString(),aux.getPrecio())
                );
            }
            return true;
        } catch (FileNotFoundException e) {
            return false;
        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }finally {
            try{if (out!=null){out.flush();out.close();}}
            catch(IOException ex){System.out.println(ex.getMessage());}
        }
        return false;
    }

    /**
     * TODO: Genera una lista de Portes a partir del fichero CSV, usando los límites especificados como argumentos para
     *  la capacidad de la lista
     * @param fichero
     * @param capacidad
     * @param puertosEspaciales
     * @param naves
     * @return
     */
    public static ListaPortes leerPortesCsv(String fichero, int capacidad, ListaPuertosEspaciales puertosEspaciales, ListaNaves naves) {
        ListaPortes listaPortes = new ListaPortes(capacidad);
        BufferedReader in=null;
        try {
            in = new BufferedReader(new FileReader(fichero));
            String[] porte;
            while(in.readLine()==null){
                porte = in.readLine().split(";");
                listaPortes.insertarPorte(new Porte(
                                            porte[0],
                                            naves.buscarNave(porte[1]),
                                            puertosEspaciales.buscarPuertoEspacial(porte[2]),
                                            Integer.parseInt(porte[3]),
                                            Fecha.fromString(porte[4]),
                                            puertosEspaciales.buscarPuertoEspacial(porte[5]),
                                            Integer.parseInt(porte[6]),
                                            Fecha.fromString(porte[7]),
                                            Double.parseDouble(porte[8]))
                );
            }
        } catch (Exception e) {
            return null;
        }finally {
            try{if (in!=null){in.close();}}
            catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return listaPortes;
    }
}
