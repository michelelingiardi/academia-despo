package despo.academia.gamificacao;

import java.util.List;
import java.util.Set;

public interface Armazenamento {
	public void armazenarPontuacao(Usuario usuario);
	public Usuario recuperarUsuario(String nomeUsuario);
	public List<Usuario> recuperarUsuarios();
	public Integer recuperarPontos(String tipoPonto, String nomeUsuario);
	public Set<String> recuperarTiposDePontosDoUsuario(String nomeUsuario);
}
