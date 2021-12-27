package edu.westga.cs.schoolgrades.model;

/**
 * This class models a single,
 * simple {@link Grade} holding a numerical value.
 * 
 * @author Brandon Drick
 * @version 11/07/2021
 */
public class SimpleGrade implements Grade {
	private double gradeValue;
	
	/**
	 * 1-param constructor
	 * @param gradeValue the grade amount
	 * @precondition gradeValue >= 0.0
	 * @postcondition SimpleGrade holding gradeValue
	 */
	public SimpleGrade(double gradeValue) {
		if (gradeValue < 0.0) {
			throw new IllegalArgumentException("Grade value must be greater than 0");
		}
		
		this.gradeValue = gradeValue;
	}

	@Override
	public double getValue() {
		return this.gradeValue;
	}
	
	/**
	 * Sets the grade value.
	 * 
	 * @param gradeValue new value
	 */
	public void setValue(double gradeValue) {
		if (gradeValue < 0) {
			throw new IllegalArgumentException("value should not be < 0");
		}
		
		this.gradeValue = gradeValue;
	}
}
