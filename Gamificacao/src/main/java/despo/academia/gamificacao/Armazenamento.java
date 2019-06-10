package despo.academia.gamificacao;

public interface Armazenamento {
	public Integer recuperarPontuacaoUsuario(String tipoPonto, String usuario);
	public void armazenarPontuacaoUsuario(String tipoPonto, String usuario, int pontos);	
}
