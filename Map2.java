import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;

public class Map2 extends JPanel{
   private Image img1;
   private int imgX=0, imgY=0;
   private boolean t;
   private final int imgWidth=600, imgHeight=300;
   
   public Map2(int x, int y, int w, int h, Image i){
      this.setBounds(x,y,w,h);
      img1=i;
   }
   
   public void setImage(int x, int y, Image i1, boolean tempo){
      img1=i1;
      imgX=x;
      imgY=y;
      t=tempo;
   }
   
   public void paint(Graphics g){
      g.drawImage(img1,imgX,imgY,this);
   }
}