package despo.academia.gamificacao;

public class ArmazenamentoMock implements Armazenamento {
	
	String tipoPonto;
	String usuario;
	int pontos;

	public boolean chamouRecuperarPontuacaoUsuario = false;
	public boolean chamouArmazenarUsuario = false;
	
	public int recuperarPontuacaoUsuario(String tipoPonto, String usuario) {
		this.chamouRecuperarPontuacaoUsuario = true;
		if (tipoPonto.equalsIgnoreCase(this.tipoPonto) && usuario.equalsIgnoreCase(this.usuario)) {
			return this.pontos;
		}
		return 0;
	}

	@Override
	public void armazenarPontuacaoUsuario(String tipoPonto, String usuario, int pontos) {
		this.tipoPonto = tipoPonto;
		this.usuario = usuario;
		this.pontos = pontos;
		this.chamouArmazenarUsuario = true;		
	}

}
