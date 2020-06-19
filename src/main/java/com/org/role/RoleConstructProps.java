/**
 * This is a package contains Role constructor properties
 * 
 * @author vinay.v
 */

package com.org.role;

import java.util.List;
import java.util.Map;

public class RoleConstructProps {
	private String roleName;
	private Map<String,Object> assumePolicyDocument;
	private String stackName;
	private List<String> awsServiceName;
	
	public List<String> getAwsServiceName() {
		return awsServiceName;
	}

	public void setAwsServiceName(List<String> awsServiceName) {
		this.awsServiceName = awsServiceName;
	}

	public String getStackName() {
		return stackName;
	}

	public void setStackName(String stackName) {
		this.stackName = stackName;
	}

	public Map<String, Object> getAssumePolicyDocument() {
		return assumePolicyDocument;
	}

	public void setAssumePolicyDocument(Map<String, Object> assumePolicyDocument) {
		this.assumePolicyDocument = assumePolicyDocument;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	public static RoleConstructPropsBuilder builder() {
        return new RoleConstructPropsBuilder();
    }
	
	public static final class RoleConstructPropsBuilder{
		private String roleName;
		private Map<String,Object> assumePolicyDocument;
		private String stackName;
		private List<String> awsServiceName;

		private RoleConstructPropsBuilder() {
			
		}
		
		public static RoleConstructPropsBuilder RoleConstructor() {
			return new RoleConstructPropsBuilder();
		}
		
		public RoleConstructPropsBuilder roleName(String roleName) {
			this.roleName = roleName;
			return this;
		}
		
		public RoleConstructPropsBuilder assumePolicyDocument(Map<String,Object> assumePolicyDocument) {
			this.assumePolicyDocument = assumePolicyDocument;
			return this;
		}
		
		public RoleConstructPropsBuilder stackName(String stackName) {
			this.stackName = stackName;
			return this;
		}
		
		public RoleConstructPropsBuilder awsServiceName(List<String> awsServiceName) {
			this.awsServiceName = awsServiceName;
			return this;
		}
		
		public RoleConstructProps build() {
			RoleConstructProps roleConstructProps = new RoleConstructProps();
			roleConstructProps.setAssumePolicyDocument(assumePolicyDocument);
			roleConstructProps.setRoleName(roleName);
			roleConstructProps.setStackName(stackName);
			roleConstructProps.setAwsServiceName(awsServiceName);
			return roleConstructProps;
		}
		
	}
}
