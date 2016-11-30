def call(String host, String username, String password, String script) {
    def jarfile = "CloudUnitCLI.jar"
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
