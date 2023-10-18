import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class PR132main {
    public static void main(String[] args) {
        //Instanciamos los objetos de personas
        PR132persona persona1 = new PR132persona("Maria","Lopez",36);
        PR132persona persona2 = new PR132persona("Gustavo","Ponts",63);
        PR132persona persona3 = new PR132persona("Irene","Sales",54);
        //Declaramos la variable string donde se encontrará nuestro archivo
        String path = "Codi/Project/src/myFiles/PR132people.dat";
        try {
            //abrimos un bloque try ya que el FileOutPutStream lo requiere:
            FileOutputStream fos = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            //Añadimos los objetos dentro del archivo.
            oos.writeObject(persona1);
            oos.writeObject(persona2);
            oos.writeObject(persona3);
            //cerramos los archivos
            oos.close();
            fos.close();
        } catch (Exception e){
            e.printStackTrace();
        }

        try {
            //realizamos el importe de datos
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis);
            while (true) {
                //printeamos todos los objetos
                PR132persona leido = (PR132persona) ois.readObject();
                System.out.println(leido.toString());
            }
        }catch(java.io.EOFException e){
            //como está dentro de un bucle infinito nos dará un error de este tipo que nos permitirá parar del programa.
            System.out.println("\nExportado con exito!\n ");
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        
    }
}
