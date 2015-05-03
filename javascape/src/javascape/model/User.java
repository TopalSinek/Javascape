package javascape.model;

import java.io.Serializable;

public class User implements Serializable {
	
	
	
	private static final long serialVersionUID = -5942386902865145789L;
	
	
	private String name;
	private String language;
	private String difficulty;
	private String mode;
	
	private int sectionNum=1;
	private int numOfDeath=0;
	
	private int location_x=3;
	private int location_y=50;
	private int score;
	
	
	
	private int location_xPoints=0;
	private int location_yPoints=0;
	
	public User(){
		language="EN";
		difficulty=Definitions.DIFFICULTY_NORMAL;
		mode=Definitions.MODE_CS;
		score=0;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getLanguage() {
		return language;
	}


	public void setLanguage(String language) {
		this.language = language;
	}


	public String getDifficullty() {
		return difficulty;
	}


	public void setDifficullty(String difficullty) {
		this.difficulty = difficullty;
	}


	


	public int getLocation_x() {
		return location_x;
	}


	public void setLocation_x(int location_x) {
		this.location_x = location_x;
	}


	public int getLocation_y() {
		return location_y;
	}


	public void setLocation_y(int location_y) {
		this.location_y = location_y;
	}


	public int getScore() {
		return score;
	}


	public void setScore(int score) {
		this.score = score;
	}


	public String getMode() {
		return mode;
	}


	public void setMode(String mode) {
		this.mode = mode;
	}


	public int getNumOfDeath() {
		return numOfDeath;
	}


	public void setNumOfDeath(int numOfDeath) {
		this.numOfDeath = numOfDeath;
	}
	
	public void died(){
		this.numOfDeath++;
	}


	public boolean keyPress(int keyCode) {
		boolean ret=false;
		int dd=1;
		if(keyCode==Definitions.KEY_DOWN){
			if(location_y<93){
				location_y=location_y+dd;
				ret=true;
			}
		}
		else if(keyCode==Definitions.KEY_UP){
			if(location_y>1){
				location_y=location_y-dd;
				ret=true;
			}
		}
		else if(keyCode==Definitions.KEY_LEFT){
			if(location_x>1){
				location_x=location_x-dd;
				ret=true;
			}
		}
		else if(keyCode==Definitions.KEY_RIGHT){
			if(location_x<96){
				location_x=location_x+dd;
				ret=true;
			}
		}
		
		return ret;
		
		
	}


	public int getLocation_xPoints() {
		return location_xPoints;
	}


	public void setLocation_xPoints(int location_xPoints) {
		this.location_xPoints = location_xPoints;
	}


	public int getLocation_yPoints() {
		return location_yPoints;
	}


	public void setLocation_yPoints(int location_yPoints) {
		this.location_yPoints = location_yPoints;
	}


	public String getDifficulty() {
		return difficulty;
	}


	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}


	public int getSectionNum() {
		return sectionNum;
	}


	public void setSectionNum(int sectionNum) {
		this.sectionNum = sectionNum;
	}


	public void addScore(int i) {
		score=score+i;
		
	}


	public void nextSction() {
		sectionNum=sectionNum+1;
		numOfDeath=0;
		
		location_x=3;
		location_y=50;
		
	}
	
	
	
}
