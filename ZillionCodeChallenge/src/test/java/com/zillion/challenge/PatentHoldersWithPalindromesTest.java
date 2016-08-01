package com.zillion.challenge;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class PatentHoldersWithPalindromesTest {
	private MockMvc mockMvc;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(getPatentHoldersWithPalindromesCountController).build();
	}
	
    @InjectMocks
    private GetPatentHoldersWithPalindromesCountController getPatentHoldersWithPalindromesCountController;
     
	@Test
	public void test() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/palindromes?search=temperature&limit=1"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"));
	}
	
}
