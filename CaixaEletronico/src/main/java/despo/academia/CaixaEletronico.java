package despo.academia;

public class CaixaEletronico {
	private Hardware hardware;

	public CaixaEletronico(Hardware hardware) {
		this.hardware = hardware;
	}

	public String logar() {
		try {
			hardware.pegarNumeroDaContaCartao();
			return "Usu�rio Autenticado";
		} catch(Exception e) {
			return "N�o foi poss�vel autenticar o usu�rio";
		}
	}

}
