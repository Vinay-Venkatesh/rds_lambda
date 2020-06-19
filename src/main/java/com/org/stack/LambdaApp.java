/**
 * This package contains Stack implementation to create lambda and its roles resource on aws
 * @LambdaStack - Class
 * 
 * @author : vinay.v
 * 
 */

package com.org.stack;

import com.org.role.RoleStack;
import com.org.stack.LambdaStack;
import software.amazon.awscdk.core.App;
import software.amazon.awscdk.core.Environment;
import software.amazon.awscdk.core.StackProps;



public class LambdaApp {
    public static void main(final String argv[]) {
        App app = new App();
        
        String california = "us-west-1";
        
        //US_WEST_1
        Environment us_west_1 = Environment.builder().account(System.getenv("account_number")).region(california).build();
        
        //Create Role for lambda
        new RoleStack(app, "lambda_role" , StackProps.builder().env(us_west_1).build());
        
        //environment configuration for run-infra lambda stack
        new LambdaStack(app, "start_rds" , StackProps.builder().env(us_west_1).build());
        
        //convert the constructs to cloudformation template
        app.synth();
    }
}