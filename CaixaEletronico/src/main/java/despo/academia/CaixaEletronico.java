package despo.academia;

public class CaixaEletronico {
	private Hardware hardware;

	public CaixaEletronico(Hardware hardware) {
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

}
