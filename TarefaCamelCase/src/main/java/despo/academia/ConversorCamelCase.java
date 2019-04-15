package despo.academia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ConversorCamelCase {
  public static List<String> converterCamelCase(String original) {
    validarEntrada(original);
    return converterTextoOriginal(original)
        .stream()
        .map(item -> { return tratarTexto(item); })
        .collect(Collectors.toList());
  }

  private static void validarEntrada(String original) {
    if (temNumeroNoInicio(original)) 
      throw new NumeroNoInicioException("Inválido - não deve começar com números.");  
    if (temCaracteresEspeciais(original))
      throw new CaractereEspecialException("Inválido - caracteres especiais não são permitidos, somente letras e números.");
  }

  private static boolean temNumeroNoInicio(String original) {
    return original.matches("^[0-9].+$");
  }
  
  private static boolean temCaracteresEspeciais(String original) {
    return !original.matches("^[a-zA-Z0-9]*$");
  }

  private static List<String> converterTextoOriginal(String original) {
    return new ArrayList<String>(Arrays.asList(original.split(getCriterioDeSeparacao())));
  }

  private static String getCriterioDeSeparacao() {
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
