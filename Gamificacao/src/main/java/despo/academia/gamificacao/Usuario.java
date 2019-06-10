package despo.academia.gamificacao;

import java.util.HashMap;
import java.util.Map;

public class Usuario {
	private Map<String, Integer> pontuacao = new HashMap<>();
	private String nome;	
	
	public Usuario(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getPontuacao(String tipoPonto) {
		return pontuacao.getOrDefault(tipoPonto, 0);
	}

	public void adicionarPontos(String tipoPonto, Integer pontos) {
		Integer pontuacaoAtual = this.getPontuacao(tipoPonto);
		pontuacao.put(tipoPonto, pontuacaoAtual + pontos);
	}
	
	
}
