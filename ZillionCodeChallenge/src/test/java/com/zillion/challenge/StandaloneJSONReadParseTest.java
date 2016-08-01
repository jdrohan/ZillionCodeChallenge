package com.zillion.challenge;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Iterator;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * Simple test to validate JSON Path
 * 
 * @author James Drohan
 *
 */
public class StandaloneJSONReadParseTest {
	public static void main(String[] args) throws MalformedURLException, IOException {
		
		// Use sample string to verify path
		String nasaJson = "{\"count\": 1, \"results\": [{\"category\": \"aeronautics\", \"client_record_id\": \"patent_MSC-24506-1\", \"center\": \"JSC\", \"eRelations\": [], \"reference_number\": \"MSC-24506-1\", \"expiration_date\": \"\", \"abstract\": \"Methods and systems for converting an image contrast evolution of an object to a temperature contrast evolution and vice versa are disclosed, including methods for assessing an emissivity of the object; calculating an afterglow heat flux evolution; calculating a measurement region of interest temperature change; calculating a reference region of interest temperature change; calculating a reflection temperature change; calculating the image contrast evolution or the temperature contrast evolution; and converting the image contrast evolution to the temperature contrast evolution or vice versa, respectively.\", \"title\": \"Methods and Systems for Measurement and Estimation of Normalized Contrast in Infrared Thermography\", \"innovator\": [{\"lname\": \"Koshti\", \"mname\": \"M.\", \"company\": \"NASA Johnson Space Center\", \"order\": \"1\", \"fname\": \"Ajay\"}], \"contact\": {\"phone\": \"(281) 483-3809\", \"facility\": \"NASA Johnson Space Center\", \"email\": \"jsc-techtran@mail.nasa.gov\", \"office\": \"Technology Transfer and Commercialization Office (TTO)\", \"address\": \"2101 NASA Parkway, Houston, Texas 77058\"}, \"publication\": null, \"concepts\": {\"1\": \"Thermal radiation\", \"0\": \"Thermodynamics\", \"3\": \"Relativistic heat conduction\", \"2\": \"Heat transfer\"}, \"serial_number\": \"12/971919\", \"_id\": \"53f657735904da2c9fc2fea9\", \"patent_number\": \"0\", \"id\": \"patent_MSC-24506-1\", \"trl\": \"7 - Demonstration in a space environment\"}]}";
		
		System.out.println("JSON PARSE");
        // create an ObjectMapper instance.
        ObjectMapper mapper = new ObjectMapper();
        // use the ObjectMapper to read the json string and create a tree
        JsonNode node = mapper.readTree(nasaJson);
 
        // ------ Inner iterator required because many Innovators are applicable to patent
        Iterator<JsonNode> patents = node.path("results").iterator();
        while (patents.hasNext()) {
        	Iterator<JsonNode> innovator = patents.next().path("innovator").iterator();
        	while (innovator.hasNext()) {
        		System.out.println(innovator.next().path("lname"));
        	}
        }
        
       System.out.println("DONE");
	}

}
