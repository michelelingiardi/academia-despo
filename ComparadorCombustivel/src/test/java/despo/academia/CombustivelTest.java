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
    String mensagem = criarComparadorDePrecosMock(70,100).comparar();
    assertTrue(mensagem.equals(ComparadorDePrecos.MSG_ETANOL));
  }
  
  @Test
  @DisplayName("Comparar preços com mock - vantagem Gasolina")
  void mensagemCompreGasolina() {
    String mensagem = criarComparadorDePrecosMock(70.01,100).comparar();
    assertTrue(mensagem.equals("Compre gasolina."));
  }
  
  @Test
  @DisplayName("Falha na integração")
  void consultaCombustivelIndisponivelTest() {
    assertThrows(ConsultaCombustivelIndisponivelException, criarComparadorDePrecosMock().comparar())
  }
  
  private ComparadorDePrecos criarComparadorDePrecos() {
    return new ComparadorDePrecos(new PrecoCombustivelWS());
  }
  
  private ComparadorDePrecos criarComparadorDePrecosMock(double etanol, double gasolina) {
    return new ComparadorDePrecos(new PrecoCombustivelMock(etanol, gasolina));
  }
  
  private ComparadorDePrecos criarComparadorDePrecosMock() {
    return new ComparadorDePrecos(new PrecoCombustivelExceptionMock());
  }
}
