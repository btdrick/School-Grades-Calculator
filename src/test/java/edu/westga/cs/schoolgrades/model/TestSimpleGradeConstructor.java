package edu.westga.cs.schoolgrades.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * This class tests the functionality of
 * {@link SimpleGrade#SimpleGrade() Constructor}
 * 
 * @author Brandon Drick
 * @version 11/07/2021
 */
public class TestSimpleGradeConstructor {

	private static final double DELTA = 0.001;

	/**
	 * Test cannot create SimpleGrade
	 * using a negative value.
	 * 
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testCannotCreateSimpleGradeWithNegativeValue() {
		assertThrows(IllegalArgumentException.class, () -> { 
			new SimpleGrade(-1);
		});
	}
	
	/**
	 * Test create a SimpleGrade object
	 * with a grade of 0.
	 */
	@Test
	public void testCreateSimpleGradeWithGradeOf0ShouldReturn0() {
		assertEquals(0, new SimpleGrade(0).getValue(), DELTA);
	}
	
	/**
	 * Test create a SimpleGrade object
	 * with a grade of 100.
	 */
	@Test
	public void testCreateSimpleGradeWithGradeOf100ShouldReturn100() {
		assertEquals(100.0, new SimpleGrade(100).getValue());
	}

	/**
	 * Test create a SimpleGrade object
	 * with a grade of 55.
	 */
	@Test
	public void testCreateSimpleGradeWithGradeOf55ShouldReturn55() {
		assertEquals(55.0, new SimpleGrade(55).getValue());
	}
	
	/**
	 * Test create a SimpleGrade object
	 * with a grade of 73.8.
	 */
	@Test
	public void testCreateSimpleGradeWithGradeOf73ShouldReturn73() {
		assertEquals(73.8, new SimpleGrade(73.8).getValue());
	}
}
