package despo.academia;

import foo.PrecoCombustivelWS;

public class ComparadorDePrecos {
  private static final String ETANOL = "E";
  private static final String GASOLINA = "G";
  private PrecoCombustivelWS precoCombustivel;

  public ComparadorDePrecos(PrecoCombustivelWS precoCombustivel) {
    this.precoCombustivel = precoCombustivel;
  }

  public double obterPrecoEtanol() {
    return this.precoCombustivel.obterPrecoCombustivel(ETANOL);
  }

  public double obterPrecoGasolina() {
    return this.precoCombustivel.obterPrecoCombustivel(GASOLINA);
  }
}
