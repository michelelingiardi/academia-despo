import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;
import junit.framework.TestCase;

public class CamelCaseTest extends TestCase{
	@Test
	public void testCamelCaseSimples() {
		assertEquals(
				new ArrayList<String>(Arrays.asList("nome")),
				ConversorCamelCase.converterCamelCase("nome"));
	}
}
