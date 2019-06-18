package despo.academia.gamificacao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

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
	@DisplayName("Registrar um tipo de ponto para um usuário.")
	public void registrarPontosParaUsuario() {
		placar.registrarPontoParaUsuario(ESTRELA, "guerra", 10);
		assertTrue(mock.chamouArmazenarUsuario);
		assertEquals(10, mock.recuperarPontuacaoUsuario(ESTRELA, "guerra"));
	}
	
	@Test
	@DisplayName("Adicionar mais pontos de um mesmo tipo para um usuário.")
	public void registrarMaisPontosParaUsuario() {
		placar.registrarPontoParaUsuario(ESTRELA, "guerra", 10);
		placar.registrarPontoParaUsuario(ESTRELA, "guerra", 5);
		this.verificarPontuacaoUsuario(ESTRELA, "guerra", 15);		
	}
	
	@Test
	@DisplayName("Registrar mais de um tipo de ponto para um usuário.")
	public void registrarTiposDePontosDiferentesParaUsuario() {
		placar.registrarPontoParaUsuario(ESTRELA, "guerra", 7);
		placar.registrarPontoParaUsuario(MOEDA, "guerra", 3);
		this.verificarPontuacaoUsuario(ESTRELA, "guerra", 7);
		this.verificarPontuacaoUsuario(MOEDA, "guerra", 3);
	}
	
	@Test
	@DisplayName("Retornar todos os pontos de um usuário.")
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
	@DisplayName("Retornar ranking de um tipo de ponto, com a lista de usuário que possuem "
			+ "aquele ponto ordenados do que possui mais para o que possui menos.")
	public void recuperarRanking() {
		placar.registrarPontoParaUsuario(ESTRELA, "fernandes", 19);
		placar.registrarPontoParaUsuario(ESTRELA, "guerra", 25);
		placar.registrarPontoParaUsuario(ESTRELA, "rodrigo", 17);
		placar.registrarPontoParaUsuario(ESTRELA, "toco", 0);
		placar.registrarPontoParaUsuario(MOEDA, "fernandes", 1);
		Map<String, Integer> ranking = placar.recuperarRanking(ESTRELA);
		
		List<String> 	rankingUsuario 		= new ArrayList<String>(ranking.keySet());
		List<Integer> 	rankingPontuacao 	= new ArrayList<Integer>(ranking.values());
		
		List<String> 	rankingUsuarioEsperado 		= new ArrayList<String>(Arrays.asList("guerra", "fernandes", "rodrigo"));
		List<Integer> 	rakingPontuacaoEsperado 	= new ArrayList<Integer>(Arrays.asList(25, 19, 17));
		
		assertThat(rankingUsuario, is(rankingUsuarioEsperado));
		assertThat(rankingPontuacao, is(rakingPontuacaoEsperado));
	}
	
	@Test
	@DisplayName("Retornar ranking de usuários, desconsiderando usuário com pontuação zerada.")
	public void recuperarRankingSemPontuacaoZerada() {
		placar.registrarPontoParaUsuario(CURTIDA, 	"fernandes", 	5);
		placar.registrarPontoParaUsuario(ESTRELA, 	"fernandes", 	19);
		placar.registrarPontoParaUsuario(ESTRELA, 	"guerra", 		25);
		placar.registrarPontoParaUsuario(ESTRELA, 	"rodrigo", 		17);		
		placar.registrarPontoParaUsuario(MOEDA, 	"fernandes", 	200);
		placar.registrarPontoParaUsuario(MOEDA, 	"mattos", 		201);
		placar.registrarPontoParaUsuario(MOEDA, 	"toco", 		0);
		
		Map<String, Integer> ranking = placar.recuperarRanking(MOEDA);
		
		List<String> 	rankingUsuario 		= new ArrayList<String>(ranking.keySet());
		List<Integer> 	rankingPontuacao 	= new ArrayList<Integer>(ranking.values());
		
		List<String> 	rankingUsuarioEsperado 		= new ArrayList<String>(Arrays.asList("mattos", "fernandes"));
		List<Integer> 	rakingPontuacaoEsperado 	= new ArrayList<Integer>(Arrays.asList(201, 200));
		
		assertThat(rankingUsuario, is(rankingUsuarioEsperado));
		assertThat(rankingPontuacao, is(rakingPontuacaoEsperado));
	}
	
	private void verificarPontuacaoUsuario(String tipoPonto, String nomeUsuario, Integer valorEsperado) {		
		assertEquals(valorEsperado, mock.recuperarPontuacaoUsuario(tipoPonto, nomeUsuario));
	}
}
