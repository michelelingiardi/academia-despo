import despo.academia.FalhaFuncionamentoHardwareException;
import despo.academia.Hardware;
import despo.academia.UsuarioInvalidoException;

public class HardwareMock implements Hardware {
	String numeroDaConta = "";
	boolean usuarioInvalido = false;
	boolean chamouPegarNumeroDaContaCartao = false;
	boolean chamouEntregarDinheiro = false;
	boolean chamouLerEnvelope = false;
	boolean falhaNoHardware = false;

	@Override
	public String pegarNumeroDaContaCartao() throws FalhaFuncionamentoHardwareException, UsuarioInvalidoException {
		chamouPegarNumeroDaContaCartao = true;
		if (falhaNoHardware) throw new FalhaFuncionamentoHardwareException();
		if (usuarioInvalido) throw new UsuarioInvalidoException();
		return numeroDaConta;
	}

	@Override
	public void entregarDinheiro() throws FalhaFuncionamentoHardwareException {		
		chamouEntregarDinheiro = true;
		if (falhaNoHardware) throw new FalhaFuncionamentoHardwareException();
	}

	@Override
	public void lerEnvelope() {
		chamouLerEnvelope = true;
	}
}
