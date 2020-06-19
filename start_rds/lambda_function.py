import boto3
import datetime
 
region = 'us-west-1'
instanceId='nodejs-application'
time = datetime.datetime.now().strftime('%Y%m%d')
snapshotName = int(time)
def lambda_handler(event, context):
  RDS = boto3.client('rds',region_name=region)
  responseOne = RDS.start_db_instance(
     DBInstanceIdentifier=instanceId,
)
