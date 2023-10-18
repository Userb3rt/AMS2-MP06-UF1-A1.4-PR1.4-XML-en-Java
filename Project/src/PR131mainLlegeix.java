import java.io.FileInputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;

public class PR131mainLlegeix {

    public static void main(String[] args) {
        
        FileInputStream fichero_a_buscar;
        try {
            //objeto que usamos para encontrar el archivo que tiene el objeto.
            fichero_a_buscar = new FileInputStream("D:\\Roberto\\Documents\\AMS2-MP06 Accés a dades 23-24\\PR1.2-RobertoZambrano\\PR1.2-RobertoZambranoAcces_a_dades\\Codi\\Project\\src\\myFiles\\PR131HashMapData.ser");
            //Por así decirlo es un lector de este archivo.
            ObjectInputStream lector = new ObjectInputStream(fichero_a_buscar);

            PR131hashmap hash = (PR131hashmap) lector.readObject();
            lector.close();
            fichero_a_buscar.close();
            System.out.println(hash);
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }
}
