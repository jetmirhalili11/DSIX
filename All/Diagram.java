import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.ComboBoxUI;

public class Diagram extends JPanel{
   private double[] a,b;
   private String a1,b1;
   private int[] vitet;
   protected JFrame f;
   private JLabel title;
   private JLabel[][] analis;
   
   public Diagram(String a1, String b1, int country, int dataType){
      b=null;
      a=setDatas(b1,country,dataType);
      title= new JLabel(a1+" - "+b1);
      this.setBounds(400,80,350,350);
      this.setOpaque(true);
      this.setBackground(Color.RED);
      this.a1=a1;
      this.b1=b1;
      f= new JFrame();
      f.setLayout(null);
      f.setResizable(false);
      f.setSize(800+16,500+39);
      f.setLocationRelativeTo(null);
      boxDealy(b1,country,dataType);
      titleDeal();
      analisDealy();
      addData(new Diagram2(a).getAll(),1);
      f.getContentPane().setBackground(new Color(255,255,255));
      f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      f.getContentPane().add(this);
      bacgroundDealy(b1);
      f.setVisible(true);
   }
   
   public void boxDealy(String c, int country, int dataType){
      JComboBox box= new JComboBox();
      box.setModel(new DefaultComboBoxModel(Read.country(c)));
      box.addItem("Compare with a country");
      box.setToolTipText("Search a country to compare?");
      box.setFont(new Font("Arial Rounded MT Bold",0,20));
      box.setBounds(30,350, 348, 50);
      box.setBorder(null);
      box.setSelectedItem("Compare with a country");
      box.setBackground(null);
      box.addActionListener(
         e->{
            if(!box.getSelectedItem().toString().equals("Compare with a country")){
               b=setDatas(c,box.getSelectedIndex(),dataType);
               addData(new Diagram2(b).getAll(),2);
               secondCountry(true);
            }
            else{
               b=null;
               secondCountry(false);
            }
            this.repaint();
         });
      f.add(box);
   }
   
   public void bacgroundDealy(String path){
      JLabel background= new JLabel(new ImageIcon(getClass().getResource(path+".png")));
      background.setBounds(0,0,800,500);
      f.add(background);
   }
   
   private double[] setDatas(String d, int country, int dataType){
      double[] data=Read.readFile(d,dataType,country);
      vitet= new int[5];
      for(int i=0; i!=vitet.length; i++){
         if(dataType>2){
            vitet[i]=2015+i;
         }
         else{
            vitet[i]=(int)(data[i+5]);
         }
      }
      if(dataType<3){
         double[] t=new double[5];
         for(int i=0; i!=t.length; i++){
            t[i]=data[i];
         }
         data=t;
      }
      return data;
   }
   
   private void analisDealy(){
      analis= new JLabel[3][5];
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
      secondCountry(false);
      analis[0][0].setText("Min");
      analis[0][1].setText("Max");
      analis[0][2].setText("Average");
      analis[0][3].setText("Variance");
      analis[0][4].setText("Deviation");
   }
   
   private void addData(double[] c, int j){
      for(int i=0; i!=c.length; i++){
         if((c[i]+"").length()<8){
            analis[j][i].setText(c[i]+"");
         }
         else{
            analis[j][i].setText((c[i]+"").substring(0,8)+"");
         }
      }
   }
   
   private void secondCountry(boolean t){
      for(int i=0; i!=analis[2].length; i++){
         analis[2][i].setVisible(t);
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
      g.setColor(new Color(0,0,0,150));
      g.fillRect(0,0,800,500);
      g.setColor(new Color(255,255,255));
      printColums(g);
   }
      
   private double maximum(double a[]){
      double max=0;
      for(int i=0; i!=a.length; i++){
         if(a[i]>max){
            max=a[i];
         }
      }
      int m=Integer.parseInt((max+"").charAt(0)+"");
      return (m+1)*(int)Math.pow(10,((int)(max)+"").length()-1);
   }
      
   public void printColums(Graphics g){
      int size=250;
      int x=50;
      int y=50;
      double max;
      if(b==null){
         max=maximum(a);
      }
      else{
         max=Math.max(maximum(a),maximum(b));
      }
      g.setFont(new Font("Arial Rounded MT Bold",Font.PLAIN,15));
      g.fillRect(x,size+y,size+10,5);
      g.fillRect(x,y-10,5,size+10);
      for(int i=1; i!=a.length; i++){
         g.setColor(new Color(150,150,150));
         g.drawLine(x+5,y+(4-i)*size/4,x+size+5,y+(4-i)*size/4);
      }
      g.setColor(Color.BLUE);
      g.fillRect(100,15,10,10);
      g.setColor(Color.WHITE);
      g.drawString(a1,120,25);
      for(int i=0; i!=a.length; i++){
         g.setColor(Color.BLUE);
         if(b==null){
            g.fillRect(x+20+i*size/a.length,size+y-(int)(a[i]*size/max),30,(int)(a[i]*size/max));
         }
         else{
            g.fillRect(x+20+i*size/a.length,size+y-(int)(a[i]*size/max),15,(int)(a[i]*size/max));
            g.setColor(Color.CYAN);
            g.fillRect(x+35+i*size/a.length,size+y-(int)(b[i]*size/max),15,(int)(b[i]*size/max));
         }
         g.setColor(new Color(255,255,255));
         g.drawString(vitet[i]+"",x+20+i*size/a.length,size+y+20);
         if(i<5){
            if(max*i/4!=(int)(max*i/4)){
               g.drawString((max*i/4)+"",x-10*((max*i/4)+"").length(),y+(4-i)*size/4+10);
            }
            else{
               g.drawString((int)(max*i/4)+"",x-10*((int)(max*i/4)+"").length(),y+(4-i)*size/4+10);
            }
         }
      }
      g.drawString("Years",x+size/2-("Years").length()*8/2,y+size+40);
   }
}