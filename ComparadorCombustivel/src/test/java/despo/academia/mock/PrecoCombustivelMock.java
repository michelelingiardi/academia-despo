package despo.academia.mock;
import foo.PrecoCombustivelWS;

public class PrecoCombustivelMock extends PrecoCombustivelWS {
  double etanol;
  double gasolina;
  
  public PrecoCombustivelMock(double etanol, double gasolina) {
    this.etanol = etanol;
    this.gasolina = gasolina;
  }
  
  @Override
  public double obterPrecoCombustivel(String siglaCombustivel) {
    switch (siglaCombustivel) {
    case "E":
      return this.etanol;
    case "G":
      return this.gasolina;
    default:
      throw new RuntimeException("Opção inválida.");
    }
  }
}
