package despo.academia;

import foo.PrecoCombustivel;

public class Combustivel {
  public static final String ETANOL = "E";
  private PrecoCombustivel precoCombustivel;

  public Combustivel(PrecoCombustivel precoCombustivel) {
    this.precoCombustivel = precoCombustivel;
  }

  public double obterPrecoEtanol() {
    return this.precoCombustivel.obterPrecoCombustivel(ETANOL);
  }
}
