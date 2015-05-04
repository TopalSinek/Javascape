package javascape.game;

import java.util.Iterator;
import java.util.Vector;

import javascape.composite.TopComposite;
import javascape.creater.SectionCreater;
import javascape.dialog.QuestionDialog;
import javascape.game.controler.DirectionControl;
import javascape.game.controler.RoomFinder;
import javascape.model.Definitions;
import javascape.model.Room;
import javascape.model.Section;
import javascape.model.User;
import javascape.util.ImageUtil;
import javascape.util.QuestionUtil;
import javascape.util.SoundUtil;
import javascape.util.UIUtil;

import org.eclipse.jface.window.Window;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Shell;

public class GamePlay {
	private User user;
	private Section section;
	private Canvas canvas;
	private boolean test=false;
	private TopComposite topComposite;
	private SoundUtil soundUtil;


	public void draw( GC gc){ //draw section
		Image imgWall=ImageUtil.getWall(section);
		Image imgHuman=ImageUtil.getHuman();


		Rectangle rect = canvas.getBounds();
		int width=rect.width;
		int height=rect.height;
		int imageBound=imgWall.getBounds().height;
		//before draw rooms
		for (Iterator iterator = section.getLstRooms().iterator(); iterator.hasNext();) {
			Room room = (Room) iterator.next();
			Image imgComp=ImageUtil.getComputer(room);
			Image imgDoor=ImageUtil.getDoor(room);
			int compX=0,compY=0;

			if(room.isSingle()){ // drawing single rooms
				int srcX=(width*room.getPoint1x())/100;
				int srcY=(height*room.getPoint1y())/100;
				gc.drawImage(imgWall, srcX, srcY);
				int srcX0=srcX;
				int srcY0=srcY;

				String direction1=room.getDirection1();
				String direction2=room.getDirection2();
				if(direction1.equals(Room.DIRECTION_DOWN) || direction1.equals(Room.DIRECTION_UP) ){
					direction1=room.getDirection2();
					direction2=room.getDirection1();
				}

				//System.out.println("srcX "+srcX +"  srcY "+srcY+" width "+width+" height "+height); //for testing location data
				if(direction1.equals(Room.DIRECTION_LEFT)){
					while(srcX>0){
						srcX=srcX-imageBound;
						gc.drawImage(imgWall, srcX, srcY);
					}
				}
				else if(direction1.equals(Room.DIRECTION_RIGHT)){
					while(srcX<width){
						srcX=srcX+imageBound;
						gc.drawImage(imgWall, srcX, srcY);
					}
				}



				if(direction2.equals(Room.DIRECTION_DOWN)){
					while(srcY<height){
						srcY=srcY+imageBound;
						gc.drawImage(imgWall, srcX0, srcY);
					}
				}
				else if(direction2.equals(Room.DIRECTION_UP)){
					while(srcY>0){
						srcY=srcY-imageBound;
						gc.drawImage(imgWall, srcX0, srcY);
					}
				}

				if(Room.DIRECTION_DOOR_HOR.equals(room.getDoorDirection())){ // draw door
					if(room.isLeftDirection()){
						room.setDoor_loc_x(srcX0/2);
						room.setDoor_loc_y(srcY0);
					}
					else{
						room.setDoor_loc_x((srcX0+width)/2);
						room.setDoor_loc_y(srcY0);
					}
				}
				else{
					if(room.isUpDirection()){
						room.setDoor_loc_x(srcX0);
						room.setDoor_loc_y(srcY0/2);
					}
					else{
						room.setDoor_loc_x(srcX0);
						room.setDoor_loc_y((height+srcY0)/2);
					}
				}
				gc.drawImage(imgDoor, room.getDoor_loc_x(), room.getDoor_loc_y());

				//to draw computer
				if(direction1.equals(Room.DIRECTION_LEFT)){
					compX=srcX0/2;
				}
				else{
					compX=width - ((width-srcX0)/2);
				}
				if(direction2.equals(Room.DIRECTION_UP)){
					compY=(srcY0/2)-24;
				}
				else{
					compY=24+height - ((height-srcY0)/2);
				}
				room.setComputerLocationX(compX);
				room.setComputerLocationY(compY);
				//System.out.println(direction1+" "+direction2+" " +compX+" "+compY);
				gc.drawImage(imgComp, room.getComputerLocationX(), room.getComputerLocationY());

			}
			else{//DOUBLE room MODE
				int src1X=(width*room.getPoint1x())/100;
				int src1Y=(height*room.getPoint1y())/100;
				gc.drawImage(imgWall, src1X, src1Y);


				int src2X=(width*room.getPoint2x())/100;
				int src2Y=(height*room.getPoint2y())/100;
				gc.drawImage(imgWall, src2X, src2Y);

				int src1X0=src1X;
				int src1Y0=src1Y;
				int src2X0=src2X;
				int src2Y0=src2Y;

				String direction1=room.getDirection1();

				if(direction1.equals(Room.DIRECTION_LEFT)){
					while(src1X>0){
						src1X=src1X-imageBound;
						gc.drawImage(imgWall, src1X, src1Y);
					}
					while(src2X>0){
						src2X=src2X-imageBound;
						gc.drawImage(imgWall, src2X, src2Y);
					}
					int yMin=Math.min(src1Y, src2Y);
					int yMax=Math.max(src1Y, src2Y);

					while(yMin<yMax-imageBound){
						yMin=yMin+imageBound;
						gc.drawImage(imgWall, src1X0, yMin);
					}
				}
				else if(direction1.equals(Room.DIRECTION_RIGHT)){
					while(src1X<width){
						src1X=src1X+imageBound;
						gc.drawImage(imgWall, src1X, src1Y);
					}
					while(src2X<width){
						src2X=src2X+imageBound;
						gc.drawImage(imgWall, src2X, src2Y);
					}
					int yMin=Math.min(src1Y, src2Y);
					int yMax=Math.max(src1Y, src2Y);

					while(yMin<yMax-imageBound){
						yMin=yMin+imageBound;
						gc.drawImage(imgWall, src1X0, yMin);
					}
				}


				else if(direction1.equals(Room.DIRECTION_UP)){
					while(src1Y>0){
						src1Y=src1Y-imageBound;
						gc.drawImage(imgWall, src1X, src1Y);
					}
					while(src2Y>0){
						src2Y=src2Y-imageBound;
						gc.drawImage(imgWall, src2X, src2Y);
					}
					int xMin=Math.min(src1X, src2X);
					int xMax=Math.max(src1X, src2X);

					while(xMin<xMax-imageBound){
						xMin=xMin+imageBound;
						gc.drawImage(imgWall, xMin, src1Y0);
					}
				}

				else if(direction1.equals(Room.DIRECTION_DOWN)){
					while(src1Y<height){
						src1Y=src1Y+imageBound;
						gc.drawImage(imgWall, src1X, src1Y);
					}
					while(src2Y<height){
						src2Y=src2Y+imageBound;
						gc.drawImage(imgWall, src2X, src2Y);
					}
					int xMin=Math.min(src1X, src2X);
					int xMax=Math.max(src1X, src2X);

					while(xMin<xMax-imageBound){
						xMin=xMin+imageBound;
						gc.drawImage(imgWall, xMin, src1Y0);
					}
				}

				//Double room doors
				if(room.isLeftDirection() || room.isRightDirection()){
					room.setDoor_loc_x(src1X0);
					room.setDoor_loc_y(src1Y0+((src2Y0-src1Y0)/2));
				}
				else{
					room.setDoor_loc_y(src1Y0);
					room.setDoor_loc_x(src1X0+((src2X0-src1X0)/2));
				}
				gc.drawImage(imgDoor, room.getDoor_loc_x(), room.getDoor_loc_y());



				//to draw computer
				if(room.isLeftDirection()){
					compX=src1X0/2;
					compY=Math.min(src1Y0, src2Y0)+Math.abs(src2Y0-src1Y0)/2;
				}
				else if(room.isRightDirection()){
					compX=src1X0+  (width-src1X0)/2;
					compY=Math.min(src1Y0, src2Y0)+Math.abs(src2Y0-src1Y0)/2;
				}
				else if(room.isUpDirection()){
					compX=Math.min(src1X0, src2X0)+Math.abs(src2X0-src1X0)/2;
					compY=src1Y0/2;
				}
				else if(room.isDownDirection()){
					compX=Math.min(src1X0, src2X0)+Math.abs(src2X0-src1X0)/2;
					compY=src1Y0+  (height-src1Y0)/2;
				}
				room.setComputerLocationX(compX);
				room.setComputerLocationY(compY);
				//System.out.println(direction1+" "+direction2+" " +compX+" "+compY);
				gc.drawImage(imgComp, room.getComputerLocationX(), room.getComputerLocationY());
			}

		}

		//draw user 
		int srcX=(width*user.getLocation_x())/100;
		int srcY=(height*user.getLocation_y())/100;
		user.setLocation_xPoints(srcX);
		user.setLocation_yPoints(srcY);
		gc.drawImage(imgHuman, srcX, srcY);


	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public Section getSection() {
		return section;
	}



	public void setSection(Section section) {
		this.section = section;
	}



	public void redraw() {
		canvas.redraw();

	}



	public Canvas getCanvas() {
		return canvas;
	}



	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}

