node ('master') {

   stage "build"
        // build without tests
        checkout scm
        sh './mvnw clean install'
   
   stage "acceptance tests"
   sh (script: './mvnw test', returnStatus: true)
   
   if (currentBuild.result == null) {
	    stage "publish results"
            sh (script: './mvnw cukedoctor:execute', returnStatus: true)
            step([$class: 'ResultArchiver',
                 testResults: '**/target/docs/Thomas-Bayer.pdf'])
    }
}

