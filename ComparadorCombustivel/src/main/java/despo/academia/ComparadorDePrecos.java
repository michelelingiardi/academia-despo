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
    double valor = (this.obterPrecoEtanol() * 100 / this.obterPrecoGasolina()) - 100 * -1 ;
    return (valor <= 70) ? MSG_ETANOL : MSG_GASOLINA;
  }
}