	public void keyPress(int keyCode) {  // press 'e' to open and close door
		//System.out.println("keyCode "+keyCode);
		boolean redraw=false;
		if(Definitions.isDirectionKey(keyCode))
			redraw=directionKeyPress(keyCode);
		else{
			if(Definitions.isKeyE(keyCode)){
				Room room=RoomFinder.findRoom(this);
				if(room!=null){
					Room previousRoom = null;
					for (Iterator iterator = section.getLstRooms().iterator(); iterator.hasNext();) {
						Room roomX = (Room) iterator.next();
						if(roomX.getId() == room.getId()-1)
							previousRoom = roomX;
					}
					
					if(previousRoom.isQuestionSolved()){
						room.setDoorOpen(!room.isDoorOpen());
						redraw=true;
					}
					else{
						UIUtil.showMsgBox(canvas.getShell(), "Not Yet", "You have to solve the previous problem!");
					}
				}
				else{
					UIUtil.showMsgBox(canvas.getShell(), "Invalid button", "Get closer to a door and press 'e'");
				}
			}
			else if(Definitions.isKeyQ(keyCode)){

				Room room=RoomFinder.findComputerRoom(this);
				if(room!=null){
					if(room.isQuestionSolved()){
						UIUtil.showMsgBox(canvas.getShell(), "Try another", "You already solved this question");
					}
					else{
						QuestionDialog dgQ=new QuestionDialog(canvas.getShell());
						dgQ.set(ImageUtil.getQuestionImg(room),QuestionUtil.getQuestion(room));
						if(Window.OK==dgQ.open()){
							room.setQuestionSolved(true);
							UIUtil.showMsgBox(canvas.getShell(), "Congrulations", "You solved the question");
							user.addScore(1);
							
							int solvedRoomNum=0;
							for (Iterator iterator = section.getLstRooms().iterator(); iterator.hasNext();) {
								Room roomX = (Room) iterator.next();
								if(roomX.isQuestionSolved())
									solvedRoomNum++;
							}

							if(solvedRoomNum==section.getLstRooms().size()){
								user.nextSction();
								UIUtil.showMsgBox(canvas.getShell(), "Congrulations", "Congrulations. Go to Next Section");
								section=SectionCreater.createSection(user.getSectionNum());
								redraw=true;
							}


						}
						else
							//Chekpoint Load TODO
							if(user.getisDead()){
								Game.load();
							}
							redraw = true;
					}


				}
			}
		}
		if(redraw){
			redraw();
			printUser();
		}
	}

