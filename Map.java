import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.net.URL;
import javax.imageio.ImageIO;
import java.awt.event.*;
import javax.swing.JLayeredPane;

public class Map extends JPanel implements MouseMotionListener{
   private BufferedImage img1, img2;
   private Image b1, base1;
   private final int imgWidth=600, imgHeight=300;
   private final BufferedImage img;
   private boolean t=false, drag=false;
   private int width1, width2, height1, height2, width11, height11, width22, height22;
   private int x=-10,y=-10, imgX=0, imgY=0, count=0;
   private Map2 m;
   private JButton button[];
   private JLabel title, legend;
   private JFrame f;
   private JLayeredPane p;
   private String dataType2, year, month;
   
   public Map(String dataType, String year, String month){
      button = new JButton[2];
      this.year=year; this.month=month;
      String legend1= Read.getTheInstrument(dataType,"Scale");
      dataType2= Read.getTheInstrument(dataType,"Instrument");
      width11=width22=width1=width2=imgWidth;
      height1=height22=height2=imgHeight;
      f= new JFrame();
      f.setSize(600+16,2*300+50+39);
      f.setResizable(false);
      f.setLocationRelativeTo(null);
      f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
      whell();
      layeredDeal();
      this.setBounds(0,50,imgWidth,imgHeight);
      img=img2=getPhoto();
      setPhoto();
      this.addMouseMotionListener(this);
      if(month.length()>0){
         labelDeal(dataType+" - "+month+"/"+year,legend1);
      }
      else{
         labelDeal(dataType+" - "+year,legend1);
      }
      buttonDealy();
      m= new Map2(0,imgHeight+50,imgWidth,imgHeight,base1);
      p.add(this,JLayeredPane.DEFAULT_LAYER);
      p.add(m,JLayeredPane.DEFAULT_LAYER);
      f.setVisible(true);
      go();
   }
   
   public void layeredDeal(){
      p= new JLayeredPane();
      p.setSize(f.getWidth(),f.getHeight());
      p.setLayout(null);
      f.add(p);
   }
   
