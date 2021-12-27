package edu.westga.cs.schoolgrades.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class tests the functionality of
 * {@link CompositeGrade#removeGrade(Grade) removeGrade} method.
 * 
 * @author Brandon Drick
 * @version 11/07/2021
 */
public class TestCompositeGradeRemoveGrade {
	private CompositeGrade composite;
	private Grade mockGrade0;
	private Grade mockGrade1;
	private Grade mockGrade2;
	private List<Grade> gradeList;
	
	/**
	 * Creates a CompositeGrade with {@link SumOfGradesStrategy}
	 * before each test.
	 * 
	 * @throws Exception
	 */
	@BeforeEach
	public void setUp() throws Exception {		
		this.composite = new CompositeGrade(mock(GradeStrategy.class));
		this.mockGrade0 = mock(Grade.class);
		when(this.mockGrade0.getValue()).thenReturn(100.00);
		this.mockGrade1 = mock(Grade.class);
		when(this.mockGrade1.getValue()).thenReturn(88.00);
		this.mockGrade2 = mock(Grade.class);
		when(this.mockGrade2.getValue()).thenReturn(70.4);
		this.gradeList = new ArrayList<Grade>(Arrays.asList(this.mockGrade0, 
				this.mockGrade1, this.mockGrade2));
		this.composite.addAll(this.gradeList);
	}
	
	/**
	 * Test that a null Grade cannot be removed.
	 * 
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testCannotRemoveNullGrade() {
		assertThrows(IllegalArgumentException.class, () -> {
			this.composite.removeGrade(null);
		});
	}

	/**
	 * Test that an empty list cannot have a Grade removed.
	 * 
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testCannotRemoveFromEmptyList() {
		Grade grade = new SimpleGrade(100);
		assertThrows(IllegalArgumentException.class, () -> {
			this.composite.removeGrade(grade);
		});
	}
	
	/**
	 * Test that a Grade not present cannot be removed.
	 * 
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testCannotRemoveNonexistingGrade() {
		Grade grade = new SimpleGrade(100);
		assertThrows(IllegalArgumentException.class, () -> {
			this.composite.removeGrade(grade);
		});
	}
	
	/**
	 * Test that an invalid index of -1 cannot be removed
	 * 
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testCannotRemoveNegativeIndex() {
		assertThrows(IllegalArgumentException.class, () -> {
			this.composite.removeGrade(-1);
		});
	}
	
	/**
	 * Test that an invalid index of 3 cannot be removed
	 * from list of 3 Grades.
	 * 
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testCannotRemoveOutOfBoundsIndex() {
		assertThrows(IllegalArgumentException.class, () -> {
			this.composite.removeGrade(3);
		});
	}
	
	/**
	 * Test that Grade is removed from a list.
	 */
	@Test
	public void testRemoveGradeAtIndex1ShouldReturn2Grades() {
		ArrayList<Grade> grades = this.composite.getGrades();
		Grade grade1 = grades.get(0);
		Grade grade2 = grades.get(2);
		this.composite.removeGrade(1);
		grades = this.composite.getGrades();
		assertEquals(2, grades.size());
		assertEquals(grade1, grades.get(0));
		assertEquals(grade2, grades.get(1));
	}
}
