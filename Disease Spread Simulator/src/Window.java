import processing.core.*;

import java.awt.Color;
import java.util.ArrayList;

import controlP5.*;

public class Window extends PApplet {

	private final int WindowX = 800;
	private final int WindowY = 600;
	
	private boolean running = false;
	private double populationSize;
	private double initialDiseased;
	private ArrayList<Human> population = new ArrayList<Human>();
	
	private double infectivity;
	private double mortality;
	private double spreadDist;
	private double cureRate;
	private double generation;
	private double simSpeed;
	
	public void settings() {
		this.size(WindowX, WindowY);
		
	}

	@SuppressWarnings("deprecation")
	public void setup() {
		ControlP5 cp5 = new ControlP5(this);
		cp5.addButton("Create Simulation", 0,625, 20, 100,20);
		cp5.addButton("Stop Simulation", 0,625, 60, 100,20);
		cp5.addButton("Resume Simulation", 0,625, 100, 100,20);
		cp5.addButton("Clear Simulation", 0,625, 140, 100,20);
		cp5.addSlider("Population Size", 0, 1000, 500, 625,180,100,20);
		cp5.addSlider("Initial Diseased", 0, 1000, 500, 625,220,100,20);
		cp5.addSlider("Infectivity Rate", 0, 1000, 500, 625,260,100,20);
		cp5.addSlider("Mortality Rate", 0, 1000, 500, 625,300,100,20);
		cp5.addSlider("Cure Rate", 0, 500, 250, 625, 340, 100, 20);
		cp5.addSlider("Spread Distance", 0, 100, 25, 625,380,100,20);
		cp5.addSlider("Simulation Speed", 0, 100, 50, 625, 420, 100, 20);
		
		initialDiseased = 500;
		infectivity = 500;
		mortality = 500;
		spreadDist = 25;
		populationSize = 500;
		cureRate = 250;
		simSpeed = 50;
		
	}
	
	
	public void draw() {
		//Main Loop
		delay((int)(100-simSpeed));
		drawUI();
		drawHumans();
		if (running) {
			updateHumans();
		}
		
	}
	
	public void updateHumans() {

		for (int i = 0; i < population.size(); i++) {
			if (population.get(i).isDead()) {
				population.remove(i);
			}
			else {
				population.get(i).update(population, i);
			}
	
		}
	}
	
	public void drawHumans() {
		
		for (int i = 0; i < population.size(); i++) {
			if (population.get(i).isINFECTED()) {
				fill(255,0,0);
			}
			else {
				fill(0,255,0);
			}
			noStroke();
			this.ellipse(population.get(i).getxPos(), population.get(i).getyPos(), 5, 5);
		}
	}
	
	public void drawUI() {
		
		this.background(0);
		this.stroke(255);
		this.line(600, 0, 600, 600);
	}
	
	public void controlEvent(ControlEvent ce) {
		
		String name = ce.getController().getName();
		if (name.equals("Create Simulation")) {
			running = true;
			population.clear();
			startSimulation();
		}
		else if (name.equals("Stop Simulation")) {
			running = false;
		}
		else if (name.equals("Resume Simulation")) {
			running = true;
		}
		else if (name.equals("Clear Simulation")) {
			running = false;
			population.clear();
			
		}
		else if (name.equals("Population Size")) {
			populationSize = ce.getController().getValue();
		}
		else if (name.equals("Initial Diseased")) {
			initialDiseased = ce.getController().getValue();
		}
		else if (name.equals("Infectivity Rate")) {
			infectivity = ce.getController().getValue();
		}
		else if (name.equals("Mortality Rate")) {
			mortality = ce.getController().getValue();
		}
		else if (name.equals("Cure Rate")){
			cureRate = ce.getController().getValue();
		}
		else if (name.equals("Spread Distance")) {
			spreadDist = ce.getController().getValue();
		}
		else if (name.equals("Simulation Speed")) {
			simSpeed = ce.getController().getValue();
		}
	}
	
	public void startSimulation() {
		for (int i = 0; i < populationSize; i++) {
			boolean infected = false;
			if (i < initialDiseased) {
				infected = true;
			}
			Disease d = new Disease(infectivity, mortality, spreadDist, cureRate, Color.RED);
			float fx = (float)(Math.random()*600);
			float fy = (float)(Math.random()*600);
			population.add(new Human(infected, d, fx, fy, Color.GREEN));
		}
	}

}
