package despo.academia.CamelCase;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
    
    public static List<String> converterCamelCase(String original){
    	List<String> lista = new ArrayList<String>();    	
    	lista.add(original);
    	return lista;
    };
}
