import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.event.ActionListener;

public class Serious extends JFrame {
   private int h=670, w=570;
   private String[] dataTypes;
   private JComboBox comboBox_1, comboBox, year;
   private String[] countries, a;
   private JLabel l1,C ;
   private JButton btnSerach;
   private int startY, endY, startM, endM;
   private String[] regions, countrys, value;
   private boolean analise, go=false;
   private int startMonth=-1, endMonth=-1, startYear=-1, endYear=-1;

   public static void main(String[] args) {
      new Serious();
   }
      
   public Serious(){
      try {
         for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
               javax.swing.UIManager.setLookAndFeel(info.getClassName());
               break;
            }
         }
      } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
         java.util.logging.Logger.getLogger(Serious.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      }
      valueDealy();
      setCountrys();
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
      setSize(h, w);
      setName("NASA SPACE APPS");
      setLocationRelativeTo(null);
      setResizable(false);
      getContentPane().setLayout(null);
      btnSerach = new JButton(new ImageIcon (getClass().getResource("background//po.png")));
      twoButtons();
      checkCountries();
      checkType();
      yearDealy();
      buton();
      logo();
      backPhoto();
      setVisible(true); 
      go();  
   }
   
   public void go(){
      while(true){
         System.out.print("");
         if(go){
            go=false;
            if(startMonth==-1){
               new Map(comboBox.getSelectedItem().toString(),comboBox_1.getSelectedItem().toString(),"");
            }
            else{
               new Map(comboBox.getSelectedItem().toString(),comboBox_1.getSelectedItem().toString(),year.getSelectedItem().toString());
            }
         }
      }
   }
   
   public void valueDealy(){
      value= new String[]{"----","Celcius in january","Percent of total area","Hectares","Percent of total area","","Square kilometers(000's)","Percent of total area"};
   }
   
   public void dataTypesDealy(boolean t){
      if(t){
         dataTypes= new String[]{"Demographic type","Climate","CropLand","Fire damage","InlandWater","PM2.5 concentration","Tree Cover","Artificial surfaces"};
         comboBox.setModel(new DefaultComboBoxModel(dataTypes));
         comboBox.setFont(new Font("Arial Rounded MT Bold",0,20));
         comboBox.setSelectedItem("Demographic type");
      }
      else{
         comboBox.setModel(new DefaultComboBoxModel(Read.getCountry("instrumentet")));
         comboBox.setFont(new Font("Arial Rounded MT Bold",0,15));
         comboBox.addItem("Demographic type");
         comboBox.setSelectedItem("Demographic type");
      }
   }
   
   public void countryDeal(){
      if(analise){
         comboBox_1.setModel(new DefaultComboBoxModel(Read.getCountry(comboBox.getSelectedItem().toString())));
         for(int i=0; i!=regions.length; i++){
            comboBox_1.addItem(regions[i]);
         }
         comboBox_1.addItem("Choose a Country");
         comboBox_1.setSelectedItem("Choose a Country");
         comboBox_1.setEnabled(true);
         year.setVisible(false);
      }
      else{
         Read2 r= new Read2();
         r.setYearMonth(comboBox.getSelectedItem().toString());
         startYear=r.startYear;
         endYear=r.endYear;
         startMonth=r.startMonth;
         endMonth=r.endMonth;
         btnSerach.setEnabled(false);
         year.setEnabled(false);
         comboBox_1.setEnabled(true);
         String tempo[]= new String[endYear-startYear+1];
         for(int i=0; i<tempo.length; i++){
            tempo[i]=(i+startYear+"");
         }
         comboBox_1.setModel(new DefaultComboBoxModel(tempo));
         comboBox_1.addItem("Year");
         comboBox_1.setSelectedItem("Year");
         if(startMonth==-1){
            year.setEnabled(false);
            btnSerach.setEnabled(true);
            btnSerach.setVisible(true);
         }
      }
   }
   
   public void setCountrys(){
      regions= new String[]{"----Countrys----","North America","South America","Africa","Europe","Asia","Oceania","---Continents---"};
   }
   
   public void twoButtons(){
      JButton[] b= new JButton[2];
      b[0]= new JButton("Analise");
      b[1]= new JButton("Visualise");
      for(int i=0; i!=b.length; i++){
         b[i].setFont(new Font("Arial Rounded MT Bold",0,20));
         b[i].setBounds(175+170*i,200,150,50);
         getContentPane().add(b[i]);
      }
      b[0].addActionListener(
         e->{
            analise=true;
            dataTypesDealy(true);
            btnSerach.setVisible(true);
            comboBox_1.setVisible(true);
            comboBox_1.setSelectedItem(null);
            year.setSelectedItem(null);
            comboBox.setVisible(true);
            comboBox_1.setBounds(238, 260, 260, 50);
            year.setBounds(500, 260, 90, 50);
            year.setVisible(false);
            comboBox.setBounds(10, 260, 219, 50);
         });
      b[1].addActionListener(
         e->{
            analise=false;
            dataTypesDealy(false);
            btnSerach.setVisible(true);
            comboBox_1.setSelectedItem(null);
            year.setSelectedItem(null);
            comboBox_1.setVisible(true);
            comboBox.setVisible(true);
            comboBox_1.setBounds(425, 260, 90, 50);
            year.setBounds(520, 260, 70, 50);
            year.setVisible(true);
            comboBox.setBounds(10, 260, 410, 50);         
         });
   }
   
   public void buton(){
      btnSerach.setEnabled(false);
      btnSerach.setVisible(false);
      btnSerach.setBounds(596, 260, 44, 49);
      btnSerach.addActionListener(
         e->{
            if(analise){
               boolean t=true;
               for(int i=1; i!=regions.length-1; i++){
                  if(comboBox_1.getSelectedItem().toString().equals(regions[i])){
                     t=false;
                     break;
                  }
               }
               if(t){
                  new Diagram(comboBox_1.getSelectedItem().toString(),comboBox.getSelectedItem().toString(),value[comboBox.getSelectedIndex()]);
               }
               else{
                  new DiagramRegion(comboBox_1.getSelectedItem().toString(),comboBox.getSelectedItem().toString(),year.getSelectedItem().toString(),value[comboBox.getSelectedIndex()]);
               }
            }
            else{
               go=true;
            }
         });
      getContentPane().add(btnSerach);
   }
   
   public void yearDealy(){
      year= new JComboBox();
      year.setVisible(false);
      year.setFont(new Font("Arial Rounded MT Bold",0,20));
      year.setBounds(500, 260, 90, 50);
      year.addActionListener(
         e->{
            if(analise){
               btnSerach.setEnabled(year.getSelectedItem()!=null && !year.getSelectedItem().toString().equals("Year"));
            }
            else{
               btnSerach.setEnabled(year.getSelectedItem()!=null && !year.getSelectedItem().toString().equals("M"));
            }
         });
      getContentPane().add(year);      
   }

   public void checkCountries() {
      comboBox_1 = new JComboBox();
      comboBox_1.setVisible(false);
      comboBox_1.setEnabled(false);
      comboBox_1.setToolTipText("Search a country?");
      comboBox_1.setFont(new Font("Arial Rounded MT Bold",0,20));
      comboBox_1.setBounds(238, 260, 260, 50);
      comboBox_1.addItem("Choose a Country");
      comboBox_1.addActionListener(
         e->{
            if(analise){
               if(comboBox_1.getSelectedItem()!=null && !comboBox_1.getSelectedItem().toString().equals("Choose a Country") && !comboBox_1.getSelectedItem().toString().equals("----Countrys----") && !comboBox_1.getSelectedItem().toString().equals("---Continents---")){
                  btnSerach.setEnabled(true);
                  boolean t=true;
                  for(int i=1; i!=regions.length-1; i++){
                     if(comboBox_1.getSelectedItem().toString().equals(regions[i])){
                        t=false;
                        year.setModel(new DefaultComboBoxModel(Read.getYears(comboBox.getSelectedItem().toString())));
                        year.addItem("Year");
                        year.setSelectedItem("Year");
                        break;
                     }
                  }
                  year.setVisible(!t);
                  btnSerach.setEnabled(t);
               }
               else{
                  year.setVisible(false);
                  btnSerach.setEnabled(false);
               }
            }
            else{
               if(comboBox_1.getSelectedItem()!=null && !comboBox_1.getSelectedItem().toString().equals("Year")){
                  int tempoS= startMonth;
                  int tempoE= endMonth;
                  if(!comboBox_1.getSelectedItem().toString().equals(startYear+"")){
                     tempoS=1;
                  }
                  if(!comboBox_1.getSelectedItem().toString().equals(endYear+"")){
                     tempoE=12;
                  }
                  if(startMonth!=-1){
                     String tempo[]= new String[tempoE-tempoS+1];
                     for(int i=0; i<tempo.length; i++){
                        tempo[i]=tempoS+i+"";
                     }
                     year.setModel(new DefaultComboBoxModel(tempo));
                     year.addItem("M");
                     year.setSelectedItem("M");
                     year.setEnabled(true);
                  }
                  else{
                     year.setSelectedItem(null);
                     btnSerach.setEnabled(true);
                  }
               }
            }
         });
      getContentPane().add(comboBox_1);
   
   }
   public void checkType() {
      comboBox = new JComboBox();
      comboBox.setVisible(false);
      comboBox.setFont(new Font("Arial Rounded MT Bold",0,20));
      comboBox.setForeground(Color.BLACK);
      comboBox.setBounds(10, 260, 219, 50);
      comboBox.addActionListener(
         e->{
            if(!comboBox.getSelectedItem().toString().equals("Demographic type")){
               countryDeal();
            }
            else{
               comboBox_1.setEnabled(false);
               btnSerach.setEnabled(false);
            }
         });
      getContentPane().add(comboBox);
   
   }
   public void logo(){
      l1 = new JLabel();
      l1.setIcon( new ImageIcon(this.getClass().getResource("background//kk.png")));
      l1.setBounds(87, 59, 522, 128);
      getContentPane().add(l1);
   }
   public void backPhoto() {
      C = new JLabel("");
      C.setBounds(-111, -40, 1017, 600);
      C.setIcon( new ImageIcon(this.getClass().getResource("background//gg.png")));
      getContentPane().add(C);
   }
}
