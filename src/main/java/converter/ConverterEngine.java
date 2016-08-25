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
			/**
			 * It is possible to make the body here recursive 
			 * for all possible cases.
			 * use lowerExtreme and upperExtreme
			 * change extremes to the next when these are equal 
			 */
			if (number < five.getValue()) {
				int timesBelow = 1;

				if (number == five.getValue() - timesBelow) {
					this.setNumberInRoman(timesBelow, one.name());
					this.convert(number + timesBelow);
				} else
					this.setNumberInRoman(number, one.name());

			} else {
				int timesAbove = 0;
				while (timesAbove + five.getValue() != number) {
					timesAbove++;
				}
				this.convert(number - timesAbove);
				this.setNumberInRoman(timesAbove, one.name());
			}
			
			/**
			 * End of body to make recursive
			 */			
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
		}
		
		if (!this.numberInRoman.isEmpty())
			this.setDoneConversion();
		
		return numberInRoman;
	}
}
