package despo.academia.gamificacao;

import java.util.HashMap;
import java.util.Map;

public class ArmazenamentoMock implements Armazenamento {
	Map<String, Usuario> usuarios = new HashMap<>();
	
	public boolean chamouArmazenarUsuario = false;
	
	public Integer recuperarPontuacaoUsuario(String tipoPonto, String nomeUsuario) {
		Usuario usuario = this.usuarios.get(nomeUsuario);
		if (null != usuario) {
			return usuario.getPontuacao(tipoPonto);
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
