package despo.academia.gamificacao;

import java.util.HashMap;
import java.util.Map;

public class Pontuacao {
	private Map<String, Integer> pontos = new HashMap<>();
	
	public Integer getPontos(String tipoPonto) {
		return pontos.getOrDefault(tipoPonto, 0);
	}
	
	public Map<String, Integer> getTodosOsPontos() {
		return pontos;
	}
	
	public void adicionarPontos(String tipoPonto, Integer quantidadePontos) {
		Integer pontuacaoAtual = this.getPontos(tipoPonto);
		pontos.put(tipoPonto, pontuacaoAtual + quantidadePontos);	
	}

	@Override
	public String toString() {
		StringBuilder pontosBuilder = new StringBuilder();
		pontos.forEach((tipoPonto, quantidade) -> {
			pontosBuilder.append(tipoPonto + "=" + quantidade + ";");
		});
		return pontosBuilder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pontos == null) ? 0 : pontos.hashCode());
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
		Pontuacao other = (Pontuacao) obj;
		if (pontos == null) {
			if (other.pontos != null)
				return false;
		} else if (pontos.entrySet().stream()
		      .allMatch(e -> e.getValue().equals(other.pontos.get(e.getKey())))) {
			return true;
		} else if (!pontos.equals(other.pontos)) {
			return false;
		}
		return true;
	}
	
	

}
