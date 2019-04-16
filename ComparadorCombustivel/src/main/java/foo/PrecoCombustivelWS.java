package foo;
import java.util.Random;

public class PrecoCombustivelWS {
  public double obterPrecoCombustivel(String siglaCombustivel) {
    Random random = new Random();
    double minValue = 1;
    double maxValue = 10;
    boolean ocorreuErro = (random.nextInt(100) == 1);
    if (ocorreuErro) {
       throw new IntegracaoConsultaCombustivelIndisponivelException();
    }
    return random.nextDouble() * (maxValue - minValue) + minValue;
  } 
}
