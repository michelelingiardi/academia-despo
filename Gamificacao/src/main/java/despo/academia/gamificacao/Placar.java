package despo.academia.gamificacao;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Placar {	
	Armazenamento armazenamento;
	
	public Placar(Armazenamento armazenamento) {
		this.armazenamento = armazenamento;
	}

	public void registrarPontoParaUsuario(String tipoPonto, String nomeUsuario, int pontos) {
		Usuario usuario = armazenamento.recuperarUsuario(nomeUsuario);
		if (pontos != 0) {
			adicionarOuRemoverPontos(tipoPonto, pontos, usuario);
		}
	}

	private void adicionarOuRemoverPontos(String tipoPonto, int pontos, Usuario usuario) {
		usuario.adicionarPontos(tipoPonto, pontos);		
		armazenamento.armazenarPontuacao(usuario);
	}

	public Pontuacao recuperarPontuacaoDoUsuario(String nomeUsuario) {
		Usuario usuario = armazenamento.recuperarUsuario(nomeUsuario);
		return usuario.getPontuacao();
	}

	public Map<String, Integer> recuperarRanking(String tipoPonto) {
		List<Usuario> usuarios = armazenamento.recuperarUsuarios();
		
		Comparator<Usuario> usuarioComparator = (Usuario u1, Usuario u2) -> u1.getPontos(tipoPonto)-u2.getPontos(tipoPonto);
		
		usuarios = usuarios.stream()
				.filter( u -> u.getPontos(tipoPonto) > 0 )
				.sorted(usuarioComparator.reversed())
				.collect(Collectors.toList());
		
		Map<String, Integer> ranking = new LinkedHashMap<>();
		usuarios.forEach( u -> ranking.put(u.getNome(), u.getPontos(tipoPonto)));
		
		return ranking;
	}

}
