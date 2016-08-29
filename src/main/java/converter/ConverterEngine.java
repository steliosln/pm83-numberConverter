package converter;

public class ConverterEngine {
	private ValueMapper[] enumValues = ValueMapper.values();
	private ValueMapper one = ValueMapper.I;
	private ValueMapper five = ValueMapper.V;
//	private ValueMapper ten = ValueMapper.X;
	private ValueMapper fifty = ValueMapper.L;
//	private ValueMapper hundred = ValueMapper.C;
//	private ValueMapper fiveHundred = ValueMapper.D;
//	private ValueMapper thousand = ValueMapper.M;
	private boolean doneConversion;
	private String numberInRoman;
	
	private int numberInDecimal;
	private String result;
	private String resultOfDoConversion;
	
	//TODO change methods to private and call start() which will be public
	public ConverterEngine(int number) {
		this.numberInDecimal = number;
		this.doneConversion = false;
		this.numberInRoman = "";
		this.resultOfDoConversion = "";
		this.result = "";
	}
	
	public String execute(){
		this.print(numberInDecimal);
		return this.getResultOfDoConversion();
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
	
	public String getNumberInRoman() {
		return numberInRoman;
	}
	
	/**
	 * Gets the letter that comes before a given letter
	 * @param letter any letter to check the letter that comes before
	 * @return any Roman letter except V and L
	 */
	public ValueMapper getLetterBefore(ValueMapper letter){
		int currentPosition = letter.ordinal();
		int position = currentPosition == 0 ? currentPosition : currentPosition - 1;
		
		if(enumValues[position].name().equals(five.name()) || enumValues[position].equals(fifty.name()))
			position -= 1;
		
		return enumValues[position];
	}
	
	public ValueMapper getLetterAfter(ValueMapper letter){
		int currentPosition = letter.ordinal();
		int position = currentPosition == enumValues.length - 1 ? currentPosition : currentPosition + 1;
		return enumValues[position];
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
			result += number;
			this.doConversionOf(number);
		}
		else{
			convert(remain);
			result += remain;
			this.doConversionOf(remain);
			print(afterModulus);
		}
	}
	
	//TODO replace convert(int)
	private void doConversionOf(int number){
		boolean gotAMatch = false;
		
		for (ValueMapper values : enumValues) {
			//if it is I, V, X, L, C, D, M
			if (number == values.getValue()){
				gotAMatch = true;
				this.setResultOfDoConversion(1, values);
			}
			else if(number == values.getValue() - 1){
				gotAMatch = true;
				this.setResultOfDoConversion(1, this.getLetterBefore(values));
				this.setResultOfDoConversion(1, values);
			}
		}
		if (!gotAMatch){
			if(number <= 3){
				this.setResultOfDoConversion(number, this.one);
			}
			else{
				this.setResultOfDoConversion(1, this.five);
				this.doConversionOf(number - this.five.getValue());
			}			
		}			
	}

	public String getResultOfDoConversion() {
		return resultOfDoConversion;
	}
	
	public void setResultOfDoConversion(int numberOfLetters, ValueMapper letter) {
		//if numberOfLetters <=3
		//if numberOfLetters  TODO rework here in case more than 4 or dont allow it
		if(numberOfLetters==4){
			int lastIndex = this.resultOfDoConversion.length() - 1; 
			if(!this.resultOfDoConversion.isEmpty())
				this.resultOfDoConversion = this.resultOfDoConversion.substring(0, lastIndex);
			
			setResultOfDoConversion(1, this.getLetterBefore(letter));
			setResultOfDoConversion(1, this.getLetterAfter(letter));
		}
		else
			for (int i = 0; i < numberOfLetters; i++) 
				this.resultOfDoConversion += letter.name();
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
		}
		return numberInRoman;
	}

	/**
	 * Converts the extremities Limit to 1000
	 * @param numberInDecimal the numberInDecimal to be checked
	 * @return the Roman equivalent or an empty string if numberInDecimal is not an extremity numberInDecimal
	 */
	public String convertBases(int number) {
		this.doneConversion = false;
		for (ValueMapper values : enumValues) {
			if (number == values.getValue()){
				this.setNumberInRoman(1, values);
				this.setDoneConversion();
			}
		}
		return numberInRoman;
	}
}
