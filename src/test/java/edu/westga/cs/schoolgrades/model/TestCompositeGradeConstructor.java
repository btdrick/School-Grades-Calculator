package edu.westga.cs.schoolgrades.model;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;

/**
 * This class tests the functionality of
 * {@link CompositeGrade} constructor.
 * 
 * @author Brandon Drick
 * @version 11/07/2021
 */
public class TestCompositeGradeConstructor {
	private CompositeGrade compositeGrade;
	
	/**
	 * Test that constructor throws exception
	 * for null parameter.
	 * 
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testCannotCreateCompositeGradeWithNullStrategy() {
		assertThrows(IllegalArgumentException.class, () -> {
			new CompositeGrade(null);
		});
	}
	
	/**
	 * Test that CompositeGrade
	 * created with empty list
	 */
	@Test
	public void testShouldHaveNoGradesWhenCreated() {
		this.compositeGrade = new CompositeGrade(mock(GradeStrategy.class));
		assertTrue(this.compositeGrade.getGrades().isEmpty());
	}
}
