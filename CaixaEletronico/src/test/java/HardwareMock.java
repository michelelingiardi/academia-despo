import despo.academia.Hardware;

public class HardwareMock implements Hardware {
	String numeroDaConta = "";
	boolean usuarioInvalido = false;
	boolean chamouPegarNumeroDaContaCartao = false;
	boolean chamouEntregarDinheiro = false;
	boolean chamouLerEnvelope = false;
	boolean falhaNoHardware = false;

	@Override
	public String pegarNumeroDaContaCartao() {
		chamouPegarNumeroDaContaCartao = true;
		if (usuarioInvalido) throw new RuntimeException();
		return numeroDaConta;
	}

	@Override
	public void entregarDinheiro() {
		chamouEntregarDinheiro = true;
	}

	@Override
	public void lerEnvelope() {
		chamouLerEnvelope = true;
	}
}
