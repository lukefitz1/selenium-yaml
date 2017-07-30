package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.TestNG;
import org.testng.annotations.BeforeSuite;
import org.testng.xml.XmlSuite;
import org.yaml.snakeyaml.Yaml;

public class Config {

	static String base_url;
	static String base_tests_file;
	static String client_tests_file;
	
	public void loadYaml() throws IOException {
		System.out.println("Hello world from config file");
				
		InputStream input = new FileInputStream(new File("/Users/Luke/Documents/workspace/selenium-yaml/client_config.yaml"));
		Yaml yaml = new Yaml();
		
		String client = System.getProperty("client");
		String env = System.getProperty("env");
		
		//Search client config file for client, create object for that client
		@SuppressWarnings("unchecked")
		Map<String, String> object = (Map<String, String>) yaml.load(input);
		Object val = object.get(client);
		
		//Search client object for env, create env object 
		@SuppressWarnings("unchecked")
		Map<String, String> obj = (Map<String, String>) val;	
		Object val1 = obj.get(env);
		
		//Search env object for base url, create object for url
		@SuppressWarnings("unchecked")
		Map<String, String> obj1 = (Map<String, String>) val1;		
		Object val2 = obj1.get("url");	
		
		//Search client object for tests, create tests object 
		@SuppressWarnings("unchecked")
		Map<String, String> tests_map = (Map<String, String>) val;	
		Object tests_obj =  tests_map.get("tests");
		
		//Search client object for tests, create tests object 
		@SuppressWarnings("unchecked")
		Map<String, String> tests_map1 = (Map<String, String>) tests_obj;	
		Object base_tests = tests_map1.get("base_tests");
		Object client_tests = tests_map1.get("client_tests");
		
		base_tests_file = (String) base_tests;
		client_tests_file = (String) client_tests;
		base_url = (String) val2;
		
		System.out.println(base_tests_file);
		System.out.println(client_tests_file);
		System.out.println(base_url);
		
		input.close();
        
	}
}
