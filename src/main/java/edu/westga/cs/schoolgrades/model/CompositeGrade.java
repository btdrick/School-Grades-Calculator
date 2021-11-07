package edu.westga.cs.schoolgrades.model;

import java.util.ArrayList;

/**
 * This class models a composite pattern
 * for the Grade interface.
 * 
 * @author Brandon Drick
 * @version 11/07/2021
 */
public class CompositeGrade implements Grade {
	private ArrayList<Grade> theGrades;
	private GradeStrategy gradingStrategy;
	
	/**
	 * 1-param constructor
	 * @param gradingStrategy the strategy to use
	 */
	public CompositeGrade(GradeStrategy gradingStrategy) {
		if (gradingStrategy == null) {
			throw new IllegalArgumentException("Strategy cannot be null");
		}
		
		this.theGrades = new ArrayList<Grade>();
		this.gradingStrategy = gradingStrategy;
	}
	
	/**
	 * Adds a Grade object to theGrades
	 * @param newGrade Grade object
	 */
	public void addGrade(Grade newGrade) {
		if (newGrade == null) {
			throw new IllegalArgumentException("Grade object cannot be null");
		}
		
		this.theGrades.add(newGrade);
	}
	
	/**
	 * Removes a Grade object from theGrades
	 * @param gradeToRemove Grade object
	 */
	public void removeGrade(Grade gradeToRemove) {
		if (gradeToRemove == null) {
			throw new IllegalArgumentException("Grade object cannot be null");
		}
		if (this.theGrades.size() == 0) {
			throw new IllegalStateException("There are no grades stored");
		}
		if (!this.theGrades.contains(gradeToRemove)) {
			throw new IllegalArgumentException("That grade does not exist");
		}
		
		this.theGrades.remove(gradeToRemove);
	}
	
	/**
	 * Returns a list form of theGrades
	 * @return theGrades as String
	 */
	public String toString() {
		String theGrades = "";
		
		if (this.theGrades.size() > 0) {
			for (Grade current : this.theGrades) {
				theGrades += current.getValue() + ", ";
			}
			
			theGrades = "Current Grades: " + theGrades.substring(0, theGrades.length() - 2);
		} else {
			theGrades = "There are no grades";
		}
		
		return theGrades;
	}
	
	/**
	 * Sets gradingStrategy instance variable
	 * @param newStrategy GradeStrategy object
	 */
	public void setStrategy(GradeStrategy newStrategy) {
		if (newStrategy == null) {
			throw new IllegalArgumentException("Strategy cannot be null");
		}
		
		this.gradingStrategy = newStrategy;
	}
	
	@Override
	public double getValue() {
		double gradeValue = this.gradingStrategy.calculateGrade(this.theGrades);
		return gradeValue;
	}
}
