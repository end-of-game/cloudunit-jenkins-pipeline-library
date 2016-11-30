![CloudUnit Logo](https://raw.githubusercontent.com/Treeptik/cloudunit/master/documentation/img/Cloudunit_by_Treeptik.png "CloudUnit By Treeptik")

# CloudUnit Jenkins Pipeline Library

This repository contains a Jenkins Pipeline library for running [CloudUnit](https://github.com/Treeptik/cloudunit) tasks.

# How to use

## Importing the library

The documentation for the Jenkins [Pipeline Shared Groovy Libraries Plugin](https://github.com/jenkinsci/workflow-cps-global-lib-plugin)
contains all the details for importing and using a Pipeline library.

## Available commands

```groovy
cloudunit(String host, String username, String password, String script)
```

- `host` contains the URL to connect to a CloudUnit Manager
- `username` CloudUnit user
- `password` password
- `script` contains a CloudUnit CLI script

Connects to the CloudUnit Manager running at the specified URL, and runs the script, then disconnects.

Example:
```groovy
def cloudunitHost = "http://cloudunit.dev"

cloudunit(cloudunitHost, "johndoe", "****", """
  create-app --name ${env.BRANCH_NAME} --type tomcat-8
  deploy --path target/ROOT.war
""")
```
