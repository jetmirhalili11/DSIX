import java.io.BufferedReader;  
import java.io.FileReader;  
import java.io.IOException;  
import java.util.ArrayList;
import java.util.Arrays;

public class Read{
   public static Object[] dataTypes(){
      String line = "";  
      String splitBy = ",";  
      String[] lineArr= null;
      try{  
         BufferedReader br = new BufferedReader(new FileReader("2015.csv"));  
         line = br.readLine();      
         lineArr = line.split(splitBy);  
      }
      catch (IOException e){  
         e.printStackTrace();  
      }  
      ArrayList<String> list= new ArrayList<>(Arrays.asList(lineArr));
      list.remove(0);
      list.add(0,"Fire damage (Ha)");
      list.add(0,"Climate");
      list.add(0,"PM2.5 concentration"); 
      return list.toArray();  
   }

   public static Object[] country(String type){
      String path;
      if(type.equals("Fire damage (Ha)")  || type.equals("Climate") || type.equals("PM2.5 concentration")){
         path=type+".csv";
      }
      else{
         path="2015.csv";
      }
      ArrayList<String> list= new ArrayList<>();
      String line = "";  
      String splitBy = ",";  
      int i=0;
      try{  
         BufferedReader br = new BufferedReader(new FileReader(path));  
         while ((line = br.readLine()) != null){   
            if(i!=0){   
               String[] lineArr = line.split(splitBy);   
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
   
   public static double [] readFile(String dataType, int type, int country){
      double[] rez= new double[5];
      String[] lineArr= null;
      String[] lineArr2=null;
      String line = "";  
      String splitBy = ","; 
      int i=0;
      if(type<3){
         rez= new double[10]; 
         try{  
            BufferedReader br = new BufferedReader(new FileReader(dataType+".csv"));  
            while ((line = br.readLine()) != null){ 
               if(i==0){
                  lineArr2= line.split(splitBy);
               }
               if(i-1==country){     
                  lineArr = line.split(splitBy); 
                  break;  
               }
               i++;
            }  
         }
         catch (IOException e){  
            e.printStackTrace();  
         } 
         for(int j=0; j!=5; j++){
            try{
               rez[j]=Double.parseDouble(lineArr[j+1]);
               rez[j+5]=Double.parseDouble(lineArr2[j+1]);
            }
            catch(Exception e){
               rez[j]=-1;
            }
         } 
      }
      else{ 
         for(int year=2015; year<2020; year++){
            i=0;
            String[] tempArr=null;
            try{  
               BufferedReader br = new BufferedReader(new FileReader(year+".csv"));  
               while ((line = br.readLine()) != null){ 
                  if(i-1==country){     
                     tempArr = line.split(splitBy); 
                     break;  
                  }
                  i++;
               }  
            }
            catch (IOException e){  
               e.printStackTrace();  
            }
            try{ 
               rez[year-2015]=Double.parseDouble(tempArr[type-2]);
            }
            catch(Exception e){
               rez[year-2015]=-1;
            }
         }
      }
      return rez;
   }
}