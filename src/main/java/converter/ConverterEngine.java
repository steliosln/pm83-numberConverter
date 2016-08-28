package converter;

public class ConverterEngine {
	public static String result2 = "";
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
	private ValueMapper upperBase;
	private ValueMapper lowerBase;
	
	private int numberInDecimal;
	private String result;
	
	public ConverterEngine(int number) {
		this.numberInDecimal = number;
		this.doneConversion = false;
		this.result = "";
	}

	public String getResult() {
		return result;
	}
	
	@Deprecated
	public ValueMapper getUpperBase() {
		return upperBase;
	}
	
	@Deprecated
	public ValueMapper getLowerBase() {
		return lowerBase;
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
	
	@Deprecated
	public void setBases(int number){
		for (int i = enumValues.length - 1; i >= 0; i--) {
			if(number < enumValues[i].getValue())
				upperBase = enumValues[i];
				if(i != 0)
					lowerBase = enumValues[i-1];
		}		
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
		System.out.println("inside print");

		ConverterEngine converterEngine = new ConverterEngine(number);
		int decimalPlaces = converterEngine.getDecimalPlaces();
		int remain = (int) (number % Math.pow(10, decimalPlaces));
		System.out.println("decimal places " + decimalPlaces);
		System.out.println("remain " + remain);
		
		if(decimalPlaces == 0)
			result += number;
		else{
			result += (number - remain);
			print(remain);
		}
	}
	
	@Deprecated
	public static void print2(int number){
		System.out.println("inside print2");
		
		ConverterEngine converterEngine = new ConverterEngine(number);
		int decimalPlaces = converterEngine.getDecimalPlaces();
		int remain = (int) (number % Math.pow(10, decimalPlaces));
		
		if(decimalPlaces == 0)
			result2 += number;
		else{
			result2 += (number - remain);
			print2(remain);
		}
	}
	
	
	
	public String convert(int number) {
		int remain = (int) (number % Math.pow(10, this.getDecimalPlaces(number)));
		System.out.println("Remain: " + remain);
		
		this.convertBases(number);
		
		
		if (!this.isDoneConversion()){
//			this.convert(number - remain);
//			this.convert(remain);
			
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
