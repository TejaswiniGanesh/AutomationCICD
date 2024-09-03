package Utilities;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {
	
	public List<HashMap<String, String>> getJsonData() throws IOException
	{
		//convert json content  to string
		String jsoncontent = FileUtils.readFileToString(new File(System.getProperty("user.dir")+"\\src\\main\\java\\Utilities\\Submitorder.json"),StandardCharsets.UTF_8);
		
		//convert string to hashmap using Jackson Databind
		ObjectMapper mapper =  new ObjectMapper();
		List<HashMap<String,String>> Data = mapper.readValue(jsoncontent, new TypeReference<List<HashMap<String,String>>>(){});
		return Data;
		
		
	}

}
