package despo.academia.gamificacao;

public interface Armazenamento {
	public int recuperarPontuacaoUsuario(String tipoPonto, String usuario);
	public void armazenarPontuacaoUsuario(String tipoPonto, String usuario, int pontos);	
}
