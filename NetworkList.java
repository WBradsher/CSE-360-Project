import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class NetworkList extends JFrame implements ActionListener
{
    static int WIDTH = 150;
    static int HEIGHT = 450;
    JFrame Display = new JFrame("Network List");
    FlowLayout flow = new FlowLayout();
    FlowLayout DisplayFlow = new FlowLayout();
    JLabel DisplayList = new JLabel();
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
    JScrollPane scroll = new JScrollPane();
    ArrayList<LinkedList<NetworkNode>> AdjacencyList = new ArrayList<LinkedList<NetworkNode>>(); 
    static boolean startingNode = false;		//true if we have a starting node already
    static NetworkNode startNode; 
    JTextArea textArea = new JTextArea(24,32);
    JScrollPane scrollPane = new JScrollPane(textArea);
    NetworkNode endNode;
    String teststr = "";
    boolean punter;
    int localcount = 0;
    String teststr2 = "";  
    
    
    
    
    
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
        Display.setSize(400, 455);
        Display.setLocation(844, 300); 
        Display.setLayout(DisplayFlow);
        Display.setVisible(true);
        //Display.setResizable(false);
        setLocation(600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        
        

        Display.getContentPane().add(scrollPane, BorderLayout.CENTER); 
        
        setLayout(flow);
        Add.addActionListener(this);
        restart.addActionListener(this);
        process.addActionListener(this);
        help.addActionListener(this);
        about.addActionListener(this);
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
                if(name.equals(""))
                {
                	textArea.setText("Node must have a name!\n\nThis node was NOT added."); 
                	Display.getContentPane().add(scrollPane, BorderLayout.CENTER);
                }
                else if(startingNode == true && (depend.equals("")))
                {
                	textArea.setText("A starting node has already been entered. \nPlease enter a Node that has dependencies. \n\nThis node was NOT added as a result.");
                	Display.getContentPane().add(scrollPane, BorderLayout.CENTER);
                }
                else
                {
                	boolean alreadyInNetwork = false;
                	for(int i = 0; i < AdjacencyList.size(); i++)
                	{
                		String checker = AdjacencyList.get(i).getFirst().getName();
                		if(checker.equals(temp.getName()))
                		{
                			alreadyInNetwork = true; 
                			break;
                		}
                	}
                	if(alreadyInNetwork == true)
                	{
                		textArea.setText("Node not added.\n\nYou've already added this node.\nIf you'd like to change node properties, you must restart.");
                		Display.getContentPane().add(scrollPane, BorderLayout.CENTER);
                	}
                	else
                	{
                		LinkedList<NetworkNode> temp2 = new LinkedList<NetworkNode>();
                    	temp2.add(temp);
                    	AdjacencyList.add(temp2);		//add to array
                    	if(depend.equals(""))
    	                {
    	                	startingNode = true;
    	                	startNode = temp; 
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
    	                textArea.setText("Node " + temp.getName() + " added successfully!");
    	                Display.getContentPane().add(scrollPane, BorderLayout.CENTER);
                	}
                }
                
                
            }
            catch(Exception ex)
            {
            	String exception = ex.getMessage();
            	if(exception.substring(0, 16).equals("For input string"))
            	{
            		textArea.setText("Data format(s) incorrect. Ensure that:\n1)    Name is a string.\n2)    Duration is an integer.\n3)    Dependencies are lists of Nodes, separated only\nby a comma (no spaces). Example: \"A,B,C,D\" ");
                    Display.getContentPane().add(scrollPane, BorderLayout.CENTER);
            	}
            }

            if(!(false))		//this should be the exception
            {
                
            	ActivityNameF.setText("");
            	DependenciesF.setText("");
            	DurationF.setText("");
                Display.invalidate();						//very important!!!!
                Display.validate();
                Display.repaint();

            }
        }
        if(e.getSource() == restart)
        {
        	
        	//this section should wipe all data from the program.clear the arraylist, etc.
        	
        	
            /*quantityField.setText("");
            totalPriceField.setText(""); 
            taxPriceField.setText("");
            endPriceField.setText("");
            finalPriceField.setText("");
            a = 0;
            Display.dispose();
            Display = new JFrame("My Display");			//old code, can be used as a baseline.
            Display.setSize(300, 400);
            Display.setLocation(850, 300);
            Display.setLayout(DisplayFlow);
            Display.setVisible(true);
            Display.setResizable(false);
            Display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/
        }
        if(e.getSource() == process)
        {
            if(AdjacencyList.size() < 2)
            {
            	textArea.setText("Cannot process: Less than 2 Nodes Entered.\n\nThe input values are still in the system, please continue."); 					//check this. 2+ nodes needed for list?
            }             
            else
            {
            	
	            String test = printPaths(AdjacencyList, startNode);
	            test = "All Paths in the Network:\n\n" + test;
	            textArea.setText("" + test); 
	            Display.getContentPane().add(scrollPane, BorderLayout.CENTER);  
            }
            
            
            
            ActivityNameF.setText(""); 
        	DependenciesF.setText("");
        	DurationF.setText("");
            Display.invalidate();						//very important!!!! 
            Display.validate();
            Display.repaint();
        }
        if(e.getSource() == help) 
        {
        	textArea.setText("HELP:\n\nENTERING INPUTS:\n"
        			+ "    -Activity name: Must be a string made of multiple characters\n"
        			+ "    -Duration: must be entered as an integer\n    "
        			+ "-Dependencies: Should be a list of Nodes in your network\n"
        			+ "           -make sure to enter the list of Nodes speparated\n"
        			+ "            only by a comma (no spaces). Example: \"A,B,C,D\"\n"
        			+ "           NOTE: Only leave this field blank for the starting node.\n    "
        			+ "\nADDING A NODE:\n"
        			+ "    -Once all input fields are entered click \"add to network\" to add \n"
        			+ "      the node to your network\n\n"
        			+ "BUILD YOUR DIAGRAM:\n"
        			+ "     -Once all nodes are added to the network\n"
        			+ "       click '*process*' to build your diagram\n\n"
        			+ "ERRORS:\n--- \"Data format(s) incorrect. "
        			+ "Ensure that\":\n"
        			+ "    1)    Name is a string.\n"
        			+ "    2)    Duration is an integer.\n"
        			+ "    3)    Dependencies are lists of Nodes, "
        			+ "separated only by \n"
        			+ "            a comma (no spaces). Example: \"A,B,C,D\"\n\n"
        			+ "--- \"Node not added\":\n"
        			+ "    -Each node must have a "
        			+ "different name\n"
        			+ "     -If you wish to change node propertires, please restart\n\n"
        			+ "--- \"A starting node already entered\":"
        			+ "\n    -There can only be one start node for each network\n"
        			+ "    -Please enter dependies for the node\n    -If you wish to change "
        			+ "node propertires, please restart\n\n"
        			+ "--- \"Node must have name\": each node must have a name, \n"
        			+ "     please check and make sure the Name field is not left blank"
        			+ "\n\n\n\n For more information please view the User Manual");
        	
        	
            ActivityNameF.setText("");
        	DependenciesF.setText("");
        	DurationF.setText("");
            Display.invalidate();						//very important!!!! 
            Display.validate();
            Display.repaint();

        }
        if(e.getSource() == about)
        {
        	textArea.setText("ABOUT:\r\n" + 
        			"\r\n" + 
        			"SOFTWARE VERSION: 1.0.0\r\n" + 
        			"\r\n" + 
        			"AUTHORS:\r\n" + 
        			"Wyatt Bradsher\r\n" + 
        			"Ryan Burdett\r\n" + 
        			"Orlando Cota Ceballos\r\n" + 
        			"Andrew Hampton\r\n" + 
        			"\r\n" + 
        			"DOWLOAD LINK:\r\n" + 
        			"https://github.com/Rburdett4/CSE-360-Project.git\r\n" + 
        			"\r\n" + 
        			"\r\n" + 
        			"\r\n" + 
        			"------------------WEDT2 General Public License------------------\r\n" + 
        			"This program is free software; you can redistribute it and/or\r\n" + 
        			"modify it under the terms of the WEDT2 General Public License\r\n" + 
        			"as published by the Free Software Foundation; either version 2\r\n" + 
        			"of the License, or (at your option) any later version.\r\n" + 
        			"\r\n" + 
        			"This program is distributed in the hope that it will be useful, but\r\n" + 
        			"WITHOUT ANY WARRANTY; without even the implied warranty\r\n" + 
        			"of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.\n");
        	
        	
            ActivityNameF.setText("");
        	DependenciesF.setText(""); 
        	DurationF.setText("");
            Display.invalidate();						//very important!!!! 
            Display.validate();
            Display.repaint();

        }
        if(e.getSource() == quit)
        {
            Display.setVisible(false);			//this is done! Just closes all windows.  -Ryan
            setVisible(false);
            dispose();
            Display.dispose();

        }
    }
    
    
    public String printPaths(ArrayList<LinkedList<NetworkNode>> AList, NetworkNode starter)				//CAN'T FORGET TO CLEAR ALL DATA AT VERY END
    {
    	String toReturn = "";  
    	NetworkNode tracker = null;  
    	boolean pushedFlag = false;  
    	boolean poppedFlag = false; 
    	int count = 0;
    	String nodesInPath = "";
    	for(int i = 0; i < AList.size(); i++) 
    	{
    		AList.get(i).add(null);							//the LinkedList class is weird. had to append a null onto each linked list.
    	}
    	
    	//let's find our END node
    	for(int i = 0; i < AList.size(); i++)
    	{
    		if(AList.get(i).get(1) == null)  
    		{
    			endNode = AList.get(i).getFirst();
    		}
    	}
    	
    	//okay, now let's make a stack for a DFS
    	Stack<NetworkNode> stack = new Stack<NetworkNode>(); 
    	stack.push(starter);
    	
    	while(!stack.empty())
    	{
    		
    		
	    	NetworkNode current = stack.peek();
	    	int arrIndex = AList.indexOf(current);
	    	
	    	
	    	for(int i = 0; i < AList.size(); i++)
	    	{
	    		if(AList.get(i).getFirst() == current)
	    		{
	    			arrIndex = AList.indexOf(AList.get(i));
	    			break;
	    		}
	    	}
	    	if(AList.get(arrIndex).get(1) == null) 
	    	{
	    		break;
	    	}
	    	ListIterator<NetworkNode> iterator = AList.get(arrIndex).listIterator(1);
	    	while(iterator.hasNext())
	    	{
	    		pushedFlag = false;
	    		poppedFlag = false;
	    		NetworkNode a = iterator.next();
	    		for(int i = 0; i < AList.size(); i++)
	        	{
	        		if(AList.get(i).getFirst() == a)
	        		{ 
	        			if(AList.get(i).get(1) != null)
	            		{
	            			stack.push(AList.get(i).getFirst());
	            			for(int j = 0; j < AList.size(); j++)
	            	    	{
	            	    		if(AList.get(j).getFirst() == stack.peek())
	            	    		{
	            	    			arrIndex = AList.indexOf(AList.get(j));
	            	    			break;
	            	    		}
	            	    	}
	            			pushedFlag = true;
	            			iterator = AList.get(arrIndex).listIterator(1); 
	            		}
	        			else
	        			{
	        				stack.push(AList.get(i).getFirst());
	        				Iterator<NetworkNode> stackIterator = stack.iterator();
	        				Iterator<NetworkNode> stackIterator2 = stack.iterator();
	        				if(stack.peek() == endNode)
	        				{
	        					localcount = 0;
	        					teststr = "";
		        				while (stackIterator.hasNext())  
		        				{
		        				   teststr += stackIterator.next().getName(); 							//this is where we should be storing our data so we can organize it in DESCENDING order.
		        				   localcount += stackIterator2.next().getDuration(); 				//i'd probably consider putting each string into an array of strings, then just sorting that, but i'm not sure.
		        				   punter = true; 
		        				   
		        				}
		        				teststr2 += teststr + ": " + localcount;
		        				teststr2 += "\n"; 
		        				stack.pop();
	        				}
	        				//tally count
	        				count += AList.get(i).getFirst().getDuration();  
	        				tracker = stack.pop();					//when we pop off the stack, we should remove it's apearance (1 level only?) "upstream" of where it was.
	        				count += tracker.getDuration();
	        				nodesInPath += AList.get(i).getFirst().getName();
	        				nodesInPath += tracker.getName();
	        				
	        				
	        				
	        				if(punter == true)
	        				{
	        					toReturn = teststr2;
	        					punter = false;
	        					localcount = 0;
	        				}
	        				
	        				
	        				for(int j = 0; j < AList.size(); j++)
	            	    	{
	            	    		if(AList.get(j).getFirst() == stack.peek())
	            	    		{
	            	    			arrIndex = AList.indexOf(AList.get(j)); 
	            	    			break;
	            	    		}
	            	    	}
	        				poppedFlag = true;
	        				AList.get(arrIndex).remove(tracker); 
	            			iterator = AList.get(arrIndex).listIterator(1); 
	            			
	        			}
	        		}
	        	}
	    		//if we are at a node that no longer points to anything, pop it!
	    		//search through the adjacency list to see if that linked list is empty.
	    		NetworkNode boo = stack.peek();
	    		boolean booFlag = false;
	    		for(int k = 0; k < AList.size(); k++)
	    		{
	    			if(boo == AList.get(k).getFirst())
	    			{
		    			if(AList.get(k).get(1) == null)
		    			{
		    				if(poppedFlag == true)
		    				{
		    					booFlag = true;
		    				}
		    			}
	    			}
	    		}
	    		
	    		
	    		if((booFlag == true))
	    		{
		    		//tally count
					tracker = stack.pop();					//when we pop off the stack, we should remove it's apearance (1 level only?) "upstream" of where it was.
					count += tracker.getDuration();
					nodesInPath += tracker.getName();
					for(int j = 0; j < AList.size(); j++)
	    	    	{
						if(stack.empty())
						{
							break;
						}
	    	    		if(AList.get(j).getFirst() == stack.peek())
	    	    		{
	    	    			arrIndex = AList.indexOf(AList.get(j));
	    	    			break;
	    	    		}
	    	    	}
					AList.get(arrIndex).remove(tracker);
	    			iterator = AList.get(arrIndex).listIterator(1);
	    		}
	    		
	    		
	    	}
    	}
    	

            count = 0;
    	
    	
    	return toReturn;
    }
    
    
}




