import java.awt.Color;
import java.util.ArrayList;

public class Human {

	private boolean INFECTED;
	private Disease disease;
	private float xPos;
	private float yPos;
	private Color humanColor;
	private boolean dead = false;
	
	public Human(boolean infected, Disease d, float x, float y, Color c) {
		setINFECTED(infected);
		setDisease(d);
		setxPos(x);
		setyPos(y);
		setHumanColor(c);
	}
	
	public void update(ArrayList<Human> al, int thisIndex) {
		
		move();
		
		//Getting infected
		for (int i = 0; i < al.size(); i++) {
			if (i != thisIndex && !INFECTED && distance(al.get(i)) < al.get(i).getDisease().getSpreadDistance() && al.get(i).isINFECTED()) {
				double inf = al.get(i).getDisease().getInfectivityRate();
				if (Math.random()*1000 < inf) {
					this.setINFECTED(true);
					this.setDisease(al.get(i).getDisease());
				}
			}
		}
		//Death and Cure code
		if (this.isINFECTED()) {
			if (Math.random()*1000<this.getDisease().getMortalityRate()) {
				this.setDead(true);
			}
			if (Math.random()*1000<this.getDisease().getCureRate()) {
				this.setINFECTED(false);
			}
		}
		
		
	}
	
	public void move() {
		xPos += (Math.random()*10)-5;
		yPos += (Math.random()*10)-5;
		
		if (xPos < 0) {
			xPos = 0;
		}
		if (xPos > 600) {
			xPos = 600;
		}
		if (yPos < 0) {
			yPos = 0;
		}
		if (yPos > 600) {
			yPos = 600;
		}
	}
	
	public double distance(Human other) {
		
		double f = Math.pow(this.getxPos()-other.getxPos(), 2);
		double f2 = Math.pow(this.getyPos()-other.getyPos(), 2);
		
		return Math.sqrt(f+f2);
		
	}

	public boolean isINFECTED() {
		return INFECTED;
	}

	public void setINFECTED(boolean iNFECTED) {
		INFECTED = iNFECTED;
	}

	public float getyPos() {
		return yPos;
	}

	public void setyPos(float yPos) {
		this.yPos = yPos;
	}

	public Disease getDisease() {
		return disease;
	}

	public void setDisease(Disease disease) {
		this.disease = disease;
	}

	public float getxPos() {
		return xPos;
	}

	public void setxPos(float xPos) {
		this.xPos = xPos;
	}

	public Color getHumanColor() {
		return humanColor;
	}

	public void setHumanColor(Color humanColor) {
		this.humanColor = humanColor;
	}

	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}
	
	
}
