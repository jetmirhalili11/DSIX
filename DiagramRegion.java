import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.ComboBoxUI;
import java.util.ArrayList;

public class DiagramRegion extends JPanel{
   private double[] a,b;
   private String[] countrys;
   protected JFrame f;
   private JLabel title, value;
   private JLabel[][] analis;
   private Color[] colors;
   private ArrayList<Double> second;
   private JComboBox[] box;
   
   public DiagramRegion(String region, String dataType, String year, String value1){
      second= new ArrayList<>();
      colorDealy();
      a=setDatas(region, dataType,year);
      title= new JLabel(region+" - "+dataType+"("+year+")");
      this.setBounds(280,80,500,350);
      this.setOpaque(true);
      this.setBackground(Color.RED);
      f= new JFrame();
      f.setLayout(null);
      f.setResizable(false);
      f.setSize(800+16,500+39);
      f.setLocationRelativeTo(null);
      boxDealy();
      titleDeal();
      analisDealy(value1);
      addData(1);
      f.getContentPane().setBackground(new Color(255,255,255));
      f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      f.getContentPane().add(this);
      bacgroundDealy(dataType);
      f.setVisible(true);
   }
   
   public void colorDealy(){
      colors= new Color[10];
      colors[0]=Color.BLUE;
      colors[1]=Color.GRAY;
      colors[2]=Color.CYAN;
      colors[3]=Color.RED;
      colors[4]=Color.GREEN;
      colors[5]=Color.MAGENTA;
      colors[6]=Color.ORANGE;
      colors[7]=Color.PINK;
      colors[8]=Color.YELLOW;
      colors[9]=Color.LIGHT_GRAY;
   }
   
   public void boxDealy(){
      box= new JComboBox[2];
      JButton[] button= new JButton[2];
      for(int i=0; i!=box.length; i++){
         box[i]= new JComboBox();
         box[i].setFont(new Font("Arial Rounded MT Bold",0,15));
         box[i].setBounds(30,350+i*50, 170, 30);
         box[i].setBorder(null);
         box[i].setBackground(null);
         f.add(box[i]);
      }
      box[0].setModel(new DefaultComboBoxModel(countrys));
      box[0].addItem("Add a country");
      box[0].setToolTipText("Choose a country to add at Diagram");
      box[0].setSelectedItem("Add a country");
      box[1].addItem("Remove a country");
      box[1].setToolTipText("Choose a country to remove at Diagram");
      box[1].setSelectedItem("Remove a country");
      
      button[0]= new JButton("Add");
      button[0].setToolTipText("Add country you choosed");
      button[1]= new JButton("R");
      button[1].setToolTipText("Remove country you choosed");
      for(int i=0; i!=button.length; i++){
         button[i].setBackground(null);
         button[i].setBounds(210, 350+i*50, 50, 30);
         button[i].setBackground(null);
         button[i].setBorder(null);
         button[i].setFont(new Font("Arial Rounded MT Bold",0,15));
         f.add(button[i]);
      } 
      button[0].addActionListener(
         e->{
            if(second.size()<10 && !box[0].getSelectedItem().equals("Add a country")){ 
               box[1].addItem(box[0].getSelectedItem());
               second.add(a[box[0].getSelectedIndex()]);
               box[0].setSelectedItem("Add a country");
               repaint();
            }
         });
      button[1].addActionListener(
         e->{
            if(!box[1].getSelectedItem().equals("Remove a country")){
               second.remove(box[1].getSelectedIndex()-1);
               box[1].removeItem(box[1].getSelectedItem());
               box[1].setSelectedItem("Remove a country");
               repaint();
            }
         });
   }
   
   public void bacgroundDealy(String path){
      JLabel background= new JLabel(new ImageIcon(getClass().getResource("background//"+path+".png")));
      background.setBounds(0,0,800,500);
      f.add(background);
   }
   
   private double[] setDatas(String region, String dataType, String year){
      Read r= new Read();
      r.getDataForRegion(dataType,Read.getRegion(dataType,region),year);
      countrys=r.c;
      return r.data;
   }
   
