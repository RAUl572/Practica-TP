import java.util.Scanner;

/**
 * Description of the class
 *
 * @author
 * @author
 * @version     1.0
 */
public class Utilidades {

    /**
     * TODO: Solicita un número repetidamente hasta que se introduzca uno correcto (dentro de los límites)
     * @param teclado nombre del Scanner que recibe el método
     * @param mensaje mensaje que se muestra por pantalla e indica al usuario los datos que se necesitan
     * @param minimo número más pequeño que se puede recibir
     * @param maximo número más grande que se puede recibir
     * @return int numero valor que se ha introducido y cumple los límites puestos
     */
    public static int leerNumero(Scanner teclado, String mensaje, int minimo, int maximo) {
        int numero;
        System.out.println(mensaje);
        do {
            System.out.print("Introduce un número menor que "+ maximo+ " y mayor que "+minimo+": ");
            numero = teclado.nextInt();
            if (numero<minimo || numero>maximo){
                System.out.println("Numero incorrecto");
            }
        }while (numero<minimo || numero>maximo);

        return numero;
    }

    /**
     * TODO: Solicita un número repetidamente hasta que se introduzca uno correcto (dentro de los límites)
     * @param teclado nombre del Scanner que recibe el método
     * @param mensaje mensaje que se muestra por pantalla e indica al usuario los datos que se necesitan
     * @param minimo número más pequeño que se puede recibir
     * @param maximo número más grande que se puede recibir
     * @return long numero valor que se ha introducido y cumple los límites puestos
     */
    public static long leerNumero(Scanner teclado, String mensaje, long minimo, long maximo) {
        long numero;
        System.out.println(mensaje);
        do {
            System.out.println("Introduce un número menor que "+ maximo+ " y mayor que "+minimo+": ");
            numero = teclado.nextLong();
            if (numero<minimo || numero>maximo){
                System.out.println("Numero incorrecto");
            }
        }while (numero<minimo || numero>maximo);
        return numero;
    }

    /**
     * TODO: Solicita un número repetidamente hasta que se introduzca uno correcto (dentro de los límites)
     * @param teclado nombre del Scanner que recibe el método
     * @param mensaje mensaje que se muestra por pantalla e indica al usuario los datos que se necesitan
     * @param minimo número más pequeño que se puede recibir
     * @param maximo número más grande que se puede recibir
     * @return double numero valor que se ha introducido y cumple los límites puestos
     */
    public static double leerNumero(Scanner teclado, String mensaje, double minimo, double maximo) {
        double numero;
        System.out.println(mensaje);
        do {
            System.out.println("Introduce un número menor que "+ maximo+ " y mayor que "+minimo+": ");
            numero = teclado.nextDouble();
            if (numero<minimo || numero>maximo){
                System.out.println("Numero incorrecto");
            }
        }while (numero<minimo || numero>maximo);
        return numero;
    }

    /**
     * TODO: TODO: Solicita una letra repetidamente hasta que se introduzca uno correcto (dentro de los límites)
     * @param teclado nombre del Scanner que recibe el método
     * @param mensaje mensaje que se muestra por pantalla e indica al usuario los datos que se necesitan
     * @param minimo caracter mínimo(en valor de la tabla ASCII) que se puede introducir
     * @param maximo caracter máximo(en valor de la tabla ASCII) que se puede introducir
     * @return char letra caracter que se ha introducido y cumple los límites puestos
     */
    public static char leerLetra(Scanner teclado, String mensaje, char minimo, char maximo) {
        char letra;
        System.out.println(mensaje);
        do {
            System.out.println("Introduce un caracter entre "+ maximo + " y "+minimo+": ");
            letra = teclado.next().charAt(0);
            if (letra<minimo || letra>maximo){
                System.out.println("Caracter incorrecto");
            }
        }while (letra<minimo || letra>maximo);
        return letra;
    }


    /**
     * TODO: Solicita una fecha repetidamente hasta que se introduzca una correcta
     * @param teclado nombre del Scanner que recibe el método
     * @param mensaje mensaje que se muestra por pantalla e indica al usuario los datos que se necesitan
     * @return Fecha nueva fecha correcta creada
     */
    public static Fecha leerFecha(Scanner teclado, String mensaje) {
        int dia=0;
        int mes=0;
        int anio=0;
        System.out.println(mensaje);
        do {
            while (dia<1 || dia> 31){
                System.out.print("Introduce el día: ");
                dia = teclado.nextInt();
            }
            while (mes<1 || mes> 12){
                System.out.print("Introduce el mes: ");
                mes = teclado.nextInt();
            }
            while (anio<1900){
                System.out.print("Introduce el año: ");
                anio = teclado.nextInt();
            }
            if (!Fecha.comprobarFecha(dia,mes,anio)){
                System.out.println("Fecha incorrecta ");
                dia = mes = anio = 0;
            }
        }while(!Fecha.comprobarFecha(dia,mes,anio));
        return new Fecha(dia, mes, anio);
    }


    /**
     * TODO: Solicita una fecha y hora repetidamente hasta que se introduzcan unas correctas
     * @param teclado nombre del Scanner que recibe el método
     * @param mensaje mensaje que se muestra por pantalla e indica al usuario los datos que se necesitan
     * @return Fecha nueva fecha con hora correcta
     */
    public static Fecha leerFechaHora(Scanner teclado, String mensaje) {
        int dia=0;
        int mes=0;
        int anio=0;
        int hora;
        int minuto;
        int segundo;
        System.out.println(mensaje);
        do {
            while (dia<1 || dia> 31){
                System.out.print("Introduce el día: ");
                dia = teclado.nextInt();
            }
            while (mes<1 || mes> 12){
                System.out.print("Introduce el mes: ");
                mes = teclado.nextInt();
            }
            while (anio<1900){
                System.out.print("Introduce el año: ");
                anio = teclado.nextInt();
            }
            if (!Fecha.comprobarFecha(dia,mes,anio)){
                System.out.println("Fecha incorrecta ");
                dia = mes = anio = 0;
            }
        }while(!Fecha.comprobarFecha(dia,mes,anio));
        do {
            System.out.print("Introduce la hora: ");
            hora = teclado.nextInt();
            System.out.print("Introduce el minuto: ");
            minuto = teclado.nextInt();
            System.out.print("Introduce los segundos: ");
            segundo = teclado.nextInt();
            if (!Fecha.comprobarHora(hora,minuto,segundo)){
                System.out.println("Hora incorrecta ");
            }
        }while(!Fecha.comprobarHora(hora,minuto,segundo));


        return new Fecha(dia, mes, anio, hora, minuto, segundo);
    }

    /**
     * TODO: Imprime por pantalla el String pasado por parámetro
     * @param teclado nombre del Scanner que recibe el método
     * @param s String que se debe mostrar
     * @return Cadena de que se recibe
     */
    public static String leerCadena(Scanner teclado, String s) {
        System.out.print(s);
        return teclado.next();
    }
}
