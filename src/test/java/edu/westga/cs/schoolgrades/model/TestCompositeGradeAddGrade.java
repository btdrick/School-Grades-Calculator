package edu.westga.cs.schoolgrades.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class tests the functionality of
 * {@link CompositeGrade} addGrade method.
 * 
 * @author Brandon Drick
 * @version 11/07/2021
 */
public class TestCompositeGradeAddGrade {
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
	 * Test that a null Grade cannot be added.
	 */
	@Test
	public void testCannotAddNullGrade() {
		assertThrows(IllegalArgumentException.class, () -> {
			this.compositeGrade.addGrade(null);
		});
	}

	/**
	 * Tests that CompositeGrade 
	 * adds a new {@link SimpleGrade}.
	 */
	@Test
	public void testCompositeGradeConstructorShouldReturnEmptyGradeList() {
		assertEquals("There are no grades", this.compositeGrade.toString());
	}
	
	/**
	 * Tests that a SimpleGrade
	 * can be added.
	 */
	@Test
	public void testAddSimpleGradeShouldAddGradeOf100() {
		this.compositeGrade.addGrade(this.theGrades.get(0));
		ArrayList<Grade> grades = this.compositeGrade.getGrades();
		assertEquals(this.theGrades.get(0), grades.get(0));
	}
	
	/**
	 * Tests that multiple Grade objects
	 * can be added.
	 */
	@Test
	public void testAddMultipleGradesShouldContain3() {
		for (int index = 0; index < 3; index++) {
			this.compositeGrade.addGrade(this.theGrades.get(index));
		}
		ArrayList<Grade> grades = this.compositeGrade.getGrades();
		assertEquals(3, grades.size());
		for (int index = 0; index < 3; index++) {
			assertEquals(this.theGrades.get(index), grades.get(index));
		}
	}
	
	/**
	 * Tests that a Grade can be 
	 * added to an index.
	 */
	@Test
	public void testAddGradeToIndexShouldReturnSameGradeForIndex1() {
		this.compositeGrade.addGrade(this.theGrades.get(0));
		this.compositeGrade.addGrade(this.theGrades.get(1), 1);
		ArrayList<Grade> grades = this.compositeGrade.getGrades();
		for (int index = 0; index < 2; index++) {
			assertEquals(this.theGrades.get(index), grades.get(index));
		}
	}
}
