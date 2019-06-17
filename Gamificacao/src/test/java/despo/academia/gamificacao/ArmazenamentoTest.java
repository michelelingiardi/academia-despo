package despo.academia.gamificacao;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
}
