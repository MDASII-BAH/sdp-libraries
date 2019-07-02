/*
  Copyright © 2018 Booz Allen Hamilton. All Rights Reserved.
  This software package is licensed under the Booz Allen Public License. The license can be found in the License file or at http://boozallen.github.io/licenses/bapl
*/

void call(app_env, branch){
  stage "Cleanup ${app_env.long_name}", {
    // validate required parameters

    // configuration repository storing the chart
    def config_repo = app_env.helm_configuration_repository ?:
                      config.helm_configuration_repository  ?:
                      {error "helm_configuration_repository not defined in library config or application environment config"}()
    def config_repo_branch = app_env.helm_configuration_repository_branch ?:
                             config.helm_configuration_repository_branch  ?:
                             {error "helm_configuration_repository_branch not defined in library config or application environment config"}()

    // jenkins credential ID for user to access config repo
    // definable in library spec or app env spec via "helm_configuration_repository_credential"
    // or - a globally defined github credential at the root of the pipeline config "github_credential"
    def git_cred = app_env.helm_configuration_repository_credential ?:
                   config.helm_configuration_repository_credential  ?:
                   pipelineConfig.github_credential                 ?:
                   {error "GitHub Credential For Configuration Repository Not Defined"}()

    /*
       tiller namespace for this repository.
       can be specific in library spec or per application environment as "tiller_namespace"
    */
    def tiller_namespace = app_env.tiller_namespace ?:
                           config.tiller_namespace  ?:
                           {error "Tiller Namespace Not Defined"}()

    /*
       Jenkins credential for tiller (typically the service account running the tiller server).
       can be specific in library spec or per application environment.
    */
    def tiller_credential = app_env.tiller_credential ?:
                            config.tiller_credential  ?:
                            {error "Tiller Credential Not Defined"}()
    /*
       ocp url
       can be specific in library spec as "url"
       or per application environment as "openshift_url"
    */
    def ocp_url = app_env.openshift_url ?:
                  config.url            ?:
                  {error "OpenShift URL Not Defined"}()

    /*
       helm release name.
       will use "tiller_release_name" if present on app env object
       or will use "short_name" if present on app_env object.
       will fail otherwise.
    */
    
    def release = app_env.tiller_release_name ?:
                  app_env.short_name          ? (config.app_name + '-' + (branch ?: env.BRANCH_NAME) + '-' + app_env.short_name).toLowerCase() :
                  {error "App Env Must Specify tiller_release_name or short_name"}()

    inside_sdp_image "openshift_helm", {
      withCredentials([string(credentialsId: tiller_credential, variable: 'token')]) {
        withEnv(["TILLER_NAMESPACE=${tiller_namespace}"]) {
          dir("charts") {
            this.oc_login ocp_url, token
            this.delete_release release
          }
        }
      }
    }
  }
}

void oc_login(ocp_url, token){
  try {
    echo "Trying to log in via token..."
    sh "oc login --insecure-skip-tls-verify ${ocp_url} --token=${token} > /dev/null"
  } catch (any){
    echo "Trying to log in via user/pass..."
    sh "oc login --insecure-skip-tls-verify ${ocp_url} -u ${user} -p ${token} > /dev/null"
  }
}

void delete_release(release){
  def chart_doesnt_exist = sh(returnStatus: true, script: "helm history --max 1 ${release}")
  if (!chart_doesnt_exist){
    sh "helm delete --purge ${release}"
  }
}