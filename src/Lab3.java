import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Lab3 extends JFrame{
	JButton calculateButton;
	JComboBox <String>numberOfCoursesCombo;
	//JComboBox <String>creditHoursCombo [] = new JComboBox [6];
	//JComboBox <String>gradeCombo [] = new JComboBox [6];
	JLabel creditsL, currentGPA_L, 
			numberOfCoursesL, 
			itemNrL, courseCodeL, creditHoursL, gradeL,
			finalGPA_L;
	JLabel itemNr2_L [] = new JLabel[6];
	JPanel topPanel, coursePanel, bottomPanel,
			topPanelUpper, topPanelLower;
	JTextField creditsF, currentGPA_F;
	JTextField courseCodeTF [] = new JTextField[6];

	String[] courseTextBoxes = {"course1", "course2", "course3", "course4", "course5", "course6"}; 
	String[] creditComboBoxes = {"credit1", "credit2", "credit3", "credit4", "credit5", "credit6"};
	String[] gradeComboBoxes = {"grade1", "course2", "course3", "course4", "course5", "course6"};
	String[] creditsHours = {"1", "2","3","4"};
	String[] grades = {"F 0-49%","D 50-54%","D+ 55-59%","C 60-64%","C+ 65-69%","B 70-74%","B+ 75-79%","A 80-89%","A+ 90-100%"};
	double[] gradePointVal = {0,1,1.5,2,2.5,3,3.5,4,4.5};
	//Component rowList = new Component[6];
	ArrayList<JComboBox> rowList=new ArrayList<>();
	public Lab3()
	{
		setLayout( new BorderLayout()); 
		bottomPanel = new JPanel();
		topPanel = new JPanel();
		coursePanel = new JPanel();
		topPanelUpper = new JPanel();
		topPanelLower = new JPanel();
		
		creditsL = new JLabel ("Current Credits");
		creditsF = new JTextField (4);
		currentGPA_L = new JLabel ("Current GPA");
		currentGPA_F= new JTextField (4);
		numberOfCoursesL = new JLabel("Number of Courses");
		String [] courses = {"0", "1", "2", "3", "4", "5", "6"};
		numberOfCoursesCombo = new JComboBox <String>(courses);
		
		topPanelUpper.add(creditsL);
		topPanelUpper.add(creditsF);
		topPanelUpper.add(currentGPA_L);
		topPanelUpper.add(currentGPA_F);
		topPanel.setLayout(new BorderLayout());
		topPanel.add("North", topPanelUpper);
		topPanelLower.add(numberOfCoursesL);
		topPanelLower.add(numberOfCoursesCombo);
		numberOfCoursesCombo.addActionListener(new ComboHandler());
		topPanel.add("South", topPanelLower);
		
		
		itemNrL = new JLabel("Nr");
		courseCodeL = new JLabel("Course code");
		creditHoursL = new JLabel("Credit hours");
		gradeL = new JLabel ("Grade");
		coursePanel.setLayout(new GridLayout(0, 4));
		coursePanel.add(itemNrL);
		coursePanel.add(courseCodeL);
		coursePanel.add(creditHoursL);
		coursePanel.add(gradeL);
		for(int i = 0; i < 24; i++){
			coursePanel.add(new JLabel());
		}
		
		bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		finalGPA_L = new JLabel ("Final GPA");
		calculateButton = new JButton ("Calculate");
		calculateButton.addActionListener(new ComboHandler());
		bottomPanel.add(finalGPA_L);
		bottomPanel.add(calculateButton);
		add("North", topPanel);
		add("Center", coursePanel);
		add("South", bottomPanel);
	}

	public static void main(String[] args) {
		Lab3 frame = new Lab3();
		frame.setTitle("GPA Calculator");
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.setSize(480,320);
		frame.setVisible(true);
	}
	
	private class ComboHandler implements ActionListener{
	  
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == numberOfCoursesCombo){
				coursePanel.removeAll();
				coursePanel.add(itemNrL);
				coursePanel.add(courseCodeL);
				coursePanel.add(creditHoursL);
				coursePanel.add(gradeL);
				for (int i = 0; i < numberOfCoursesCombo.getSelectedIndex(); i++){
					
					coursePanel.add(new JLabel(Integer.toString(i+1)));
					coursePanel.add(new JTextField());
					JComboBox temp = new JComboBox(creditsHours);
					coursePanel.add(temp);
					rowList.add(temp);
					JComboBox gradeTemp = new JComboBox(grades); 
					coursePanel.add(gradeTemp);
					rowList.add(gradeTemp);
				}
				coursePanel.revalidate();
			}
			else if (e.getSource() == calculateButton)
			{
				double initQP = Double.parseDouble(currentGPA_F.getText())* Integer.parseInt(creditsF.getText());
				int totalCreditHours = Integer.parseInt(creditsF.getText());
				int creditHours = 0;
				double qualityPoints=0; 
				double gpa; 
				double totalGPA=Double.parseDouble(currentGPA_F.getText()); 
				for (int i = 0; i < rowList.size(); i+=2)
				{
					totalCreditHours += Integer.parseInt(rowList.get(i).getSelectedItem().toString()); 
					creditHours = Integer.parseInt(rowList.get(i).getSelectedItem().toString());
					gpa = gradePointVal[rowList.get(i+1).getSelectedIndex()];
					qualityPoints += creditHours*gpa; 
				}
				totalGPA = (qualityPoints + initQP) /totalCreditHours; 
				
				finalGPA_L.setText("Final GPA: " + totalGPA);
				 
				totalCreditHours = 0; 
				creditHours =0;
				qualityPoints=0;
				gpa=0;
				totalGPA = 0;
				
				
				
			}
			
		}
	}

	
}

















