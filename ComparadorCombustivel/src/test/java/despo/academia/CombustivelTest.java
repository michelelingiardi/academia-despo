package despo.academia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import foo.PrecoCombustivelWS;

class CombustivelTest {
  @Test
  @DisplayName("Obter preço do Etanol")
  void obterPrecoEtanolTest() {
    double precoEtanol = criarComparadorDePrecos().obterPrecoEtanol();
    assertTrue(precoEtanol >= 1 && precoEtanol <= 10);
  }
  
  @Test
  @DisplayName("Obter preço da Gasolina")
  void obterPrecoGasolinaTest() {
    double precoGasolina = criarComparadorDePrecos().obterPrecoGasolina();
    assertTrue(precoGasolina >= 1 && precoGasolina <= 10);
  }
  
  private ComparadorDePrecos criarComparadorDePrecos() {
    return new ComparadorDePrecos(new PrecoCombustivelWS());
  }
}
