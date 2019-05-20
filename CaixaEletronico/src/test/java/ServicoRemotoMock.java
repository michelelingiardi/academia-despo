import despo.academia.ContaCorrente;
import despo.academia.ServicoRemoto;

public class ServicoRemotoMock implements ServicoRemoto {

	int saldo;
	ContaCorrente contaCorrente;
	boolean chamouPersistirConta;
	

	@Override
	public ContaCorrente recuperarConta(String numeroDaConta) {
		this.contaCorrente = new ContaCorrente(this.saldo, "1234");
		return this.contaCorrente;
	}

	@Override
	public void persistirConta(ContaCorrente contaCorrente) {
		this.chamouPersistirConta = true;		
		
	}

	public int recuperaSaldo() {
		return contaCorrente.getSaldo();
	}
}
