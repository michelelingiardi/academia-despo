import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import despo.academia.CaixaEletronico;

public class CaixaEletronicoTest {
	HardwareMock hardwareMock;
	
	@Before
	public void before() {
		hardwareMock = new HardwareMock();
	}
	
	@Test
	public void logarComSucesso() {
		CaixaEletronico c = new CaixaEletronico(hardwareMock);
		String mensagemRecebida = c.logar();
		assertEquals("Usuário Autenticado", mensagemRecebida);
	}

	@Test
	public void logarComErro() {
		hardwareMock.usuarioInvalido = true;
		CaixaEletronico c = new CaixaEletronico(hardwareMock);
		String mensagemRecebida = c.logar();		
		assertEquals("Não foi possível autenticar o usuário", mensagemRecebida);
	}
	
}
