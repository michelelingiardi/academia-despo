package despo.academia;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import despo.academia.ConversorCamelCase;
import junit.framework.TestCase;

public class ConversorCamelCaseTest extends TestCase{
	@Test
	public void testCamelCaseSimples() {
		assertEquals(
				new ArrayList<String>(Arrays.asList("nome")),
				ConversorCamelCase.converterCamelCase("nome"));
	}
	
	@Test
	public void testCamelCaseLetraMaiuscula() {
	  assertEquals(
	      new ArrayList<String>(Arrays.asList("nome")),
	      ConversorCamelCase.converterCamelCase("Nome"));
	}
	
	@Test
	public void testCamelCaseComposto() {
	  assertEquals(
        new ArrayList<String>(Arrays.asList("nome","composto")),
        ConversorCamelCase.converterCamelCase("nomeComposto"));
	  assertEquals(
        new ArrayList<String>(Arrays.asList("nome","composto")),
        ConversorCamelCase.converterCamelCase("NomeComposto"));
	}
	
	@Test
	public void testCamelCaseSigla() {
    assertEquals(
        new ArrayList<String>(Arrays.asList("CPF")),
        ConversorCamelCase.converterCamelCase("CPF"));
	}
	
	@Test
	public void testCamelCaseSiglaComposto() {
	  assertEquals(
        new ArrayList<String>(Arrays.asList("numero","CPF")),
        ConversorCamelCase.converterCamelCase("numeroCPF"));
    assertEquals(
        new ArrayList<String>(Arrays.asList("numero","CPF","contribuinte")),
        ConversorCamelCase.converterCamelCase("numeroCPFContribuinte"));
	}
	
  @Test
  public void testCamelCaseNumero() {
    assertEquals(
        new ArrayList<String>(Arrays.asList("recupera","10","primeiros")),
        ConversorCamelCase.converterCamelCase("recupera10Primeiros"));
  }
	 
  @Test(expected=NumeroNoInicioException.class)
  public void testCamelCaseInvalidoNumeroNoInicio() {
    ConversorCamelCase.converterCamelCase("10Primeiros")
  }
}
