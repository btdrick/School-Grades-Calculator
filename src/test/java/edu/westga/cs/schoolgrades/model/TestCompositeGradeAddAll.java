package edu.westga.cs.schoolgrades.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class tests the functionality of
 * {@link CompositeGrade#addAll(java.util.List) addAll} method.
 * 
 * @author Brandon Drick
 * @version 11/07/2021
 */
public class TestCompositeGradeAddAll {
	private CompositeGrade composite;
	private Grade mockGrade0;
	private Grade mockGrade1;
	private Grade mockGrade2;
	private List<Grade> gradeList;
	
	/**
	 * Set up objects used for testing.
	 * 
	 * @throws Exception
	 */
	@BeforeEach
	public void setup() throws Exception {
		this.composite = new CompositeGrade(mock(GradeStrategy.class));
		this.mockGrade0 = mock(Grade.class);
		when(this.mockGrade0.getValue()).thenReturn(10.00);
		this.mockGrade1 = mock(Grade.class);
		when(this.mockGrade1.getValue()).thenReturn(20.00);
		this.mockGrade2 = mock(Grade.class);
		when(this.mockGrade2.getValue()).thenReturn(30.00);
		this.gradeList = new ArrayList<Grade>();
	}
	
	/**
	 * Test cannot add null list.
	 */
	@Test
	public void testShouldNotAddNullGradesList() {
		assertThrows(IllegalArgumentException.class, () -> { 
			this.composite.addAll(null);
		});
	}
	
	/**
	 * Test add empty Grade list.
	 */
	@Test
	public void testShouldAddEmptyList() {
		this.composite.addAll(new ArrayList<Grade>());
		assertTrue(this.composite.getGrades().isEmpty());
	}
	
	/**
	 * Test add Grade list holding one element.
	 */
	@Test
	public void testShouldAddOneElementList() {
		this.gradeList.add(this.mockGrade0);
		this.composite.addAll(this.gradeList);
		List<Grade> actual = this.composite.getGrades();
		assertEquals(1, actual.size());
		assertEquals(this.mockGrade0, actual.get(0));
	}
	
	/**
	 * Test add Grade list holding multiple elements.
	 */
	@Test
	public void testShouldAddListOfManyElements() {
		this.gradeList.add(this.mockGrade0);
		this.gradeList.add(this.mockGrade1);
		this.gradeList.add(this.mockGrade2);
		this.composite.addAll(this.gradeList);
		List<Grade> actual = this.composite.getGrades();
		assertEquals(3, actual.size());
		assertEquals(this.mockGrade0, actual.get(0));
		assertEquals(this.mockGrade1, actual.get(1));
		assertEquals(this.mockGrade2, actual.get(2));
	}
}
