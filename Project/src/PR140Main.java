import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class PR140Main {

    public static void main(String[] args) {
        int longitud_tabulador = 15;
        String[] capsalera = {"Nom","Cognom","Edat","Ciutat"};
        DocumentBuilderFactory dbfaFactory = DocumentBuilderFactory.newInstance();
        try {
            //buscamos el fichero donde se encuentra nuesto xml.
            File xml = new File("Project/src/myFiles/persones.xml");
            //hacemos un document builder
            DocumentBuilder dBuilder = dbfaFactory.newDocumentBuilder();
            //parseamos el documento para que java lo pueda leer.
            Document doc = dBuilder.parse(xml);
            //quitamos los espacios.
            doc.getDocumentElement().normalize();
            //Cogemos los tag de persona y los enlistamos para poder gestionar los nodos.
            NodeList listaPersonas = doc.getElementsByTagName("persona");
            //Printeamos la cabecera.
            for (String string : capsalera) {
                System.err.print(string);
                for (int i = 0; i < longitud_tabulador-string.length(); i++) {
                    System.out.print(" ");
                }
            }
            //printeamos una linea para que no se nos mezcle la información de la lista de nodos.
            System.out.println();
            //iteramos la longitud de la cantidad del nodo "peronsa" que hay
            for (int i = 0; i < listaPersonas.getLength(); i++) {
                //vamos a buscar todos los nodos dentro de persona para iteramos dentro de cada persona.
                for (int j = 0; j < listaPersonas.item(i).getChildNodes().getLength(); j++) {
                    //esta condicion es muy importante ya que nos servira para encontrar los impares, ya que los pares hacen referencia a los nodos inutiles
                    //como </nom> o <persona> ya que no nos tienen textcontent.
                    if (j % 2 != 0) {
                        //imprimimos el contenido de los nodos <nom>,<cognom> etc...
                        System.out.print(listaPersonas.item(i).getChildNodes().item(j).getTextContent());
                        //aqui imprimimos espacios para tabular y nos quede simetrico.
                        for (int j2 = 0; j2 < longitud_tabulador
                                - listaPersonas.item(i).getChildNodes().item(j).getTextContent().length(); j2++) {
                            System.out.print(" ");
                        }
                    }
                }
                //printeamos una \n para que nos separe la información.
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
