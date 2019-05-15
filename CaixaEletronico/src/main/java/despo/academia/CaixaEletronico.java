package despo.academia;

public class CaixaEletronico {
	private Hardware hardware;

	public CaixaEletronico(Hardware hardware, ServicoRemoto servicoRemoto) {
		this.hardware = hardware;
	}

	public String logar() {
		try {
			hardware.pegarNumeroDaContaCartao();
			return "Usuário Autenticado";
		} catch(Exception e) {
			return "Não foi possível autenticar o usuário";
		}
	}

	public String sacar() {
		// TODO Auto-generated method stub
		return null;
	}

}
