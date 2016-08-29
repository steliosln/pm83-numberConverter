package converter;

public class Calculator {
	
	public static void main(String[] args) {
//		System.out.println(111%100);
//		System.out.println(11%10);
//		System.out.println(20%10);
//		System.out.println(Math.pow(10, 2));//decimalPlaces - 1

		ConverterEngine converterEngine = new ConverterEngine(77);
		converterEngine.print(19);
		System.out.println("\n\nthe numberInRoman: " + converterEngine.getNumberInRoman());
		System.out.println("\nthe result: " + converterEngine.getResult());
		System.out.println("\nthe resultOfDoConv: " + converterEngine.getResultOfDoConversion());
		
		/*
		for(int i=1; i< 23; i++){
			ConverterEngine converter = new ConverterEngine(i);
			converter.print(i);
			System.out.println("numberInRoman: " + i + " = " + converter.getNumberInRoman());
			System.out.println("resultOfDoConv: " + converterEngine.getResultOfDoConversion());
		}
		*/
	}
}
