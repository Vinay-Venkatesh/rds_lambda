/**
 * This is a package contains Policy constructor properties
 * 
 * @author vinay.v
 */

package com.org.role;

import java.util.Map;
import software.amazon.awscdk.services.iam.Role;

public class PolicyConstructProps {
	private String policyName;
	Map<String,Object> policyDocument = null;
	private String roleName;
	Role role = null;
	
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getPolicyName() {
		return policyName;
	}
	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}
	public Map<String, Object> getPolicyDocument() {
		return policyDocument;
	}
	public void setPolicyDocument(Map<String, Object> policyDocument) {
		this.policyDocument = policyDocument;
	}
	public Role getRoleObject() {
		return role;
	}
	public void setRoleObject(Role role) {
		this.role = role;
	}
	public static PolicyConstructPropsBuilder builder() {
        return new PolicyConstructPropsBuilder();
    }
	
	public static final class PolicyConstructPropsBuilder{
		private String policyName;
		Map<String, Object> policyDocument = null;
		private String roleName;
		Role role = null;
		
		
		private PolicyConstructPropsBuilder() {
			
		}
		
		public static PolicyConstructPropsBuilder PolicyConstructor() {
			return new PolicyConstructPropsBuilder();
		}
		
		public PolicyConstructPropsBuilder policyName(String policyName) {
			this.policyName = policyName;
			return this;
		}
		
		public PolicyConstructPropsBuilder role(Role role) {
			this.role = role;
			return this;
		}
		
		public PolicyConstructPropsBuilder roleName(String roleName) {
			this.roleName = roleName;
			return this;
		}

		
		public PolicyConstructPropsBuilder policyDocument(Map<String, Object> policyDocument) {
			this.policyDocument = policyDocument;
			return this;
		}
		
		public PolicyConstructProps build() {
			PolicyConstructProps policyConstructProps = new PolicyConstructProps();
			policyConstructProps.setPolicyDocument(policyDocument);
			policyConstructProps.setPolicyName(policyName);
			policyConstructProps.setRoleObject(role);
			policyConstructProps.setRoleName(roleName);
			return policyConstructProps;
		}
		
	}
}
