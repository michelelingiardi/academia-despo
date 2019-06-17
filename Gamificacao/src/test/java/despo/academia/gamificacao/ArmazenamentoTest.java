package despo.academia.gamificacao;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ArmazenamentoTest {

	public static final String ESTRELA = "estrela";
	public static final String MOEDA = "moeda";
	public static final String CURTIDA = "curtida";
	
	ArmazenamentoArquivo armazenamento;
	
	@BeforeEach
	public void criarArquivo() {
		armazenamento = new ArmazenamentoArquivo("testeArmazenamentoPontuacao.txt");
	}
	
	@Test
	public void armazenarPontuacaoUsuario() {
		Usuario usuario = new Usuario("guerra");
		usuario.adicionarPontos(ESTRELA, 7);
		armazenamento.armazenarPontuacaoUsuario(usuario);
		Path arquivo = Paths.get("testeArmazenamentoPontuacao.txt");
		assertTrue(Files.exists(arquivo));
//		assertEquals(usuario, armazenamento.recuperarUsuario("guerra"));
	}
	
	@Test
	public void recuperarPontuacaoTodosUsuarios() throws IOException {		
		Path arquivo = Paths.get("testeArmazenamentoPontuacao.txt");
		Files.write(arquivo, Arrays.asList("marv:estrela=7;","arthur_dent:estrela=1;curtida=4;"), StandardCharsets.UTF_8);
		
		Usuario usuario1 = new Usuario("marv");
		usuario1.adicionarPontos(ESTRELA, 7);
		Usuario usuario2 = new Usuario("arthur_dent");
		usuario2.adicionarPontos(ESTRELA, 1);
		usuario2.adicionarPontos(CURTIDA, 4);
		
		List<Usuario> resultadoEsperado = new ArrayList<>();
		resultadoEsperado.add(usuario1);
		resultadoEsperado.add(usuario2);
		
		List<Usuario> resultadoObtido = armazenamento.recuperarUsuarios();		
		assertThat(resultadoObtido, is(resultadoEsperado));
	}
	
	@AfterEach
	private void excluirArquivo() throws IOException {
		Path arquivo = Paths.get("testeArmazenamentoPontuacao.txt");
		Files.deleteIfExists(arquivo);
	}
}
