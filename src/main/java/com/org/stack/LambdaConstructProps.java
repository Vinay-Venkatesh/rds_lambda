/**
 * This package contains custom construct properties for lambda.
 * 
 * 
 */

package com.org.stack;

import software.amazon.awscdk.services.lambda.CfnFunction.CodeProperty;
import software.amazon.awscdk.services.lambda.CfnFunction.EnvironmentProperty;
import software.amazon.awscdk.services.lambda.CfnFunction.VpcConfigProperty;

public class LambdaConstructProps {
	private String functionName;
	private String lambdaHandler;
	private String description;
	private String lambdaRole;
	private String lambdaRuntime;
	private String lambdaTimeOut;
	private String queueArn;
	private CodeProperty lambdaCodeDefinition;
	private EnvironmentProperty lambdaEnvironmentProps;
	private VpcConfigProperty lambdaVpcConfigProp;
	

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	
	public String getQueueArn() {
		return queueArn;
	}

	public void setQueueArn(String queueArn) {
		this.queueArn = queueArn;
	}

	public String getLambdaHandler() {
		return lambdaHandler;
	}

	public void setLambdaHandler(String lambdaHandler) {
		this.lambdaHandler = lambdaHandler;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLambdaRole() {
		return lambdaRole;
	}

	public void setLambdaRole(String lambdaRole) {
		this.lambdaRole = lambdaRole;
	}

	public String getLambdaRuntime() {
		return lambdaRuntime;
	}

	public void setLambdaRuntime(String lambdaRuntime) {
		this.lambdaRuntime = lambdaRuntime;
	}

	public String getLambdaTimeOut() {
		return lambdaTimeOut;
	}

	public void setLambdaTimeOut(String lambdaTimeOut) {
		this.lambdaTimeOut = lambdaTimeOut;
	}

	public CodeProperty getLambdaCodeDefinition() {
		return lambdaCodeDefinition;
	}

	public void setLambdaCodeDefinition(CodeProperty lambdaCodeDefinition) {
		this.lambdaCodeDefinition = lambdaCodeDefinition;
	}

	public EnvironmentProperty getLambdaEnvironmentProps() {
		return lambdaEnvironmentProps;
	}

	public void setLambdaEnvironmentProps(EnvironmentProperty lambdaEnvironmentProps) {
		this.lambdaEnvironmentProps = lambdaEnvironmentProps;
	}

	public VpcConfigProperty getLambdaVpcConfigProp() {
		return lambdaVpcConfigProp;
	}

	public void setLambdaVpcConfigProp(VpcConfigProperty lambdaVpcConfigProp) {
		this.lambdaVpcConfigProp = lambdaVpcConfigProp;
	}

	public static LambdaConstructPropsBuilder builder() {
        return new LambdaConstructPropsBuilder();
    }
	
	public static final class LambdaConstructPropsBuilder {
		private String functionNameProp;
		private String lambdaHandlerProp;
		private String lambdaDescriptionProp;
		private String lambdaRoleProp;
		private String lambdaRuntimeProp;
		private String lambdaTimeoutProp;
		private String queueArn;
		private CodeProperty lambdaCodeDefinition;
		private EnvironmentProperty lambdaEnvironmentProp;
		private VpcConfigProperty lambdaVpcConfigProp;
		
		private LambdaConstructPropsBuilder() {
			
		}
		
		public static LambdaConstructPropsBuilder aLambdaConstructProps() {
            return new LambdaConstructPropsBuilder();
        }
		
		public LambdaConstructPropsBuilder lambdaCodeDefinition(CodeProperty lambdaCodeDefinition) {
           this.lambdaCodeDefinition = lambdaCodeDefinition;
           return this;
        }
		
		public LambdaConstructPropsBuilder lambdaEnvironmentProperties(EnvironmentProperty lambdaEnvironmentProp) {
			this.lambdaEnvironmentProp = lambdaEnvironmentProp;
			return this;
	    }
		
		public LambdaConstructPropsBuilder lambdaVpcConfigProperties(VpcConfigProperty lambdaVpcConfigProp) {
			this.lambdaVpcConfigProp = lambdaVpcConfigProp;
			return this;
	    }
		
		public LambdaConstructPropsBuilder lambdaFunctionNameProperties(String functionNameProp) {
			this.functionNameProp = functionNameProp;
			return this;
	    }
		
		public LambdaConstructPropsBuilder lambdaHandlerProperties(String lambdaHandlerProp) {
			this.lambdaHandlerProp = lambdaHandlerProp;
			return this;
	    }
		
		public LambdaConstructPropsBuilder lambdaDescriptionProperties(String lambdaDescriptionProp) {
			this.lambdaDescriptionProp = lambdaDescriptionProp;
			return this;
	    }
		
		public LambdaConstructPropsBuilder lambdaRoleProperties(String lambdaRoleProp) {
			this.lambdaRoleProp = lambdaRoleProp;
			return this;
	    }
		
		public LambdaConstructPropsBuilder lambdaRuntimeProperties(String lambdaRuntimeProp) {
			this.lambdaRuntimeProp = lambdaRuntimeProp;
			return this;
	    }
		
		public LambdaConstructPropsBuilder lambdaTimeoutProperties(String lambdaTimeoutProp) {
			this.lambdaTimeoutProp = lambdaTimeoutProp;
			return this;
	    }
		
		public LambdaConstructPropsBuilder lambdaQueueArnProperties(String queueArn) {
			this.queueArn = queueArn;
			return this;
	    }
		
		public LambdaConstructProps build() {
			LambdaConstructProps lambdaConstructProps = new LambdaConstructProps();
			lambdaConstructProps.setLambdaCodeDefinition(lambdaCodeDefinition);
			lambdaConstructProps.setLambdaEnvironmentProps(lambdaEnvironmentProp);
			lambdaConstructProps.setLambdaVpcConfigProp(lambdaVpcConfigProp);
			lambdaConstructProps.setFunctionName(functionNameProp);
			lambdaConstructProps.setLambdaHandler(lambdaHandlerProp);
			lambdaConstructProps.setDescription(lambdaDescriptionProp);
			lambdaConstructProps.setLambdaRole(lambdaRoleProp);
			lambdaConstructProps.setLambdaRuntime(lambdaRuntimeProp);
			lambdaConstructProps.setLambdaTimeOut(lambdaTimeoutProp);
			lambdaConstructProps.setQueueArn(queueArn);
			return lambdaConstructProps;
		}
	}
}
