/**
 * PuertoEspacial contiene los atributos que indican donde se sitúan los diferentes puertos desde los que salen y llegan las naves
 * de Planet Express con sus cargamentos. También incluye la identificación del puerto y el número de muelles de los que dispone.
 * Esta clase será útil para ver donde pueden parar las naves y las distancias que deben recorrer.
 *
 * @author Raúl Fernández Iglesias
 * @author Noel López Losada
 * @version     1.0
 */
public class PuertoEspacial {

    /**
     * Nombre del puerto espacial.
     */
    private String nombre;
    /**
     * Código identificativo del puerto espacial.
     */
    private String codigo;
    /**
     * Parte de las coordenadas esféricas del puerto espacial (el Sol es el origen de coordenadas).
     * Indica la distancia en UA entre el Sol y el perto.
     */
    private double radio;
    /**
     * Parte de las coordenadas esféricas del puerto espacial (el Sol es el origen de coordenadas).
     * El ángulo entre el eje x y la proyección del puerto en el plano xy.
     */
    private double azimut;
    /**
     * Parte de las coordenadas esféricas del puerto espacial (el Sol es el origen de coordenadas).
     * El ángulo entre el eje z y el puerto.
     */
    private double polar;
    /**
     * Número de muelles del puerto espacial.
     */
    private int numMuelles;

    /**
     * Constructor de la clase que pide todos los atributos para construir un objeto.
     *
     * @param nombre Nombre del puerto espacial.
     * @param codigo Código identificativo del puerto espacial.
     * @param radio Distancia del puerto con el Sol.
     * @param azimut Ángulo entre la proyección del puerto en plano xy y el eje x.
     * @param polar Ángulo entre el eje z y el puerto.
     * @param numMuelles Número de muelles del puerto.
     */
    public PuertoEspacial(String nombre, String codigo, double radio, double azimut, double polar, int numMuelles) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.radio = radio;
        this.azimut = azimut;
        this.polar = polar;
        this.numMuelles = numMuelles;
    }

    /**
     * Getter del atributo nombre.
     * @return Nombre del puerto espacial.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Getter del atributo codigo.
     * @return Código identificativo del puerto espacial
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Getter del atributo radio.
     * @return Distancia entre el Sol y el puerto espacial.
     */
    public double getRadio() {
        return radio;
    }

    /**
     * Getter del atributo azimut.
     * @return Ángulo entre el eje x y la proyección del puerto en el plano xy.
     */
    public double getAzimut() {
        return azimut;
    }

    /**
     * Getter del atributo polar.
     * @return Ángulo entre el eje z y el puerto.
     */
    public double getPolar() {
        return polar;
    }

    /**
     * Getter del atributo numMuelles.
     * @return Número de muelles del puerto espacial
     */
    public int getMuelles() {
        return numMuelles;
    }

    /**
     * Método que calcula la distancia entre 2 puertos espaciales.
     * @param destino Puerto espacial de destino con coordenadas cartesianas(x1,y1,z1).
     * @return Distancia(en UAs) entre el puerto actual con coordenadas cartesianas(x,y,z) y el puerto de destino.
     */
    //TODO: Método para calcular la distancia entre el puerto espacial que recibe el mensaje y el puerto
    // espacial "destino" siguiendo las ecuaciones del enunciado (Las formulas se encuentran en el enunciado)
    public double distancia(PuertoEspacial destino) {
        // TODO: Para calcular la distancia entre dos Puertos Espaciales, se transforman sus coordenadas esféricas a cartesianas
        double x,y,z,x1,y1,z1,distancia;
         x=radio * Math.sin(azimut) * Math.cos(polar);
         y=radio * Math.sin(azimut) * Math.sin(polar);
         z=radio * Math.cos(azimut);

         x1=destino.radio * Math.sin(destino.azimut) * Math.cos(destino.polar);
         y1=destino.radio * Math.sin(destino.azimut) * Math.sin(destino.polar);
         z1=destino.radio * Math.cos(destino.azimut);
        // TODO: Una vez se tienen las coordenadas cartesianas, basta con calcular la distancia euclídea entre ellas:
        distancia = Math.sqrt(Math.pow(x1-x,2)+Math.pow(y1-y,2)+Math.pow(z1-z,2));
        return distancia;
    }

    /**
     * Método que crea un String con todos los datos del puerto y con formato.
     * @return ejemplo -> "Gaia Galactic Terminal(GGT), en (1.0 90.0 0.0), con 8 muelles" (Radio, Azimut, Polar)
     */
    //TODO: Método que crea un String con los datos de un puerto espacial con el siguiente formato:
    public String toString() {
        return getNombre() + " (" + getCodigo() + "), en (" + getRadio() + " " + getAzimut() + " " + getPolar() + " " + "), con " + getMuelles() + " muelles";
    }

    /**
     * Crea un string con el formato que se usa en los ficheros .csv
     * @return ejemplor del formato: "Gaia Galactic Terminal;GGT;1.0;90.0;0.0;8"
     */
    public String toStringCsv() {
        return getNombre() + ";" + getCodigo() + ";" + getRadio() + ";" + getAzimut() + ";" + getPolar() + ";" + getMuelles();
    }
    /**
     * Método que crea un String con los datos del puerto simplificados.
     * @return ejemplo -> "Gaia Galactic Terminal (GGT)"
     */
    //TODO: Método que crea un String con los datos de un aeropuerto con el siguiente formato:
    public String toStringSimple() {
        return getNombre() + " (" + getCodigo() + ")";
    }
}
