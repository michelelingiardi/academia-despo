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
			return "Usuário Autenticado";
		} catch(Exception e) {
			return "Não foi possível autenticar o usuário";
		}
	}

	public String sacar() {
		ContaCorrente conta = servicoRemoto.recuperarConta(numeroDaConta);
		
	}

}
