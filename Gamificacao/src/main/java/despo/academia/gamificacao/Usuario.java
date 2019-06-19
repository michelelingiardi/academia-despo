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
		return nome + ":" + pontuacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((pontuacao == null) ? 0 : pontuacao.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (pontuacao == null) {
			if (other.pontuacao != null)
				return false;
		} else if (!pontuacao.equals(other.pontuacao))
			return false;
		return true;
	}	
}
