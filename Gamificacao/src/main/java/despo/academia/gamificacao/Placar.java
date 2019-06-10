package despo.academia.gamificacao;

public class Placar {	
	Armazenamento armazenamento;
	public Placar(Armazenamento armazenamento) {
		this.armazenamento = armazenamento;
	}

	public void registrarPontoParaUsuario(String tipoPonto, String nomeUsuario, int pontos) {
		Usuario usuario = armazenamento.recuperarUsuario(nomeUsuario);
		usuario.adicionarPontos(tipoPonto, pontos);
		armazenamento.armazenarPontuacaoUsuario(usuario);		
	}

}
