package edu.westga.cs.schoolgrades.model;

/**
 * This class decorates a {@link Grade} by applying a 
 * multiplicative weight to the grade value.
 * 
 * @author Brandon Drick
 * @version 11/07/2021
 */
public class WeightedGrade extends GradeDecorator {
	private final double weight;
	
	/**
	 * 2-param constructor for WeightedGrade
	 * 
	 * @param theGrade Grade to decorate
	 * @param gradeWeight multiplicative weight amount
	 * @precondition gradeWeight between 0.0 and 1.0
	 * @postcondition Grade with weight applied to value
	 */
	public WeightedGrade(Grade theGrade, double gradeWeight) {
		super(theGrade);

		if (gradeWeight < 0.0 || gradeWeight > 1.0) {
			throw new IllegalArgumentException("Invalid grade weight - must be between 0.0 and 1.0");
		}
		
		this.weight = gradeWeight;
	}
	
	@Override
	public double getValue() {
		return super.getValue() * this.weight;
	}
}
