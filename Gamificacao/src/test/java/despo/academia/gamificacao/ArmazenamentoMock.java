package despo.academia.gamificacao;

public class ArmazenamentoMock implements Armazenamento {

	public boolean chamouArmazenarUsuario = false;
	
	String tipoPonto;
	String usuario;
	int pontos;
	

	public int getPontuacaoUsuario(String tipoPonto, String usuario) {
		if (tipoPonto.equalsIgnoreCase(this.tipoPonto) && usuario.equalsIgnoreCase(this.usuario)) {
			return this.pontos;
		}
		return 0;
	}

	@Override
	public void armazenarPontoUsuario(String tipoPonto, String usuario, int pontos) {
		this.tipoPonto = tipoPonto;
		this.usuario = usuario;
		this.pontos = pontos;
		this.chamouArmazenarUsuario = true;		
	}

}
