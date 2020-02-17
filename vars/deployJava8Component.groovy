def call(Map config) {

  final artifactDir = "${config.project}-${config.component}-artifacts"
  final mvn = { cmd ->
    ansiColor('xterm') {
      dir(config.baseDir) {
        configFileProvider([
          configFile(
            fileId: 'maven-config',
            replaceTokens: true,
            variable: 'MAVEN_SETTINGS'
            )
          ]) {
          sh "mvn --batch-mode -s \"$MAVEN_SETTINGS\" ${cmd}"
        }
      }
    }
  }

      stage('Build Release') {
        mvn "package"
      }

      stage('Package') {
        sh "mkdir -p ${artifactDir}"

        sh "cp -r ${config.baseDir}/${config.component}/target/* ${artifactDir}/"
      }

    stage('Archive to Jenkins') {
      def tarName = "${config.project}-${config.component}-${config.buildNumber}.tar.gz"
      sh "tar -czvf \"${tarName}\" -C \"${artifactDir}\" ."
      archiveArtifacts tarName
    }

  

}