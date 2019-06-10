import despo.academia.ContaCorrente;
import despo.academia.ServicoRemoto;

public class ServicoRemotoMock implements ServicoRemoto {
	private ContaCorrente contaCorrente;
	boolean chamouPersistirConta = false;
	boolean chamouRecuperarConta = false;
	@Override
	public ContaCorrente recuperarConta(String numeroDaConta) {		
		this.chamouRecuperarConta = true;
		return this.contaCorrente;
	}

	@Override
	public void persistirConta(ContaCorrente contaCorrente) {
		this.chamouPersistirConta = true;		
		
	}

	public int recuperaSaldo() {
		return contaCorrente.getSaldo();
	}
	
	public void criarContaCorrente(String numeroConta, int saldo) {
		this.contaCorrente = new ContaCorrente(numeroConta, saldo);
	}
}
