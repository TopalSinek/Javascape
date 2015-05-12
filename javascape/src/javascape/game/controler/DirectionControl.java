package javascape.game.controler;

import java.util.Iterator;

import javascape.game.GamePlay;
import javascape.model.Definitions;
import javascape.model.Room;
import javascape.model.Section;
import javascape.model.User;

import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;

public class DirectionControl {


	public static boolean directionControl(int keyCode,GamePlay gamePlay){
		boolean ret=true;


		User user=gamePlay.getUser();
		Section section=gamePlay.getSection();
		Canvas canvas=gamePlay.getCanvas();
		int icrement_x=2;
		int increment_y=2;
		Rectangle rec=canvas.getBounds(); // getting canvas bounds
		int x_bound=new Double(Math.ceil((48*100)/rec.width)).intValue();
		if(x_bound==0)
			x_bound=1;

		x_bound=x_bound+icrement_x;

		int y_bound=new Double(Math.ceil((48*100)/rec.height)).intValue();

		if(y_bound==0)
			y_bound=1;
		//System.out.println("x_bound "+x_bound +"  y_bound "+y_bound);
		y_bound=y_bound+increment_y;

		for (Iterator iterator = section.getLstRooms().iterator(); iterator.hasNext();) {
			Room room = (Room) iterator.next();

			int userX=user.getLocation_xPoints();
			int userY=user.getLocation_yPoints();
			int marginX=(rec.width/100)+1;
			int marginY=(rec.height/100)+1;

			if(keyCode==Definitions.KEY_UP){ 
				userY=userY-marginY;
			}
			else if(keyCode==Definitions.KEY_DOWN){ 
				userY=userY+marginY;
			}
			else if(keyCode==Definitions.KEY_LEFT){ 
				userX=userX-marginX;
			}
			else if(keyCode==Definitions.KEY_RIGHT){ 
				userX=userX+marginX;
			}

			if(computerIntersection(userX, userY, room.getComputerLocationX(), room.getComputerLocationY())){
				ret = false;
				break;
			}


			if(keyCode==Definitions.KEY_UP){ //creating collision box for up key
				if(room.isSingle()){ // single rooms
					boolean control=true;
					if(room.isDoorOpen()){ // making the doors passable when open 
						if(Room.DIRECTION_DOOR_HOR.equals(room.getDoorDirection())){
							if(room.getDoor_loc_x()<user.getLocation_xPoints() && room.getDoor_loc_x()+48>user.getLocation_xPoints())
								control=false;
						}

						if(Room.DIRECTION_DOOR_VER.equals(room.getDoorDirection())){
							if(userInDoorVer(room, user)){
								ret=false;
								break;}
						}

					}
					if(control){ // if statement to check admin mode
						//x control
						if(room.isLeftDirection()){ // Left sing rooms 
							if(room.getPoint1x()+x_bound-icrement_x>=user.getLocation_x()){
								if(user.getLocation_y()>room.getPoint1y() &&  (user.getLocation_y()-room.getPoint1y())<y_bound){
									ret=false;
									break;
								}

							}
						}
						else if(room.isRightDirection()){ // right direction single room
							if(room.getPoint1x()-x_bound+icrement_x<=user.getLocation_x()){
								if(user.getLocation_y()>room.getPoint1y() &&  (user.getLocation_y()-room.getPoint1y())<y_bound){
									ret=false;
									break;
								}

							}
						}
					}


				}
				else{  //double
					boolean control=true;
					if(room.isDoorOpen()){ // check if door open
						if(Room.DIRECTION_DOOR_HOR.equals(room.getDoorDirection())){
							if(room.getDoor_loc_x()<user.getLocation_xPoints() && room.getDoor_loc_x()+48>user.getLocation_xPoints())
								control=false;
						}

						if(Room.DIRECTION_DOOR_VER.equals(room.getDoorDirection())){
							if(userInDoorVer(room, user)){
								ret=false;
								break;}

						}

					}
					if(control){ //admin mode
						if(room.isLeftDirection()){ //left direction double room
							if(room.getPoint1x()+x_bound-icrement_x>=user.getLocation_x()){
								if(user.getLocation_y()>room.getPoint1y() &&  (user.getLocation_y()-room.getPoint1y())<y_bound){
									ret=false;
									break;
								}

								if(user.getLocation_y()>room.getPoint2y() &&  (user.getLocation_y()-room.getPoint2y())<y_bound){
									ret=false;
									break;
								}

							}
						}
						else if(room.isUpDirection() || room.isDownDirection()){ // up and down direction room ( same algorithm)
							if(room.getPoint2x()+x_bound-icrement_x>=user.getLocation_x() && room.getPoint1x()-x_bound+icrement_x<=user.getLocation_x() ){
								if(user.getLocation_y()>room.getPoint1y() &&  (user.getLocation_y()-room.getPoint1y())<y_bound){
									ret=false;
									break;
								}
							}
						}
						else if(room.isRightDirection()){ // right direction double room
							if(room.getPoint1x()-x_bound+icrement_x<=user.getLocation_x()){
								if(user.getLocation_y()>room.getPoint1y() &&  (user.getLocation_y()-room.getPoint1y())<y_bound){
									ret=false;
									break;
								}

								if(user.getLocation_y()>room.getPoint2y() &&  (user.getLocation_y()-room.getPoint2y())<y_bound){
									ret=false;
									break;
								}

							}
						}
					}
				}
			}
			else if(keyCode==Definitions.KEY_DOWN){ // collision control for down key 
				if(room.isSingle()){

					boolean control=true;
					if(room.isDoorOpen()){ // passable door
						if(Room.DIRECTION_DOOR_HOR.equals(room.getDoorDirection())){
							if(room.getDoor_loc_x()<user.getLocation_xPoints() && room.getDoor_loc_x()+48>user.getLocation_xPoints())
								control=false;
						}

						if(Room.DIRECTION_DOOR_VER.equals(room.getDoorDirection())){
							if(userInDoorVer(room, user)){
								ret=false;
								break;}
						}
					}
					if(control){ // admin mode
						//x control
						if(room.isLeftDirection()){ // left direction single room
							if(room.getPoint1x()+x_bound-icrement_x>=user.getLocation_x()){
								if(user.getLocation_y()<room.getPoint1y() &&  (room.getPoint1y()-user.getLocation_y())<y_bound){
									ret=false;
									break;
								}

							}
						}
						else if(room.isRightDirection()){ // right direction single room
							if(room.getPoint1x()-x_bound+icrement_x<=user.getLocation_x()){
								if(user.getLocation_y()<room.getPoint1y() &&  (room.getPoint1y()-user.getLocation_y())<y_bound){
									ret=false;
									break;
								}

							}
						}
					}


				}
				else{  //double
					boolean control=true;
					if(room.isDoorOpen()){ 
						if(Room.DIRECTION_DOOR_HOR.equals(room.getDoorDirection())){
							if(room.getDoor_loc_x()<user.getLocation_xPoints() && room.getDoor_loc_x()+48>user.getLocation_xPoints())
								control=false;
						}

						if(Room.DIRECTION_DOOR_VER.equals(room.getDoorDirection())){
							if(userInDoorVer(room, user)){
								ret=false;
								break;}
						}
					}
					if(control){
						if(room.isLeftDirection()){ 
							if(room.getPoint1x()+x_bound-icrement_x>=user.getLocation_x()){
								if(user.getLocation_y()<room.getPoint1y() &&  (room.getPoint1y()-user.getLocation_y())<y_bound){
									ret=false;
									break;
								}

								if(user.getLocation_y()<room.getPoint2y() &&  (room.getPoint2y()-user.getLocation_y())<y_bound){
									ret=false;
									break;
								}

							}
						}
						else if(room.isUpDirection() || room.isDownDirection()){
							if(room.getPoint2x()+x_bound-icrement_x>=user.getLocation_x() && room.getPoint1x()-x_bound+icrement_x<=user.getLocation_x() ){
								if(user.getLocation_y()<room.getPoint1y() &&  (room.getPoint1y()-user.getLocation_y())<y_bound){
									ret=false;
									break;
								}
							}
						}
						else if(room.isRightDirection()){
							if(room.getPoint1x()-x_bound+icrement_x<=user.getLocation_x()){
								if(user.getLocation_y()<room.getPoint1y() &&  (room.getPoint1y()-user.getLocation_y())<y_bound){
									ret=false;
									break;
								}

								if(user.getLocation_y()<room.getPoint2y() &&  (room.getPoint2y()-user.getLocation_y())<y_bound){
									ret=false;
									break;
								}

							}
						}
					}
				}
			}

			//Left

			else if(keyCode==Definitions.KEY_LEFT){
				if(room.isSingle()){

					boolean control=true;
					if(room.isDoorOpen()){
						if(Room.DIRECTION_DOOR_VER.equals(room.getDoorDirection())){
							if(room.getDoor_loc_y()<user.getLocation_yPoints() && room.getDoor_loc_y()+48>user.getLocation_yPoints())
								control=false;
						}

						if(Room.DIRECTION_DOOR_HOR.equals(room.getDoorDirection())){
							if(userInDoorHor(room,user)){
								ret=false;
								break;}
						}
					}
					if(control){
						//x control
						if(room.isUpDirection()){
							if(room.getPoint1y()+y_bound-increment_y>=user.getLocation_y()){
								if(user.getLocation_x()>room.getPoint1x() &&  (user.getLocation_x()-room.getPoint1x())<(x_bound)){
									ret=false;
									break;
								}

							}
						}
						else if(room.isDownDirection()){
							if(room.getPoint1y()-y_bound+increment_y<=user.getLocation_y()){
								if(user.getLocation_x()>room.getPoint1x() &&  (user.getLocation_x()-room.getPoint1x())<x_bound){
									ret=false;
									break;
								}

							}
						}
					}


				}
				else {  //double
					boolean control=true;
					if(room.isDoorOpen()){
						if(Room.DIRECTION_DOOR_VER.equals(room.getDoorDirection())){
							if(room.getDoor_loc_y()<user.getLocation_yPoints() && room.getDoor_loc_y()+48>user.getLocation_yPoints())
								control=false;
						}

						if(Room.DIRECTION_DOOR_HOR.equals(room.getDoorDirection())){
							if(userInDoorHor(room,user)){
								ret=false;
								break;}
						}
					}
					if(control){
						if(room.isUpDirection()){
							if(room.getPoint1y()+y_bound-increment_y>=user.getLocation_y()){
								if(user.getLocation_x()>room.getPoint1x() &&  (user.getLocation_x()-room.getPoint1x())<x_bound){
									ret=false;
									break;
								}

								if(user.getLocation_x()>room.getPoint2x() &&  (user.getLocation_x()-room.getPoint2x())<x_bound){
									ret=false;
									break;
								}

							}
						}
						else if(room.isLeftDirection()|| room.isRightDirection()){
							if(room.getPoint2y()+y_bound-increment_y>=user.getLocation_y() && room.getPoint1y()-y_bound+increment_y<=user.getLocation_y() ){
								if(user.getLocation_x()>room.getPoint1x() &&  (user.getLocation_x()-room.getPoint1x())<x_bound){
									ret=false;
									break;
								}
							}
						}
						else if(room.isDownDirection()){
							if(room.getPoint1y()-y_bound+increment_y<=user.getLocation_y()){
								if(user.getLocation_x()>room.getPoint1x() &&  (user.getLocation_x()-room.getPoint1x())<x_bound){
									ret=false;
									break;
								}

								if(user.getLocation_x()>room.getPoint2x() &&  (user.getLocation_x()-room.getPoint2x())<x_bound){
									ret=false;
									break;
								}

							}
						}
					}
				}
			}
			//Right
			else if(keyCode==Definitions.KEY_RIGHT){
				if(room.isSingle()){

					boolean control=true;
					if(room.isDoorOpen()){
						if(Room.DIRECTION_DOOR_VER.equals(room.getDoorDirection())){
							if(room.getDoor_loc_y()<user.getLocation_yPoints() && room.getDoor_loc_y()+48>user.getLocation_yPoints())
								control=false;
						}

						if(Room.DIRECTION_DOOR_HOR.equals(room.getDoorDirection())){
							if(userInDoorHor(room,user)){
								ret=false;
								break;}
						}
					}
					if(control){
						//x control
						if(room.isUpDirection()){
							if(room.getPoint1y()+y_bound-increment_y>=user.getLocation_y()){
								if(user.getLocation_x()<room.getPoint1x() &&  (room.getPoint1x()-user.getLocation_x())<(x_bound)){
									ret=false;
									break;
								}

							}
						}
						else if(room.isDownDirection()){
							if(room.getPoint1y()-y_bound+increment_y<=user.getLocation_y()){
								if(user.getLocation_x()<room.getPoint1x() &&  (room.getPoint1x()-user.getLocation_x())<x_bound){
									ret=false;
									break;
								}

							}
						}
					}

				}
				else {  //double
					boolean control=true;
					if(room.isDoorOpen()){
						if(Room.DIRECTION_DOOR_VER.equals(room.getDoorDirection())){
							if(room.getDoor_loc_y()<user.getLocation_yPoints() && room.getDoor_loc_y()+48>user.getLocation_yPoints())
								control=false;
						}

						if(Room.DIRECTION_DOOR_HOR.equals(room.getDoorDirection())){
							if(userInDoorHor(room,user)){
								ret=false;
								break;}
						}
					}
					if(control){
						if(room.isUpDirection()){
							if(room.getPoint1y()+y_bound-increment_y>=user.getLocation_y()){
								if(user.getLocation_x()<room.getPoint1x() &&  (room.getPoint1x()-user.getLocation_x())<x_bound){
									ret=false;
									break;
								}

								if(user.getLocation_x()<room.getPoint2x() &&  (room.getPoint2x()-user.getLocation_x())<x_bound){
									ret=false;
									break;
								}

							}
						}
						else if(room.isLeftDirection()|| room.isRightDirection()){
							if(room.getPoint2y()+y_bound-increment_y>=user.getLocation_y() && room.getPoint1y()-y_bound+increment_y<=user.getLocation_y() ){
								if(user.getLocation_x()<room.getPoint1x() &&  (room.getPoint1x()-user.getLocation_x())<x_bound){
									ret=false;
									break;
								}
							}
						}
						else if(room.isDownDirection()){
							if(room.getPoint1y()-y_bound+increment_y<=user.getLocation_y()){
								if(user.getLocation_x()<room.getPoint1x() &&  (room.getPoint1x()-user.getLocation_x())<x_bound){
									ret=false;
									break;
								}

								if(user.getLocation_x()<room.getPoint2x() &&  (room.getPoint2x()-user.getLocation_x())<x_bound){
									ret=false;
									break;
								}

							}
						}
					}
				}
			}



		}


		return ret;
	}

