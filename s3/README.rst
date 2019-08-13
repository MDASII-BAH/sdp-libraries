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

===============
Example Snippet
===============
.. code:: groovy

  libraries{
    s3{
      url: "http://aws.bah.com/
      bucket: "bdso"

    }
  }


==============
Configurations
==============

.. csv-table::  Docker Configuration Options
   :header: "Field", "Description", "Default Value", "Required"

=====================
Dependencies
=====================
requires https://plugins.jenkins.io/pipeline-aws

