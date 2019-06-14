package despo.academia.gamificacao;

import java.util.Map;

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

	public Pontuacao recuperarPontuacaoDoUsuario(String nomeUsuario) {
		Usuario usuario = armazenamento.recuperarUsuario(nomeUsuario);
		return usuario.getPontuacao();
	}

	public Map<String, Integer> recuperarRanking(String estrela) {
		// TODO Auto-generated method stub
		return null;
	}

}
