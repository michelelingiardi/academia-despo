package despo.academia;

import foo.PrecoCombustivelWS;

public class ComparadorDePrecos {
  private static final String ETANOL = "E";
  private static final String GASOLINA = "G";
  public static final String MSG_ETANOL = "Compre etanol.";
  public static final String MSG_GASOLINA = "Compre gasolina.";
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

  public String comparar() {
    double valor = (this.obterPrecoEtanol() / this.obterPrecoGasolina());
    return (valor <= 0.7) ? MSG_ETANOL : MSG_GASOLINA;
  }
}
