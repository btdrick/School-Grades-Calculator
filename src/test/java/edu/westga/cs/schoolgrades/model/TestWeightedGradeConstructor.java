package edu.westga.cs.schoolgrades.model;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;

/**
 *  This class tests the functionality of
 *  {@link WeightedGrade#WeightedGrade(Grade, double) Constructor} decorator.
 * 
 * @author Brandon Drick
 * @version 11/07/2021
 */
public class TestWeightedGradeConstructor {
	
	/**
	 * Tests that WeightedGrade cannot
	 * be created with null Grade object.
	 * 
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testCannotUseNullGrade() {
		assertThrows(IllegalArgumentException.class, () -> { 
			new WeightedGrade(null, 10.0);
		});
	}
	
	/**
	 * Tests that WeightedGrade with
	 * weight -0.1 cannot be created.
	 * 
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testCannotApplyNegativeWeight() {
		assertThrows(IllegalArgumentException.class, () -> {
			new WeightedGrade(mock(Grade.class), -10.0);
		});
	}
	
	/**
	 * Tests that WeightedGrade with 
	 * weight 1.1 cannot be created.
	 * 
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testCannotApplyInvalidWeight() {
		assertThrows(IllegalArgumentException.class, () -> {
			new WeightedGrade(mock(Grade.class), 10.0);
		});
	}
}
