import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Before;
import org.junit.Test;

import despo.academia.CaixaEletronico;
import despo.academia.FalhaFuncionamentoHardwareException;

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
	public void sacarComSucesso() throws FalhaFuncionamentoHardwareException {
		CaixaEletronico c = new CaixaEletronico(hardwareMock, servicoRemotoMock);
		servicoRemotoMock.criarContaCorrente("1234", 100);
		assertEquals(100, servicoRemotoMock.recuperaSaldo());
		String mensagemRecebida = c.sacar(100);		
		assertTrue(servicoRemotoMock.chamouRecuperarConta);
		assertTrue(hardwareMock.chamouEntregarDinheiro);
		assertTrue(servicoRemotoMock.chamouPersistirConta);
		assertEquals(0, servicoRemotoMock.recuperaSaldo());
		assertEquals("Retire seu dinheiro", mensagemRecebida);
	}
	
	@Test
	public void sacarSaldoInsuficiente() throws FalhaFuncionamentoHardwareException {
		CaixaEletronico c = new CaixaEletronico(hardwareMock, servicoRemotoMock);
		servicoRemotoMock.criarContaCorrente("1234", 100);
		assertEquals(100, servicoRemotoMock.recuperaSaldo());
		String mensagemRecebida = c.sacar(101);
		assertTrue(servicoRemotoMock.chamouRecuperarConta);
		assertFalse(hardwareMock.chamouEntregarDinheiro);
		assertFalse(servicoRemotoMock.chamouPersistirConta);
		assertEquals(100, servicoRemotoMock.recuperaSaldo());
		assertEquals("Saldo insuficiente", mensagemRecebida);
	}
	
	@Test
	public void depositar() throws FalhaFuncionamentoHardwareException {
		CaixaEletronico c = new CaixaEletronico(hardwareMock, servicoRemotoMock);
		servicoRemotoMock.criarContaCorrente("1234", 100);
		assertEquals(100, servicoRemotoMock.recuperaSaldo());
		String mensagemRecebida = c.depositar(1);
		assertTrue(servicoRemotoMock.chamouRecuperarConta);
		assertTrue(hardwareMock.chamouLerEnvelope);		
		assertTrue(servicoRemotoMock.chamouPersistirConta);
		assertEquals(101, servicoRemotoMock.recuperaSaldo());
		assertEquals("Depósito recebido com sucesso", mensagemRecebida);
	}
	
	@Test
	public void saldo() {
		CaixaEletronico c = new CaixaEletronico(hardwareMock, servicoRemotoMock);
		servicoRemotoMock.criarContaCorrente("1234", 100);
		String mensagemRecebida = c.saldo();
		assertEquals("O saldo é R$100,00", mensagemRecebida);
	}
	
	@Test
	public void falhaDeHardwareAoEntregarDinheiro() {		
		CaixaEletronico c = new CaixaEletronico(hardwareMock, servicoRemotoMock);
		servicoRemotoMock.criarContaCorrente("1234", 100);
		hardwareMock.falhaNoHardware = true;
		assertThrows(FalhaFuncionamentoHardwareException.class, () -> c.sacar(100));		
		assertTrue(hardwareMock.chamouEntregarDinheiro);
		assertFalse(servicoRemotoMock.chamouPersistirConta);		
	}	
	

	@Test
	public void falhaDeHardwareAoLerEnvelope() {		
		CaixaEletronico c = new CaixaEletronico(hardwareMock, servicoRemotoMock);
		servicoRemotoMock.criarContaCorrente("1234", 100);
		hardwareMock.falhaNoHardware = true;
		assertThrows(FalhaFuncionamentoHardwareException.class, () -> c.depositar(100));	
		assertTrue(hardwareMock.chamouLerEnvelope);
		assertFalse(servicoRemotoMock.chamouPersistirConta);		
	}	
	
	@Test
	public void falhaDeHardwareAoLerCartao() {
		CaixaEletronico c = new CaixaEletronico(hardwareMock, servicoRemotoMock);
		servicoRemotoMock.criarContaCorrente("1234", 100);
		hardwareMock.falhaNoHardware = true;
		assertThrows(FalhaFuncionamentoHardwareException.class, () -> c.logar());
	}
}
