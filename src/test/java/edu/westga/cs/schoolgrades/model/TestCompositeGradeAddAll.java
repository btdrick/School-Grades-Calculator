package edu.westga.cs.schoolgrades.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class tests the functionality of
 * {@link CompositeGrade} addAll method.
 * 
 * @author Brandon Drick
 * @version 11/07/2021
 */
public class TestCompositeGradeAddAll {
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
	 * Tests that null List of {@link Grade}
	 * objects cannot be added.
	 */
	@Test
	public void testCannotAddNullList() {
		assertThrows(IllegalArgumentException.class, () -> {
			this.compositeGrade.addAll(null);
		});
	}
	
	/**
	 * Tests that list of Grade objects can be added.
	 */
	@Test
	public void testAddGradeListShouldReturn3Objects() {
		this.compositeGrade.addAll(this.theGrades);
		ArrayList<Grade> grades = this.compositeGrade.getGrades();
		assertEquals(3, grades.size());
		for (int index = 0; index < 3; index++) {
			assertEquals(this.theGrades.get(index), grades.get(index));
		}
	}

}
