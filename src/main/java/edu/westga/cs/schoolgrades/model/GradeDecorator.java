package edu.westga.cs.schoolgrades.model;

/**
 * This decorator class serves to adjust
 * the {@link Grade} value.
 * 
 * @author Brandon Drick
 * @version 11/07/2021
 */
public abstract class GradeDecorator implements Grade {
	private final Grade decoratedGrade;
	
	/**
	 * 1-param constructor
	 * 
	 * @param decoratedGrade interface
	 * @precondition decoratedGrade != null
	 * @postcondition a Grade decorator holding a Grade to decorate
	 */
	public GradeDecorator(Grade decoratedGrade) {
		if (decoratedGrade == null) {
			throw new IllegalArgumentException("Grade object cannot be null");
		}
		
		this.decoratedGrade = decoratedGrade;
	}
	
	@Override
	public double getValue() {
		return this.decoratedGrade.getValue();
	}
}
