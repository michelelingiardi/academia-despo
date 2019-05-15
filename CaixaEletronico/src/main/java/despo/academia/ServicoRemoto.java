package despo.academia;
public interface ServicoRemoto {
	ContaCorrente recuperarConta(String numeroDaConta);
	void persistirConta(ContaCorrente contaCorrente);
	
}
