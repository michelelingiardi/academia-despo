package despo.academia.mock;

import foo.IntegracaoConsultaCombustivelIndisponivelException;
import foo.PrecoCombustivelWS;

public class PrecoCombustivelExceptionMock extends PrecoCombustivelWS {
  @Override
  public double obterPrecoCombustivel(String siglaCombustivel) {
    throw new IntegracaoConsultaCombustivelIndisponivelException();
  }
}