   private void analisDealy(String value1){
      analis= new JLabel[2][5];
      JLabel l= new JLabel();
      l.setBounds(50,500,100,30);
      f.add(l);
      for(int i=0; i!=analis.length; i++){
         for(int j=0; j!=analis[i].length; j++){
            analis[i][j]= new JLabel();
            analis[i][j].setOpaque(true);
            analis[i][j].setBackground(new Color(0,0,0,150));
            analis[i][j].setForeground(new Color(255,255,255));
            analis[i][j].setFont(new Font("Arial Rounded MT Bold",Font.PLAIN,20));
            analis[i][j].setBounds(30+i*120,150+j*35,110,25);
            analis[i][j].setHorizontalAlignment(SwingConstants.CENTER);
            analis[i][j].setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
            f.add(analis[i][j]);
         }
      }
      value= new JLabel(value1);
      value.setOpaque(true);
      value.setBackground(new Color(0,0,0,150));
      value.setForeground(new Color(255,255,255));
      value.setFont(new Font("Arial Rounded MT Bold",Font.PLAIN,18));
      value.setBounds(30,150-35,230,25);
      value.setHorizontalAlignment(SwingConstants.CENTER);
      value.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
      f.add(value);
      analis[0][0].setText("Min");
      analis[0][1].setText("Max");
      analis[0][2].setText("Average");
      analis[0][3].setText("Variance");
      analis[0][4].setText("Deviation");
   }
   
   private void addData(int j){
      Diagram2 d= new Diagram2(a);
      double[] c= d.getAll();
      if(d.minIndex!=-1 && d.maxIndex!=-1){
         analis[1][0].setToolTipText("This result is found in "+countrys[d.minIndex]);
         analis[1][1].setToolTipText("This result is found in "+countrys[d.maxIndex]);
      }
      for(int i=0; i!=c.length; i++){
         if((c[i]+"").length()<8){
            analis[j][i].setText(c[i]+"");
         }
         else{
            analis[j][i].setText((c[i]+"").substring(0,8)+"");
         }
      }
   }
   
   private void titleDeal(){
      title.setBackground(null);
      title.setHorizontalAlignment(SwingConstants.CENTER);
      title.setBounds(0,10,f.getWidth()-16,45);
      title.setForeground(Color.BLACK);
      title.setFont(new Font("Arial Rounded MT Bold",Font.PLAIN,40));
      f.add(title);
   }
   
   public void paint(Graphics g){
      g.setColor(new Color(50,50,50,255));
      g.fillRect(0,0,800,500);
      g.setColor(new Color(255,255,255));
      printColums(g);
   }
      
   private double maximum(ArrayList<Double> a){
      double max=0;
      for(int i=0; i!=a.size(); i++){
         if(a.get(i)>max){
            max=a.get(i);
         }
      }
      int m=Integer.parseInt((max+"").charAt(0)+"");
      return (m+1)*(int)Math.pow(10,((int)(max)+"").length()-1);
   }
      
   public void printColums(Graphics g){
      int size=250;
      int x=60;
      int y=50;
      double max;
      max=maximum(second);
      g.setFont(new Font("Arial Rounded MT Bold",Font.PLAIN,15));
      g.fillRect(x,size+y,size+10,5);
      g.fillRect(x,y-10,5,size+10);
      for(int i=1; i!=5; i++){
         g.setColor(new Color(150,150,150));
         g.drawLine(x+5,y+(4-i)*size/4,x+size+5,y+(4-i)*size/4);
      }
      g.setFont(new Font("Arial Rounded MT Bold",Font.PLAIN,12));
      for(int i=0; i<second.size(); i++){
         g.setColor(colors[i]);
         g.fillRect(x+10+i*(25-second.size()+10),size+y-(int)(second.get(i)*size/max),25-second.size(),(int)(second.get(i)*size/max));
         g.fillRect(330,50+i*25,8,8);
         g.setColor(new Color(255,255,255));
         if((second.get(i)+"").length()<8){
            g.drawString(box[1].getItemAt(i+1).toString()+" = "+second.get(i),340,60+i*25);
         }
         else{
            g.drawString(box[1].getItemAt(i+1).toString()+" = "+(second.get(i)+"").substring(0,8),340,60+i*25);
         }
      }
      g.setFont(new Font("Arial Rounded MT Bold",Font.PLAIN,15));
      for(int i=0; i<5; i++){
         if(max*i/4!=(int)(max*i/4)){
            g.drawString((max*i/4)+"",x-10*((max*i/4)+"").length(),y+(4-i)*size/4+10);
         }
         else{
            g.drawString((int)(max*i/4)+"",x-10*((int)(max*i/4)+"").length(),y+(4-i)*size/4+10);
         }
      }
   }
}