import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class PR121Files {
    public static void main(String[] args) {
        //Creació de la carpeta myFiles
         
        File ficha = new File("D:\\rzajr\\Documents\\PR1.2-RobertoZambranoAcces_a_dades\\Codi\\Project\\src\\myFiles");
        ficha.mkdir();

        //Instanciem els 2 objectes Files 1 y 2 amb el pathname de la ruta on volem aquests arxius.
        File file1 = new File("D:\\rzajr\\Documents\\PR1.2-RobertoZambranoAcces_a_dades\\Codi\\Project\\src\\myFiles\\file1.txt");
        File file2 = new File("D:\\rzajr\\Documents\\PR1.2-RobertoZambranoAcces_a_dades\\Codi\\Project\\src\\myFiles\\file2.txt");
        try {
            //Creació dels 2 files.
            if (!file1.exists()) {file1.createNewFile();}
            if (!file2.exists()) {file2.createNewFile();}
        } catch (IOException e) {
            System.out.println(e);
        }

        //Rename del archiu file2.txt
        file2.renameTo(new File("Codi\\Project\\src\\myFiles\\renamedFile.txt"));
        
        mostrar_llistat(ficha);

        //Eliminació del "file1.txt"
        file1.delete();

        mostrar_llistat(ficha);
    }

    public static void mostrar_llistat(File ficha){
         //llistat dels arxius dins de la carpeta “myFiles”
        System.out.println("Els arxius de la carpeta són:");
        for (File ficheroEntrada : ficha.listFiles()) {
            System.out.println(ficheroEntrada.getName());
        }
    }
}
