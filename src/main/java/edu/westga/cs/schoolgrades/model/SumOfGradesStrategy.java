package edu.westga.cs.schoolgrades.model;

import java.util.ArrayList;

/**
 * This GradeStrategy calculates the
 * sum of a {@link Grade} objects list.
 * 
 * @author Brandon Drick
 * @version 11/07/2021
 */
public class SumOfGradesStrategy implements GradeStrategy {

	/**
	 * Calculates the sum of the grade list.
	 */
	@Override
	public double calculateGrade(ArrayList<Grade> gradeList) {
		if (gradeList == null) {
			throw new IllegalArgumentException("Grade list cannot be null");
		}
		
		double sumOfGrades = 0.0;
		
		for (Grade current : gradeList) {
			sumOfGrades += current.getValue();
		}
		
		return sumOfGrades;
	}

}
