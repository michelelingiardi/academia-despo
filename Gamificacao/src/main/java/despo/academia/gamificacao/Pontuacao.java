package despo.academia.gamificacao;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Pontuacao {
	private Map<String, Integer> pontos = new LinkedHashMap<>();
	
	public Integer getPontos(String tipoPonto) {
		return pontos.getOrDefault(tipoPonto, 0);
	}
	
	public Map<String, Integer> getTodosOsPontos() {
		return pontos;
	}
	
	public void adicionarPontos(String tipoPonto, Integer quantidadePontos) {
		validarNomenclaturaPonto(tipoPonto);
		Integer pontuacaoAtual = this.getPontos(tipoPonto);
		Integer novaPontuacao = pontuacaoAtual + quantidadePontos;
		if (novaPontuacao != 0) {
			pontos.put(tipoPonto, pontuacaoAtual + quantidadePontos);
		} else {
			pontos.remove(tipoPonto);
		}
		ordenarPontosPorNome();
	}

	private void validarNomenclaturaPonto(String tipoPonto) {
		if (!tipoPonto.matches("^[a-zA-Z0-9_]*$"))
			throw new CaracteresInvalidosException("Nomenclatura do ponto deve conter apenas caracteres alfanuméricos.");
	}

	private void ordenarPontosPorNome() {
		pontos = pontos.entrySet()
			.stream()
			.sorted(Map.Entry.<String, Integer>comparingByKey())
			.collect(
				Collectors.toMap(
					Map.Entry::getKey, Map.Entry::getValue, 
					(tipoPonto, quantidade) ->
						tipoPonto, LinkedHashMap::new
				)
			);
	}

	@Override
	public String toString() {
		StringBuilder pontosBuilder = new StringBuilder();
		pontos.forEach((tipoPonto, quantidade) -> pontosBuilder.append(tipoPonto + "=" + quantidade + ";"));
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
