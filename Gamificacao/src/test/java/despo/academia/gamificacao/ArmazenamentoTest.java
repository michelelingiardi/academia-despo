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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ArmazenamentoTest {

	public static final String ESTRELA = "estrela";
	public static final String MOEDA = "moeda";
	public static final String CURTIDA = "curtida";
	
	public static final String NOME_DO_ARQUIVO = "testeArmazenamentoPontuacao";
	public static final String EXTENSAO_DO_ARQUIVO = ".txt";
	
	private ArmazenamentoArquivo armazenamento;
	
	@BeforeEach
	public void inicializarArmazenamento() {
		armazenamento = new ArmazenamentoArquivo(NOME_DO_ARQUIVO);
	}
	
	@Test
	@DisplayName("Armazenar que um usuário recebeu uma quantidade de um tipo de ponto.")
	public void armazenarPontuacaoUsuario() throws IOException {
		Usuario usuario = new Usuario("guerra");
		usuario.adicionarPontos(ESTRELA, 7);
		armazenamento.armazenarPontuacao(usuario);
		Path arquivo = criarPath();
		assertTrue(Files.exists(arquivo));
		List<String> valorArmazenado = Files.readAllLines(arquivo, StandardCharsets.UTF_8);		
		assertThat(valorArmazenado.get(0), is("guerra:estrela=7;"));
	}
	
	@Test
	@DisplayName("Armazenar pontuação para usuário já existente.")
	public void armazenarPontuacaoUsuarioExistente() throws IOException {
		criarArquivo(Arrays.asList("marv:estrela=7;","arthur_dent:estrela=1;curtida=4;"));		
		Usuario usuario = new Usuario("marv");
		usuario.adicionarPontos(CURTIDA, 1);
		usuario.adicionarPontos(MOEDA, 10);
		armazenamento.armazenarPontuacao(usuario);
		
		Path arquivo = criarPath();
		List<String> valorArmazenado = Files.readAllLines(arquivo, StandardCharsets.UTF_8);
		
		assertThat(valorArmazenado.get(0), is("marv:curtida=1;moeda=10;"));
		assertThat(valorArmazenado.get(1), is("arthur_dent:estrela=1;curtida=4;"));		
	}
	
	private void criarArquivo(List<String> conteudoDoArquivo) throws IOException {
		Files.write(criarPath(), conteudoDoArquivo, StandardCharsets.UTF_8);
	}

	@Test
	@DisplayName("Retornar todos os usuários que já receberam algum tipo de ponto.")
	public void recuperarPontuacaoTodosUsuarios() throws IOException {		
		criarArquivo(Arrays.asList("marv:estrela=7;","arthur_dent:estrela=1;curtida=4;"));
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
		criarArquivo(Arrays.asList("marv:estrela=7;curtida=15;moeda=20","arthur_dent:topico=4;"));
		Set<String> resultadoEsperado = new HashSet<String>();
		resultadoEsperado.addAll(Arrays.asList(ESTRELA, CURTIDA, MOEDA));
		
		assertThat(armazenamento.recuperarTiposDePontosDoUsuario("marv"), is(resultadoEsperado));
	}
	
	@Test
	@DisplayName("Recuperar quantos pontos de um tipo tem um usuário.")
	public void recuperarQuantidadeDePontosDoUsuario() throws IOException {
		Files.write(criarPath(), Arrays.asList("marv:estrela=7;","arthur_dent:estrela=1;curtida=4;"), StandardCharsets.UTF_8);
		assertThat(armazenamento.recuperarPontos(ESTRELA, "arthur_dent"), is(1));
	}
	
	@Test
	@DisplayName("Arquivo inválido.")
	public void arquivoInvalido() throws IOException {
		Files.write(criarPath(), Arrays.asList("marv:"), StandardCharsets.UTF_8);
		assertThrows(ArquivoInvalidoException.class, () -> armazenamento.recuperarPontos(ESTRELA, "arthur_dent"));
	}
	
	@AfterEach
	private void excluirArquivo() throws IOException {
		Files.deleteIfExists(criarPath());
	}

	private Path criarPath() {
		return Paths.get(NOME_DO_ARQUIVO + EXTENSAO_DO_ARQUIVO);
	}
}
