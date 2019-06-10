package despo.academia.gamificacao;

public interface Armazenamento {
	public int getPontuacaoUsuario(String tipoPonto, String usuario);
	public void armazenarPontoUsuario(String tipoPonto, String usuario, int pontos);	
}
