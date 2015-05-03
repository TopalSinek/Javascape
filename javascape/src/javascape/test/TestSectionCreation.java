package javascape.test;

import javascape.model.Room;
import javascape.model.Section;
import javascape.model.User;

public class TestSectionCreation {

	
	public static Section createSection(boolean rtype){
		Section sec=new Section();
		sec.setId(1);
		
		sec.setSectionNum(1);
		sec.setWallImageName("wall.jpg");
		
		
		boolean singleRoom=rtype;
		
		
		Room room=new Room();
		room.setDirection1(Room.DIRECTION_LEFT);
		room.setDirection2(Room.DIRECTION_DOWN);
		room.setPoint1x(20);
		room.setPoint1y(70);
		room.setType(Room.TYPE_SINGLE);
		room.setDoorImageName("door");
		room.setDoorOpen(true);
		room.setDoorDirection(Room.DIRECTION_DOOR_HOR);
		if(singleRoom)
		  sec.getLstRooms().add(room);
		
		
		room=new Room();
		room.setDirection1(Room.DIRECTION_LEFT);
		room.setDirection2(Room.DIRECTION_UP);
		room.setPoint1x(40);
		room.setPoint1y(20);
		room.setType(Room.TYPE_SINGLE);
		room.setDoorImageName("door");
		room.setDoorOpen(true);
		room.setDoorDirection(Room.DIRECTION_DOOR_HOR);
		if(singleRoom)
		  sec.getLstRooms().add(room);
		
		
		
		room=new Room();
		room.setDirection1(Room.DIRECTION_RIGHT);
		room.setDirection2(Room.DIRECTION_UP);
		room.setPoint1x(70);
		room.setPoint1y(30);
		room.setType(Room.TYPE_SINGLE);
		room.setDoorImageName("door");
		room.setDoorOpen(true);
		room.setDoorDirection(Room.DIRECTION_DOOR_VER);
		if(singleRoom)
			sec.getLstRooms().add(room);
		
		
		
		room=new Room();
		room.setDirection2(Room.DIRECTION_RIGHT);
		room.setDirection1(Room.DIRECTION_DOWN);
		room.setPoint1x(80);
		room.setPoint1y(70);
		room.setType(Room.TYPE_SINGLE);
		room.setDoorImageName("door");
		room.setDoorDirection(Room.DIRECTION_DOOR_HOR);
		if(singleRoom)
			sec.getLstRooms().add(room);
		
		
		
		
		room=new Room();
		room.setDirection1(Room.DIRECTION_LEFT);
		
		room.setPoint1x(40);
		room.setPoint1y(40);
		
		room.setPoint2x(40);
		room.setPoint2y(70);
		
		
		room.setType(Room.TYPE_DOUBLE);
		room.setDoorImageName("door");
		room.setDoorOpen(false);
		room.setDoorDirection(Room.DIRECTION_DOOR_VER);
		
		if(!singleRoom)
		sec.getLstRooms().add(room);
		
		
		room=new Room();
		room.setDirection1(Room.DIRECTION_RIGHT);
		
		room.setPoint1x(55);
		room.setPoint1y(40);
		
		room.setPoint2x(55);
		room.setPoint2y(70);
		
		
		room.setType(Room.TYPE_DOUBLE);
		room.setDoorImageName("door");
		room.setDoorOpen(true);
		room.setDoorDirection(Room.DIRECTION_DOOR_VER);
		
		if(!singleRoom)
		sec.getLstRooms().add(room);
		
		
		
		
		room=new Room();
		room.setDirection1(Room.DIRECTION_UP);
		
		room.setPoint1x(40);
		room.setPoint1y(20);
		
		room.setPoint2x(55);
		room.setPoint2y(20);
		
		
		room.setType(Room.TYPE_DOUBLE);
		room.setDoorImageName("door");
		room.setDoorDirection(Room.DIRECTION_DOOR_HOR);
		
		if(!singleRoom)
		sec.getLstRooms().add(room);
		
		
		
		
		room=new Room();
		room.setDirection1(Room.DIRECTION_DOWN);
		
		room.setPoint1x(40);
		room.setPoint1y(80);
		
		room.setPoint2x(55);
		room.setPoint2y(80);
		
		
		room.setType(Room.TYPE_DOUBLE);
		room.setDoorImageName("door");
		room.setDoorDirection(Room.DIRECTION_DOOR_HOR);
		if(!singleRoom)
		sec.getLstRooms().add(room);
		
		
		return sec;
	}
	
	public static User createUser(){
		User user=new User();
		
		
		return user;
	}
	
}
