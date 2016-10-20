def call(String host, String username, String password, String script) {
    def tag = env.BUILD_TAG
    def jarfile = "/tmp/cloudunit-cli-${tag}.jar"
    sh """
        wget -q ${host}/resources/CloudUnitCLI.jar -O ${jarfile}
        java -jar ${jarfile} <<<EOF
        connect --login ${username} --password ${password} --host ${host}
        ${script}
        disconnect
        EOF
        rm ${jarfile}
    """
}
