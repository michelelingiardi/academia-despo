package despo.academia.gamificacao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.ArrayMatching.arrayContainingInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
		String[] resultadoEsperado = { "mcmillan:moeda=20;estrela=150;" };
		
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
	
	@AfterEach
	private void excluirArquivo() throws IOException {
		Path arquivo = Paths.get(NOME_DO_ARQUIVO);
		Files.deleteIfExists(arquivo);
	}
}
