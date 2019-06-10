package despo.academia.gamificacao;

public class Placar {
	
	Armazenamento armazenamento;

	public Placar(Armazenamento armazenamento) {
		this.armazenamento = armazenamento;
	}

	public void registrarPontoParaUsuario(String tipoPonto, String usuario, int pontos) {
		pontos += armazenamento.recuperarPontuacaoUsuario(tipoPonto, usuario);	
		armazenamento.armazenarPontuacaoUsuario(tipoPonto, usuario, pontos);		
	}

}
