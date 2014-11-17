import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Scanner;
import java.awt.Color;
import java.awt.Font;
import java.io.*;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.ExpressionBuilder;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;
public class Calculator {

	public static void changeJOP()
	{
	UIManager.put("Label.font", new FontUIResource
			(new Font("Simhei", Font.BOLD, 28)));
	UIManager.put("OptionPane.messageForeground",new Color(72,61,139));
	UIManager.put("Panel.background",Color.lightGray);
	UIManager.put("OptionPane.background",new Color(0,0,205));
	UIManager.put("Button.background",new Color(132,112,255));
	UIManager.put("Button.foreground", new Color(72,61,139));
	UIManager.put("Button.font", new FontUIResource
			(new Font("Simhei", Font.BOLD, 20)));
	}
	
	static void EulerMethod()throws UnparsableExpressionException, UnknownFunctionException, EmptyStackException
	{
	String equation = JOptionPane.showInputDialog
			("Enter your equation in the form 5*x + 6*y:");
	int min_range = Integer.parseInt(JOptionPane.showInputDialog
			("Enter the minimum of the range\nIf none given, it is the initial x value"));
	int max_range = Integer.parseInt(JOptionPane.showInputDialog
			("Enter the maximum of the range"));
	double step_size = new Double(JOptionPane.showInputDialog
			("Enter the step size:"));
	int initial_x = Integer.parseInt(JOptionPane.showInputDialog
			("Enter the initial x value:"));
	int initial_y = Integer.parseInt(JOptionPane.showInputDialog
			("Enter the initial y value:"));
		
		double var1 = min_range;
		double var2 = initial_y;
		Calculable calc = new ExpressionBuilder(equation)
		.withVariable("x", var1)
        .withVariable("y", var2)
        .withVariable("e", Math.E)
        .build();
		double result = calc.calculate();
		result = result * step_size;
		double y = initial_y + result;
		double x= step_size;
//		double [] arr1 = new double[(int) (max_range/step_size)];
//		int a = 0;
//		min_range = (int) min_range;
//		for(double i=min_range+step_size; i< max_range; i+=step_size)
//		{
//			arr1[a]=i;
//			a++;
//		}
		double [] arr = new double[(int) (max_range/step_size)];
		int n=0;
		for(double i = min_range + step_size; i < max_range; i+=step_size)
		{
			
			 calc = new ExpressionBuilder(equation)
			.withVariable("x", x)
	        .withVariable("y", y)
	        .withVariable("e", Math.E)
	        .build();
			double result_1=calc.calculate();
			//System.out.println(y);
			arr[n]=y;
			n++;
			if(i != max_range)
			{
			x+=step_size;
			y = (result_1* step_size) +y;	
		
			}	
		
		}
		JOptionPane.showMessageDialog(null, "x: Start from "+min_range+
				" and increment by "+step_size+" until you reach "+
				max_range+".\n"+ "y:"+Arrays.toString(arr));
	}

	static void ImprovedEulerMethod()throws UnparsableExpressionException, UnknownFunctionException, EmptyStackException
	{
		double x,  u1, u2, y; 
		String equation = JOptionPane.showInputDialog
				("Enter your equation in the form 5*x + 6*y:");
		int min_range = Integer.parseInt(JOptionPane.showInputDialog
				("Enter the minimum of the range\nIf none given, it is the initial x value"));
		int max_range = Integer.parseInt(JOptionPane.showInputDialog
				("Enter the maximum of the range"));
		double step_size = new Double(JOptionPane.showInputDialog
				("Enter the step size:"));
		int initial_x = Integer.parseInt(JOptionPane.showInputDialog
				("Enter the initial x value:"));
		int initial_y = Integer.parseInt(JOptionPane.showInputDialog
				("Enter the initial y value:"));
		
			double var1 = min_range;
			double var2 = initial_y;
			Calculable calc = new ExpressionBuilder(equation)
			.withVariable("x", var1)
	        .withVariable("y", var2)
	        .withVariable("e", Math.E)
	        .build();
			double result = calc.calculate();
			double result_u1 = result * step_size;
			u1 = initial_y + result_u1;
			x= step_size;
			calc = new ExpressionBuilder(equation)
			.withVariable("x", x)
	        .withVariable("y", u1)
	        .withVariable("e", Math.E)
	        .build();
			double result2 = calc.calculate();
			y = ((result+result2) * step_size/2) + initial_y;
			double [] arr1 = new double[(int) (max_range/step_size)];
			int a = 0;
			for(double i=min_range+step_size; i< max_range; i+=step_size)
			{
				arr1[a]=i;
				a++;
			}
			double [] arr = new double[(int) (max_range/step_size)];
			int n=0;
			//System.out.println(y);
			for(double i = min_range + step_size; i < max_range; i+=step_size)
			{
				
				 calc = new ExpressionBuilder(equation)
				.withVariable("x", x)
		        .withVariable("y", y)
		        .withVariable("e", Math.E)
		        .build();
				double result_4=calc.calculate();
				u2 = y + (result_4 * step_size);
				//System.out.println(y);
				arr[n]=y;
				n++;
				if(i!=max_range)
				{	
				x += step_size;
				calc = new ExpressionBuilder(equation)
				.withVariable("x", x )
		        .withVariable("y", u2)
		        .withVariable("e", Math.E)
		        .build();
				double result3 = calc.calculate();
				
				y = ((result_4+result3) * step_size/2) + y;
				
				//x+=step_size;	
				}
			
					
				
			}

			JOptionPane.showMessageDialog(null, "x: Start from "+min_range+
					" and increment by "+step_size+" until you reach "+
					max_range+".\n"+ "y:"+Arrays.toString(arr));
			}
	
	static int menu(){
		int choice;
		String[] options = 
			{"Euler's", "Improved Euler's"};
		choice=JOptionPane.showOptionDialog
			(null, "Please select an option (currently unable to calculate logs):", "Approximations",
				0, 3, null, options, null);
		return choice;
	}
	

	public static void main(String[] args) throws UnparsableExpressionException, 
		UnknownFunctionException, EmptyStackException
	{
		JOptionPane.showMessageDialog(null, "This is an approximation method calculator. \n"
				+ "It can be used for approximations with Euler's and Improved Euler's Methods.");
		int choice = menu();
		if(choice==0)
		{
			EulerMethod();
		}
		else ImprovedEulerMethod();
	}

}


