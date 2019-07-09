/*
  Copyright © 2018 Booz Allen Hamilton. All Rights Reserved.
  This software package is licensed under the Booz Allen Public License. The license can be found in the License file or at http://boozallen.github.io/licenses/bapl

  Twistlock scan with Twistlock Jenkins plugins
*/


def call() {

    stage "Scanning Container Images", {
        node {
            withCredentials([usernamePassword(credentialsId: config.credential, passwordVariable: 'pass', usernameVariable: 'user')]) {
                unstash "workspace"
                login_to_registry() // from container image building library
                // comes from whatever library builds container images
                // for now .. just docker
                get_images_to_build().each { img ->
                  image = "${img.registry}/${img.repo.toLowerCase()}:${img.tag} " //The trailing space is intentional
                  sh "docker pull ${image}"
                  this.do_scan(image)
                }
            }
        }
    }
}

String do_scan(image) {
    twistlockScan ca: '', cert: '', compliancePolicy: 'warn', dockerAddress: 'unix:///var/run/docker.sock', 
        containerized: true, ignoreImageBuildTime: true, key: '', logLevel: 'true', policy: 'warn', 
        image: "${image}", requirePackageUpdate: false, timeout: 10
    
    twistlockPublish ca: '', cert: '', dockerAddress: 'unix:///var/run/docker.sock', 
        containerized: true, ignoreImageBuildTime: true, key: '', logLevel: 'true', 
        image: "${image}", timeout: 10
}
