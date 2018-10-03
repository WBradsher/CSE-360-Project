import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class NetworkList extends JFrame implements ActionListener
{
    static int WIDTH = 150;
    static int HEIGHT = 450;
    JFrame Cart = new JFrame("Network List");
    FlowLayout flow = new FlowLayout();
    FlowLayout cartFlow = new FlowLayout();
    JLabel cartList = new JLabel();
    JLabel aLabel = new JLabel("Activity Name:");
    JLabel blabel = new JLabel("Duration: ");
    JLabel clabel = new JLabel("Dependencies: "); 
    JLabel dlabel = new JLabel("                            ");
    JTextField DurationF = new JTextField(10);
    JTextField DependenciesF = new JTextField(10);
    JTextField ActivityNameF = new JTextField(10);
    JButton Add= new JButton("Add to Network"); 
    JButton restart = new JButton("Restart");
    JButton process = new JButton("*Process*");
    JButton help = new JButton("Help");
    JButton about = new JButton("About"); 
    JButton quit = new JButton("Quit"); 
    String output;
    String output2;
    String output3;														//may or may not be used
    String output4;
    JScrollPane scroll = new JScrollPane();
    ArrayList<LinkedList<NetworkNode>> AdjacencyList = new ArrayList<LinkedList<NetworkNode>>(); 
    static boolean startingNode = false;
    
    
    
    public static void main(String[] arguments)
    {
        JFrame frame = new NetworkList();
        frame.setSize(WIDTH, HEIGHT); 
        frame.setVisible(true);
        frame.setLocation(688, 300); 
        frame.setResizable(false);
    }

    public NetworkList()
    {
        super("Network Program");
        Cart.setSize(300, 450);
        Cart.setLocation(850, 300); 
        Cart.setLayout(cartFlow);
        Cart.setVisible(true);
        Cart.setResizable(false);
        setLocation(600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Cart.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setLayout(flow);
        Add.addActionListener(this);
        restart.addActionListener(this);
        quit.addActionListener(this);
        Cart.add(cartList);
        add(aLabel);
        add(ActivityNameF); 
        add(blabel);
        add(DurationF);
        add(clabel); 
        add(DependenciesF);
        add(dlabel);
        add(Add);
        add(restart);
        add(process);
        add(help);
        add(about); 
        add(quit);
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == Add)
        {
            try
            {
            	String name = ActivityNameF.getText();
            	int dur = Integer.parseInt(DurationF.getText());
            	String depend = DependenciesF.getText();						//create new node
                NetworkNode temp = new NetworkNode(name, dur, depend);
                if( !(AdjacencyList.contains(temp)))
                {
                	LinkedList<NetworkNode> temp2 = new LinkedList<NetworkNode>();
                	temp2.add(temp);
                	AdjacencyList.add(temp2);		//add to array
                }
                if(startingNode == true && (depend.equals("")))
                {
                	//throw error!!!!!!!
                }
                if(depend.equals(""))
                {
                	startingNode = true;
                }
                //now, we should parse out it's dependencies.
                StringTokenizer multiTokenizer = new StringTokenizer(depend, ",");
                while (multiTokenizer.hasMoreTokens())
                {
                	String token = multiTokenizer.nextToken();
                	for(int i = 0; i < AdjacencyList.size(); i++)							//hunt through the Adjacency List
                	{
                		if(AdjacencyList.get(i).getFirst().getName().equals(token))	//if the dependency is in the list, add it.
                		{
                			AdjacencyList.get(i).add(temp); 
                		} 
                	}
                }
                
                
                
            }
            catch(Exception ex)
            {
                //handle exception- print in console? this can be checked.
            }

            if(!(false))		//this should be the exception
            {
                /*//int pizzaNum = pizzaBox.getSelectedIndex();
                totalPrice = pizzaPrice[pizzaNum];
                output = "$" + totalPrice;
                output2 = "$" +((double)(totalPrice * .05)) + " ";								//consider this section what we want to do/display.
                output3 = "$" +((double)(totalPrice + (totalPrice * .05))) + " ";				//this is old code that can be deleted, but we can use it as a baseline.
                a = a + ((double)(multiplier*(totalPrice + (totalPrice * .05)))) ;
                totalPriceField.setText(output);
                taxPriceField.setText(output2);
                endPriceField.setText(output3);
                finalPriceField.setText("$" + Double.toString(a));
                //for(int i = 0; i < multiplier; i++)
                //    Cart.add(new JLabel(pizzaBox.getSelectedItem().toString() + " " + colorBox.getSelectedItem().toString() + "Skateboard"));
                */
            	ActivityNameF.setText("");
            	DependenciesF.setText("");
            	DurationF.setText("");
                Cart.invalidate();
                Cart.validate();
                Cart.repaint();

            }
        }
        if(e.getSource() == restart)
        {
            /*quantityField.setText("");
            totalPriceField.setText(""); 
            taxPriceField.setText("");
            endPriceField.setText("");
            finalPriceField.setText("");
            a = 0;
            Cart.dispose();
            Cart = new JFrame("My Cart");			//old code, can be used as a baseline.
            Cart.setSize(300, 400);
            Cart.setLocation(850, 300);
            Cart.setLayout(cartFlow);
            Cart.setVisible(true);
            Cart.setResizable(false);
            Cart.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/
        }
        if(e.getSource() == process)
        {
            

        }
        if(e.getSource() == help)
        {
            

        }
        if(e.getSource() == about)
        {
            

        }
        if(e.getSource() == quit)
        {
            Cart.setVisible(false);			//this is done! Just closes all windows.  -Ryan
            setVisible(false);
            dispose();
            Cart.dispose();

        }
    }
}




