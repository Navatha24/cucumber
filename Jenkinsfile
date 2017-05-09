node ('master') {

   stage "build"
        // build without tests
        checkout scm
        sh 'mvn clean'
   
   stage "acceptance tests"
   sh (script: 'mvn test', returnStatus: true)
   
   if (currentBuild.result == null) {
	    stage "publish results"
            sh (script: 'mvn cukedoctor:execute', returnStatus: true)
            step([$class: 'ResultArchiver',
                 testResults: '**/target/docs/Thomas-Bayer.pdf'])
    }
}

