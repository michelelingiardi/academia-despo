import despo.academia.Hardware;

public class HardwareMock implements Hardware {
	boolean usuarioInvalido = false;
	boolean chamouPegarNumeroDaContaCartao = false;
	public boolean chamouEntregarDinheiro = false; 
	String numeroDaConta = "";


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
	
}
