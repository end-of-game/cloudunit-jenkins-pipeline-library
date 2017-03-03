def call(String email, boolean notifyResult = false, String details = "" , myFunction) {
  try {
    myFunction()
  } catch (e) {
    notifyBuild(email, "FAILED", details)
    throw e
  } finally {
    if (notifyResult) notifyBuild(email, currentBuild.result, details)
  }
}

def notifyBuild(String email, String buildStatus = 'STARTED', String details = "") {
  // build status of null means successful
  buildStatus =  buildStatus ?: 'SUCCESSFUL'
  
  // Default values
  def colorName = 'RED'
  def colorCode = '#FF0000'
  def subject = "${buildStatus}: Job '${env.GIT_BRANCH}'"
  def summary = "${subject} (${env.BUILD_URL})"

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
