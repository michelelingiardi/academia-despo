package despo.academia.gamificacao;

public interface Armazenamento {
	public Integer recuperarPontuacaoUsuario(String tipoPonto, String usuario);
	public void armazenarPontuacaoUsuario(Usuario usuario);
	public Usuario recuperarUsuario(String nomeUsuario);	
}
