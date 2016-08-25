package converter;

public class ConverterEngine {
	private String numberInRoman;
	ValueMapper five = ValueMapper.V;

	public ConverterEngine() {
		this.numberInRoman = "";
	}

	public String convert(int number){
		if(number < this.five.getValue())
			for (int i = 0; i < number; i++) {
				this.numberInRoman += ValueMapper.I.name();
			}
		
		return numberInRoman;
	}
}
