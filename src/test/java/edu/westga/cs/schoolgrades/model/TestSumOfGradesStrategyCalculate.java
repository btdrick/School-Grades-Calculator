package edu.westga.cs.schoolgrades.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the functionality of
 * {@link SumOfGradesStrategy#calculateGrade(ArrayList) calculateGrade} method.
 * 
 * @author Brandon Drick
 * @version 12/27/2021
 */
public class TestSumOfGradesStrategyCalculate {

	private static final double DELTA = 0.001;
	private Grade mockGrade0;
	private Grade mockGrade1;
	private Grade mockGrade2;
	private ArrayList<Grade> grades;
	private SumOfGradesStrategy strategy;
	
	/**
	 * Set up the objects used for testing
	 */
	@BeforeEach
	public void setup() {
		this.mockGrade0 = mock(Grade.class);
		when(this.mockGrade0.getValue()).thenReturn(10.00);
		this.mockGrade1 = mock(Grade.class);
		when(this.mockGrade1.getValue()).thenReturn(20.00);
		this.mockGrade2 = mock(Grade.class);
		when(this.mockGrade2.getValue()).thenReturn(30.00);
		
		this.grades = new ArrayList<Grade>();
		
		this.strategy = new SumOfGradesStrategy();
	}
	
	/**
	 * Test cannot calculate with empty list.
	 */
	@Test
	public void testShouldNotAllowNullGradesList() {
		assertThrows(IllegalArgumentException.class, () -> { 
			this.strategy.calculateGrade(null);
		});
	}
	
	/**
	 * Test return 0 from empty list.
	 */
	@Test
	public void testShouldGiveZeroIfNoGrades() {
		assertEquals(0, this.strategy.calculateGrade(this.grades), DELTA);
	}
	
	/**
	 * Test that the value of one-element list
	 * is returned.
	 */
	@Test
	public void testShouldCalculateSumOfOneGrades() {
		this.grades.add(this.mockGrade0);
		assertEquals(this.mockGrade0.getValue(), this.strategy.calculateGrade(this.grades), DELTA);
	}

	/**
	 * Test that list of multiple elements
	 * returns the sum.
	 */
	@Test
	public void testShouldCalculateSumOManyGrades() {
		this.grades.add(this.mockGrade0);
		this.grades.add(this.mockGrade1);
		this.grades.add(this.mockGrade2);
		assertEquals(60, this.strategy.calculateGrade(this.grades), DELTA);
	}
}
