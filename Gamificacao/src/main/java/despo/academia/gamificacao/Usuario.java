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

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Pontuacao getPontuacao() {
		return this.pontuacao;
	}
	
	public void adicionarPontos(String tipoPonto, Integer quantidadePontos) {
		if (quantidadePontos > 0) {
			this.pontuacao.adicionarPontos(tipoPonto, quantidadePontos);
		}
	}

	@Override
	public String toString() {
		return "Usuario [pontuacao=" + pontuacao + ", nome=" + nome + "]";
	}
	
	
}
