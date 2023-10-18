import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


public class PR122cat {
    //Guardamos la ruta de nuestro archivo/carpeta o el parametro que nos introduza el usuario
    private String path;
    //creamos constructor donde le diremos que nos pase un parametro STRING para poder guardar la variable como atributo de la clase
    public PR122cat(String path){
    this.path = path;
    }
    /*en el main vamos a abrir un scanner donde introduciremos 
    la ruta que queremos verificar para que nos la busque e instanciaremos 
    un objeto para poder llamar al metodo de mostra el texto de retorno. mas
    adelante hablare de él.
    */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce una ruta:");
        PR122cat cat = new PR122cat(sc.nextLine());
        System.out.println(cat.getTextFile());
    }
    /*Agregaremos un getter para poder obtener el path más tarde 
     * en el metodo getTextFile
     */
    public String getPath() {
        return path;
    }
    /*Este metodo nos servira en el main, para llamarlo desde el objeto
     * y que nos haga un return de un STRING, que podrá ser de el archivo ya leido,
     * del mensaje de que no corresponde a un archivo sino a una carpeta o que
     * simplemente no es una ruta.
     */
    public String getTextFile(){
        String text = "";
        try{
            File archivo_a_mostrar = new File(getPath());
            Path ruta = Paths.get(path);
            if (Files.isDirectory(ruta))  {
                return "El path no correspon a un arxiu, sinó a una carpeta";
            }else if (archivo_a_mostrar.exists()) {
                Scanner scanear = new Scanner(archivo_a_mostrar);
                while (scanear.hasNextLine()) {
                    text += scanear.nextLine();
                    text += "\n";
                }
                scanear.close();
                return text;
            }else
            
            return "Eso no es una ruta valida";
        }catch(Exception e){
            return "Ha habido un Error";
        }
    }
    
   
}



