package edu.westga.cs.schoolgrades.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class tests the functionality of
 * {@link CompositeGrade#addGrade(Grade) addGrade} method.
 * 
 * @author Brandon Drick
 * @version 11/07/2021
 */
public class TestCompositeGradeAddGrade {
	private CompositeGrade composite;
	private Grade mockGrade0;
	private Grade mockGrade1;
	private Grade mockGrade2;
	
	/**
	 * Sets up the objects used in testing
	 */
	@BeforeEach
	public void setup() {
		this.composite = new CompositeGrade(mock(GradeStrategy.class));
		this.mockGrade0 = mock(Grade.class);
		when(this.mockGrade0.getValue()).thenReturn(10.00);
		this.mockGrade1 = mock(Grade.class);
		when(this.mockGrade1.getValue()).thenReturn(20.00);
		this.mockGrade2 = mock(Grade.class);
		when(this.mockGrade2.getValue()).thenReturn(30.00);
	}
	
	/**
	 * Tests cannot add null Grade object.
	 * 
	 * @throws IllegalArgumentException
	 */
	@Test
	public void shouldNotAddNullGrade() {
		assertThrows(IllegalArgumentException.class, () -> { 
			this.composite.addGrade(null);
		});
	}
	
	/**
	 * Test can add one Grade object.
	 */
	@Test
	public void canAddOneGrade() {
		this.composite.addGrade(this.mockGrade0);
		List<Grade> grades = this.composite.getGrades();
		assertEquals(1, grades.size());
		assertEquals(this.mockGrade0, grades.get(0));
	}
	
	/**
	 * Test can add multiple Grade objects.
	 */
	@Test
	public void canAddManyGrades() {
		this.composite.addGrade(this.mockGrade0);
		this.composite.addGrade(this.mockGrade1);
		this.composite.addGrade(this.mockGrade2);
		List<Grade> grades = this.composite.getGrades();
		assertEquals(3, grades.size());
		assertEquals(this.mockGrade0, grades.get(0));
		assertEquals(this.mockGrade1, grades.get(1));
		assertEquals(this.mockGrade2, grades.get(2));
	}
}
