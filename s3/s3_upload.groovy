void call(Map params = [:] ){
  def url = config.url
  def bucket = config.bucket

  params.bucket = config.bucket ?: params.bucket

  withAWS(credentials:'AwsBsdo') {
    s3Upload(params)
  }

}
