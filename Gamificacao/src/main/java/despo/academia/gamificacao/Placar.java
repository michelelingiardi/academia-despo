package despo.academia.gamificacao;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Placar {	
	Armazenamento armazenamento;
	
	public Placar(Armazenamento armazenamento) {
		this.armazenamento = armazenamento;
	}

	public void registrarPontoParaUsuario(String tipoPonto, String nomeUsuario, int pontos) {
		Usuario usuario = armazenamento.recuperarUsuario(nomeUsuario);
		if (pontos > 0) {
			usuario.adicionarPontos(tipoPonto, pontos);
			armazenamento.armazenarPontuacaoUsuario(usuario);
		}
	}

	public Pontuacao recuperarPontuacaoDoUsuario(String nomeUsuario) {
		Usuario usuario = armazenamento.recuperarUsuario(nomeUsuario);
		return usuario.getPontuacao();
	}

	public Map<String, Integer> recuperarRanking(String tipoPonto) {
		List<Usuario> usuarios = armazenamento.recuperarUsuarios();
		
		usuarios.sort((Usuario u1, Usuario u2) -> u2.getPontos(tipoPonto)-u1.getPontos(tipoPonto));
		
		Map<String, Integer> ranking = new LinkedHashMap<>();
		usuarios.forEach( u -> {
			ranking.put(u.getNome(), u.getPontos(tipoPonto));
		});
		
		return ranking;
	}

}
