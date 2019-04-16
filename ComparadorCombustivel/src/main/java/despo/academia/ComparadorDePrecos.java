package despo.academia;

import foo.PrecoCombustivelWS;

public class ComparadorDePrecos {
  public static final String ETANOL = "E";
  private PrecoCombustivelWS precoCombustivel;

  public ComparadorDePrecos(PrecoCombustivelWS precoCombustivel) {
    this.precoCombustivel = precoCombustivel;
  }

  public double obterPrecoEtanol() {
    return this.precoCombustivel.obterPrecoCombustivel(ETANOL);
  }
}
