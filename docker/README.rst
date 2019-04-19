.. _Docker Library:
------
Docker
------

The Docker library will build docker images and push them into a docker repository.

Steps Provided
==============

- build()
- get_images_to_build()
- login_to_registry()
- retag()

Library Configuration Options
=============================

.. csv-table::  Docker Library Configuration Options
   :header: "Field", "Description", "Default Value"

   "build_strategy", "This defines the type of docker build to be used. There are three options to choose from; docker-compose, modules, and dockerfile", "dockerfile"
   "registry", "This sets the target registry to which built Docker images will be pushed", "none"
   "cred", "The ID of the Jenkins Credential containing a username/password to access the target registry", "none"
   "repo_path_prefix", "Selects the folder, or base path, of the registry names for built images", "empty string"

Example Configuration Snippet
=============================

.. code:: groovy

   libraries{
     docker {
        build_strategy = "modules"
        registry = "docker-registry.default.svc:5000"
        repo_path_prefix = "my-project"
        cred = "docker-registry"
     }
   }

External Dependencies
=====================

- A Docker repository must be setup and configured. Credentials to the repository are also needed.
- The github_enterprise library and the sdp library needs to be loaded as libraries inside your pipeline_config.groovy file.

Troubleshooting
===============

FAQ
===

**Q:**  How are the images tagged?

**A:**  docker image name = registry + "/" + repo_path_prefix + "/" + *source repository name* + ":" + *GIT SHA*
