void call(Map params = [:]){
  params.toBucket = params.toBucket ?: config.bucket
  params.fromBucket = params.fromBucket ?: config.bucket

  def awsConfig = [:]
  awsConfig.credentials = config.credentials ?: params.credentials
  awsConfig.region = config.region ?: params.region

  withAWS(awsConfig) {
    s3Copy(params)
  }

}
