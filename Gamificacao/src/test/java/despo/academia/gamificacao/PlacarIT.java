package despo.academia.gamificacao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class PlacarIT {
	
	public static final String NOME_DO_ARQUIVO = "placarTesteIntegracao.txt";
	public static final String ESTRELA = "estrela";
	public static final String MOEDA = "moeda";
	
	@Test
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
		
		assertThat(resultadoEsperado, is(resultadoObtido.getTodosOsPontos()));
	}
	
	@AfterEach
	private void excluirArquivo() throws IOException {
		Path arquivo = Paths.get(NOME_DO_ARQUIVO);
		Files.deleteIfExists(arquivo);
	}
}
