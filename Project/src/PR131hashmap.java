import java.io.Serializable;
import java.util.HashMap;
import java.util.Map.Entry;

public class PR131hashmap implements Serializable{

    HashMap<String,Integer> hash = new HashMap<String,Integer>();
    public PR131hashmap(){
        //hago que el hashmap por defecto al instanciarse se introduzcan los siguientes valores:
        hash.put("Roberto", 1);
        hash.put("Marc", 2);
        hash.put("Sara", 3);
        hash.put("Sara", 4);
    }
    
    @Override
    public String toString() {
        String text = "";
        for (Entry<String, Integer> entry: hash.entrySet()) {
            text +="Nom: "+entry.getKey()+"  numero: "+entry.getValue()+"\n";
        }
        return text; 
    }
}
