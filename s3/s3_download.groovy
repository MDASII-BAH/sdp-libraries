void call(Map params = [:] ){
  def url = config.url
  def bucket = config.bucket

  params.bucket = config.bucket ?: params.bucket

  withAws(credentials:'AwsBsdo') {
    s3Download(params)
  }

}
