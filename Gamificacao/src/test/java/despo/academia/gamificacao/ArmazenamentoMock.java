package despo.academia.gamificacao;

import java.util.HashMap;
import java.util.Map;

public class ArmazenamentoMock implements Armazenamento {
	Map<String, Usuario> usuarios = new HashMap<>();
	
	public boolean chamouArmazenarUsuario = false;
	
	public Integer recuperarPontuacaoUsuario(String tipoPonto, String nomeUsuario) {
		
		// TODO refactor Assert valor esperado pontuaç/ão
		Usuario usuario = this.usuarios.get(nomeUsuario);
		if (null != usuario && null != usuario.getPontuacao()) {
			return usuario.getPontuacao().getPontos(tipoPonto);
		}
		return 0;
	}
	
	@Override
	public Usuario recuperarUsuario(String nomeUsuario) {
		return this.usuarios.getOrDefault(nomeUsuario, new Usuario(nomeUsuario));
	}

	@Override
	public void armazenarPontuacaoUsuario(Usuario usuario) {
		this.chamouArmazenarUsuario = true;
		this.usuarios.put(usuario.getNome(), usuario);
	}

}
