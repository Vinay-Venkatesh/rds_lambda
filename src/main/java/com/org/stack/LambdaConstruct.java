/**
 * This package contains custom construct for lambda function creation.
 * @CfnFunction, @CfnEventSourceMapping - Clouformation resources. 
 * 
 * 
 */



package com.org.stack;

import software.amazon.awscdk.core.Construct;
import software.amazon.awscdk.services.lambda.CfnEventSourceMapping;
import software.amazon.awscdk.services.lambda.CfnEventSourceMappingProps;
import software.amazon.awscdk.services.lambda.CfnFunction;
import software.amazon.awscdk.services.lambda.CfnFunctionProps;

public class LambdaConstruct extends Construct{
	public LambdaConstruct(final Construct parent, final String name, final LambdaConstructProps props) {
        super(parent, name);
        
        //create lambda function
		CfnFunction lambdaFunction = new CfnFunction(this, "lambdaFunction", CfnFunctionProps.builder()
        		.functionName(props.getFunctionName())
        		.description(props.getDescription())
        		.code(props.getLambdaCodeDefinition())
        		.handler(props.getLambdaHandler())
        		.role(props.getLambdaRole())
        		.runtime(props.getLambdaRuntime())
        		.timeout(Integer.parseInt(props.getLambdaTimeOut()))
        		.vpcConfig(props.getLambdaVpcConfigProp())
        		.environment(props.getLambdaEnvironmentProps())
        		.build());
        
		//enable trigger between lambda function and sqs
		CfnEventSourceMapping configureTrigger = new CfnEventSourceMapping(this, "trigger", CfnEventSourceMappingProps.builder()
				.batchSize(1) //default we need to process 1 message at a time
				.enabled(true) //default enable trigger between sqs and lambda
				.eventSourceArn(props.getQueueArn())
				.functionName(props.getFunctionName())
				.build());
		
		//trigger config depends on function creation
		configureTrigger.addDependsOn(lambdaFunction);
    }
}