import despo.academia.Hardware;

public class HardwareMock implements Hardware {
	boolean usuarioInvalido = false;
	boolean chamouPegarNumeroDaContaCartao = false;
	String numeroDaConta = "";

//	@Override
	public String pegarNumeroDaContaCartao() {
		if (usuarioInvalido) throw new RuntimeException();
		return numeroDaConta;
	};
}
