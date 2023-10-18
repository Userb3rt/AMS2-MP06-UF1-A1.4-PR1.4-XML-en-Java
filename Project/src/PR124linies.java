import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Random;

public class PR124linies {
    public static void main(String[] args) {
        try {
            //Creamos el archivo o lo encontramos:
            File numeros_txt  =new File("Codi\\Project\\src\\myFiles\\numeros.txt");
            //Creamos el objeto para poder escribirlo y el escritor.
            FileWriter escritor;
            //si el archivo ya exite pues vamos añadir 10 lineas más y si no pues lo creamos desde el principio.
            if (!numeros_txt.exists()) {
                escritor = new FileWriter(numeros_txt, false);
            }else{
                escritor = new FileWriter(numeros_txt, true);
            }
            BufferedWriter bufferEscritura = new BufferedWriter(escritor);
            //creamos un string de numeros random para rellenarlos y luego escribirlos
            String numeros_random= "";
            //iteramos 10 veces para crear los 10 numeros aleatorios
            for (int i = 0; i<10;i++) {
                //Instanciamos un objeto random para luego crear el numero
                Random random = new Random();
                //creamos un numero del 1 al 100 y lo guardamos en num_random
                int num_random = random.nextInt(100)+1;
                //lo añadimos a la STRING
                numeros_random += num_random+"\n";
            }
            //Escribimos en el archivo
            bufferEscritura.write(numeros_random);
            //Cerramos BufferedWriter y FileWriter
            bufferEscritura.close();
            escritor.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
