package despo.academia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import foo.PrecoCombustivel;

class CombustivelTest {
  @Test
  @DisplayName("Obter preço do Etanol")
  void obterPrecoEtanolTest() {
    Combustivel combustivel = new Combustivel(new PrecoCombustivel());
    double precoEtanol = combustivel.obterPrecoEtanol(Combustivel.ETANOL);
    assertTrue(precoEtanol >= 1 && precoEtanol <= 10);
  }
}
