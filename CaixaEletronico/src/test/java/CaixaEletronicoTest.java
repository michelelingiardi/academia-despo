import static org.junit.Assert.*;

import org.junit.Test;

import despo.academia.CaixaEletronico;

public class CaixaEletronicoTest {

	@Test
	public void logarComSucesso() {
		CaixaEletronico c = new CaixaEletronico();
		String mensagemRecebida = c.logar();
		assertEquals("Usuário Autenticado", mensagemRecebida);
	}
}
