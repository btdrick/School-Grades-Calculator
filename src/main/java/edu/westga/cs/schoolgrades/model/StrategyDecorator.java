package edu.westga.cs.schoolgrades.model;

import java.util.List;

/**
 * This decorator serves to adjust the output of
 * the {@link GradeStrategy} class.
 * 
 * @author Brandon Drick
 * @version 12/27/2021
 */
public abstract class StrategyDecorator implements GradeStrategy {
private GradeStrategy gradingStrategy;
	
	/**
	 * 1-param constructor
	 * @param gradingStrategy strategy to use
	 */
	public StrategyDecorator(GradeStrategy gradingStrategy) {
		if (gradingStrategy == null) {
			throw new IllegalArgumentException("Strategy cannot be null");
		}
		
		this.gradingStrategy = gradingStrategy;
	}
	
	/**
	 * Return gradingStrategy
	 * @return this.gradingStrategy
	 */
	public GradeStrategy getStrategy() {
		return this.gradingStrategy;
	}
	
	/**
	 * Assigns new strategy to decorator
	 * @param newStrategy GradeStrategy
	 */
	public void setStrategy(GradeStrategy newStrategy) {
		if (newStrategy == null) {
			throw new IllegalArgumentException("Strategy cannot be null");
		}
		
		this.gradingStrategy = newStrategy;
	}
	
	@Override
	public double calculateGrade(List<Grade> gradeList) {
		if (gradeList == null) {
			throw new IllegalArgumentException("Grade list cannot be null");
		}
		
		double grade = this.gradingStrategy.calculateGrade(gradeList);
		return grade;
	}
}
