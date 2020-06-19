/**
 * This package contains Stack implementation to create lambda resource on aws
 * @CfnSecurityGroup - Clouformation resources. 
 * @LambdaConstruct - Custom constructor
 * 
 * @author vinay.v
 */


package com.org.stack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;

import com.org.util.JsonUtil;

import software.amazon.awscdk.core.Construct;
import software.amazon.awscdk.core.Fn;
import software.amazon.awscdk.core.Stack;
import software.amazon.awscdk.core.StackProps;
import software.amazon.awscdk.services.ec2.CfnSecurityGroup;
import software.amazon.awscdk.services.ec2.CfnSecurityGroup.IngressProperty;
import software.amazon.awscdk.services.ec2.CfnSecurityGroupProps;
import software.amazon.awscdk.services.lambda.CfnFunction.CodeProperty;
import software.amazon.awscdk.services.lambda.CfnFunction.EnvironmentProperty;
import software.amazon.awscdk.services.lambda.CfnFunction.VpcConfigProperty;
import software.amazon.awscdk.services.s3.assets.Asset;
import software.amazon.awscdk.services.s3.assets.AssetProps;

public class LambdaStack extends Stack {
    public LambdaStack(final Construct parent, final String id) {
        this(parent, id, null);
    }

	public LambdaStack(final Construct parent, final String id, final StackProps props) {
        super(parent, id, props);
        
        //defining string list to store all subnet ids imported from vpc stack
        List<String> subnetIds = new ArrayList<String>();
        
        //import private subnet ids from vpc stack
        for ( int i = 0 ; i < getAvailabilityZones().size() ; i++ ) {
        	subnetIds.add(Fn.importValue("private-subnet-"+i));
        }

        //import vpcId from vpc stack
        String vpcId = Fn.importValue("vpcId");
        
        //lambda code to put in assets bucket and upload to lambda function
        Asset lambdaCodeStore = new Asset(this, "lambdaCode", AssetProps.builder()
        		.path(getStackName())
        		.build());
        
        
        //defining list to store all the ingress rules
        List <Object> ingressList = new ArrayList<Object> ();
        JSONArray securityGroups = JsonUtil.getSecurityGroups();
        JSONArray lambdConfig = JsonUtil.getLambdaConfig();
        
        //iterate the security groups from the config file
        for( int i = 0 ; i < securityGroups.length() ; i++ ) {
        	//create ingress rules
            IngressProperty ingressRules = IngressProperty.builder()
            		.cidrIp(securityGroups.getJSONObject(i).getString("source_cidr"))
            		.toPort(securityGroups.getJSONObject(i).getInt("to_port"))
            		.fromPort(securityGroups.getJSONObject(i).getInt("from_port"))
            		.ipProtocol(securityGroups.getJSONObject(i).getString("protocol"))
            		.description(securityGroups.getJSONObject(i).getString("description"))
            		.build();
            
            //add the ingress rules to the list
            ingressList.add(ingressRules);
            
        }
        
        //create security groups
        CfnSecurityGroup createSecurityGroup = new CfnSecurityGroup(this, "secuirtyGroups", CfnSecurityGroupProps.builder()
        		.groupName("lambda-security-group-"+getStackName())
        		.groupDescription("security group to manage lamda inbound and outbound")
        		.securityGroupIngress(ingressList)
        		.vpcId(vpcId)
        		.build());
        
        List<String> securityGroupIds = new ArrayList<String>();
        securityGroupIds.add(createSecurityGroup.getRef());
        
        //vpc config for lambda
        VpcConfigProperty lambdaVpcConfigProp = VpcConfigProperty.builder()
        		.subnetIds(subnetIds)
        		.securityGroupIds(securityGroupIds)
        		.build();
		
        //defining the code location for lambda
        CodeProperty lambdaCodeDefinition = CodeProperty.builder()
        		.s3Bucket(lambdaCodeStore.getS3BucketName())
        		.s3Key(lambdaCodeStore.getS3ObjectKey())
        		.build();
        
        //iterate over function_name objects 
        for ( int i = 0 ; i < lambdConfig.length() ; i++ ) {
        	Map<String,String> environmentProps = new HashMap<String, String> ();
        	
        	//defines environment variable port and the port number in the lambda function
        	try {
        		environmentProps.put(JsonUtil.getJsonObject().getString("envVariablePort"), JsonUtil.getJsonObject().getString("envVariableValuePort"));
        	} catch (Exception e) {
        		System.out.println("json object not found!");
        	}
        	
        	//defines environment variable host and hostname in the lambda function
        	try {
        		environmentProps.put(JsonUtil.getJsonObject().getString("envVariableHubHost"), JsonUtil.getJsonObject().getString("envVariableValueHubHost"));
        	} catch (Exception e) {
        		System.out.println("json object not found!");
        	}
        	
        	//defines environment variable context_path and sets the context path in the lambda function
        	try {
        		environmentProps.put("context_path", lambdConfig.getJSONObject(i).getString("context_path"));
        	} catch (Exception e) {
        		System.out.println("json object not found!");
        	}
        	
        	//defines the functionName 
        	String functionName = null;
        	try {
        		functionName = lambdConfig.getJSONObject(i).getString("function_name");
        	} catch (Exception e) {
        		System.out.println("json object not found!");
        	}
        	
        	//defines the function description
        	String functionDescription = null;
        	try {
        		functionDescription = JsonUtil.getJsonObject().getString("description");
        	} catch (Exception e) {
        		System.out.println("json object not found!");
        	}
        	
        	//defines the function handler
        	String functionHandler = null;
        	try {
        		functionHandler = JsonUtil.getJsonObject().getString("codeHandler");
        	} catch (Exception e) {
        		System.out.println("json object not found!");
        	}
        	
        	//defines the function role
        	String functionRole = null;
        	try {
        		functionRole = JsonUtil.getRoleArn("us-west-1");
        	} catch (Exception e) {
        		System.out.println("json object not found!");
        	}
        	
        	//defines the function runtime
        	String functionRuntime = null;
        	try {
        		functionRuntime = JsonUtil.getJsonObject().getString("lambda_runtime");
        	} catch (Exception e) {
        		System.out.println("json object not found!");
        	}
        	
        	//defines the function timeout
        	String functionTimeout = null;
        	try {
        		functionTimeout = JsonUtil.getJsonObject().getString("lambdaTimeout");
        	} catch (Exception e) {
        		System.out.println("json object not found!");
        	}
        	
        	//construct used to translate environment prop map to cloudformation
        	EnvironmentProperty lambdaEnvironmentProps = EnvironmentProperty.builder()
            		.variables(environmentProps)
            		.build();
        	
        	//construct to create lambda function
        	new LambdaConstruct(this, "lambdaConstruct"+i, LambdaConstructProps.builder()
        			.lambdaFunctionNameProperties(functionName)
        			.lambdaDescriptionProperties(functionDescription)
        			.lambdaHandlerProperties(functionHandler)
        			.lambdaRoleProperties(functionRole)
        			.lambdaRuntimeProperties(functionRuntime)
        			.lambdaTimeoutProperties(functionTimeout)
            		.lambdaCodeDefinition(lambdaCodeDefinition)
            		.lambdaEnvironmentProperties(lambdaEnvironmentProps)
            		.lambdaVpcConfigProperties(lambdaVpcConfigProp)
            		.lambdaQueueArnProperties(Fn.importValue(functionName+"-arn"))
            		.build());
        	
        }
    }
}
 