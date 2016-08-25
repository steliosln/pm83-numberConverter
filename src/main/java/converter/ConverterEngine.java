package converter;

public class ConverterEngine {
	private String numberInRoman;
	private ValueMapper one = ValueMapper.I;
	private ValueMapper five = ValueMapper.V;
	private ValueMapper ten = ValueMapper.X;
	private ValueMapper fifty = ValueMapper.L;
	private ValueMapper hundred = ValueMapper.C;
	private ValueMapper fiveHundred = ValueMapper.D;
	private ValueMapper thousand = ValueMapper.M;
	boolean doneConversion;

	public boolean isDoneConversion() {
		return doneConversion;
	}

	public void setDoneConversion() {
		this.doneConversion = true;
	}

	public ConverterEngine() {
		this.numberInRoman = "";
		this.doneConversion = false;
	}
	
	public String getNumberInRoman() {
		return numberInRoman;
	}
	
	public void setNumberInRoman(int numberOfLetters, String letter) {
		for (int i = 0; i < numberOfLetters; i++) {
			this.numberInRoman += letter;
		}		
	}


	public String convert(int number) {
		this.convertExtremities(number);
		
		if (!this.isDoneConversion()){
			
			
			if (number < five.getValue())
				this.setNumberInRoman(number, one.name());
	/*			for (int i = 0; i < number; i++) {
					//this.numberInRoman += one.name();
					writeRoman(2, one.name());
				}*/
		}

		return numberInRoman;
	}
	
	public void writeRoman(int numberOfLetters, String letter){
				
	}

	/**
	 * Converts the extremities Limit to 1000
	 * @param number the number to be checked
	 * @return the Roman equivalent or an empty string if number is not an extremity number
	 */
	public String convertExtremities(int number) {
		for (ValueMapper values : ValueMapper.values()) {
			if (number == values.getValue())
				numberInRoman = values.name();
		}
		if (!this.numberInRoman.isEmpty())
			this.setDoneConversion();
		return numberInRoman;
	}
}
