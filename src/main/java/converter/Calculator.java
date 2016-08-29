package converter;

public class Calculator {
	
	public static void main(String[] args) {
//		System.out.println(111%100);
//		System.out.println(11%10);
//		System.out.println(6%1);
//		System.out.println(Math.pow(10, 2));//decimalPlaces - 1

		ConverterEngine converterEngine = new ConverterEngine(7);
//		converterEngine.print(44);
//		System.out.println("\n\nthe numberInRoman: " + converterEngine.getNumberInRoman());
//		System.out.println("\nthe result: " + converterEngine.getResult());
//		System.out.println("\nthe resultOfDoConv: " + converterEngine.getResultOfDoConversion());
		System.out.println("\nthe resultOfDoConv: " + converterEngine.execute());
		
		/*
		for(int i=1; i< 10; i++){
			ConverterEngine converter = new ConverterEngine(i);
			converter.print(i);
			System.out.print("numberInRoman: " + i + " = " + converter.getNumberInRoman());
			System.out.println(" resultOfDoConv: " + converterEngine.getResultOfDoConversion());
		}
		**/
		
	}
}
