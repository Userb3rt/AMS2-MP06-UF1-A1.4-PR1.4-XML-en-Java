import java.io.File;
import java.util.Scanner;

public class PR120ReadFile {
    public static void main(String[] args) {
        int linea_number = 1; //Este es el numero de linea que pondremos delante de la misma que luego sumaremos.
        File texto = new File("PR120ReadFile.java"); //abrimos o creamos si no existe un archivo.
        try {
            Scanner lector = new Scanner(texto); //instanciamos un Scanner con nombre lector, porque esa ser´su utilidad, la de leernos el texto
            // y luego poder iterar dentro de las lineas.
            while (lector.hasNextLine()) {
                System.out.println(linea_number+"   "+lector.nextLine());// printeamos el el resultado.
                linea_number++;// sumamos el numero de linea para que nos aparezca el siguiente valor a la siguiente iteración.
            }
            lector.close(); //cerramos el lector.
        } catch (Exception e) {
            System.out.println(e); //por si hay algun error me lo diría.
        }
    }
}
