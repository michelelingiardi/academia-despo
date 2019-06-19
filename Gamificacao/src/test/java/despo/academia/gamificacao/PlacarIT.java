package despo.academia.gamificacao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlacarIT {
	
	public static final String NOME_DO_ARQUIVO = "placarTesteIntegracao.txt";
	public static final String ESTRELA 	= "estrela";
	public static final String MOEDA 	= "moeda";
	public static final String TOPICO 	= "topico";
	public static final String CURTIDA 	= "curtida";
	
	public static final String USUARIO_1 = "mcmillan";
	public static final String USUARIO_2 = "fenchurch";
	public static final String USUARIO_3 = "prefect";
	public static final String USUARIO_4 = "ar_dent";
	
	
	@Test
	@DisplayName("Registrar diferentes pontos para usuário.")
	public void registrarPontosParaUsuario() {
		Map<String, Integer> resultadoEsperado = new HashMap<>();
		resultadoEsperado.put(ESTRELA, 150);
		resultadoEsperado.put(MOEDA, 20);

		Placar placar = new Placar(new ArmazenamentoArquivo(NOME_DO_ARQUIVO));
		String nomeUsuario = "mcmillan";		
		placar.registrarPontoParaUsuario(ESTRELA, nomeUsuario, 100);
		placar.registrarPontoParaUsuario(ESTRELA, nomeUsuario, 50);
		placar.registrarPontoParaUsuario(MOEDA, nomeUsuario, 20);
		
		Pontuacao resultadoObtido = placar.recuperarPontuacaoDoUsuario(nomeUsuario);
		
		assertThat(resultadoObtido.getTodosOsPontos(), is(resultadoEsperado));
	}
	
	@Test
	@DisplayName("Registrar diferentes tipos de ponto para usuários distintos.")
	public void registrarDiferentesPontosParaUsuariosDiversos() {
		ArmazenamentoArquivo armazenamento = new ArmazenamentoArquivo(NOME_DO_ARQUIVO);
		Placar placar = new Placar(armazenamento);
		placar.registrarPontoParaUsuario(ESTRELA, 	USUARIO_1,  0);
		placar.registrarPontoParaUsuario(MOEDA, 	USUARIO_2, 20);
		placar.registrarPontoParaUsuario(MOEDA, 	USUARIO_1,  5);
		placar.registrarPontoParaUsuario(TOPICO, 	USUARIO_3, 40);
		placar.registrarPontoParaUsuario(TOPICO, 	USUARIO_3, -1);
		placar.registrarPontoParaUsuario(ESTRELA, 	USUARIO_2, 40);
		placar.registrarPontoParaUsuario(MOEDA, 	USUARIO_2, 30);
		placar.registrarPontoParaUsuario(TOPICO, 	USUARIO_2, 60);
		placar.registrarPontoParaUsuario(MOEDA, 	USUARIO_4, 10);
		
		List<Usuario> resultadoObtido = armazenamento.recuperarUsuarios();
		
		Usuario u1 = new Usuario(USUARIO_1);
		Usuario u2 = new Usuario(USUARIO_2);
		Usuario u3 = new Usuario(USUARIO_3);
		Usuario u4 = new Usuario(USUARIO_4);
		u1.adicionarPontos(MOEDA, 	 5);
		u2.adicionarPontos(MOEDA, 	50);
		u2.adicionarPontos(ESTRELA, 40);
		u2.adicionarPontos(TOPICO, 	60);
		u3.adicionarPontos(TOPICO,	39);
		u4.adicionarPontos(MOEDA,	10);
		List<Usuario> resultadoEsperado = new ArrayList<>(Arrays.asList(u1, u2, u3, u4));
		
		assertThat(resultadoObtido, is(resultadoEsperado));
	}
	
	@AfterEach
	private void excluirArquivo() throws IOException {
		Path arquivo = Paths.get(NOME_DO_ARQUIVO);
		Files.deleteIfExists(arquivo);
	}
}
