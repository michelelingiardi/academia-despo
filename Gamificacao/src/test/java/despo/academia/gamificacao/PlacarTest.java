package despo.academia.gamificacao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PlacarTest {

	@Test
	public void registrarPontoParaUsuario() {
		ArmazenamentoMock mock = new ArmazenamentoMock();
		Placar placar = new Placar(mock);
		placar.registrarPontoParaUsuario("estrela", "guerra", 10);
		assertTrue(mock.chamouArmazenarUsuario);
		assertEquals(10, mock.getPontuacaoUsuario("estrela", "guerra"));
	}
}
