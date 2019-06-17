package despo.academia.gamificacao;

import org.junit.jupiter.api.Test;

public class ArmazenamentoTest {

	public static final String ESTRELA = "estrela";
	public static final String MOEDA = "moeda";
	public static final String CURTIDA = "curtida";
	
	@Test
	public void armazenarPontuacaoUsuario() {
		Usuario usuario = new Usuario("guerra");
		ArmazenamentoArquivo armazenamento = new ArmazenamentoArquivo();
		usuario.adicionarPontos(ESTRELA, 7);
		armazenamento.armazenarPontuacaoUsuario(usuario);
		
		assertEquals(usuario, armazenamento.recuperaUsuario("guerra"));
	}
}
