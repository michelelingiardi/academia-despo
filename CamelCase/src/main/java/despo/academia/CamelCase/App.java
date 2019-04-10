package despo.academia.CamelCase;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ){
    	
    }
    
    public static List<String> converterCamelCase(String original){
    	List<String> lista = new ArrayList<String>();    	
    	for (String palavra : original.split("(?<=[a-z])(?=[A-Z])")) {
    		lista.add(palavra.toLowerCase());
    	}
    	return lista;
    };
}
