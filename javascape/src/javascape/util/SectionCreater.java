package javascape.util;

import java.util.Iterator;

import javascape.model.Room;
import javascape.model.Section;

public class SectionCreater {

	public static Section createSection(int sectionNum){
		Section ret = null;
		if(sectionNum == 1){
			ret = getSection1();
			return ret;
		}
		else if(sectionNum == 2){
			ret = getSection2();
			return ret;
		}
		else
			return null;
		
	}

	private static Section getSection1(){

		Section sec=new Section();
		sec.setId(1);

		int roomId=1;
		sec.setSectionNum(1);
		sec.setWallImageName("wall.jpg");
		

		Room room=new Room();
		room.setDirection1(Room.DIRECTION_LEFT);
		room.setDirection2(Room.DIRECTION_DOWN);
		room.setPoint1x(20);
		room.setPoint1y(70);
		room.setType(Room.TYPE_SINGLE);
		room.setDoorImageName("door");
		room.setDoorOpen(true);
		room.setDoorDirection(Room.DIRECTION_DOOR_HOR);

		sec.getLstRooms().add(room);


		room=new Room();
		room.setDirection1(Room.DIRECTION_LEFT);
		room.setDirection2(Room.DIRECTION_UP);
		room.setPoint1x(40);
		room.setPoint1y(20);
		room.setType(Room.TYPE_SINGLE);
		room.setDoorImageName("door");
		room.setDoorOpen(false);
		room.setDoorDirection(Room.DIRECTION_DOOR_HOR);

		sec.getLstRooms().add(room);



		room=new Room();
		room.setDirection1(Room.DIRECTION_RIGHT);
		room.setDirection2(Room.DIRECTION_UP);
		room.setPoint1x(70);
		room.setPoint1y(30);
		room.setType(Room.TYPE_SINGLE);
		room.setDoorImageName("door");
		room.setDoorOpen(false);
		room.setDoorDirection(Room.DIRECTION_DOOR_VER);

		sec.getLstRooms().add(room);



		room=new Room();
		room.setDirection2(Room.DIRECTION_RIGHT);
		room.setDirection1(Room.DIRECTION_DOWN);
		room.setPoint1x(80);
		room.setPoint1y(70);
		room.setType(Room.TYPE_SINGLE);
		room.setDoorImageName("door");
		room.setDoorOpen(false);
		room.setDoorDirection(Room.DIRECTION_DOOR_HOR);
		sec.getLstRooms().add(room);

		for (Iterator iterator = sec.getLstRooms().iterator(); iterator.hasNext();) {
			Room r = (Room) iterator.next();
			r.setId(roomId++);
			r.setSectionNum(1);
		}

		return sec;
	}

	private static Section getSection2(){
		Section sec=new Section();
		sec.setId(2);

		int roomId=1;
		sec.setSectionNum(1);
		sec.setWallImageName("wall.jpg");

		Room room = new Room();
		room.setDirection1(Room.DIRECTION_LEFT);
		room.setPoint1x(30);
		room.setPoint1y(40);
		room.setPoint2x(30);
		room.setPoint2y(70);
		room.setType(Room.TYPE_DOUBLE);
		room.setDoorImageName("door");
		room.setDoorOpen(false);
		room.setDoorDirection(Room.DIRECTION_DOOR_VER);
		sec.getLstRooms().add(room);


		room=new Room();
		room.setDirection1(Room.DIRECTION_RIGHT);
		room.setPoint1x(65);
		room.setPoint1y(40);
		room.setPoint2x(65);
		room.setPoint2y(70);
		room.setType(Room.TYPE_DOUBLE);
		room.setDoorImageName("door");
		room.setDoorOpen(false);
		room.setDoorDirection(Room.DIRECTION_DOOR_VER);
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
		sec.getLstRooms().add(room);
		
		for (Iterator iterator = sec.getLstRooms().iterator(); iterator.hasNext();) {
			Room r = (Room) iterator.next();
			r.setId(roomId++);
			r.setSectionNum(2);
		}
		
		return sec;

	}
}
