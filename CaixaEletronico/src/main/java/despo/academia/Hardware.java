package despo.academia;

public interface Hardware {
	public String pegarNumeroDaContaCartao() throws FalhaFuncionamentoHardwareException, UsuarioInvalidoException;
	public void entregarDinheiro() throws FalhaFuncionamentoHardwareException;
	public void lerEnvelope();
}
