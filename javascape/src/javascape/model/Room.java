package javascape.model;

import java.io.Serializable;

public class Room  implements Serializable{
	
	
	private static final long serialVersionUID = -1594475075781280774L;
	public static final String TYPE_SINGLE="SINGLE";
	public static final String TYPE_DOUBLE="DOUBLE";
	
	
	public static final String DIRECTION_UP="UP";
	public static final String DIRECTION_DOWN="DOWN";
	public static final String DIRECTION_LEFT="LEFT";
	public static final String DIRECTION_RIGHT="RIGHT";
	
	public static final String DIRECTION_DOOR_HOR="HORIZONTAL";
	public static final String DIRECTION_DOOR_VER="VERTICAL";
	public static final String OPEN_DOOR_IMG_HOR="door_hor_white.jpg";
	public static final String OPEN_DOOR_IMG_VER="door_ver_white.jpg";
	
	private int id;
	private int sectionNum;
	private String difficulty;
	private String type;
	private int point1x;
	private int point1y;
	private int point2x;
	private int point2y;
	private String direction1;
	private String direction2;
	
	private String doorDirection;
	
	private int computerLocatinX;
	private int computerLocatinY;
	private int questionLevel;
	
	
	private String doorImageName;
	
	private boolean doorOpen=false;
	
	private int door_loc_x=0;
	private int door_loc_y=0;
	
	private boolean questionSolved=false;
	
	
	public Room(){
		
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getPoint1x() {
		return point1x;
	}
	public void setPoint1x(int point1x) {
		this.point1x = point1x;
	}
	public int getPoint1y() {
		return point1y;
	}
	public void setPoint1y(int point1y) {
		this.point1y = point1y;
	}
	public int getPoint2x() {
		return point2x;
	}
	public void setPoint2x(int point2x) {
		this.point2x = point2x;
	}
	public int getPoint2y() {
		return point2y;
	}
	public void setPoint2y(int point2y) {
		this.point2y = point2y;
	}
	
	public int getComputerLocationX() {
		return computerLocatinX;
	}
	public void setComputerLocationX(int computerLocatinX) {
		this.computerLocatinX = computerLocatinX;
	}
	public int getComputerLocationY() {
		return computerLocatinY;
	}
	public void setComputerLocationY(int computerLocatinY) {
		this.computerLocatinY = computerLocatinY;
	}


	public int getSectionNum() {
		return sectionNum;
	}


	public void setSectionNum(int sectionNum) {
		this.sectionNum = sectionNum;
	}


	public String getDoorDirection() {
		return doorDirection;
	}


	public void setDoorDirection(String doorDirection) {
		this.doorDirection = doorDirection;
	}


	public int getQuestionLevel() {
		return questionLevel;
	}


	public void setQuestionLevel(int questionLevel) {
		this.questionLevel = questionLevel;
	}


	public boolean isSingle() {
		
		return TYPE_SINGLE.endsWith(type);
	}


	public String getDirection1() {
		return direction1;
	}


	public void setDirection1(String direction1) {
		this.direction1 = direction1;
	}


	public String getDirection2() {
		return direction2;
	}


	public void setDirection2(String direction2) {
		this.direction2 = direction2;
	}


	public boolean isLeftDirection() {
		
		return direction1.equals(DIRECTION_LEFT) || DIRECTION_LEFT.equals(direction2);
	}
	public boolean isRightDirection() {
		
		return direction1.equals(DIRECTION_RIGHT) || DIRECTION_RIGHT.equals(direction2);
	}
	
	public boolean isUpDirection() {
		
		return direction1.equals(DIRECTION_UP) || DIRECTION_UP.equals(direction2);
	}
	
	public boolean isDownDirection() {
		
		return direction1.equals(DIRECTION_DOWN) || DIRECTION_DOWN.equals(direction2);
	}


	public String getDoorImageNameStr() {
		if(isSingle()){
			if(DIRECTION_DOOR_HOR.equals(doorDirection))
				if(isDoorOpen())
					return OPEN_DOOR_IMG_HOR;
				else
					return doorImageName+"_horizontal.jpg";
			else
				if(isDoorOpen())
					return OPEN_DOOR_IMG_VER;
				else
					return doorImageName+"_vertical.jpg";
		}
		else{
			if(isUpDirection() || isDownDirection())
				if(isDoorOpen())
					return  OPEN_DOOR_IMG_HOR;
				else
					return doorImageName+"_horizontal.jpg";
			else
				if(isDoorOpen())
					return  OPEN_DOOR_IMG_VER;
				else
					return doorImageName+"_vertical.jpg";
		}
		
		
	}

	
	
	public String getDoorImageName() {
		return doorImageName;
	}


	public void setDoorImageName(String doorImageName) {
		this.doorImageName = doorImageName;
	}


	public boolean isDoorOpen() {
		return doorOpen;
	}


	public void setDoorOpen(boolean doorOpen) {
		this.doorOpen = doorOpen;
	}


	public int getDoor_loc_x() {
		return door_loc_x;
	}


	public void setDoor_loc_x(int door_loc_x) {
		this.door_loc_x = door_loc_x;
	}


	public int getDoor_loc_y() {
		return door_loc_y;
	}


	public void setDoor_loc_y(int door_loc_y) {
		this.door_loc_y = door_loc_y;
	}


	public boolean isQuestionSolved() {
		return questionSolved;
	}


	public void setQuestionSolved(boolean questionSolved) {
		this.questionSolved = questionSolved;
	}
	
}
