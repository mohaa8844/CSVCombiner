package csvCombiner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Vector;

public class Combiner {
	public static void main(String[] args) throws IOException{
		
		BufferedReader reader =new BufferedReader(new InputStreamReader(System.in)); 
		System.out.print("Enter the path to your csvs:\n");
		String path = reader.readLine(); 
		if(path.charAt(path.length()-1)!='/' &&path.charAt(path.length()-1)!='\\')path+="/";
		FileWriter outFile = new FileWriter(path+"out.csv");
		boolean first=true;
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		Vector<String> csvs=new Vector();
		String ext="";
		for (File file : listOfFiles) {
		    if (file.isFile()) {
		    	String[]temp=file.getName().replace('.', ',').split(",");
		    	if (temp.length>1){
		    		ext =temp[temp.length-1];
			        if(ext.equals("csv")&& !file.getName().equals("out.csv")){
			        	csvs.add(file.toString());
			        }
		    	}
		    }
		}
		
		for (String csv : csvs) {
			System.out.println("in "+csv);
			try (BufferedReader br = Files.newBufferedReader( Paths.get(csv))) { 
				String line = br.readLine(); 
				while (line != null) {
					if (first)first=false;
					else outFile.write("\n");
					outFile.write(line);
					line = br.readLine(); 
					
				} 
			} catch (IOException ioe) { 
				ioe.printStackTrace(); 
			}
		}
		outFile.close();
		
	}
}

