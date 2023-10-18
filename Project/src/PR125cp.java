import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

public class PR125cp {
    //Creamos 2 atributos que serán las direcciones del origen y el destino.
    String PathOrigen, PathDestino;
    //constructor que los valores creados por los usuarios se hagan nuestros atributos.
    public PR125cp(String PathOrigen,String PathDestino){
        this.PathOrigen = PathOrigen;
        this.PathDestino = PathDestino;
    }
    //Hacemos que el usuario introduzca su origen y destino con el scanner
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Origen de la carpeta o el archivo a copiar:");
        String O = sc.nextLine();
        System.out.println("Destino del archivo o carpeta (puedes enviarlo a alguna carpeta):");
        String D = sc.nextLine();
        //instanciamos el la clase para que podamos llamar al metodo CPfile
        PR125cp CP = new PR125cp(O, D);
        System.out.println(CP.CPfile());
        
    }
    //hacemos el metodo CPfile
    public String CPfile(){
        try {
            //Instanciamos el path origen y destino que luego utilizaremos.
            Path origen = Paths.get(PathOrigen);
            Path destino = Paths.get(PathDestino);

            //instanciamos los archivos para poder saber de su naturaleza o si existen
            File fileOrigen = new File(PathOrigen);
            File fileDestino = new File(PathDestino);
            //si no existe el path origen pues nos retornara un mensaje de que no existe
            if (!fileOrigen.exists()) {
                return "El archivo path de origen no existe";
            //si el destino no es un directorio nos retornará un mensajito
            }else if (!fileDestino.isDirectory()) {
                return "No puedes enviar esto a un Archivo.";
            //Y si no existe lo mismo
            }else if (!fileDestino.exists()) {
                return "La ruta path de destino no existe";
            //Si el origen y el destino son iguales nos mostrará lo siguiente
            }else if(origen == destino){
                return "El Origen no puede ser igual al Destino";
            }
            //en caso de que las demas condiciones no sean ciertas:
            else{
                                        /*La clase Files tiene un metodo donde nos pide
                                         * los parametros de origen, destino, y opciones para copiar, claro está que el destino se llamará 
                                         * como el origen ya que lo estamos copiando, una vez
                                         * hayamos hecho esto añadiremos StandardCopyOption.REPLACE_EXISTING donde nos remplazará el
                                         * archivo por si existe.
                                         */
                Path archivoCopiado = Files.copy(origen, destino.resolve(origen.getFileName()), StandardCopyOption.REPLACE_EXISTING);
                return "Copia realizada. se ha creado:"+ archivoCopiado.toString();
            }
        } catch (Exception e) {
            return e+"";
        }
            
    }
}
