import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class PR131mainEscriu {
    public static void main(String[] args) {

        PR131hashmap hash_a_guardar = new PR131hashmap();

        try {
            //Creamos el archivo donde guardaremos nuestro objeto.
            FileOutputStream fichero_donde_guardar = new FileOutputStream("PR1.2-RobertoZambranoAcces_a_dades\\Codi\\Project\\src\\myFiles\\PR131HashMapData.ser");
            //Creamos por asi decirlo un lector, donde nos otorgará unas herramientas para abrir este archivo y guardarnos el objeto.
            ObjectOutputStream lector_fichero_objeto = new ObjectOutputStream(fichero_donde_guardar);
            //con el objeto que recibe el archivo para escribirlo, usamos el metodo writeObject para que guarde el hash.
            lector_fichero_objeto.writeObject(hash_a_guardar);
            System.out.println("Creat");
            lector_fichero_objeto.close();
            fichero_donde_guardar.close();
        } catch (Exception e) {
            // TODO: handle exception
        }

    }
}
