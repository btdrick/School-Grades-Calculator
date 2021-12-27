package edu.westga.cs.schoolgrades.model;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the functionality of
 * {@link GradeDropperLowestGrade#calculateGrade(ArrayList) calculateGrade} method.
 * 
 * @author Brandon Drick
 * @version 12/27/2021
 */
public class TestGradeDropperLowestGradeCalculate {
	private GradeDropperLowestGrade dropLowestStrategy;
	private GradeStrategy mockChildStrategy;
	
	private Grade mockGrade0;
	private Grade mockGrade1;
	private Grade mockGrade2;
	
	private ArrayList<Grade> grades;
	private ArrayList<Grade> mockGrades;
	
	/**
	 * Set up objects used in testing
	 * @throws Exception
	 */
	@BeforeEach
	public void setUp() throws Exception {
		this.mockGrade0 = mock(Grade.class);
		when(this.mockGrade0.getValue()).thenReturn(10.00);
		this.mockGrade1 = mock(Grade.class);
		when(this.mockGrade1.getValue()).thenReturn(20.00);
		this.mockGrade2 = mock(Grade.class);
		when(this.mockGrade2.getValue()).thenReturn(30.00);
		
		this.grades = new ArrayList<Grade>();
		this.mockGrades = new ArrayList<Grade>();
		
		this.mockChildStrategy = mock(SumOfGradesStrategy.class);
		this.dropLowestStrategy = new GradeDropperLowestGrade(this.mockChildStrategy);
	}

	/**
	 * Test should not calculate
	 * with null Grade list.
	 */
	@Test
	public void testShouldNotAllowNullGradesList() {
		assertThrows(IllegalArgumentException.class, () -> { 
			this.dropLowestStrategy.calculateGrade(null);
		});
	}

	/**
	 * Test should not drop 
	 * from empty Grade list.
	 */
	@Test
	public void testShouldNotDropLowestIfGradesListIsEmpty() {
		this.dropLowestStrategy.calculateGrade(this.grades);
		verify(this.mockChildStrategy).calculateGrade(this.mockGrades);
	}
	
	/**
	 * Test should not drop 
	 * from one-element list.
	 */
	@Test
	public void testShouldNotDropLowestIfGradesListHasOneElement() {
		this.grades.add(this.mockGrade0);
		this.dropLowestStrategy.calculateGrade(this.grades);
		
		this.mockGrades.add(this.mockGrade0);
		verify(this.mockChildStrategy).calculateGrade(this.mockGrades);
	}
	
	/**
	 * Test drop Grade where lowest
	 * Grade is first in list.
	 */
	@Test
	public void testShouldDropFirstGrade() {
		this.grades.add(this.mockGrade0);
		this.grades.add(this.mockGrade1);
		this.grades.add(this.mockGrade2);
		this.dropLowestStrategy.calculateGrade(this.grades);
		
		this.mockGrades.add(this.mockGrade1);
		this.mockGrades.add(this.mockGrade2);
		verify(this.mockChildStrategy).calculateGrade(this.mockGrades);
	}
	
	/**
	 * Test drop Grade where lowest
	 * Grade is last in list.
	 */
	@Test
	public void testShouldDropLastGrade() {
		this.grades.add(this.mockGrade1);
		this.grades.add(this.mockGrade2);
		this.grades.add(this.mockGrade0);
		this.dropLowestStrategy.calculateGrade(this.grades);
		
		this.mockGrades.add(this.mockGrade1);
		this.mockGrades.add(this.mockGrade2);
		verify(this.mockChildStrategy).calculateGrade(this.mockGrades);
	}
	
	/**
	 * Test drop Grade where lowest
	 * Grade is in middle of list.
	 */
	@Test
	public void testShouldDropMiddleGrade() {
		this.grades.add(this.mockGrade1);
		this.grades.add(this.mockGrade0);
		this.grades.add(this.mockGrade2);
		this.dropLowestStrategy.calculateGrade(this.grades);
		
		this.mockGrades.add(this.mockGrade1);
		this.mockGrades.add(this.mockGrade2);
		verify(this.mockChildStrategy).calculateGrade(this.mockGrades);
	}
	
	/**
	 * Test drop Grade where multiple
	 * Grade objects are the lowest.
	 */
	@Test
	public void testShouldDropFirstLowestGradeInListOfMultipleLowestGrades() {
		this.grades.add(this.mockGrade1);
		this.grades.add(this.mockGrade0);
		this.grades.add(this.mockGrade2);
		this.grades.add(this.mockGrade0);
		this.dropLowestStrategy.calculateGrade(this.grades);
		
		this.mockGrades.add(this.mockGrade1);
		this.mockGrades.add(this.mockGrade2);
		this.mockGrades.add(this.mockGrade0);
		verify(this.mockChildStrategy).calculateGrade(this.mockGrades);
	}
}
