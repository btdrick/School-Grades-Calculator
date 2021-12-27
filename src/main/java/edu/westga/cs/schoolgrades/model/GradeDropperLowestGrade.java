package edu.westga.cs.schoolgrades.model;

import java.util.ArrayList;

/**
 * This class calculates with a {@link GradeStrategy}
 * by dropping the lowest Grade.
 * 
 * @author Brandon Drick
 * @version 12/27/2021
 */
public class GradeDropperLowestGrade extends StrategyDecorator {
	
	/**
	 * 1-param constructor
	 * @param gradingStrategy strategy to use
	 */
	public GradeDropperLowestGrade(GradeStrategy gradingStrategy) {
		super(gradingStrategy);
	}

	@Override
	public double calculateGrade(ArrayList<Grade> grades) {
		if (grades == null) {
			throw new IllegalArgumentException("grades may not be null");
		}
		
		if (grades.size() < 2) {
			return super.calculateGrade(grades);
		}
		
		ArrayList<Grade> withLowestRemoved = this.dropLowestFrom(grades);
		return super.calculateGrade(withLowestRemoved);
	} 

	private ArrayList<Grade> dropLowestFrom(ArrayList<Grade> grades) {
		Grade lowest = grades.get(0);
		for (Grade grade: grades) {
			if (lowest.getValue() > grade.getValue()) {
				lowest = grade;
			}
		}
		ArrayList<Grade> result = new ArrayList<Grade>(grades);
		result.remove(lowest);
		return result;
	}
}
