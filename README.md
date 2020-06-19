
# CDK Java project!

It is a Maven-based project, so you can open this directory with any Maven-compatible Java IDE,
and you should be able to build and run tests from your IDE.

The Main Class is LambdaApp.
For multiaccount deployments specify the account_number in the Environment Interface.

# Custom Constructs:
1. LambdaConstruct
2. PolicyConstruct
3. RoleConstruct

The `cdk.json` file tells the CDK Toolkit how to execute your app. This example relies on maven
to do that.

# Useful commands

 * `mvn package`     compile and run tests
 * `cdk ls`          list all stacks in the app
 * `cdk synth`       emits the synthesized CloudFormation template
 * `cdk deploy`      deploy this stack to your default AWS account/region
 * `cdk diff`        compare deployed stack with current state
 * `cdk docs`        open CDK documentation

Enjoy!
