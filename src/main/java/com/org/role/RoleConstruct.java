/**
 * This is a package contains code for role creation.
 * @Role - aws cdk construct
 * 
 * @author vinay.v
 */

package com.org.role;

import java.util.ArrayList;
import java.util.List;

import com.org.util.JsonUtil;

import software.amazon.awscdk.core.CfnOutput;
import software.amazon.awscdk.core.CfnOutputProps;
import software.amazon.awscdk.core.Construct;
import software.amazon.awscdk.services.iam.Role;
import software.amazon.awscdk.services.iam.ServicePrincipal;

public class RoleConstruct extends Construct{

	public RoleConstruct(Construct scope, String id, final RoleConstructProps props) {
		super(scope, id);
		List<String> service_names = new ArrayList<String>();
		Role role = null;
		
		// Creates Role for weekend automation
		service_names = props.getAwsServiceName();
		if(service_names.isEmpty()) {
			System.out.println("No service name found to assume a role");
			System.exit(1);
		}else {
			for(int i = 0 ; i < service_names.size() ; i ++) {
				role = Role.Builder.create(this, props.getRoleName()+i)
						   .assumedBy(new ServicePrincipal(service_names.get(i)))
						   .roleName(props.getRoleName())
						   .build();
			
				List<String> roles = new ArrayList<String>();
				roles.add(role.getRoleName());
					
					
				//Policy Creation
				if(JsonUtil.getRolePolicies().length() != 0) {
					for(int j = 0 ; j < JsonUtil.getRolePolicies().length() ; j++) {
						String policyName = JsonUtil.getRolePolicies().getString(j);
							new PolicyConstruct(this, policyName, PolicyConstructProps
								.builder()
								.policyName(policyName)
								.role(role)
								.roleName(props.getRoleName())
								.build());
							}
						}else {
							System.out.println("No policy found for the role "+props.getRoleName());
						}
					}
				}
		
			//Output RoleName and RoleArn
			new CfnOutput(this, "Role-name" + props.getRoleName(),
					CfnOutputProps.builder()
					.description("output Role name")
					.value(props.getRoleName())
					.exportName(props.getRoleName().replace("_", "-")).build());
			
			new CfnOutput(this, "Role-arn" + props.getRoleName(),
					CfnOutputProps.builder()
					.description("output Role arn")
					.value(role.getRoleArn())
					.exportName("roleArn-"+props.getRoleName().replace("_", "-"))
					.build());
		}
}