boolean call(Map params = [:]){
  def awsConfig = [:]
  awsConfig.credentials = config.credentials ?: params.credentials
  awsConfig.region = config.region ?: params.region

  withAWS(awsConfig) {
    return s3DoesObjectExist(params)
  }

}