	public boolean directionKeyPress(int keyCode) {  //collision box
		boolean ret=true;

		if(isTest()){
			return getUser().keyPress(keyCode);
		}


		ret=DirectionControl.directionControl(keyCode, this);



		if(ret)
			ret=getUser().keyPress(keyCode);


		return ret;
	}

	public boolean isTest() {
		return test;
	}

	public void setTest(boolean test) {
		this.test = test;
	}

	public void printUser(){
		if(user!=null && topComposite!=null){
			topComposite.lblScore.setText("Score: " + user.getScore());
			topComposite.lblUserName.setText(user.getName());
			topComposite.lblDeath.setText("Number of tries: " + user.getNumOfDeath());
			topComposite.lblSection.setText(" Section: "+user.getSectionNum());
		}
		else if(user==null && topComposite!=null){
			topComposite.lblScore.setText("Score: ");
			topComposite.lblUserName.setText("");
			topComposite.lblDeath.setText("Number of tries: ");
			topComposite.lblSection.setText("");
		}
	}


	public TopComposite getTopComposite() {
		return topComposite;
	}



	public void setTopComposite(TopComposite topComposite) {
		this.topComposite = topComposite;
	}



	public SoundUtil getSoundUtil() {
		return soundUtil;
	}



	public void setSoundUtil(SoundUtil soundUtil) {
		this.soundUtil = soundUtil;
	}



	public Shell getShell() {

		return getCanvas().getShell();
	}

	public Vector getSaveData() { // adding user and secion data to vector object
		Vector v=new Vector();
		v.add(user);
		v.add(section);
		return v;
	}
}
