package despo.academia.gamificacao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PlacarTest {
	
	ArmazenamentoMock mock;

	@Test
	public void registrarPontosParaUsuario() {
		mock = new ArmazenamentoMock();
		Placar placar = new Placar(mock);
		placar.registrarPontoParaUsuario("estrela", "guerra", 10);
		assertTrue(mock.chamouArmazenarUsuario);
		assertEquals(10, mock.recuperarPontuacaoUsuario("estrela", "guerra"));
	}
	
	@Test
	public void registrarMaisPontosParaUsuario() {
		mock = new ArmazenamentoMock();
		Placar placar = new Placar(mock);
		placar.registrarPontoParaUsuario("estrela", "guerra", 10);
		placar.registrarPontoParaUsuario("estrela", "guerra", 5);
		this.verificarPontuacaoUsuario("estrela", "guerra", 15);		
	}
	
	@Test
	public void registrarTiposDePontosDiferentesParaUsuario() {
		mock = new ArmazenamentoMock();
		Placar placar = new Placar(mock);
		placar.registrarPontoParaUsuario("estrela", "guerra", 7);
		placar.registrarPontoParaUsuario("moeda", "guerra", 3);
		this.verificarPontuacaoUsuario("estrela", "guerra", 7);
		this.verificarPontuacaoUsuario("moeda", "guerra", 3);
	}
	
	private void verificarPontuacaoUsuario(String tipoPonto, String nomeUsuario, Integer valorEsperado) {
		assertEquals(valorEsperado, mock.recuperarPontuacaoUsuario(tipoPonto, nomeUsuario));
	}
}
