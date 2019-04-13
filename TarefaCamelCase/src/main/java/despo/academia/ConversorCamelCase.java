package despo.academia;

import java.util.ArrayList;
import java.util.List;

public class ConversorCamelCase {
  public static List<String> converterCamelCase(String original) {
    List<String> lista = new ArrayList<String>();
    String[] separated = original.split("(?=[A-Z])");
    for (String palavra : separated) {
      lista.add(palavra.toLowerCase());
    }    
    return lista;
	}
}
