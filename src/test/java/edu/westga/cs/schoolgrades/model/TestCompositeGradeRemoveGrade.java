package edu.westga.cs.schoolgrades.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class tests the functionality of
 * {@link CompositeGrade} removeGrade method.
 * 
 * @author Brandon Drick
 * @version 11/07/2021
 */
public class TestCompositeGradeRemoveGrade {
	private CompositeGrade compositeGrade;
	private ArrayList<Grade> theGrades;
	
	/**
	 * Creates a CompositeGrade with {@link SumOfGradesStrategy}
	 * before each test.
	 * @throws Exception
	 */
	@BeforeEach
	public void setUp() throws Exception {
		this.compositeGrade = new CompositeGrade(new SumOfGradesStrategy());
		Grade grade1 = new SimpleGrade(100);
		Grade grade2 = new SimpleGrade(88);
		Grade grade3 = new WeightedGrade(grade2, 0.8);
		this.theGrades = new ArrayList<Grade>(Arrays.asList(grade1, grade2, grade3));
	}
	
	/**
	 * Test that a null Grade cannot be removed.
	 */
	@Test
	public void testCannotRemoveNullGrade() {
		assertThrows(IllegalArgumentException.class, () -> {
			this.compositeGrade.removeGrade(null);
		});
	}

	/**
	 * Test that an empty list cannot have a Grade removed.
	 */
	@Test
	public void testCannotRemoveFromEmptyList() {
		Grade grade = new SimpleGrade(100);
		assertThrows(IllegalArgumentException.class, () -> {
			this.compositeGrade.removeGrade(grade);
		});
	}
	
	/**
	 * Test that a Grade not present cannot be removed.
	 */
	@Test
	public void testCannotRemoveNonexistingGrade() {
		Grade grade = new SimpleGrade(100);
		this.compositeGrade.addAll(this.theGrades);
		assertThrows(IllegalArgumentException.class, () -> {
			this.compositeGrade.removeGrade(grade);
		});
	}
	
	/**
	 * Test that an invalid index of -1 cannot be removed
	 */
	@Test
	public void testCannotRemoveNegativeIndex() {
		this.compositeGrade.addAll(this.theGrades);
		assertThrows(IllegalArgumentException.class, () -> {
			this.compositeGrade.removeGrade(-1);
		});
	}
	
	/**
	 * Test that an invalid index of 3 cannot be removed
	 * from list of 3 Grades.
	 */
	@Test
	public void testCannotRemoveOutOfBoundsIndex() {
		this.compositeGrade.addAll(this.theGrades);
		assertThrows(IllegalArgumentException.class, () -> {
			this.compositeGrade.removeGrade(3);
		});
	}
	
	/**
	 * Test that Grade is removed from a list.
	 */
	@Test
	public void testRemoveGradeAtIndex1ShouldReturn2Grades() {
		this.compositeGrade.addAll(this.theGrades);
		ArrayList<Grade> grades = this.compositeGrade.getGrades();
		Grade grade1 = grades.get(0);
		Grade grade2 = grades.get(2);
		this.compositeGrade.removeGrade(1);
		grades = this.compositeGrade.getGrades();
		assertEquals(2, grades.size());
		assertEquals(grade1, grades.get(0));
		assertEquals(grade2, grades.get(1));
	}
}
