
package tdas;

import java.io.*;
import java.util.*;

public class ciudades {
    
    public  HashMap<String,String> cargarCiudades (String nombreArchivo){
        HashMap<String,String> dic= new HashMap<>();
        try (Scanner sc = new Scanner(new File(nombreArchivo))) {
            while(sc.hasNextLine()) {
                String[] codCiu= sc.nextLine().split("\\,");
                dic.put(codCiu[0], codCiu[1]);
            }
        } catch (FileNotFoundException ex) {}
    
        return dic;
    }
    
    
   
    public   String buscarCodigoCiudad (HashMap<String,String> codigos ,String ciudad){
        Map<String, String> map = codigos;
        for (Map.Entry<String,String> entry : map.entrySet()) {
            if(ciudad.toLowerCase().equals(entry.getValue().toLowerCase())){
                return entry.getKey();
            }
                
        }
        return null;
    }
}
