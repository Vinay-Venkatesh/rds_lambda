/**
 * This is a package contains util to read json objects and get desired values
 * @Methods : setStackName , getStackName , getJsonObject , getLambdaConfig getSecurityGroups getRolePolicies getRoleArn
 * 
 * 
 * @author vinay.v
 */

package com.org.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.stream.Collectors;
import org.json.JSONArray;
import org.json.JSONObject;
import com.amazonaws.services.cloudformation.AmazonCloudFormation;
import com.amazonaws.services.cloudformation.AmazonCloudFormationClient;
import com.amazonaws.services.cloudformation.model.Export;
import com.amazonaws.services.cloudformation.model.ListExportsRequest;
import com.amazonaws.services.cloudformation.model.ListExportsResult;

public class JsonUtil {
	
		private static String stack_name = null;
		
		public static void setStackName(String stack_name) {
			 JsonUtil.stack_name = stack_name;
		}
		
		public static String getStackName() {
			return stack_name;
		}
	
		/**
	     * Function used to read the config file and return the parsed json
	     * @return JSONObject
	     */
	    public static JSONObject getJsonObject () {
	    	String cwd = System.getProperty("user.dir");
			BufferedReader bufferedReader = null;
			try {
				bufferedReader = new BufferedReader(new FileReader(cwd + "/config-" + getStackName() + ".json"));
			} catch (FileNotFoundException e) {
				System.out.println("Config file not found!" + getStackName());
			}
		    
			String configJson = bufferedReader.lines().collect(Collectors.joining());
			JSONObject configJsonObject = new JSONObject(configJson);
			return configJsonObject;
	    }
	    
	    
	    /**
	     * Function used to return json array by reading the config file prop lambdaConfig
	     * @return JSONArray
	     */
	    public static JSONArray getLambdaConfig () {
	    	return getJsonObject().getJSONArray("lambdaConfig");
	    }
	    
	    /**
	     * Function to return security group ingress rules
	     * @return
	     */
	    public static JSONArray getSecurityGroups () {
	    	return getJsonObject().getJSONArray("security_groups");
	    }
	    
	    /**
	     * Function to return security group ingress rules
	     * @return
	     */
	    public static JSONArray getRolePolicies () {
	    	return getJsonObject().getJSONArray("role_policies");
	    }
	    
	    /**
	     * Function to return role arn by using aws sdk
	     * @param get role arn
	     * @return String
	     */
	    public static String getRoleArn (String region) {
	    	//create the client connection to aws cloud formation by passing region code
	    	AmazonCloudFormation cloudFormationClient = AmazonCloudFormationClient.builder()
	    			.withRegion(region)
	    			.build();

	    	//listing the exports from all the stacks
	    	ListExportsResult exports = cloudFormationClient.listExports(new ListExportsRequest());
			
			for (Export export : exports.getExports()) {
				if (export.getName().equals("roleArn-lambdaRole")) {
					return export.getValue();
				}
			}
			
			//return as string if the export name is not found
			return "role arn not found in stack! please run iam stack before running lambda stack!";
	    }
	    
}
