void call(Map params = [:] ){
  def url = config.url
  def bucket = config.bucket

  params.bucket = config.bucket ?: params.bucket

  withAWS(credentials:'AwsBsdo', region:'us-east-1') {
    s3Download(params)
  }

}
