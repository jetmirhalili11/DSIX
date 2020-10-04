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
   private JComboBox comboBox_1, comboBox ;
   private String[] countries, a;
   private JLabel l1,C ;
   private JButton btnSerach;

   public static void main(String[] args) {
      EventQueue.invokeLater(
         new Runnable() {
            public void run() {
               try {
                  Serious frame = new Serious();
                  frame.setVisible(true);
               } catch (Exception e) {
                  e.printStackTrace();
               }
            }
         });
   }
   
   public Serious(){
      try {
         for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
               javax.swing.UIManager.setLookAndFeel(info.getClassName());
               break;
            }
         }
      } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
         java.util.logging.Logger.getLogger(Serious.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      }
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
      setSize(h, w);
      setName("NASA SPACE APPS");
      setLocationRelativeTo(null);
      setResizable(false);
      getContentPane().setLayout(null);
      btnSerach = new JButton(new ImageIcon (getClass().getResource("po.png")));
      checkCountries();
      checkType();
      buton();
      logo();
      backPhoto();   
   }
   
   public void buton() {
      btnSerach.setEnabled(false);
      btnSerach.setBackground(null);
      btnSerach.setBounds(596, 208, 44, 49);
      btnSerach.setBackground(null);
      btnSerach.setBorder(null);
      btnSerach.addActionListener(
         e->{
            new Diagram(comboBox_1.getSelectedItem().toString(),comboBox.getSelectedItem().toString(),comboBox_1.getSelectedIndex(),comboBox.getSelectedIndex());
         });
      getContentPane().add(btnSerach);
   }

   public void checkCountries() {
      comboBox_1 = new JComboBox();
      comboBox_1.setEnabled(false);
      comboBox_1.setToolTipText("Search a country?");
      comboBox_1.setFont(new Font("Arial Rounded MT Bold",0,20));
      comboBox_1.setBounds(238, 208, 348, 50);
      comboBox_1.addItem("Choose a Country");
      
      comboBox_1.setBackground(null);
      comboBox_1.addActionListener(
         e->{
            if(comboBox_1.getSelectedItem()!=null){
               btnSerach.setEnabled(!comboBox_1.getSelectedItem().toString().equals("Choose a Country"));
            }
         });
      getContentPane().add(comboBox_1);
   
   }
   public void checkType() {
      comboBox = new JComboBox();
      comboBox.setModel(new DefaultComboBoxModel(Read.dataTypes()));
      comboBox.addItem("Demographic type");
      comboBox.setFont(new Font("Arial Rounded MT Bold",0,20));
      comboBox.setSelectedItem("Demographic type");
      comboBox.setForeground(Color.BLACK);
      comboBox.setBounds(10, 208, 219, 50);
      comboBox.addActionListener(
         e->{
            if(!comboBox.getSelectedItem().toString().equals("Demographic type")){
               comboBox_1.setModel(new DefaultComboBoxModel(Read.country(comboBox.getSelectedItem().toString())));
               comboBox_1.addItem("Choose a Country");
               comboBox_1.setEnabled(true);
            }
            else{
               comboBox_1.setEnabled(false);
               btnSerach.setEnabled(false);
            }
            comboBox_1.setSelectedItem("Choose a Country");
         });
      getContentPane().add(comboBox);
   
   }
   public void logo() {
      l1 = new JLabel();
      l1.setIcon( new ImageIcon(this.getClass().getResource("kk.png")));
      l1.setBounds(87, 59, 522, 128);
      getContentPane().add(l1);
   }
   public void backPhoto() {
      C = new JLabel("");
      C.setBounds(-111, -40, 1017, 600);
      C.setIcon( new ImageIcon(this.getClass().getResource("gg.png")));
      getContentPane().add(C);
   }
}
