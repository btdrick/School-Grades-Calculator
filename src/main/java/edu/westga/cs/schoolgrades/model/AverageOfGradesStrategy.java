package edu.westga.cs.schoolgrades.model;

import java.util.List;

/**
 * Strategy for averaging a list of {@link Grade} objects.
 * Extends the {@link SumOfGradesStrategy} class.
 * 
 * @author Brandon Drick
 * @version 12/27/2021
 */
public class AverageOfGradesStrategy extends SumOfGradesStrategy {
	@Override
	public double calculateGrade(List<Grade> gradeList) {
		if (gradeList == null) {
			throw new IllegalArgumentException("Grades ArrayList can not be null");
		}
		if (gradeList.isEmpty()) {
			return 0;
		}
		
		double gradeSum = super.calculateGrade(gradeList);
		return gradeSum / gradeList.size();
	}
}
