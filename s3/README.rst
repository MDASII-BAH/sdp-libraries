.. _S3:
--
S3
--

The S3 library to connect to AWS S3 buckets

==============
Steps provided
==============

-- s3_upload()
-- s3_download()
-- s3_copy()
-- s3_delete()

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

=====================
Dependencies
=====================
requires https://plugins.jenkins.io/pipeline-aws

