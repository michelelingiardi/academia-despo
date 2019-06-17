package despo.academia.gamificacao;

public class Usuario {
	private Pontuacao pontuacao;
	private String nome;	
	
	public Usuario(String nome) {
		this.nome = nome;
		this.pontuacao = new Pontuacao();
	}

	public String getNome() {
		return nome;
	}
	
	public Pontuacao getPontuacao() {
		return this.pontuacao;
	}
	
	public Integer getPontos(String tipoPonto) {
		return pontuacao.getPontos(tipoPonto);
	}
	
	public void adicionarPontos(String tipoPonto, Integer quantidadePontos) {
		if (quantidadePontos > 0) {
			this.pontuacao.adicionarPontos(tipoPonto, quantidadePontos);
		}
	}
	
	@Override
	public String toString() {
//		return "Usuario [pontuacao=" + pontuacao + ", nome=" + nome + "]";
		return nome + ":" + pontuacao;
	}
	
	
}
