node ('master') { 
		def mvnHome = tool 'mvn'
				
		stage('Build'){
			checkout scm
			sh "${mvnHome}/bin/mvn versions:set -DnewVersion=${env.BUILD_NUMBER}"
			sh "${mvnHome}/bin/mvn clean"
			
		}

		stage('Test'){
				sh "${mvnHome}/bin/mvn test"
				sh "${mvnHome}/bin/mvn cukedoctor:execute"
		}

		stage('Deploy'){
			step{
				sh 'echo write your deploy code here'
			}
		}
	
}
