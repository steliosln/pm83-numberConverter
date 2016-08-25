package converter;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class ConverterEngineTest {
	private String expected;
	private String actual;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("==Initializing Test Converter Engine Class==");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("==Tearing down Test Converter Engine Class==");
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("=Initializing test case=");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("=Tearing test case=\n");
	}

	@Test
	public void testConvertLessOrThree() {
		expected = "II";
		ConverterEngine converterEngine = new ConverterEngine();
		actual = converterEngine.convert(2);
		System.out.println("THREEORLESS::: Actual " + actual);
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void testConvertExtremes() {
		expected = "X";
		ConverterEngine converterEngine = new ConverterEngine();
		actual = converterEngine.convert(10);
		System.out.println("EXTREMES::: Actual " + actual);
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void testConvertFour() {
		expected = "IV";
		ConverterEngine converterEngine = new ConverterEngine();
		actual = converterEngine.convert(4);
		System.out.println("FOUR::: Actual " + actual);
		assertEquals(expected, actual);
		
	}

}
