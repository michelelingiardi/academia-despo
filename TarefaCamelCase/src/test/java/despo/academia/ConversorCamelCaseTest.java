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
}
