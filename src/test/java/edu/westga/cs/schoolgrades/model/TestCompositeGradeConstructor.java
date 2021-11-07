package edu.westga.cs.schoolgrades.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class tests the functionality of
 * CompositeGrade constructor.
 * 
 * @author Brandon Drick
 * @version 11/07/2021
 */
public class TestCompositeGradeConstructor {
	private CompositeGrade compositeGrade;
	
	/**
	 * Test that constructor throws exception
	 * for null parameter.
	 */
	@Test
	public void tesCannotCreateCompositeGradeWithNullStrategy() {
		assertThrows(IllegalArgumentException.class, () -> {
			new CompositeGrade(null);
		});
	}
	
	/**
	 * Creates a CompositeGrade with SumOfGradesStrategy
	 * before each test.
	 * @throws Exception
	 */
	@BeforeEach
	public void setUp() throws Exception {
		this.compositeGrade = new CompositeGrade(new SumOfGradesStrategy());
	}

	/**
	 * Tests that CompositeGrade 
	 * created with empty list.
	 */
	@Test
	public void testCompositeGradeConstructorShouldReturnEmptyGradeList() {
		assertEquals("There are no grades", this.compositeGrade.toString());
	}
}
