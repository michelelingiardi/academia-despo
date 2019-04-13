package despo.academia;

import java.util.ArrayList;
import java.util.List;

public class ConversorCamelCase {
  public static List<String> converterCamelCase(String original) {
    List<String> lista = new ArrayList<String>();
    String[] separated = original.split("(?<=[a-z])(?=[A-Z])");
    for (String palavra : separated) {
      if (!palavra.matches("([A-Z]{2,})")) palavra = palavra.toLowerCase();
      lista.add(palavra);
    }    
    return lista;
	}
}
