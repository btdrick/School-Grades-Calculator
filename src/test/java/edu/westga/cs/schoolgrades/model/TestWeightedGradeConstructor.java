package edu.westga.cs.schoolgrades.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *  This class tests the functionality of
 *  WeightedGrade decorator.
 * 
 * @author Brandon Drick
 * @version 11/07/2021
 */
public class TestWeightedGradeConstructor {
	private Grade simpleGrade;
	
	/**
	 * Creates a SimpleGrade holding value of 100
	 * before each test.
	 * @throws Exception
	 */
	@BeforeEach
	public void setUp() throws Exception {
		this.simpleGrade = new SimpleGrade(100);
	}
	
	/**
	 * Tests that WeightedGrade with
	 * weight -0.1 cannot be created.
	 */
	@Test
	public void testCannotApplyNegativeWeight() {
		assertThrows(IllegalArgumentException.class, () -> {
			new WeightedGrade(this.simpleGrade, -0.1);
		});
	}
	
	/**
	 * Tests that WeightedGrade with 
	 * weight 1.1 cannot be created.
	 */
	@Test
	public void testCannotApplyInvalidWeight() {
		assertThrows(IllegalArgumentException.class, () -> {
			new WeightedGrade(this.simpleGrade, 1.1);
		});
	}
	
	/**
	 * Tests that a weight of 1.0
	 * does not change the grade.
	 */
	@Test
	public void testApplyWeightOf100ShouldReturn100() {
		assertEquals(100.0, new WeightedGrade(this.simpleGrade, 1.0).getValue());
	}

	/**
	 * Tests that a weight of 0.0
	 * clears the grade value.
	 */
	@Test
	public void testApplyWeightOf0ShouldReturn0() {
		assertEquals(0.0, new WeightedGrade(this.simpleGrade, 0.0).getValue());
	}
	
	/**
	 * Tests that a weight of 0.5
	 * returns value of 50
	 */
	@Test
	public void testApplyWeightOf50ShouldReturn50() {
		assertEquals(50.0, new WeightedGrade(this.simpleGrade, 0.5).getValue());
	}
	
	/**
	 * Tests that a weight of 0.65
	 * returns value of 65
	 */
	@Test
	public void testApplyWeightOf65ShouldReturn65() {
		assertEquals(65.0, new WeightedGrade(this.simpleGrade, 0.65).getValue());
	}
}
