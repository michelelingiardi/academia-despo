package despo.academia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ConversorCamelCase {
  
  public static List<String> converterCamelCase(String original) {
    return criarLista(original).stream()
        .map(item -> {
          return tratarTexto(item);          
        })
        .collect(Collectors.toList());
	}

  private static List<String> criarLista(String original) {
    return new ArrayList<String>(Arrays.asList(original.split(getRegex())));
  }

  private static String getRegex() {
    return "(?=[A-Z]|[0-9])(?<=[a-z])|(?<=[A-Z]|[0-9])(?=[A-Z][a-z])";
  }
  
  private static String tratarTexto(String item) {
    if (!isSigla(item)) item = item.toLowerCase();
    return item;
  }
  
  private static boolean isSigla(String item) {
    return item.matches("([A-Z]{2,})");
  }
  
}
