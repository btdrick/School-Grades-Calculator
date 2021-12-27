package edu.westga.cs.schoolgrades.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *  This class tests the functionality of
 *  SimpleGrade setValue method.
 * 
 * @author Brandon Drick
 * @version 11/07/2021
 */
public class TestSimpleGradeSetValue {

	private SimpleGrade simpleGrade;
	
	/**
	 * Creates a SimpleGrade holding value of 100
	 * before each test.
	 * 
	 * @throws Exception
	 */
	@BeforeEach
	public void setUp() throws Exception {
		this.simpleGrade = new SimpleGrade(100);
	}
	
	/**
	 * Tests that a grade value cannot
	 * be set to a negative number.
	 * 
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testCannotSetValueToNegativeNumber() {
		assertThrows(IllegalArgumentException.class, () -> {
			this.simpleGrade.setValue(-0.1);
		});
	}
	
	/**
	 * Tests that a grade value of 100
	 * can be set to 0.
	 */
	@Test
	public void testSetValueTo0ShouldReturn0() {
		this.simpleGrade.setValue(0);
		assertEquals(0.0, this.simpleGrade.getValue());
	}
	
	/**
	 * Tests that a grade value of 0
	 * can be set to 100.
	 */
	@Test
	public void testSetValueTo100ShouldReturn100() {
		this.simpleGrade.setValue(0);
		this.simpleGrade.setValue(100);
		assertEquals(100.0, this.simpleGrade.getValue());
	}
	
	/**
	 * Tests that a grade value of 100
	 * can be set to 51.4.
	 */
	@Test
	public void testSetValueTo51ShouldReturn51() {
		this.simpleGrade.setValue(51.4);
		assertEquals(51.4, this.simpleGrade.getValue());
	}
}