	private static boolean userInDoorHor(Room room, User user) {
		return(room.getDoor_loc_x()<=user.getLocation_xPoints()&& room.getDoor_loc_x()+96>=user.getLocation_xPoints()&& 
				room.getDoor_loc_y() <= user.getLocation_yPoints()+48 && room.getDoor_loc_y()+48 >= user.getLocation_yPoints());
	}

	public static boolean computerIntersection(int userX,int userY,int compX,int compY){

		if(intersect(userX,userY,compX,compY))
			return true;
		else if(intersect(userX,userY + 48,compX,compY))
			return true;
		else if(intersect(userX + 48,userY,compX,compY))
			return true;
		else if(intersect(userX + 48,userY + 48,compX,compY))
			return true;

		return false;
	}



	public static boolean intersect(int userX,int userY,int compX,int compY){

		return ((userX >= compX && userX <= compX+48) && (userY >= compY && userY <= compY+48));

	}

	public static boolean userInDoorVer(Room room,User user){
		return(room.getDoor_loc_y()<=user.getLocation_yPoints() && room.getDoor_loc_y()+96>=user.getLocation_yPoints() && 
				room.getDoor_loc_x() <= user.getLocation_xPoints()+48 && room.getDoor_loc_x()+48 >= user.getLocation_xPoints());


	}




}
