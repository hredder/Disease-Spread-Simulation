import java.awt.Color;

public class Disease {

	private double infectivityRate; //Chance to infect someone given they are in a certain radius of an individual
	private double mortalityRate; //Chance to kill someone per tick if they have the virus
	private double spreadDistance; //The radius around an individual for a possiblility of spreading
	private double cureRate;
	private Color diseaseColor;
	
	public Disease(double i, double m, double s, double cu, Color c) {
		setInfectivityRate(i);
		setMortalityRate(m);
		setSpreadDistance(s);
		setDiseaseColor(c);
		setCureRate(cu);
	}

	public double getInfectivityRate() {
		return infectivityRate;
	}

	public void setInfectivityRate(double infectivityRate) {
		this.infectivityRate = infectivityRate;
	}

	public double getSpreadDistance() {
		return spreadDistance;
	}

	public void setSpreadDistance(double spreadDistance) {
		this.spreadDistance = spreadDistance;
	}

	public double getMortalityRate() {
		return mortalityRate;
	}

	public void setMortalityRate(double mortalityRate) {
		this.mortalityRate = mortalityRate;
	}

	public Color getDiseaseColor() {
		return diseaseColor;
	}

	public void setDiseaseColor(Color diseaseColor) {
		this.diseaseColor = diseaseColor;
	}

	public double getCureRate() {
		return cureRate;
	}

	public void setCureRate(double cureRate) {
		this.cureRate = cureRate;
	}
}
