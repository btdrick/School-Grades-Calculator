package edu.westga.cs.schoolgrades.model;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Tests the functionality of
 * {@link GradeDropperLowestGrade#GradeDropperLowestGrade(GradeStrategy) constructor}.
 * 
 * @author Brandon Drick
 * @version 12/27/2021
 */
public class TestGradeDropperLowestGradeConstructor {

	/**
	 * Test cannot pass a null strategy.
	 * 
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testShouldNotAllowNullChildStrategy() {
		assertThrows(IllegalArgumentException.class, () -> { 
			new GradeDropperLowestGrade(null);
		});
	}
}
