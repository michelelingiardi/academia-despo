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
			return "Usuario Autenticado";
		} catch(Exception e) {
			return "Nao foi possivel autenticar o usuario";
		}
	}

	public String sacar(int i) {
		ContaCorrente conta = servicoRemoto.recuperarConta(numeroDaConta);
		return "";
	}

}
