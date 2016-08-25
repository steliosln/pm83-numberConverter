package converter;

public class ConverterEngine {
	private ValueMapper one = ValueMapper.I;
	private ValueMapper five = ValueMapper.V;
	private ValueMapper ten = ValueMapper.X;
	private ValueMapper fifty = ValueMapper.L;
	private ValueMapper hundred = ValueMapper.C;
	private ValueMapper fiveHundred = ValueMapper.D;
	private ValueMapper thousand = ValueMapper.M;
	private boolean doneConversion;
	private String numberInRoman;

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
	
	/**
	 * Adds/appends a passed letter to the {@link #numberInRoman}
	 * @param numberOfLetters the amount of times the letter is to be added
	 * @param letter the letter to add
	 */
	public void setNumberInRoman(int numberOfLetters, String letter) {
		for (int i = 0; i < numberOfLetters; i++) {
			this.numberInRoman += letter;
		}		
	}

	public String convert(int number) {
		this.convertExtremities(number);
		
		if (!this.isDoneConversion()){
			//if(number > one.getValue())
			if (number < five.getValue()){
				if(number == five.getValue() - 1){
					this.setNumberInRoman(1, one.name());
					this.setNumberInRoman(1, five.name());
				}
				else
					this.setNumberInRoman(number, one.name());
				
			}
			
			if(number == five.getValue() + 1){
				this.convert(number-1);
				this.setNumberInRoman(1, one.name());
			}
		}

		return numberInRoman;
	}

	/**
	 * Converts the extremities Limit to 1000
	 * @param number the number to be checked
	 * @return the Roman equivalent or an empty string if number is not an extremity number
	 */
	public String convertExtremities(int number) {
		for (ValueMapper values : ValueMapper.values()) {
			if (number == values.getValue())
				this.setNumberInRoman(1, values.name());
//				numberInRoman = values.name();
		}
		if (!this.numberInRoman.isEmpty())
			this.setDoneConversion();
		return numberInRoman;
	}
}
