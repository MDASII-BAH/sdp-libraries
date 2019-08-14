void call(Map params = [:] ){
  def url = config.url
  def bucket = config.bucket

  params.bucket = config.bucket ?: params.bucket

  withAWS(credentials:'AwsBsdo', endpointUrl:'s3.us-east-1.amazonaws.com') {
    s3Upload(params)
  }

}
