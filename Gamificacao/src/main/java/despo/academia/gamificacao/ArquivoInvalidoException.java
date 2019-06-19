package despo.academia.gamificacao;

@SuppressWarnings("serial")
public class ArquivoInvalidoException extends RuntimeException {
	public ArquivoInvalidoException(String mensagem) {
		super(mensagem);
	}
}
