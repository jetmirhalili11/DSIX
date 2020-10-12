import java.io.BufferedReader;  
import java.io.FileReader; 
import java.io.File; 
import java.io.IOException;  
import java.util.ArrayList;
import java.util.Arrays;

public class Read2{
   public int startMonth=-1, endMonth=-1, startYear=-1, endYear=-1;
   
   public void setYearMonth(String dataType){
      String line = "";  
      int j=0;
      int monthSI=0;
      int yearSI=0;
      int monthEI=0;
      int yearEI=0;
      try{  
         BufferedReader br = new BufferedReader(new FileReader("dataSets//instrumentet.csv"));  
         while ((line = br.readLine()) != null){   
            String[] lineArr = line.split(",");
            if(j==0){      
               for(int i=1; i!=lineArr.length; i++){
                  if(lineArr[i].equals("Start Month")){
                     monthSI=i;
                  }
                  else if(lineArr[i].equals("Start Year")){
                     yearSI=i;
                  }
                  else if(lineArr[i].equals("End Month")){
                     monthEI=i;
                  }
                  else if(lineArr[i].equals("End Year")){
                     yearEI=i;
                  }
               }
               j++;
            }
            else{
               if(lineArr[0].equals(dataType)){
                  startMonth=Integer.parseInt(lineArr[monthSI]);
                  endMonth=Integer.parseInt(lineArr[monthEI]);
                  startYear=Integer.parseInt(lineArr[yearSI]);
                  endYear=Integer.parseInt(lineArr[yearEI]);
               }
            }
         }  
      }
      catch (IOException e){}  
   }
}