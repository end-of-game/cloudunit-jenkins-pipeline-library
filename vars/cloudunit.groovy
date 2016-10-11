def call(String host, String script) {
    def tag = env.BUILD_TAG
    def jarfile = "/tmp/cloudunit-cli-${tag}.jar"
    sh """
        wget -q ${host}/resources/CloudUnitCLI.jar -O ${jarfile}
        java -jar ${jarfile} <<<EOF
        ${script}
        EOF
        rm ${jarfile}
    """
}
