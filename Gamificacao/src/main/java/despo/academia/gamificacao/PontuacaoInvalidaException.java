package despo.academia.gamificacao;

@SuppressWarnings("serial")
public class PontuacaoInvalidaException extends RuntimeException {
	public PontuacaoInvalidaException(String mensagem) {
		super(mensagem);
	}
}
