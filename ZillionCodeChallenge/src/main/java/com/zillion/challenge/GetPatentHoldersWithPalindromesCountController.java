package com.zillion.challenge;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * GetPatentHoldersWithPalindromesCountController, 
 * RESTFul Spring Controller used to query NASA Patent API
 * 
 * @param search - query string for searching Patents
 * @param limit  - number of returned matches
 * 
 * @author James Drohan
 *
 */
@RestController
public class GetPatentHoldersWithPalindromesCountController {

    @RequestMapping("/palindromes")
    public List<PatentHolder> patentHoldersWithPalindromesCount(@RequestParam(value="search") String search,
    																@RequestParam(value="limit", defaultValue="1") int limit) 
    																		throws MalformedURLException, IOException {
    	
    	List<PatentHolder> patentHolders = new ArrayList<PatentHolder>();
    	
    	// URL to NASA Patent lookup
    	String urlCall = "https://api.nasa.gov/patents/content?query=" + search +
    						"&limit=" + limit + "&api_key=DEMO_KEY";
		
        // create an ObjectMapper instance.
        ObjectMapper mapper = new ObjectMapper();
        // use the ObjectMapper to read the json string and create a tree
        JsonNode node = mapper.readTree(new URL(urlCall)); 
        
        // ------ Inner iterator required because many Innovators are applicable to patent
        Iterator<JsonNode> patents = node.path("results").iterator();
        while (patents.hasNext()) {
        	Iterator<JsonNode> innovators = patents.next().path("innovator").iterator();
        	while (innovators.hasNext()) {
        		JsonNode innovator = innovators.next();
        		String name = innovator.path("fname") + " " + innovator.path("lname");
        		name = name.replaceAll("\"","");
        		// Create PatentHolder Object
        		PatentHolder patentHolder = new PatentHolder(name);
        		// Calculate the Palindrome permutations
        		patentHolder.setCount(patentHolder.calculatePalindromeCount());
        		patentHolders.add(patentHolder);
        	}
        }
    	
    	Collections.sort(patentHolders);
    	
    	return patentHolders;
    }
}