.. _S3:
--
S3
--

The S3 library to connect to AWS S3 buckets.  See https://github.com/jenkinsci/pipeline-aws-plugin for parameters to steps.


==============
Steps provided
==============

* s3_upload(Map args)
* s3_download(Map args)
* s3_copy(Map args); toBucket and fromBucket arguments default to config.bucket
* s3_delete(Map args)
* s3_object_exists(Map args)
* s3_find_files(Map args)

===============
Example Snippet
===============
.. code:: groovy

  libraries{
    s3{
      credentials = "AwsAccount"
      region = "us-east-1"
      bucket = "bucket-0"

    }
  }


==============
Configurations
==============

.. csv-table::  S3 Configuration Options
   :header: "Field", "Description", "Default Value", "Required"

   "credentials", "the id for the jenkins credential to be used", null, "true"
   "region", "the AWS region to be used", null, "true"
   "bucket", "the S3 bucket to be used", null, "true"


=====================
Dependencies
=====================
* requires the pipeline-aws plugin: https://plugins.jenkins.io/pipeline-aws
* A credential has been placed in the Jenkins credential store to access the console

