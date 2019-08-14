void call(Map params = [:] ){
  def url = config.url
  def bucket = config.bucket
  def region = config.region ?: params.region

  params.bucket = config.bucket ?: params.bucket

  def awsConfig = [credentials: 'AwsBsdo']
  awsConfig.region = config.region ?: params.region

  withAWS(awsConfig) {
    s3Download(params)
  }

}
