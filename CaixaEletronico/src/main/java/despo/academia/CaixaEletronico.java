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

	public String sacar(int valorSaque) {
		ContaCorrente contaCorrente = servicoRemoto.recuperarConta(numeroDaConta);
		try{
			contaCorrente.sacar(valorSaque);		
		} catch(Exception e) {
			return "Saldo insuficiente";
		}
		hardware.entregarDinheiro();
		servicoRemoto.persistirConta(contaCorrente);
		return "Retire seu dinheiro";
	}

	public String depositar(int i) {
		// TODO Auto-generated method stub
		return null;
	}

}
