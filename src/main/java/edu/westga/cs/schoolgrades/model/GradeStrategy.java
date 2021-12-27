package edu.westga.cs.schoolgrades.model;

import java.util.ArrayList;

/**
 * This strategy pattern interface
 * uses calculation strategies for
 * multiple {@link Grade} objects.
 * 
 * @author Brandon Drick
 * @version 10/17/2021
 */
public interface GradeStrategy {
	
	/**
	 * Calculates the grade based on strategy
	 * @return the calculated grade
	 * @param gradeList as Grade ArrayList
	 */
	double calculateGrade(ArrayList<Grade> gradeList);
}
