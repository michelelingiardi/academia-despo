package despo.academia;

public class ContaCorrente {
	private int saldo;
	private String numeroDaConta;
	
	public ContaCorrente(int saldo, String numeroDaConta) {
		this.saldo = saldo;
		this.numeroDaConta = numeroDaConta;
	}
	
	public int getSaldo() {
		return this.saldo;
	}
	
	public String getNumeroDaConta() {
		return this.numeroDaConta;
	}
	
	public void sacar(int valorSaque) {
		this.saldo = this.saldo - valorSaque;
	}

}
