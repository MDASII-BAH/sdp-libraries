.. _S3:
--
S3
--

The S3 library to connect to AWS S3 buckets

==============
Steps provided
==============

* s3_upload()
* s3_download()
* s3_copy()
* s3_delete()
* s3_object_exists()
* s3_find_files()

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

