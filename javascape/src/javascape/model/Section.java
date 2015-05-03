package javascape.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Section implements Serializable{
	

	private static final long serialVersionUID = -4911300615567515892L;
	private int id;
	private int sectionNum;
	private List<Room> lstRooms=new ArrayList<Room>();
	private String wallImageName;
	
	
	
	
	public Section(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSectionNum() {
		return sectionNum;
	}

	public void setSectionNum(int sectionNum) {
		this.sectionNum = sectionNum;
	}

	public List<Room> getLstRooms() {
		return lstRooms;
	}

	public void setLstRooms(List<Room> lstRooms) {
		this.lstRooms = lstRooms;
	}

	
	public String getWallImageName() {
		return wallImageName;
	}

	public void setWallImageName(String wallImageName) {
		this.wallImageName = wallImageName;
	}

	
	
	

}
