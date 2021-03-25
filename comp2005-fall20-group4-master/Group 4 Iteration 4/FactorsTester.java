import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

class FactorsTester {

	@Test
	void testPerfect1()
	{	
		// TEST 1: should throw the exception because the parameter value is less than 1
		assertThrows(IllegalArgumentException.class, () -> FactorsUtility.perfect(0));
	}
	
	@Test
	void testPerfect2()
	{	
		// TEST 2: should succeed because 1 is a valid parameter value, but is not a perfect number
		assertFalse(FactorsUtility.perfect(1));
	}
	
	@Test
	void testPerfect3()
	{	
		// TEST 3: should succeed because 6 is a valid parameter value, and is a perfect number
		assertTrue(FactorsUtility.perfect(6));
	}
	
	@Test
	void testPerfect4()
	{	
		// TEST 4: should succeed because 7 is a valid parameter value, but is not a perfect number
		// I've coded this using assertEquals to show that there's often more than one way to write a test 
		boolean expected = false;
		assertEquals(expected, FactorsUtility.perfect(7));
	}
	@Test
	void testgetFactor1()
	{	ArrayList<Integer> arr=new ArrayList<Integer>();
		arr.add(1);
		assertEquals(arr, FactorsUtility.getFactors(2));
	}
	@Test
	void testgetFactor2()
	{	ArrayList<Integer> arr=new ArrayList<Integer>();
		assertEquals(arr, FactorsUtility.getFactors(1));
	}
	@Test
	void testgetFactor3()
	{	ArrayList<Integer> arr=new ArrayList<Integer>();
		assertEquals(arr, FactorsUtility.getFactors(0));
	}
	@Test
	void testgetFactor4()
	{	
		assertThrows(IllegalArgumentException.class, () -> FactorsUtility.getFactors(-1));
	}
	@Test
	void testgetFactor5()
	{	ArrayList<Integer> arr=new ArrayList<Integer>();
		arr.add(1);arr.add(2);arr.add(3);arr.add(4);arr.add(6); // adding 1,2,3,4,6 in one line to save space
		assertEquals(arr, FactorsUtility.getFactors(12));
	}
	@Test
	void testFactor1()
	{
		assertThrows(IllegalArgumentException.class, () -> FactorsUtility.factor(-1, -2));
	}
	@Test
	void testFactor2()
	{
		assertEquals(true, FactorsUtility.factor(4, 2));
	}
	@Test
	void testFactor3()
	{
		assertThrows(IllegalArgumentException.class, () -> FactorsUtility.factor(4, -2));
	}
	@Test
	void testFactor4()
	{
		assertThrows(IllegalArgumentException.class, () -> FactorsUtility.factor(-4, 2));
	}
	@Test
	void testFactor5()
	{
		assertEquals(false, FactorsUtility.factor(6, 5));
	}
	

}
