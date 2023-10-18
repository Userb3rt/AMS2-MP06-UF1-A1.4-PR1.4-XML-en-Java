import java.io.File;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
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
                            "\n2)Llistar tots els alumnes." +
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
                    llistartutors(doc);
                    break;
                case 2:
                    llistarallalumns(doc);
                    break;
                case 3:
                    /* Funcion */break;
                case 4:
                    llistaralumnes(doc);
                    break;
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

}
