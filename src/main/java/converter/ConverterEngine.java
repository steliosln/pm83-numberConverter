package converter;

public class ConverterEngine {
	private String numberInRoman;

	public ConverterEngine() {
		this.numberInRoman = "";
	}

	public String convert(int number){
		if(number<=3)
			for (int i = 0; i < number; i++) {
				this.numberInRoman +="I";
			}
		
		return numberInRoman;
	}
}
