import java.io.File;
import java.util.Scanner;

import javax.swing.text.html.parser.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
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
                            "\n2)Llistar alumnes." +
                            "\n3)Mostrar ids i titols dels mòduls a partir d'un id de curs." +
                            "\n4)Llistar alumnes d'un curs." +
                            "\n5)Afegir un alumne a un curs." +
                            "\n6)Afegir un alumne a un curs." +
                            "\n100)Sortir.");
            System.out.print("-->");
            int opcio = sc.nextInt();
            switch (opcio) {
                case 0:
                    llistaridcursos(doc);
                    break;
                case 1:
                    /* Funcion */break;
                case 2:
                    /* Funcion */break;
                case 3:
                    /* Funcion */break;
                case 4:
                    /* Funcion */break;
                case 5:
                    /* Funcion */break;
                case 6:
                    /* Funcion */break;
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
        System.out.println("Hi ha "+ listdecursos.getLength()+" cursos");
        for (int i = 0; i < listdecursos.getLength(); i++) {
            String cursonombre = listdecursos.item(i)+"";
            System.out.println("Curs "+ cursonombre.replaceAll("[id=\"\"]", ""));
        }
        System.out.println();
    }

    
}
