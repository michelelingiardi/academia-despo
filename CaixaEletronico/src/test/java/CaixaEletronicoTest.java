import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import despo.academia.CaixaEletronico;

public class CaixaEletronicoTest {
	HardwareMock hardwareMock;
	ServicoRemotoMock servicoRemotoMock;
	
	@Before
	public void before() {
		hardwareMock = new HardwareMock();
		servicoRemotoMock = new ServicoRemotoMock();
	}
	
	@Test
	public void logarComSucesso() {
		CaixaEletronico c = new CaixaEletronico(hardwareMock, servicoRemotoMock);
		String mensagemRecebida = c.logar();
		assertEquals("Usu�rio Autenticado", mensagemRecebida);
	}

	@Test
	public void logarComErro() {
		hardwareMock.usuarioInvalido = true;
		CaixaEletronico c = new CaixaEletronico(hardwareMock, servicoRemotoMock);
		String mensagemRecebida = c.logar();		
		assertEquals("N�o foi poss�vel autenticar o usu�rio", mensagemRecebida);
	}
	
	@Test
	public void sacarComSucesso() {
		CaixaEletronico c = new CaixaEletronico(hardwareMock, servicoRemotoMock);		
		String mensagemRecebida = c.sacar();
		
		assertEquals("Retire seu dinheiro", mensagemRecebida);
	}
	
}
