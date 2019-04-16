package despo.academia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import despo.academia.mock.PrecoCombustivelMock;
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
  
  @Test
  @DisplayName("Comparar preços")
  void compararPrecosTest() {
    String mensagem = criarComparadorDePrecos().comparar();
    assertTrue(mensagem.equals(ComparadorDePrecos.MSG_ETANOL)
        || mensagem.equals(ComparadorDePrecos.MSG_GASOLINA));
  }
  
  @Test
  @DisplayName("Comparar preços com mock - vantagem Etanol")
  void mensagemCompreEtanol() {
    ComparadorDePrecos comparadorDePrecos 
      = new ComparadorDePrecos(new PrecoCombustivelMock(70, 100));
    String mensagem = comparadorDePrecos.comparar();
    assertTrue(mensagem.equals(ComparadorDePrecos.MSG_ETANOL));
  }
  
  private ComparadorDePrecos criarComparadorDePrecos() {
    return new ComparadorDePrecos(new PrecoCombustivelWS());
  }
}
