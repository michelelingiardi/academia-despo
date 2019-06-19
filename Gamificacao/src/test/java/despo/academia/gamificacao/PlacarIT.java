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
	public static final String NOME_DO_ARQUIVO 		= "placarTesteIntegracao";
	public static final String EXTENSAO_DO_ARQUIVO 	= ".txt";
	
	private static final String SEPARADOR_USUARIO 			= ":";
	private static final String SEPARADOR_TIPO_PONTO 		= ";";
	private static final String SEPARADOR_QUANTIDADE_PONTOS = "=";

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
		String[] resultadoEsperado = { USUARIO_1 + SEPARADOR_USUARIO + ESTRELA + SEPARADOR_QUANTIDADE_PONTOS + "150" + SEPARADOR_TIPO_PONTO + MOEDA + SEPARADOR_QUANTIDADE_PONTOS + "20" + SEPARADOR_TIPO_PONTO};
		
		Path arquivo = criarArquivo();
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
	public void registrarDiferentesPontosParaUsuariosDiversos() throws IOException {
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
		Path arquivo = criarArquivo();
		Files.createFile(arquivo);
		List<String> dadosDoArquivo = new ArrayList<>();
		dadosDoArquivo.add(USUARIO_1 + SEPARADOR_USUARIO + MOEDA + SEPARADOR_QUANTIDADE_PONTOS + "50" + SEPARADOR_TIPO_PONTO);
		dadosDoArquivo.add(USUARIO_2 + SEPARADOR_USUARIO + MOEDA + SEPARADOR_QUANTIDADE_PONTOS + "40" + SEPARADOR_TIPO_PONTO + ESTRELA + SEPARADOR_QUANTIDADE_PONTOS + "5" + SEPARADOR_TIPO_PONTO);
		dadosDoArquivo.add(USUARIO_3 + SEPARADOR_USUARIO + MOEDA + SEPARADOR_QUANTIDADE_PONTOS + "30" + SEPARADOR_TIPO_PONTO + ESTRELA + SEPARADOR_QUANTIDADE_PONTOS + "10" + SEPARADOR_TIPO_PONTO + TOPICO + SEPARADOR_QUANTIDADE_PONTOS + "15" + SEPARADOR_TIPO_PONTO);
		dadosDoArquivo.add(USUARIO_4 + SEPARADOR_USUARIO + CURTIDA + SEPARADOR_QUANTIDADE_PONTOS + "5" + SEPARADOR_TIPO_PONTO);
		Files.write(arquivo, dadosDoArquivo, StandardCharsets.UTF_8);
		
		Placar placar = new Placar(new ArmazenamentoArquivo(NOME_DO_ARQUIVO));
		Pontuacao pontuacaoObtida = placar.recuperarPontuacaoDoUsuario(USUARIO_3);
		
		assertEquals(ESTRELA + SEPARADOR_QUANTIDADE_PONTOS + "10" + SEPARADOR_TIPO_PONTO + MOEDA + SEPARADOR_QUANTIDADE_PONTOS + "30" + SEPARADOR_TIPO_PONTO + TOPICO + SEPARADOR_QUANTIDADE_PONTOS + "15" + SEPARADOR_TIPO_PONTO, pontuacaoObtida.toString());
	}
	
	@Test
	@DisplayName("Impedir registro de nome de usuário com caracteres especiais.")
	public void impedirNomeDeUsuarioComCaracteresEspeciais() {
		Placar placar = new Placar(new ArmazenamentoArquivo(NOME_DO_ARQUIVO));
		
		assertThrows(CaracteresInvalidosException.class, 
				() -> placar.registrarPontoParaUsuario(ESTRELA, "zaphod:beeblebrox", 10));
		assertThrows(CaracteresInvalidosException.class, 
				() -> placar.registrarPontoParaUsuario(ESTRELA, "arthurdent" + SEPARADOR_TIPO_PONTO, 10));
		assertThrows(CaracteresInvalidosException.class, 
				() -> placar.registrarPontoParaUsuario(ESTRELA, "=tricia", 10));
	}
	
	@Test
	@DisplayName("Impedir nomenclatura de pontos contendo caracteres especiais.")
	public void impedirPontosComCaracteresEspeciais() {
		Placar placar = new Placar(new ArmazenamentoArquivo(NOME_DO_ARQUIVO));
		
		assertThrows(CaracteresInvalidosException.class, 
				() -> placar.registrarPontoParaUsuario("=topico", USUARIO_1, 10));
		assertThrows(CaracteresInvalidosException.class, 
				() -> placar.registrarPontoParaUsuario("moed;a", USUARIO_1, 10));
		assertThrows(CaracteresInvalidosException.class, 
				() -> placar.registrarPontoParaUsuario("estrela:", USUARIO_1, 10));
	}
	
	@AfterEach
	private void excluirArquivo() throws IOException {
		Path arquivo = criarArquivo();
		Files.deleteIfExists(arquivo);
	}

	private Path criarArquivo() {
		return Paths.get(NOME_DO_ARQUIVO + EXTENSAO_DO_ARQUIVO);
	}
}
