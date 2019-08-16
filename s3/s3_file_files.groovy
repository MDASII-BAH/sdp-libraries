def call(Map params = [:]){
  params.bucket = config.bucket ?: params.bucket

  def awsConfig = [:]
  awsConfig.credentials = config.credentials ?: params.credentials
  awsConfig.region = config.region ?: params.region

  withAWS(awsConfig) {
    return s3FindFiles(params)
  }

}
