package despo.academia.gamificacao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

public class PlacarTest {
	
	ArmazenamentoMock mock;	
	Placar placar;
	
	public static final String ESTRELA = "estrela";
	public static final String MOEDA = "moeda";
	public static final String CURTIDA = "curtida";
	
	@BeforeEach
	public void criarPlacar() {
		mock = new ArmazenamentoMock();
		placar = new Placar(mock);
	}

	@Test
	public void registrarPontosParaUsuario() {
		placar.registrarPontoParaUsuario(ESTRELA, "guerra", 10);
		assertTrue(mock.chamouArmazenarUsuario);
		assertEquals(10, mock.recuperarPontuacaoUsuario(ESTRELA, "guerra"));
	}
	
	@Test
	public void registrarMaisPontosParaUsuario() {
		placar.registrarPontoParaUsuario(ESTRELA, "guerra", 10);
		placar.registrarPontoParaUsuario(ESTRELA, "guerra", 5);
		this.verificarPontuacaoUsuario(ESTRELA, "guerra", 15);		
	}
	
	@Test
	public void registrarTiposDePontosDiferentesParaUsuario() {
		placar.registrarPontoParaUsuario(ESTRELA, "guerra", 7);
		placar.registrarPontoParaUsuario(MOEDA, "guerra", 3);
		this.verificarPontuacaoUsuario(ESTRELA, "guerra", 7);
		this.verificarPontuacaoUsuario(MOEDA, "guerra", 3);
	}
	
	@Test
	public void recuperarPontuacaoDoUsuario() {
		placar.registrarPontoParaUsuario(ESTRELA, "guerra", 7);
		placar.registrarPontoParaUsuario(MOEDA, "guerra", 1);
		placar.registrarPontoParaUsuario(CURTIDA, "guerra", 0);
		
		Pontuacao pontuacaoEsperada = new Pontuacao();
		pontuacaoEsperada.adicionarPontos(ESTRELA, 7);
		pontuacaoEsperada.adicionarPontos(MOEDA, 1);
		
		Pontuacao pontuacao = placar.recuperarPontuacaoDoUsuario("guerra");
		
		assertThat(pontuacao, is(pontuacaoEsperada));
	}
	
	@Test
	public void recuperarRanking() {
		placar.registrarPontoParaUsuario(ESTRELA, "fernandes", 19);
		placar.registrarPontoParaUsuario(ESTRELA, "guerra", 25);
		placar.registrarPontoParaUsuario(ESTRELA, "rodrigo", 17);
		placar.registrarPontoParaUsuario(ESTRELA, "toco", 0);
		placar.registrarPontoParaUsuario(MOEDA, "fernandes", 1);
		Map<String, Integer> ranking = placar.recuperarRanking(ESTRELA);
		
		Map<String, Integer> rankingEsperado = new LinkedHashMap<>();
		rankingEsperado.put("guerra", 25);
		rankingEsperado.put("fernandes", 19);
		rankingEsperado.put("rodrigo", 17);
		
		assertThat(ranking, is(rankingEsperado));
	}
	
	@Test
	public void recuperarRankingSemPontuacaoZerada() {
		placar.registrarPontoParaUsuario(CURTIDA, "fernandes", 5);
		placar.registrarPontoParaUsuario(ESTRELA, "fernandes", 19);
		placar.registrarPontoParaUsuario(ESTRELA, "guerra", 25);
		placar.registrarPontoParaUsuario(ESTRELA, "rodrigo", 17);		
		placar.registrarPontoParaUsuario(MOEDA, "fernandes", 200);
		placar.registrarPontoParaUsuario(MOEDA, "mattos", 201);
		placar.registrarPontoParaUsuario(MOEDA, "toco", 0);
		Map<String, Integer> ranking = placar.recuperarRanking(MOEDA);
		
		Map<String, Integer> rankingEsperado = new LinkedHashMap<>();
		rankingEsperado.put("mattos", 201);
		rankingEsperado.put("fernandes", 200);
		
		assertThat(ranking, is(rankingEsperado));
	}
	
	private void verificarPontuacaoUsuario(String tipoPonto, String nomeUsuario, Integer valorEsperado) {		
		assertEquals(valorEsperado, mock.recuperarPontuacaoUsuario(tipoPonto, nomeUsuario));
	}
}
