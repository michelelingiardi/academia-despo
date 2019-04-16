package despo.academia;

import foo.IntegracaoConsultaCombustivelIndisponivelException;
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
    try {
      return this.precoCombustivel.obterPrecoCombustivel(ETANOL);
    } catch (IntegracaoConsultaCombustivelIndisponivelException e) {
      throw new ConsultaCombustivelIndisponivelException();
    }
  }

  public double obterPrecoGasolina() {
    try {
      return this.precoCombustivel.obterPrecoCombustivel(GASOLINA);
    } catch (IntegracaoConsultaCombustivelIndisponivelException e) {
      throw new ConsultaCombustivelIndisponivelException();
    }
  }

  public String comparar() {
    double valor = (this.obterPrecoEtanol() / this.obterPrecoGasolina());
    return (valor <= 0.7) ? MSG_ETANOL : MSG_GASOLINA;
  }
}
