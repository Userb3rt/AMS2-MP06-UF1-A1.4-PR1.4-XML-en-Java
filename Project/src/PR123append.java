import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class PR123append {
    public static void main(String[] args) {
        try {
            //Crea el archivo en la ruta de myFiles
            File fraseMatrix = new File("Codi\\Project\\src\\myFiles\\frasesMatrix.txt");
            //Habilitamos la escritura en nuestro archivo
            FileWriter escritor = new FileWriter(fraseMatrix, true);
            //Con este objeto tiene un metodo que nos permitira escribir dentro de nuestro archivo.
            BufferedWriter bufferEscritura = new BufferedWriter(escritor);
            //Le decimos lo que queremos escribir.
            bufferEscritura.write("Yo sólo puedo mostrarte la puerta\nTú eres quien la tiene que atravesar.\n");
            //Cerramos BufferedWriter y FileWriter
            bufferEscritura.close();
            escritor.close();
            System.out.println("Archivo se ha añadido con éxito.");
        } catch (Exception e) {
            //Si hay un error nos lo mostrará:
            System.out.println("Error al sobrescribir el archivo: " + e.getMessage());
        }
    }
}
