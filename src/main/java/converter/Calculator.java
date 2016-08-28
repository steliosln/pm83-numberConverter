package converter;

public class Calculator {
	
	public static void main(String[] args) {
//		System.out.println(111%100);
//		System.out.println(11%10);
//		System.out.println(20%10);
//		System.out.println(Math.pow(10, 2));//decimalPlaces - 1

		ConverterEngine converterEngine = new ConverterEngine(77);
		converterEngine.print(77);
		String resulting = converterEngine.getResult();
		System.out.println(resulting);
		
		}
}
