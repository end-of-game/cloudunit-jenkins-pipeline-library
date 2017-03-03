def call(String email, boolean notifyResult = false, myFunction) {
  try {
    myFunction()
  } catch (e) {
    notifyBuild(email, "FAILED")
    throw e
  } finally {
    if (notifyResult) notifyBuild(email, currentBuild.result)
  }
}

def notifyBuild(String email, String buildStatus = 'STARTED') {
  // build status of null means successful
  buildStatus =  buildStatus ?: 'SUCCESSFUL'

  // Default values
  def colorName = 'RED'
  def colorCode = '#FF0000'
  def subject = "${buildStatus}: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'"
  def summary = "${subject} (${env.BUILD_URL})"
  def details = """STARTED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':
    Check console output at <a href='${env.BUILD_URL}'>${env.JOB_NAME} [${env.BUILD_NUMBER}]</a>"""

  // Override default values based on build status
  if (buildStatus == 'STARTED') {
    color = 'YELLOW'
    colorCode = '#FFFF00'
  } else if (buildStatus == 'SUCCESSFUL') {
    color = 'GREEN'
    colorCode = '#00FF00'
  } else {
    color = 'RED'
    colorCode = '#FF0000'
  }

  emailext (
      subject: subject,
      body: details,
      to: email
    )
}
