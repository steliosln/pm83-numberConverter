package converter;

public class Calculator {
	static String result = "";
	
	public static void main(String[] args) {
//		System.out.println(111%100);
//		System.out.println(11%10);
		System.out.println(20%10);
//		System.out.println(Math.pow(10, 2));//decimalPlaces - 1
		
//		System.out.println("calling print");
//		print(77);
//		System.out.println("the result is " + result);
	}
	
	public static void print(int number){
		System.out.println("inside print");
		
		ConverterEngine converterEngine = new ConverterEngine(number);
		int decimalPlaces = converterEngine.getDecimalPlaces();
		int remain = (int) (number % Math.pow(10, decimalPlaces));
		
		if(decimalPlaces == 0)
			result += number;//System.out.println(number+" ");
		else{
			result += (number - remain);
			//System.out.println((number - remain) + " ");
			print(remain);
		}
	}

}
