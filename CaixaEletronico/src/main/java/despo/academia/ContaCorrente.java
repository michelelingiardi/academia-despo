package despo.academia;

public class ContaCorrente {
	private int saldo;
	private String numeroDaConta;
	
	public ContaCorrente(String numeroDaConta, int saldo) {
		this.saldo = saldo;
		this.numeroDaConta = numeroDaConta;
	}
	
	public int getSaldo() {
		return this.saldo;
	}
	
	public String getNumeroDaConta() {
		return this.numeroDaConta;
	}
	
	public void sacar(int valorSaque) throws Exception {
		if (valorSaque > this.saldo) {
			throw new Exception("Saldo insuficiente");
		}
		this.saldo = this.saldo - valorSaque;
	}

	public void depositar(int valorDeposito) {
		this.saldo += valorDeposito;		
	}

}
