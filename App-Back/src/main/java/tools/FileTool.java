package tools; 
import java.io.BufferedReader; 
import java.io.File; 
import java.io.FileReader; 
import java.io.IOException; 
/** 
* 
* @author DO.IT-SUDPARIS 
* Ce fichier sert � 
* + Lire le contenu d'un fichier: getContents 
* + Entr�e: Objet File 
* + Sortie: une chaine de caract�re 
*/ 
public class FileTool { 
 static public String getContents(File aFile) { 
 //...checks on aFile are elided 
 StringBuilder contents = new StringBuilder(); 
 try { 
 //use buffering, reading one line at a time 
 //FileReader always assumes default encoding is OK!  
	 BufferedReader input = new BufferedReader(new  FileReader(aFile)); 
 try { 
 String line = null; 

 while ((line = input.readLine()) != null) {  contents.append(line); 
contents.append(System.getProperty("line.separator")); 
 } 
 } finally { 
 input.close(); 
 } 
 } catch (IOException ex) { 
 ex.printStackTrace(); 
 } 
 return contents.toString(); 
 } 
}
