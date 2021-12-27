package edu.westga.cs.schoolgrades.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the functionality of the
 * {@link AverageOfGradesStrategy#calculateGrade(ArrayList) calculateGrade} method.
 * 
 * @author Brandon Drick
 * @version 12/27/2021
 */
public class TestAverageOfGradesStrategyCalculate {

	private static final double DELTA = 0.001;
	private Grade mockGrade0;
	private Grade mockGrade1;
	private Grade mockGrade2;
	private ArrayList<Grade> grades;
	private AverageOfGradesStrategy strategy;
	
	/**
	 * Sets up the objects used for testing.
	 * 
	 * @throws Exception
	 */
	@BeforeEach
	public void setup() throws Exception {
		this.mockGrade0 = mock(Grade.class);
		when(this.mockGrade0.getValue()).thenReturn(10.00);
		this.mockGrade1 = mock(Grade.class);
		when(this.mockGrade1.getValue()).thenReturn(20.00);
		this.mockGrade2 = mock(Grade.class);
		when(this.mockGrade2.getValue()).thenReturn(30.00);
		
		this.grades = new ArrayList<Grade>();
		this.strategy = new AverageOfGradesStrategy();
	}
	
	/**
	 * Test cannot calculate null Grade list.
	 * 
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testShouldNotAllowNullGradesList() {
		assertThrows(IllegalArgumentException.class, () -> {
			this.strategy.calculateGrade(null);
		});
	}
	
	/**
	 * Test return 0 for empty list.
	 */
	@Test
	public void testShouldReturn0IfNoGrades() {
		assertEquals(0, this.strategy.calculateGrade(this.grades), DELTA);
	}
	
	/**
	 * Test returns value of one-element list.
	 */
	@Test
	public void testShouldCalculateAverageOfOneGrades() {
		this.grades.add(this.mockGrade0);
		assertEquals(this.mockGrade0.getValue(), this.strategy.calculateGrade(this.grades), DELTA);
	}

	/**
	 * Test returns the average of list
	 * of Grade elements.
	 */
	@Test
	public void testShouldCalculateSumOManyGrades() {
		this.grades.add(this.mockGrade0);
		this.grades.add(this.mockGrade1);
		this.grades.add(this.mockGrade2);
		assertEquals(20, this.strategy.calculateGrade(this.grades), DELTA);
	}
}
