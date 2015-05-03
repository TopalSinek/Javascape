package javascape.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

import javascape.model.Room;
import javascape.model.Section;

public class FileUtil {
	public static Vector getVectorFromFile(String fileName) throws Exception{
		Vector v=null;
		FileInputStream fin = new FileInputStream(fileName);
		   ObjectInputStream ois = new ObjectInputStream(fin);
		    v = (Vector) ois.readObject();
		   ois.close();
		   
		   return v;
	}
	
	public static void saveVectorToFile(String fileName,Vector v)throws Exception{
		FileOutputStream fout = new FileOutputStream(fileName);
		ObjectOutputStream oos = new ObjectOutputStream(fout);   
		oos.writeObject(v);
		oos.close();
	}
	
	public static void main(String[] args) {
		Section sec=readSectionFromFile("d:/utku.txt");
		System.out.println("Test");
	}
	
	
//	wallImage:wall.jpg
//	Room:1
//	locP1:30
//	locP2:20
//	Room:2
//	locP1:50
//	locP2:20
	public static Section readSectionFromFile(String fileName){
		Section sec=null;
		try {
			
			FileReader fr=new FileReader(new File(fileName));
			
			//BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			BufferedReader bf=new BufferedReader(fr);
			int strNum=1;
			String line=bf.readLine();
			
			String[] strA=line.split(":");
			System.out.println(strA[0]+" "+strA[1]);
			 sec=new Section();
			sec.setWallImageName(strA[1]);

			Room room=null;
			line=bf.readLine();
			while(line!=null){
				strA=line.split(":");
				if("Room".equals(strA[0])){
					if(room!=null)
						sec.getLstRooms().add(room);
					room=new Room();
					room.setId(Integer.parseInt(strA[1]));
				}
				else{
					if("locP1".equals(strA[0]))
						room.setPoint1x(Integer.parseInt(strA[1]));
					else if("locP2".equals(strA[0]))
						room.setPoint1y(Integer.parseInt(strA[1]));
					
				}
				//System.out.println(strNum+++ " "+line);
				line=bf.readLine();
			}
			if(room!=null)
				sec.getLstRooms().add(room);
			fr.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sec;
		
	}
}
