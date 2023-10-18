import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class PR141Main {
    public static void main(String[] args) {
        //hago una lista con los nombres de los nodos para no crear todas esas variables inecesarias.
        String[] nombresDeNodos = {
            "autor",
            "anyPublicacio",
            "editorial",
            "genere",
            "pagines",
            "disponible"
        };
        //Hacemos lo mismo con todos los textos de cada nodo. Como hemos hecho antes.
        String[] textosDeNodos = {
            "Joan Pla",
            "1998",
            "Edicions Mar",
            "Aventura",
            "320",
            "true"
        };
        //Abrimos el dbfactory.
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();
            //Hacemos el elemento root para poder insertar todos nuestros nodos
            Element elmroot = doc.createElement("llibre");
            //le añadimos su atributo.
            elmroot.setAttribute("id", "001");
            //añadimos el root a nuestro documento
            doc.appendChild(elmroot);
            //vamos a iterar en todos los nodos que vamos a introducir
            for (int i = 0; i < nombresDeNodos.length; i++) {
                //por cada nombre de nodos vamos a hacer un elemento nuevo para seguidamente insertarle
                //el texto.
                Element element = doc.createElement(nombresDeNodos[i]);
                //creamos el texto
                Text nodetext = doc.createTextNode(textosDeNodos[i]);
                //insertamos el texto en el elemnto
                element.appendChild(nodetext);
                //luego insertamos el elemento en el elemento root.
                elmroot.appendChild(element);
            }
            //Una vez acabado el el documento llamamos a la funcion para crear nuestro fichero XML y le pasamos la ruta del
            //documento y luego el documento en si para poder escribirlo dentro de nuestro archivo.
            write("Project/src/myFiles/biblioteca.xml", doc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static public void write(String path, Document doc) throws TransformerException, IOException {
        if (!new File(path).exists()) {
            new File(path).createNewFile();
        }
        // Crea una factoria de transformadors XSLT
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        // Crea un transformador XSLT
        Transformer transformer = transformerFactory.newTransformer();
        // Estableix la propietat OMIT_XML_DECLARATION a "no" per no ometre la
        // declaració XML del document XML resultant
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
        // Estableix la propietat INDENT a "yes" per indentar el document XML resultant
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        // Elimina els espais en blanc innecessaris del document XML. Implementació
        // pròpia
        // Crea una instància de DOMSource a partir del document XML
        DOMSource source = new DOMSource(doc);
        // Crea una instància de StreamResult a partir del camí del fitxer XML
        StreamResult result = new StreamResult(new File(path));
        // Transforma el document XML especificat per source i escriu el document XML
        // resultant a l'objecte especificat per result
        transformer.transform(source, result);
    }

}
