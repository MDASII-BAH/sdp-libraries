void call(Map params = [:] ){
  params.bucket = config.bucket ?: params.bucket

  def awsConfig = [credentials: 'AwsBsdo']
  awsConfig.region = config.region ?: params.region

  withAWS(awsConfig) {
    s3Upload(params)
  }

}
