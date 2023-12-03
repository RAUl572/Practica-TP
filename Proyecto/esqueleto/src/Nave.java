/**
 * La clase Nave define la marca, modelo, matrícula, el alcance y el tamaño de la bodega de carga
 * de las naves que la flota de Planet Express que transportan los pedidos.
 *
 * @author Raúl Fernández Iglesias.
 * @author Noel López Losada.
 * @version     1.0
 */

public class Nave {
    /**
     * Marca de la nave.
     */
    private String marca;
    /**
     * Modelo de la nave.
     */
    private String modelo;
    /**
     * Matrícula de la nave.
     */
    private String matricula;
    /**
     * Columnas de la bodega de carga de la nave.
     */
    private int columnas;
    /**
     *Filas de la bodega de carga de la nave.
     */
    private int filas;
    /**
     * Alcance de la nave en UAs.
     */
    private double alcance;


    /**
     * Constructor de la clase que pide todos los atributos de la clase.
     *
     * @param marca Marca de la nave.
     * @param modelo Modelo de la nave.
     * @param matricula Matrícula de la nave.
     * @param columnas Columnas de la bodega de carga de la nave.
     * @param filas Filas de la bodega de carga de la nave.
     * @param alcance Alcance de la nave en UAs.
     */
    public Nave(String marca, String modelo, String matricula, int columnas, int filas, double alcance) {
        this.marca = marca;
        this.modelo = modelo;
        this.matricula = matricula;
        this.columnas = columnas;
        this.filas = filas;
        this.alcance = alcance;
    }

    /**
     * Getter del atributo marca.
     * @return Marca de la nave.
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Getter del atributo modelo.
     * @return Modelo de la nave.
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * Getter del atributo matricula.
     * @return Matrícula de la nave.
     */
    public String getMatricula() {
        return matricula;
    }

    /**
     * Getter del atributo columnas.
     * @return Columnas de la bodega de carga.
     */
    public int getColumnas() {
        return columnas;
    }

    /**
     * Getter del atributo filas.
     * @return Filas de la bodega de carga.
     */
    public int getFilas() {
        return filas;
    }

    /**
     * Getter del atributo alcance.
     * @return El alcance de la nave en UA.
     */
    public double getAlcance() {
        return alcance;
    }


    /**
     * TODO: Crea un String con los datos de una nave con el siguiente formato:
     * @return ejemplo del formato -> "Planet Express One (EP-245732X): 40 contenedores, hasta 1.57 UA"
     */
    public String toString() {
        return getMarca()+" "+getModelo()+" ("+getMatricula()+"): "+getColumnas()*getFilas()+" contenedores, hasta "+getAlcance()+" UA";
    }

    /**
     * @return
     */
    public String toStringCsv() {
        return getMarca()+";"+getModelo()+";"+getMatricula()+";"+getFilas()+";"+getColumnas()+";"+getAlcance();
    }


    /**
     * TODO: Crea un String con los datos de una nave con el siguiente formato:
     * @return ejemplo del formato -> "Planet Express One (EP-245732X)"
     */
    public String toStringSimple() {
        return getMarca()+" "+getModelo()+" ("+getMatricula()+") ";
    }




}
