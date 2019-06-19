package despo.academia.gamificacao;

@SuppressWarnings("serial")
public class CaracteresInvalidosException extends RuntimeException {
	public CaracteresInvalidosException() {
		super("Nome de usuário deve conter apenas caracteres alfanuméricos.");
	}
}
