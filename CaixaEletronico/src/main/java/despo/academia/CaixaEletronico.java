package despo.academia;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

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

	public String depositar(int valorDeposito) {
		ContaCorrente contaCorrente = servicoRemoto.recuperarConta(numeroDaConta);
		hardware.lerEnvelope();
		contaCorrente.depositar(valorDeposito);
		servicoRemoto.persistirConta(contaCorrente);		
		return "Depósito recebido com sucesso";
	}

	public String saldo() {
		ContaCorrente contaCorrente = servicoRemoto.recuperarConta(numeroDaConta);		
		DecimalFormat formatReal = new DecimalFormat("##,###,###,##0.00", new DecimalFormatSymbols(new Locale ("pt", "BR")));
		return "O saldo é R$" + formatReal.format(contaCorrente.getSaldo());		
	}
}
