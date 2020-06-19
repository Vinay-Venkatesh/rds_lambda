/**
 * This is a package contains code for Policy creation.
 * @Policy - aws cdk construct
 * 
 * @author vinay.v
 */


package com.org.role;

import java.util.ArrayList;
import java.util.List;
import software.amazon.awscdk.core.Construct;
import software.amazon.awscdk.services.iam.Effect;
import software.amazon.awscdk.services.iam.IRole;
import software.amazon.awscdk.services.iam.Policy;
import software.amazon.awscdk.services.iam.PolicyStatement;
import software.amazon.awscdk.services.iam.Role;

public class PolicyConstruct extends Construct{
	
	public PolicyConstruct(final Construct parent, final String name, final PolicyConstructProps props) {
		super(parent, name);
		
		List<PolicyStatement> Lambda_policyStatement = new ArrayList<PolicyStatement>();

		//Default 
		Effect effect = Effect.ALLOW;
		String policyName = props.getPolicyName();
		switch(policyName) {
			
			case  "lambda_policy":
				PolicyStatement lambdaPolicy = new PolicyStatement();
				lambdaPolicy.addActions("lambda:InvokeFunction");
				lambdaPolicy.setEffect(effect);
				lambdaPolicy.addAllResources();
				
				Lambda_policyStatement.add(lambdaPolicy);

				break;
				
			case  "lambda_vpc_policy":
				PolicyStatement lambda_vpc_policy = new PolicyStatement();
				lambda_vpc_policy.addActions("ec2:CreateNetworkInterface",
						"ec2:DescribeNetworkInterfaces",
						"ec2:DeleteNetworkInterface",
						"ec2:DescribeSecurityGroups",
						"ec2:DescribeSubnets",
						"ec2:DescribeVpcs");
				lambda_vpc_policy.setEffect(effect);
				lambda_vpc_policy.addAllResources();
				
				Lambda_policyStatement.add(lambda_vpc_policy);

				break;
				
			case  "lambda_cloudwatch_policy":
				PolicyStatement cloudwatch_policy = new PolicyStatement();
				cloudwatch_policy.addActions("logs:ListTagsLogGroup",
						"logs:GetLogRecord",
						"logs:DeleteSubscriptionFilter",
						"logs:DescribeLogStreams",
						"logs:DescribeSubscriptionFilters",
						"logs:StartQuery",
						"logs:DescribeMetricFilters",
						"logs:DeleteLogStream",
						"logs:GetLogDelivery",
						"logs:ListLogDeliveries",
						"logs:CreateExportTask",
						"logs:DeleteResourcePolicy",
						"logs:CreateLogStream",
						"logs:DeleteMetricFilter",
						"logs:TagLogGroup",
						"logs:CancelExportTask",
						"logs:DeleteRetentionPolicy",
						"logs:GetLogEvents",
						"logs:DeleteLogDelivery",
						"logs:AssociateKmsKey",
						"logs:FilterLogEvents",
						"logs:PutDestination",
						"logs:DescribeResourcePolicies",
						"logs:DescribeDestinations",
						"logs:DescribeQueries",
						"logs:DisassociateKmsKey",
						"logs:UntagLogGroup",
						"logs:DescribeLogGroups",
						"logs:DeleteLogGroup",
						"logs:PutDestinationPolicy",
						"logs:StopQuery",
						"logs:TestMetricFilter",
						"logs:DeleteDestination",
						"logs:PutLogEvents",
						"logs:CreateLogGroup",
						"logs:PutMetricFilter",
						"logs:CreateLogDelivery",
						"logs:PutResourcePolicy",
						"logs:DescribeExportTasks",
						"logs:GetQueryResults",
						"logs:UpdateLogDelivery",
						"logs:PutSubscriptionFilter",
						"logs:PutRetentionPolicy",
						"logs:GetLogGroupFields");
				cloudwatch_policy.setEffect(effect);
				cloudwatch_policy.addAllResources();
				
				Lambda_policyStatement.add(cloudwatch_policy);

				break;
				
			default:
				System.out.println("No policy definition for "+policyName);
				break;
				
		}
		List<IRole> listRoles = new ArrayList<IRole>();
		props.getRoleObject();
		IRole roleArn = Role.fromRoleArn(this, props.getRoleName(), props.getRoleObject().getRoleArn());
		
		listRoles.add(roleArn);
		
		if(props.getRoleName().equals("lambdaRole")) {
			Policy.Builder.create(this, props.getPolicyName())
			.policyName(policyName)
			.statements(Lambda_policyStatement)
			.roles(listRoles)
			.build();
		}else {
			System.out.println("No policy for given roleName "+props.getRoleName());
		}
		
	}
	
}
