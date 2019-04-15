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
				resultadoEsperado(new String[]{"nome"}),
				ConversorCamelCase.converterCamelCase("nome"));
	}

  @Test
	public void testCamelCaseLetraMaiuscula() {
	  assertEquals(
	      resultadoEsperado(new String[]{"nome"}),
	      ConversorCamelCase.converterCamelCase("Nome"));
	}
	
	@Test
	public void testCamelCaseComposto() {
	  assertEquals(
	      resultadoEsperado(new String[]{"nome","composto"}),
        ConversorCamelCase.converterCamelCase("nomeComposto"));
	}
	
  @Test
  public void testCamelCaseCompostoLetraMaiuscula() {
    assertEquals(
        resultadoEsperado(new String[]{"nome","composto"}),
        ConversorCamelCase.converterCamelCase("NomeComposto"));
  }
	
	@Test
	public void testCamelCaseSigla() {
    assertEquals(
        resultadoEsperado(new String[]{"CPF"}),
        ConversorCamelCase.converterCamelCase("CPF"));
	}
	
	@Test
	public void testCamelCaseSiglaComposto() {
	  assertEquals(
	      resultadoEsperado(new String[]{"numero","CPF"}),
        ConversorCamelCase.converterCamelCase("numeroCPF"));
    assertEquals(
        resultadoEsperado(new String[]{"numero","CPF","contribuinte"}),
        ConversorCamelCase.converterCamelCase("numeroCPFContribuinte"));
	}
	
  @Test
  public void testCamelCaseNumero() {
    assertEquals(
        resultadoEsperado(new String[]{"recupera","10","primeiros"}),
        ConversorCamelCase.converterCamelCase("recupera10Primeiros"));
  }
	 
  @Test
  public void testCamelCaseInvalidoNumeroNoInicio() {
    try { 
      ConversorCamelCase.converterCamelCase("10Primeiros");
      fail();
    } catch(NumeroNoInicioException e) {}
  }
  
  @Test
  public void testCamelCaseInvalidoCaractereEspecial() {
    try { 
      ConversorCamelCase.converterCamelCase("nome#Composto");
      fail();
    } catch(CaractereEspecialException e) {}
  }
  
  private ArrayList<String> resultadoEsperado(String[] itens) {
    return new ArrayList<String>(Arrays.asList(itens));
  }
}
