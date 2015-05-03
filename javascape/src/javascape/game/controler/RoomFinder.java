package javascape.game.controler;

import java.util.Iterator;

import javascape.game.GamePlay;
import javascape.model.Room;
import javascape.model.Section;
import javascape.model.User;

public class RoomFinder {
	public static Room findRoom(GamePlay gamePlay){
		Room roomRet=null;
		 User user=gamePlay.getUser();
		 Section section=gamePlay.getSection();
		
		 
		 for (Iterator iterator = section.getLstRooms().iterator(); iterator.hasNext();) {
				Room room = (Room) iterator.next();
				if(Room.DIRECTION_DOOR_VER.equals(room.getDoorDirection())){
					if(room.getDoor_loc_x()-100<user.getLocation_xPoints() && room.getDoor_loc_x()-55>user.getLocation_xPoints() &&
							room.getDoor_loc_y()<user.getLocation_yPoints() && room.getDoor_loc_y()+48>user.getLocation_yPoints()){
						roomRet=room;
						break;
					}
					if(room.getDoor_loc_x()+52<user.getLocation_xPoints() && room.getDoor_loc_x()+100>user.getLocation_xPoints() &&
							room.getDoor_loc_y()<user.getLocation_yPoints() && room.getDoor_loc_y()+48>user.getLocation_yPoints()){
						roomRet=room;
						break;
					}
				}
				else{
					if(room.getDoor_loc_y()-100<user.getLocation_yPoints() && room.getDoor_loc_y()-48>user.getLocation_yPoints() &&
							room.getDoor_loc_x()<user.getLocation_xPoints() && room.getDoor_loc_x()+48>user.getLocation_xPoints()){
						roomRet=room;
						break;
					}
					if(room.getDoor_loc_y()+48<user.getLocation_yPoints() && room.getDoor_loc_y()+100>user.getLocation_yPoints() &&
							room.getDoor_loc_x()<user.getLocation_xPoints() && room.getDoor_loc_x()+48>user.getLocation_xPoints()){
						roomRet=room;
						break;
					}
				}
				
				
		 }
		return roomRet;
		
	}
	
	public static Room findComputerRoom(GamePlay gamePlay){
		Room roomRet=null;
		 User user=gamePlay.getUser();
		 Section section=gamePlay.getSection();
		 for (Iterator iterator = section.getLstRooms().iterator(); iterator.hasNext();) {
				Room room = (Room) iterator.next();
				if(nearBy(user.getLocation_xPoints(), user.getLocation_yPoints(), room.getComputerLocationX(), room.getComputerLocationY())){
					roomRet=room;
					break;
				}
		 }
		return roomRet;
		
	}
	
	
	
	
	private static boolean nearBy(int userX,int userY,int compX,int compY){
		compX=compX-48;
		compY=compY-48;
		if(near(userX,userY,compX,compY))
			return true;
		else if(near(userX,userY + 48,compX,compY))
			return true;
		else if(near(userX + 48,userY,compX,compY))
			return true;
		else if(near(userX + 48,userY + 48,compX,compY))
			return true;
		
		return false;
	}
	
	private static boolean near(int userX,int userY,int compX,int compY){
		
		return ((userX >= compX && userX <= compX+144) && (userY >= compY && userY <= compY+144));
	
	}
}