   public void buttonDealy(){
      button[0]= new JButton(new ImageIcon(getClass().getResource("map//zoomIn.png")));
      button[1]= new JButton(new ImageIcon(getClass().getResource("map//zoomOut.png")));
      for(int i=0; i!=button.length; i++){
         button[i].setBounds(20+60*i,350-17,41,34);
         button[i].setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0),2));  
         p.add(button[i],JLayeredPane.DRAG_LAYER);
      }
      button[0].addActionListener(
         e->{
            if(count<14){
               width2-=40;
               count++;
               zoom();
            }
         });
      button[1].addActionListener(
         e->{
            if(count>0){
               width2+=40;
               count--;
               zoom();
            }
         });
   }
   
   public void labelDeal(String title1, String legend1){
      title= new JLabel(title1);
      title.setOpaque(true);
      title.setHorizontalAlignment(SwingConstants.CENTER);
      title.setBounds(0,0,imgWidth,50);
      title.setFont(new Font("Arial Rounded MT Bold",0,20));
      title.setBackground(new Color(0,0,0));
      title.setForeground(new Color(255,255,255));
      p.add(title,JLayeredPane.DRAG_LAYER);
      
      try{
         System.out.println(legend1);
         legend= new JLabel(new ImageIcon(new URL(legend1)));
      }catch(Exception e){ legend=null; }
      legend.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0),2));
      legend.setBounds(490-(legend.getIcon().getIconWidth()+16)/2,350-(legend.getIcon().getIconHeight()+16)/2,legend.getIcon().getIconWidth()+16,legend.getIcon().getIconHeight()+16);
      legend.setOpaque(true);
      legend.setBackground(new Color(255,255,255));
      p.add(legend,JLayeredPane.DRAG_LAYER);
   }
   
   public void setPhoto(){
      ImageIcon i= new ImageIcon(getClass().getResource("map//b"+count+".png"));
      b1=i.getImage();
      i= new ImageIcon(getClass().getResource("map//base"+count+".png"));
      base1=i.getImage();
   }
   
   public BufferedImage getPhoto(){
      System.out.println(dataType2+"   "+year+"   "+month);
      try{
         if(month.length()>1){
            img1 = ImageIO.read(new URL("https://neo.sci.gsfc.nasa.gov/wms/wms?service=WMS&request=GetMap&version=1.3.0&transparent=TRUE&layers="+dataType2+"&styles=&format=image/png&width="+width1+"&height="+height1+"&time="+year+"-"+month+"T00:00:00.000Z&crs=CRS:84&bbox=-180,-90,180,90"));
         }
         else if(month.length()>0){
            img1 = ImageIO.read(new URL("https://neo.sci.gsfc.nasa.gov/wms/wms?service=WMS&request=GetMap&version=1.3.0&transparent=TRUE&layers="+dataType2+"&styles=&format=image/png&width="+width1+"&height="+height1+"&time="+year+"-0"+month+"T00:00:00.000Z&crs=CRS:84&bbox=-180,-90,180,90"));
         }
         else if(month.isBlank() || month.isEmpty()){
            img1 = ImageIO.read(new URL("https://neo.sci.gsfc.nasa.gov/wms/wms?service=WMS&request=GetMap&version=1.3.0&transparent=TRUE&layers="+dataType2+"&styles=&format=image/png&width="+width1+"&height="+height1+"&time="+year+"T00:00:00.000Z&crs=CRS:84&bbox=-180,-90,180,90"));
         }
      }
      catch(Exception e){
         System.out.println("URL not found");
      }
      return img1;
   }
   
   public void go(){
      while(true){
         System.out.print("");
         if(t){
            width1=imgWidth*imgWidth/width2;
            height1=width1*imgHeight/imgWidth;
            getPhoto();
            t=false;
            repaint();
         }
         if(width1!=imgWidth*imgWidth/width2){
            t=true;    
         }
         if(!f.isShowing()){
            break;
         }
      }
   }
   
   public void zoom(){
      width11=imgWidth*imgWidth/width2;
      height11=width11*imgHeight/imgWidth;
      height2=width2*imgHeight/imgWidth;
      imgX-=(width11-width22)/2;
      imgY-=(height11-height22)/2;
      if(count==0){
         imgX=0;
         imgY=0;
         img2=img1=img;
         setPhoto();
      }
      else{
         if(imgX>0){
            imgX=0;
         }
         if(imgY>0){
            imgY=0;
         }
         if(-imgX*imgWidth/width11+width2>imgWidth){
            imgX-=(width11-width22)/2;
         }
         if(-imgY*imgHeight/height11+height2>imgHeight){
            imgY-=(height11-height22)/2;
         }
         t=true;
         int wrongY=0;
         int wrongX=0;
         if(-imgY*imgHeight/height11+height2>imgHeight){
            wrongY=-imgY*imgHeight/height11+height2-imgHeight;
         }
         if(-imgX*imgWidth/width11+width2>imgWidth){
            wrongX=--imgX*imgWidth/width11+width2-imgWidth;
         }
         img2=img.getSubimage(-imgX*imgWidth/width11-wrongX,-imgY*imgHeight/height11-wrongY,width2,height2);
         setPhoto();
      }
      width22=width11;
      height22=height11;
      repaint();
   }
   
   public void whell(){
      this.addMouseWheelListener(
         e->{
            if(e.getWheelRotation()<0){
               button[0].doClick();
            }
            else{
               button[1].doClick();
            }
         });
   }
   
   public void mouseMoved(MouseEvent e){
      if(!drag){
         x=-10;
         y=-10;
      }
   }
   public void mouseDragged(MouseEvent e){
      if(!t){
         drag=true;
         int tX=e.getX();
         int tY=e.getY();
         if(x!=-10){
            imgX+=tX-x;
            imgY+=tY-y;
            if(!(img1.getWidth()+imgX>imgWidth && imgX<=0)){
               imgX-=tX-x;
            }
            if(!(img1.getHeight()+imgY>imgHeight && imgY<=0)){
               imgY-=tY-y;
            }
            repaint();
         }
         x=tX;
         y=tY;
         drag=false;
      }
   }
   
   public void paint(Graphics g){
      g.setColor(Color.WHITE);
      g.fillRect(0,0,imgWidth,imgHeight);
      g.drawImage(base1,imgX,imgY,this);
      if(!t){
         g.drawImage(img1,imgX,imgY,this);
      }
      else{
         g.drawImage(img2,0,0,imgWidth,imgHeight,this);
      }
      m.setImage(imgX,imgY,b1,t);
      m.repaint();
   }
}