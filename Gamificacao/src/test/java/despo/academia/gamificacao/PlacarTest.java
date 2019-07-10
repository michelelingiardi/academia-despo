package despo.academia.gamificacao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

	public static final String USUARIO_1 = "mcmillan";
	public static final String USUARIO_2 = "fenchurch";
	public static final String USUARIO_3 = "prefect";
	public static final String USUARIO_4 = "ar_dent";
	public static final String USUARIO_5 = "marvin";

	@BeforeEach
	public void criarPlacar() {
		mock = new ArmazenamentoMock();
		placar = new Placar(mock);
	}

	@Test
	@DisplayName("Registrar um tipo de ponto para um usuário.")
	public void registrarPontosParaUsuario() {
		registrarPonto(ESTRELA, USUARIO_1, 10);
		assertTrue(mock.chamouArmazenarUsuario);
		assertEquals(10, mock.recuperarPontuacaoUsuario(ESTRELA, USUARIO_1));
	}

	@Test
	@DisplayName("Adicionar mais pontos de um mesmo tipo para um usuário.")
	public void registrarMaisPontosParaUsuario() {
		registrarPonto(ESTRELA, USUARIO_1, 10);
		registrarPonto(ESTRELA, USUARIO_1, 5);
		this.verificarPontuacaoUsuario(ESTRELA, USUARIO_1, 15);
	}

	@Test
	@DisplayName("Registrar mais de um tipo de ponto para um usuário.")
	public void registrarTiposDePontosDiferentesParaUsuario() {
		registrarPonto(ESTRELA, USUARIO_1, 7);
		registrarPonto(MOEDA, USUARIO_1, 3);
		this.verificarPontuacaoUsuario(ESTRELA, USUARIO_1, 7);
		this.verificarPontuacaoUsuario(MOEDA, USUARIO_1, 3);
	}

	@Test
	@DisplayName("Retornar todos os pontos de um usuário.")
	public void recuperarPontuacaoDoUsuario() {
		registrarPonto(ESTRELA, USUARIO_1, 7);
		registrarPonto(MOEDA, USUARIO_1, 1);
		registrarPonto(CURTIDA, USUARIO_1, 0);

		Pontuacao pontuacaoEsperada = new Pontuacao();
		pontuacaoEsperada.adicionarPontos(ESTRELA, 7);
		pontuacaoEsperada.adicionarPontos(MOEDA, 1);

		Pontuacao pontuacao = placar.recuperarPontuacaoDoUsuario(USUARIO_1);
		assertThat(pontuacao, is(pontuacaoEsperada));
	}

	@Test
	@DisplayName("Zerar pontuação do usuário.")
	public void zerarPontuacaoDoUsuario() {
		Usuario usuario = new Usuario(USUARIO_1);
		usuario.adicionarPontos(ESTRELA, 10);
		usuario.adicionarPontos(MOEDA, 5);
		mock.armazenarPontuacao(usuario);
		registrarPonto(ESTRELA, USUARIO_1, -10);

		Pontuacao pontuacaoEsperada = new Pontuacao();
		pontuacaoEsperada.adicionarPontos(MOEDA, 5);

		Pontuacao pontuacao = placar.recuperarPontuacaoDoUsuario(USUARIO_1);
		assertThat(pontuacao, is(pontuacaoEsperada));
	}

	@Test
	@DisplayName("Retornar ranking de um tipo de ponto, com a lista de usuário que possuem "
			+ "aquele ponto ordenados do que possui mais para o que possui menos.")
	public void recuperarRanking() {
		registrarPonto(ESTRELA, USUARIO_2, 19);
		registrarPonto(ESTRELA, USUARIO_1, 25);
		registrarPonto(ESTRELA, USUARIO_3, 17);
		registrarPonto(ESTRELA, USUARIO_4, 0);
		registrarPonto(MOEDA, USUARIO_2, 1);
		Map<String, Integer> ranking = placar.recuperarRanking(ESTRELA);

		List<String> rankingUsuario = new ArrayList<String>(ranking.keySet());
		List<Integer> rankingPontuacao = new ArrayList<Integer>(ranking.values());

		List<String> rankingUsuarioEsperado = new ArrayList<String>(Arrays.asList(USUARIO_1, USUARIO_2, USUARIO_3));
		List<Integer> rakingPontuacaoEsperado = new ArrayList<Integer>(Arrays.asList(25, 19, 17));

		assertThat(rankingUsuario, is(rankingUsuarioEsperado));
		assertThat(rankingPontuacao, is(rakingPontuacaoEsperado));
	}

	@Test
	@DisplayName("Retornar ranking de usuários, desconsiderando usuário com pontuação zerada.")
	public void recuperarRankingSemPontuacaoZerada() {
		registrarPonto(CURTIDA, USUARIO_2, 5);
		registrarPonto(ESTRELA, USUARIO_2, 19);
		registrarPonto(ESTRELA, USUARIO_1, 25);
		registrarPonto(ESTRELA, USUARIO_3, 17);
		registrarPonto(MOEDA, USUARIO_2, 200);
		registrarPonto(MOEDA, USUARIO_5, 201);
		registrarPonto(MOEDA, USUARIO_4, 0);

		Map<String, Integer> ranking = placar.recuperarRanking(MOEDA);

		List<String> rankingUsuario = new ArrayList<String>(ranking.keySet());
		List<Integer> rankingPontuacao = new ArrayList<Integer>(ranking.values());

		List<String> rankingUsuarioEsperado = new ArrayList<String>(Arrays.asList(USUARIO_5, USUARIO_2));
		List<Integer> rakingPontuacaoEsperado = new ArrayList<Integer>(Arrays.asList(201, 200));

		assertThat(rankingUsuario, is(rankingUsuarioEsperado));
		assertThat(rankingPontuacao, is(rakingPontuacaoEsperado));
	}

	private void verificarPontuacaoUsuario(String tipoPonto, String nomeUsuario, Integer valorEsperado) {
		assertEquals(valorEsperado, mock.recuperarPontuacaoUsuario(tipoPonto, nomeUsuario));
	}

	private void registrarPonto(String tipoPonto, String usuario, Integer quantidadePontos) {
		placar.registrarPontoParaUsuario(tipoPonto, usuario, quantidadePontos);
	}
}
