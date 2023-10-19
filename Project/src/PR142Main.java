import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class PR142Main {
    static DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    static XPath xPath = XPathFactory.newInstance().newXPath();
    static File cursosxml = new File("Project/src/myFiles/cursos.xml");

    public static void main(String[] args) {
        try {
            menumasleer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void menumasleer() throws Exception {
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(cursosxml);
        doc.getDocumentElement().normalize();
        Scanner sc = new Scanner(System.in);
        boolean activo = true;
        while (activo) {
            System.out.println(
                    "0)Llistar ids de cursos." +
                            "\n1)Llistar tutors." +
                            "\n2)Llistar tots els alumnes." +
                            "\n3)Mostrar ids i titols dels mòduls a partir d'un id de curs." +
                            "\n4)Llistar alumnes d'un curs." +
                            "\n5)Afegir un alumne a un curs." +
                            "\n6)Eliminar un alumne a un curs." +
                            "\n100)Sortir.");
            System.out.print("-->");
            int opcio = sc.nextInt();
            switch (opcio) {
                case 0:
                    llistaridcursos(doc);
                    break;
                case 1:
                    llistartutors(doc);
                    break;
                case 2:
                    llistarallalumns(doc);
                    break;
                case 3:
                    listaridmodulsofcursos(doc, sc);
                    break;
                case 4:
                    llistaralumnes(doc);
                    break;
                case 5:
                    afegiralumne(doc, sc);
                    break;
                case 6:
                    eliminaralumne(doc, sc);
                    break;
                case 100:
                    sc.close();
                    activo = false;
                    break;
                default:
                    break;
            }
        }

    }

    static void llistaridcursos(Document doc) throws Exception {
        String expresion = "/cursos/curs/@id";
        NodeList listdecursos = (NodeList) xPath.compile(expresion).evaluate(doc, XPathConstants.NODESET);
        System.out.println("Hi ha " + listdecursos.getLength() + " cursos");
        for (int i = 0; i < listdecursos.getLength(); i++) {
            String cursonombre = listdecursos.item(i) + "";
            System.out.println("Curs " + cursonombre.replaceAll("[id=\"\"]", ""));
        }
        System.out.println();
    }

    static void llistarallalumns(Document doc) throws Exception {
        String expresion = "//alumne/text()";
        NodeList listdecursos = (NodeList) xPath.compile(expresion).evaluate(doc, XPathConstants.NODESET);
        System.out.println("Hi ha " + listdecursos.getLength() + " alumnes");
        for (int i = 0; i < listdecursos.getLength(); i++) {
            String alumne = listdecursos.item(i).getTextContent();
            System.out.println("alumne: " + alumne);
        }
        System.out.println();
    }

    static void llistartutors(Document doc) throws Exception {
        String expresion = "//curs/tutor/text() | //curs/@id";
        NodeList listadetutores = (NodeList) xPath.compile(expresion).evaluate(doc, XPathConstants.NODESET);
        for (int i = 0; i < listadetutores.getLength(); i++) {
            if (listadetutores.item(i).getNodeType() == 2) {
                String cursonombre = listadetutores.item(i) + "";
                System.out.print("Curso " + cursonombre.replaceAll("[id=\"\"]", "") + "\n   nombre de tutor: ");
            } else {
                System.out.print(listadetutores.item(i).getTextContent() + "\n");
            }
        }
        System.out.println();
    }

    static void llistaralumnes(Document doc) throws Exception {
        String expresion = "//curs/alumnes/alumne/text() | //curs/@id";
        NodeList listadetutores = (NodeList) xPath.compile(expresion).evaluate(doc, XPathConstants.NODESET);
        for (int i = 0; i < listadetutores.getLength(); i++) {
            if (listadetutores.item(i).getNodeType() == 2) {
                String cursonombre = listadetutores.item(i) + "";
                System.out.print("Curso " + cursonombre.replaceAll("[id=\"\"]", "") + "\n   nombre de alumnos: \n");
            } else {
                System.out.print("      " + listadetutores.item(i).getTextContent() + "\n");
            }
        }
        System.out.println();
    }

    static void listaridmodulsofcursos(Document doc, Scanner sc) throws Exception {
        String expresion = "/cursos/curs/@id";
        NodeList listdecursos = (NodeList) xPath.compile(expresion).evaluate(doc, XPathConstants.NODESET);
        opciondecurso(listdecursos);
        int opcio = sc.nextInt();
        String curs = listdecursos.item(opcio) + "";
        expresion = "//curs[@" + curs + "]//moduls//@id | //curs[@" + curs + "]//moduls//titol";
        NodeList listofidmodulecurs = (NodeList) xPath.compile(expresion).evaluate(doc, XPathConstants.NODESET);
        System.out.println("\nMODULS I TITOLS DEL CURS: " + curs.replaceAll("[id=\"\"]", ""));
        for (int i = 0; i < listofidmodulecurs.getLength(); i++) {
            if (listofidmodulecurs.item(i).getNodeType() == 1) {
                System.out.println(listofidmodulecurs.item(i).getTextContent());
            } else {
                System.out.print(listofidmodulecurs.item(i).toString().replaceAll("[id=\"\"]", "") + " ");
            }
        }
        System.out.println();
    }

    static void afegiralumne(Document doc, Scanner sc) throws Exception {
        String nomnouestudiant;
        String cognomnouestudiant;
        String nomcomplet;
        String expresion = "/cursos/curs/@id";
        Element newalumne = doc.createElement("alumne");

        sc.nextLine();
        System.out.println("Nom de nou estudiant:");
        nomnouestudiant = sc.nextLine();
        System.out.println("Cognom:");
        cognomnouestudiant = sc.nextLine();
        nomcomplet = cognomnouestudiant.toUpperCase() + ", " + nomnouestudiant;
        System.out.println("Donde quieres matricular:?");
        NodeList listdecursos = (NodeList) xPath.compile(expresion).evaluate(doc, XPathConstants.NODESET);
        opciondecurso(listdecursos);
        int opcio = sc.nextInt();

        String nomcurs = listdecursos.item(opcio) + "";
        nomcurs = nomcurs.replaceAll("[id=\"\"]", "");
        expresion = "//curs[@id=\"" + nomcurs + "\"]//alumnes";
        Node alumnos = (Node) xPath.evaluate(expresion, doc, XPathConstants.NODE);
        newalumne.setTextContent(nomcomplet);
        alumnos.appendChild(newalumne);
        write("Project/src/myFiles/cursos.xml", doc);
        System.out.println("\nAfegit amb exit!\n");
    }

    static void eliminaralumne(Document doc, Scanner sc) throws Exception {
        System.out.println("On es troba el alumne a eliminar:");
        String expresion = "/cursos/curs/@id";
        NodeList listdecursos = (NodeList) xPath.compile(expresion).evaluate(doc, XPathConstants.NODESET);
        opciondecurso(listdecursos);
        int opcio = sc.nextInt();
        expresion = "//curs[@id=\"AMS2\"]//alumne";
        NodeList listaalumnes = (NodeList) xPath.compile(expresion).evaluate(doc, XPathConstants.NODESET);
        System.out.println("\nQue alumno quieres eliminar?\n");
        for (int i = 0; i < listaalumnes.getLength(); i++) {
            System.out.println(i + ") " + listaalumnes.item(i).getTextContent());
        }
        System.out.print("-->");
        opcio = sc.nextInt();
        Node alumneeliminar = (Node) listaalumnes.item(opcio);
        Node alumnes = alumneeliminar.getParentNode();
        alumnes.removeChild(alumneeliminar);
        write("Project/src/myFiles/cursos.xml", doc);
        System.out.println("\nEliminat.\n");
    }

    static void opciondecurso(NodeList listdecursos) {
        for (int i = 0; i < listdecursos.getLength(); i++) {
            String cursonombre = listdecursos.item(i) + "";
            System.out.println(i + ") " + cursonombre.replaceAll("[id=\"\"]", ""));
        }
        System.out.println("-->");
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
        // Crea una instància de DOMSource a partir del document XML
        DOMSource source = new DOMSource(doc);
        // Crea una instància de StreamResult a partir del camí del fitxer XML
        StreamResult result = new StreamResult(new File(path));
        // Transforma el document XML especificat per source i escriu el document XML
        // resultant a l'objecte especificat per result
        transformer.transform(source, result);
    }

}
