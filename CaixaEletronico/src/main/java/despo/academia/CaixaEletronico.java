package despo.academia;

public class CaixaEletronico {
	private Hardware hardware;
	private ServicoRemoto servicoRemoto;
	private String numeroDaConta;

	public CaixaEletronico(Hardware hardware, ServicoRemoto servicoRemoto) {
		this.hardware = hardware;
		this.servicoRemoto = servicoRemoto;
	}

	public String logar() {
		try {
			numeroDaConta = hardware.pegarNumeroDaContaCartao();
			return "Usu�rio Autenticado";
		} catch(Exception e) {
			return "N�o foi poss�vel autenticar o usu�rio";
		}
	}

	public String sacar() {
		ContaCorrente conta = servicoRemoto.recuperarConta(numeroDaConta);
		
	}

}
