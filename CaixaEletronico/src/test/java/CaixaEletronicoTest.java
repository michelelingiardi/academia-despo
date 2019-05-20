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
		assertTrue(hardwareMock.chamouPegarNumeroDaContaCartao);
		assertEquals("Usuario Autenticado", mensagemRecebida);
	}

	@Test
	public void logarComErro() {
		hardwareMock.usuarioInvalido = true;
		CaixaEletronico c = new CaixaEletronico(hardwareMock, servicoRemotoMock);
		String mensagemRecebida = c.logar();
		assertTrue(hardwareMock.chamouPegarNumeroDaContaCartao);
		assertEquals("Nao foi possivel autenticar o usuario", mensagemRecebida);
	}
	
	@Test
	public void sacarComSucesso() {
		CaixaEletronico c = new CaixaEletronico(hardwareMock, servicoRemotoMock);
		servicoRemotoMock.saldo = 100;
		assertEquals(100, servicoRemotoMock.recuperaSaldo());
		String mensagemRecebida = c.sacar(100);
		assertEquals(0, servicoRemotoMock.recuperaSaldo());
		assertTrue(hardwareMock.chamouEntregarDinheiro);
		assertEquals("Retire seu dinheiro", mensagemRecebida);
	}
	
}
