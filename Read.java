import java.io.BufferedReader;  
import java.io.FileReader; 
import java.io.File; 
import java.io.IOException;  
import java.util.ArrayList;
import java.util.Arrays;

public class Read{
   public String[] c;
   public double[] data;
   
   public static Object[] getCountry(String type){
      ArrayList<String> list= new ArrayList<>();
      String line = "";  
      int i=0;
      try{  
         BufferedReader br = new BufferedReader(new FileReader("dataSets//"+type+".csv"));  
         while ((line = br.readLine()) != null){   
            if(i!=0){   
               String[] lineArr = line.split(",");   
               list.add(lineArr[0]);
            }
            i++;
         }  
      }
      catch (IOException e){  
         e.printStackTrace();  
      }  
      return (list.toArray());
   }
   
   public static String[] getYears(String dataType){
      String line[]=null;
      try{  
         BufferedReader br = new BufferedReader(new FileReader("dataSets//"+dataType+".csv")); 
         String line2[]= br.readLine().split(",");
         line= new String[line2.length-1];
         for(int i=1; i!=line2.length; i++){
            line[i-1]=line2[i];
         }
      } 
      catch(Exception e){}
      return line;
   }
   
   public static double[] readFile(String dataType, String country){
      String line = "";  
      try{  
         BufferedReader br = new BufferedReader(new FileReader("dataSets//"+dataType+".csv"));  
         while ((line = br.readLine()) != null){     
            String[] lineArr = line.split(","); 
            if(lineArr[0].equals(country)){
               double rez[]= new double[lineArr.length-1];
               for(int i=0; i!=rez.length; i++){
                  rez[i]=Double.parseDouble(lineArr[i+1]);
               }
               return rez;
            }  
         }
      }
      catch (IOException e){} 
      return new double[]{-1,-1,-1,-1,-1};
   }
   
   public static String getTheInstrument(String find, String lloji){
      String line = "";  
      String[] lineArr= null;
      int i=0;
      try{       
         BufferedReader br = new BufferedReader(new FileReader("dataSets//instrumentet.csv"));  
         while ((line = br.readLine()) != null){  
            lineArr = line.split(",");
            if(i==0){
               for(int j=1; j!=lineArr.length; j++){
                  if(lineArr[j].equals(lloji)){
                     i=j;
                  }
               }
            }
            else if(lineArr[0].equals(find)){
               return lineArr[i];
            }  
         }
      }
      catch (IOException e){}  
      return "Error404";
   }
   
   //countrys are all countrys of an region
   public void getDataForRegion(String dataType, String[] countrys, String year){
      String line = "";  
      int i=0;
      double[] rez= new double[countrys.length];
      int count=0;
      ArrayList<String> coun= new ArrayList<>();
      ArrayList<Double> dat= new ArrayList<>();
      try{       
         BufferedReader br = new BufferedReader(new FileReader("dataSets//"+dataType+".csv"));  
         while ((line = br.readLine()) != null){
            String lineArr[]=line.split(",");
            if(i==0){
               for(int j=1; j!=lineArr.length; j++){
                  if(lineArr[j].equals(year)){
                     i=j;
                  }
               }
            }
            else{
               for(int j=0; j!=countrys.length; j++){
                  if(lineArr[0].equals(countrys[j]) || lineArr[0].contains(countrys[j]) || countrys[j].contains(lineArr[0])){
                     coun.add(lineArr[0]);
                     dat.add(Double.parseDouble(lineArr[i]));
                  }
               }
            }
         }   
      }catch(Exception e){}
      c= new String[coun.size()];
      data= new double[coun.size()];
      for(int j=0; j!=c.length; j++){
         c[j]=coun.get(j);
         data[j]=dat.get(j);
      }
   }
   
   //takes states from region file
   public static String[] getRegion(String dataType, String region){
      String line = "";  
      try{       
         BufferedReader br = new BufferedReader(new FileReader("dataSets//Continents.csv"));  
         while ((line = br.readLine()) != null){
            String[] lineArr= line.split(",");      
            if(lineArr[0].equals(region)){
               String[] rez= new String[lineArr.length-1];
               for(int i=0; i!=rez.length; i++){
                  rez[i]=lineArr[i+1];
               }
               return rez;
            }
         }
      }
      catch (IOException e){}  
      return new String[]{"Error404"};
   }
}