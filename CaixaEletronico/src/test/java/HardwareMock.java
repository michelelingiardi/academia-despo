import despo.academia.Hardware;

public class HardwareMock implements Hardware {
	boolean usuarioInvalido = false;
	boolean chamouPegarNumeroDaContaCartao = false;
	String numeroDaConta = "";
	
	public boolean chamouEntregarDinheiro;

//	@Override
	public String pegarNumeroDaContaCartao() {
		chamouPegarNumeroDaContaCartao = true;
		if (usuarioInvalido) throw new RuntimeException();
		return numeroDaConta;
	};
}
