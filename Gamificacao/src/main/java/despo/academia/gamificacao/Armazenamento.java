package despo.academia.gamificacao;

public interface Armazenamento {
	public void armazenarPontuacaoUsuario(Usuario usuario);
	public Usuario recuperarUsuario(String nomeUsuario);	
}
