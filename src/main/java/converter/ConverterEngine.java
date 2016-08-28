package converter;

public class ConverterEngine {
	private ValueMapper[] enumValues = ValueMapper.values();
	private ValueMapper one = ValueMapper.I;
	private ValueMapper five = ValueMapper.V;
	private ValueMapper ten = ValueMapper.X;
//	private ValueMapper fifty = ValueMapper.L;
//	private ValueMapper hundred = ValueMapper.C;
//	private ValueMapper fiveHundred = ValueMapper.D;
//	private ValueMapper thousand = ValueMapper.M;
	private boolean doneConversion;
	private String numberInRoman;
	
	private int numberInDecimal;
	private String result;
	
	public ConverterEngine(int number) {
		this.numberInDecimal = number;
		this.doneConversion = false;
		this.numberInRoman = "";
		this.result = "";
	}

	public String getResult() {
		return result;
	}
	
	public boolean isDoneConversion() {
		return doneConversion;
	}

	public void setDoneConversion() {
		this.doneConversion = true;
	}
	
	//TODO remove
	@Deprecated
	public ConverterEngine() {
		this.numberInRoman = "";
		this.doneConversion = false;
	}
	
	public String getNumberInRoman() {
		return numberInRoman;
	}
		
	public ValueMapper getLetterBefore(ValueMapper letter){
		ValueMapper tempBLetter = null;
		for (int i = 0; i < enumValues.length; i++) {
			if(enumValues[i].equals(letter))
				tempBLetter = (i==0) ? ValueMapper.I : enumValues[i-1];
		}
		
//		int currentPosition = letter.ordinal();
//		int position = currentPosition == 0 ? currentPosition : currentPosition - 1;
		//return enumValues[position];
		
		return tempBLetter;
	}
	
	public ValueMapper getLetterAfter(ValueMapper letter){
		ValueMapper tempALetter = null;
		for(int i = enumValues.length - 1; i >= 0; i--)
			if(enumValues[i].equals(letter))
				tempALetter = (i==enumValues.length - 1) ? ValueMapper.M : enumValues[i+1];
		
//		int currentPosition = letter.ordinal();
//		int position = currentPosition == enumValues.length - 1 ? currentPosition : currentPosition + 1;
		//return enumValues[position];
		
		return tempALetter;
	}
	
	/**
	 * Adds/appends a passed letter to the {@link #numberInRoman}
	 * @param numberOfLetters the amount of times the letter is to be added
	 * @param letter the letter to add
	 */
	public void setNumberInRoman(int numberOfLetters, ValueMapper letter) {
		if(numberOfLetters==4){
			//remove last letter
			int lastIndex = this.getNumberInRoman().length() - 1; 
			if(!this.numberInRoman.isEmpty())
				this.numberInRoman = this.getNumberInRoman().substring(0, lastIndex);
			
			setNumberInRoman(1, this.getLetterBefore(letter));//one.name()
			setNumberInRoman(1, this.getLetterAfter(letter));//ten.name()
		}
		else
			for (int i = 0; i < numberOfLetters; i++) 
				this.numberInRoman += letter.name();
	}

	//TODO remove
	@Deprecated
	public int getDecimalPlaces(int number){
		int places = Integer.toString(number).length() - 1;
		System.out.println(number + " has " + places + " Decimal places");
		return places;
	}
	
	public int getDecimalPlaces(){
		int places = Integer.toString(this.numberInDecimal).length() - 1;
		return places;
	}
	
	public void print(int number){
		ConverterEngine converterEngine = new ConverterEngine(number);
		int decimalPlaces = converterEngine.getDecimalPlaces();
		int afterModulus = (int) (number % Math.pow(10, decimalPlaces));
		int remain = number - afterModulus;
		
		if(decimalPlaces == 0){
			convert(number);
			//result += convert(number);
			result += number;
			
		}
		else{
			convert(remain);
			//result += convert(remain);
			result += remain;
			print(afterModulus);
		}
	}
		
	public String convert(int number) {
		this.convertBases(number);
		
		if (!this.isDoneConversion()){	
			if (number < five.getValue()) {
				int timesBelow = 1;

				if (number == five.getValue() - timesBelow) {
					//count times below here
					this.setNumberInRoman(timesBelow, one);
					this.convert(number + timesBelow);
				} else
					this.setNumberInRoman(number, one);

			}
			else {
				int timesAbove = 0;
				while (timesAbove + five.getValue() != number) {
					timesAbove++;
				}
				this.convert(number - timesAbove);
				this.setNumberInRoman(timesAbove, one);// get letter after
			}
			this.setDoneConversion();
		}		

		return numberInRoman;
	}

	/**
	 * Converts the extremities Limit to 1000
	 * @param numberInDecimal the numberInDecimal to be checked
	 * @return the Roman equivalent or an empty string if numberInDecimal is not an extremity numberInDecimal
	 */
	public String convertBases(int number) {
		for (ValueMapper values : enumValues) {
			if (number == values.getValue())
				this.setNumberInRoman(1, values);
		}
		
		if (!this.numberInRoman.isEmpty())
			this.setDoneConversion();
		
		return numberInRoman;
	}
}
