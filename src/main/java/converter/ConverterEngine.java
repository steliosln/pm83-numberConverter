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

	public String convert(int number) {
		this.convertExtremities(number);
		
		if (!this.isDoneConversion())
			if (number < five.getValue())
				for (int i = 0; i < number; i++) {
					this.numberInRoman += one.name();
				}

		return numberInRoman;
	}

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
