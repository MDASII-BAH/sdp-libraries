void call(){
  println "maven: build()"

  stage "Maven build", {
    sh """
      mvn clean package -DskipTest=true -Dmaven.test.skip=true
    """
  }
}
