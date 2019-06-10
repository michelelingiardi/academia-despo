package despo.academia.gamificacao;

import java.util.HashMap;
import java.util.Map;

public class ArmazenamentoMock implements Armazenamento {
	Map<String, Usuario> usuarios = new HashMap<>();

	public boolean chamouRecuperarPontuacaoUsuario = false;
	public boolean chamouArmazenarUsuario = false;
	
	@Override
	public Integer recuperarPontuacaoUsuario(String tipoPonto, String nomeUsuario) {
		this.chamouRecuperarPontuacaoUsuario = true;
		Usuario usuario = this.usuarios.get(nomeUsuario);
		if (null != usuario) {
			return usuario.getPontuacao(tipoPonto);
		}
		return 0;
			
	}

	@Override
	public void armazenarPontuacaoUsuario(String tipoPonto, String nomeUsuario, int pontos) {
		this.chamouArmazenarUsuario = true;
		Usuario usuario = usuarios.getOrDefault(nomeUsuario, new Usuario(nomeUsuario));
		usuario.adicionarPontos(tipoPonto, pontos);		
		this.usuarios.put(nomeUsuario, usuario);
	}

}
