/**
 * This is a package contains code for role creation.
 * @Methods : setStackName , getStackName , getJsonObject , getLambdaConfig getSecurityGroups getRolePolicies getRoleArn
 * @RoleConstruct - Custom construct
 * 
 * @author vinay.v
 */

package com.org.role;

import java.util.ArrayList;
import java.util.List;

import com.org.util.JsonUtil;

import software.amazon.awscdk.core.Construct;
import software.amazon.awscdk.core.Stack;
import software.amazon.awscdk.core.StackProps;

public class RoleStack extends Stack {
    public RoleStack(final Construct parent, final String id) {
        this(parent, id, null);
    }

	public RoleStack(final Construct parent, final String id, final StackProps props) {
        super(parent, id, props);
        
    	//Set StackName
        JsonUtil.setStackName(getStackName());
    	
        //Creating Role
        if(JsonUtil.getJsonObject().getString("role_name") != null) {
        	String roleName = JsonUtil.getJsonObject().getString("role_name");
			List<String> awsServiceName = new ArrayList<String>();
			awsServiceName.add(JsonUtil.getJsonObject().getString("service_name"));
			new RoleConstruct(this, "role-"+roleName, RoleConstructProps
				.builder()
				.roleName(roleName)
				.awsServiceName(awsServiceName)
				.stackName(getStackName())
				.build());
        }else {
        	System.out.println("No roles for the given stack");
        }
	}
}
 