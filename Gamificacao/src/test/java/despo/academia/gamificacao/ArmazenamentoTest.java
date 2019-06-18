package despo.academia.gamificacao;

import static org.junit.jupiter.api.Assertions.assertThrows;
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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ArmazenamentoTest {

	public static final String ESTRELA = "estrela";
	public static final String MOEDA = "moeda";
	public static final String CURTIDA = "curtida";
	
	public static final String NOME_DO_ARQUIVO = "testeArmazenamentoPontuacao.txt";
	
	ArmazenamentoArquivo armazenamento;
	
	@BeforeEach
	public void inicializarArmazenamento() {
		armazenamento = new ArmazenamentoArquivo(NOME_DO_ARQUIVO);
	}
	
	@Test
	@DisplayName("Armazenar que um usuário recebeu uma quantidade de um tipo de ponto.")
	public void armazenarPontuacaoUsuario() {
		Usuario usuario = new Usuario("guerra");
		usuario.adicionarPontos(ESTRELA, 7);
		armazenamento.armazenarPontuacao(usuario);
		Path arquivo = Paths.get(NOME_DO_ARQUIVO);
		assertTrue(Files.exists(arquivo));
		assertThat(usuario.toString(), is(armazenamento.recuperarUsuario("guerra").toString()));
	}
	
	@Test
	@DisplayName("Retornar todos os usuários que já receberam algum tipo de ponto.")
	public void recuperarPontuacaoTodosUsuarios() throws IOException {		
		Path arquivo = Paths.get(NOME_DO_ARQUIVO);
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
		assertThat(resultadoObtido.toString(), is(resultadoEsperado.toString()));
	}
	
	@Test
	@DisplayName("Retornar todos os tipos de ponto que já foram registrados para algum usuário.")
	public void recuperarPontuacaoUsuario() throws IOException {
		Path arquivo = Paths.get(NOME_DO_ARQUIVO);
		Files.write(arquivo, Arrays.asList("marv:estrela=7;","arthur_dent:estrela=1;curtida=4;"), StandardCharsets.UTF_8);
		
		Usuario resultadoEsperado = new Usuario("marv");
		resultadoEsperado.adicionarPontos(ESTRELA, 7);
		
		assertThat(armazenamento.recuperarUsuario("marv").toString(), is(resultadoEsperado.toString()));
	}
	
	@Test
	@DisplayName("Busca por usuário inexistente no arquivo.")
	public void recuperarUsuarioNaoExistente() throws IOException {
		Path arquivo = Paths.get(NOME_DO_ARQUIVO);
		Files.write(arquivo, Arrays.asList("marv:estrela=7;","arthur_dent:estrela=1;curtida=4;"), StandardCharsets.UTF_8);
		
		assertThrows(UsuarioInexistenteException.class, () -> { armazenamento.recuperarUsuario("zaphod"); });
	}
	
	@Test
	@DisplayName("Recuperar quantos pontos de um tipo tem um usuário.")
	public void recuperarQuantidadeDePontosDoUsuario() throws IOException {
		Path arquivo = Paths.get(NOME_DO_ARQUIVO);
		Files.write(arquivo, Arrays.asList("marv:estrela=7;","arthur_dent:estrela=1;curtida=4;"), StandardCharsets.UTF_8);
		
		assertThat(armazenamento.recuperarPontos(ESTRELA, "arthur_dent"), is(1));
	}
	
	@AfterEach
	private void excluirArquivo() throws IOException {
		Path arquivo = Paths.get("testeArmazenamentoPontuacao.txt");
		Files.deleteIfExists(arquivo);
	}
}
