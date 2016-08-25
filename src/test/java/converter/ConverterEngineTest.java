package converter;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class ConverterEngineTest {

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
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testConvertLessOrThree() {
		String expected = "II";
		ConverterEngine converterEngine = new ConverterEngine();
		String conversion = converterEngine.convert(2);
		
		assertEquals(expected, conversion);
		
	}
	
	@Ignore("Not yet ready")
	@Test
	public void testConvertFour() {
		String expected = "IV";
		ConverterEngine converterEngine = new ConverterEngine();
		String conversion = converterEngine.convert(4);
		
		assertEquals(expected, conversion);
		
	}

}
