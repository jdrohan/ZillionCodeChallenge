package com.zillion.challenge;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ZillionCodeChallengeApplicationTests {
	PatentHolder patentHolder = new PatentHolder("Nicola Tesla");

	//test to check palindrome count
	@Test
	public void testCalculatePalindromeCount() {
		int count = patentHolder.calculatePalindromeCount();
		assertEquals(531441, count, 0.0);
	}
	
	
}
