import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.text.*;
import java.lang.*;
public class D extends Thread
{
   private JFrame mainFrame;
   public JLabel headerLabel;
   public JPanel controlPanel;
   public Thread t=new Thread(this);
   int hours=0, minutes=0, seconds=0;
   public static int choice=0;
   public String timeString = "";

   public void setLabel()
   {
	   headerLabel.setText(timeString);
   }
   public static void main(String[] args)
   {
      D d = new D();
	    d.prepareGUI();
      d.showEventDemo();
   }
   public void run()
   {
      try {
         while (true) {
			        Date today=new Date();
			           long mili=today.getTime();
			              switch(choice)
			                 {
				                     case 2:{
					                          today.setTime(mili+12600000);
					                               break;
				                             }
				                     case 3:{
					                          today.setTime(mili-34200000);
					                               break;
				                             }
                              case 4:{
					                           today.setTime(mili-12600000);
					                                break;
                                      }
			                 }
            SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss a");
            timeString = formatter.format( today );
			      headerLabel.setText(timeString);
            Thread.sleep( 1000 );
         }
       }
      catch (Exception e) { }
	 }
   public void prepareGUI(){
      mainFrame = new JFrame("World Watch");
      mainFrame.setSize(400,400);
      mainFrame.setLayout(new GridLayout(4, 4));

      headerLabel = new JLabel("",JLabel.CENTER );
      mainFrame.addWindowListener(new WindowAdapter()
       {
         public void windowClosing(WindowEvent windowEvent){
            System.exit(0);
         }
      });
      controlPanel = new JPanel();
      controlPanel.setLayout(new FlowLayout());

      mainFrame.add(headerLabel);
      mainFrame.add(controlPanel);
      mainFrame.setVisible(true);
   }

   public void showEventDemo(){
      headerLabel.setText("Select Country");

      JButton india = new JButton("India");
      JButton japan = new JButton("Japan");
      JButton uSA = new JButton("USA");
      JButton germany = new JButton("Germany");


      india.setActionCommand("1");
      japan.setActionCommand("2");
      uSA.setActionCommand("3");
      germany.setActionCommand("4");

      india.addActionListener(new ButtonClickListener());
      japan.addActionListener(new ButtonClickListener());
      uSA.addActionListener(new ButtonClickListener());
      germany.addActionListener(new ButtonClickListener());

    //  controlPanel.setBound(100,100,100,70);
      controlPanel.add(india);

      controlPanel.add(japan);
      controlPanel.add(uSA);
      controlPanel.add(germany);
      mainFrame.setVisible(true);
   }
   public class ButtonClickListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e) throws IllegalThreadStateException
	  {
         String command = e.getActionCommand();
         if( command.equals( "1" ))  {
			        choice=1;
			           t.start();
         } else if( command.equals( "2" ) )  {
			        choice=2;
			           t.start();
         } else if( command.equals( "3" ) )  {
			         choice=3;
		             t.start();
         } else {
			        choice=4;
			           t.start();
         }
      }
   }
}
