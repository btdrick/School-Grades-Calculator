package edu.westga.cs.schoolgrades.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class models a composite pattern
 * for the {@link Grade} interface.
 * 
 * @author Brandon Drick
 * @version 11/07/2021
 */
public class CompositeGrade implements Grade {
	private ArrayList<Grade> theGrades;
	private GradeStrategy gradingStrategy;
	
	/**
	 * 1-param constructor
	 * @param theStrategy the strategy to use
	 * @postcondition CompositeGrade holding {@link Grade} objects
	 */
	public CompositeGrade(GradeStrategy theStrategy) {
		this.validateStrategyNotNull(theStrategy);
		this.theGrades = new ArrayList<Grade>();
		this.setStrategy(theStrategy);
	}
	
	/**
	 * Adds a {@link Grade} object to theGrades
	 * @param newGrade Grade object
	 */
	public void addGrade(Grade newGrade) {
		this.validateGradeNotNull(newGrade);
		this.theGrades.add(newGrade);
	}
	
	/**
	 * Adds a {@link Grade} to this CompositeGrade at the given index.
	 * @param grade the grade to add
	 * @param index the index at which to add it
	 */
	public void addGrade(Grade grade, int index) {
		this.validateGradeNotNull(grade);
		this.theGrades.add(index, grade);
	}
	
	/**
	 * Adds all grades in the list.
	 * 
	 * @param grades the list of grades to add
	 * @precondition grades != null
	 */
	public void addAll(List<? extends Grade> grades) {
		if (grades == null) {
			throw new IllegalArgumentException("grades can not be null");
		}
		
		for (Grade grade: grades) {
			this.addGrade(grade);
		}
	}
	
	/**
	 * Removes a {@link Grade} object from theGrades
	 * @param gradeToRemove Grade object
	 * @precondition Grade isn't null and exists in this.theGrades
	 */
	public void removeGrade(Grade gradeToRemove) {
		this.validateGradeNotNull(gradeToRemove);
		if (!this.theGrades.contains(gradeToRemove)) {
			throw new IllegalArgumentException("That grade does not exist");
		}
		
		this.theGrades.remove(gradeToRemove);
	}
	
	/**
	 * Removes the {@link Grade} at the given index.
	 * @param index to remove Grade
	 * @precondition index exists in this.theGrades
	 */
	public void removeGrade(int index) {
		if (index < 0 || index > (this.theGrades.size() - 1)) {
			throw new IllegalArgumentException("Invalid index");
		}
		
		this.theGrades.remove(index);
	}
	
	/**
	 * Gets this.theGrades
	 * 
	 * @return all contained {@link Grade} objects
	 */
	public ArrayList<Grade> getGrades() {
		return this.theGrades;
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
		this.validateStrategyNotNull(newStrategy);
		this.gradingStrategy = newStrategy;
	}
	
	@Override
	public double getValue() {
		double gradeValue = this.gradingStrategy.calculateGrade(this.theGrades);
		return gradeValue;
	}
	
	/**
	 * Helper method to prevent invalid {@link Grade} objects
	 * @param grade to validate
	 * @precondition grade != null
	 */
	private void validateGradeNotNull(Grade grade) {
		if (grade == null) {
			throw new IllegalArgumentException("Grade object cannot be null");
		}
	}
	
	/**
	 * Helper method to prevent invalid {@link GradeStrategy} objects
	 * @param theStrategy to validate
	 * @precondition strategy != null
	 */
	private void validateStrategyNotNull(GradeStrategy theStrategy) {
		if (theStrategy == null) {
			throw new IllegalArgumentException("Strategy object cannot be null");
		}
	}
}
