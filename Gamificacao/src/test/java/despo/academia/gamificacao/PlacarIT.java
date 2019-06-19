package despo.academia.gamificacao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.ArrayMatching.arrayContainingInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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
	public void registrarPontosParaUsuario() throws IOException {
		String[] resultadoEsperado = { USUARIO_1 + ":" + ESTRELA + "=150;" + MOEDA + "=20;"};
		
		Path arquivo = Paths.get(NOME_DO_ARQUIVO);
		assertFalse(Files.exists(arquivo));
		
		Placar placar = new Placar(new ArmazenamentoArquivo(NOME_DO_ARQUIVO));
		placar.registrarPontoParaUsuario(ESTRELA, USUARIO_1, 100);
		placar.registrarPontoParaUsuario(ESTRELA, USUARIO_1, 50);
		placar.registrarPontoParaUsuario(MOEDA, USUARIO_1, 20);
		
		assertTrue(Files.exists(arquivo));
		assertThat(Files.readAllLines(arquivo).toArray(),
				arrayContainingInAnyOrder((Object[])resultadoEsperado));
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
//		placar.registrarPontoParaUsuario(TOPICO, 	USUARIO_3, -1);
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
		u3.adicionarPontos(TOPICO,	40);
		u4.adicionarPontos(MOEDA,	10);
		
		assertThat(resultadoObtido.toArray(), arrayContainingInAnyOrder(u1, u2, u3, u4));
	}
	
	@Test
	@DisplayName("Recuperar pontuação de determinado usuário.")
	public void recuperarPontuacaoUsuario() throws IOException {
		Path arquivo = Paths.get(NOME_DO_ARQUIVO);
		Files.createFile(arquivo);
		List<String> dadosDoArquivo = new ArrayList<>();
		dadosDoArquivo.add(USUARIO_1 + ":" + MOEDA + "=50;");
		dadosDoArquivo.add(USUARIO_2 + ":" + MOEDA + "=40;" + ESTRELA + "=5;");
		dadosDoArquivo.add(USUARIO_3 + ":" + MOEDA + "=30;" + ESTRELA + "=10;" + TOPICO + "=15;");
		dadosDoArquivo.add(USUARIO_4 + ":" + CURTIDA + "=5;");
		Files.write(arquivo, dadosDoArquivo, StandardCharsets.UTF_8);
		
		Placar placar = new Placar(new ArmazenamentoArquivo(NOME_DO_ARQUIVO));
		Pontuacao pontuacaoObtida = placar.recuperarPontuacaoDoUsuario(USUARIO_3);
		
		assertEquals(ESTRELA + "=10;" + MOEDA + "=30;" + TOPICO + "=15;", pontuacaoObtida.toString());
	}
	
	@Test
	@DisplayName("Impedir registro de nome de usuário com caracteres especiais.")
	public void impedirNomeDeUsuarioComCaracteresEspeciais() {
		Placar placar = new Placar(new ArmazenamentoArquivo(NOME_DO_ARQUIVO));
		
		assertThrows(CaracteresInvalidosException.class, 
				() -> placar.registrarPontoParaUsuario(ESTRELA, "zaphod:beeblebrox", 10));
		assertThrows(CaracteresInvalidosException.class, 
				() -> placar.registrarPontoParaUsuario(ESTRELA, "arthurdent;", 10));
		assertThrows(CaracteresInvalidosException.class, 
				() -> placar.registrarPontoParaUsuario(ESTRELA, "=tricia", 10));
	}
	
	@AfterEach
	private void excluirArquivo() throws IOException {
		Path arquivo = Paths.get(NOME_DO_ARQUIVO);
		Files.deleteIfExists(arquivo);
	}
}
