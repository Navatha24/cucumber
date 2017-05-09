node ('master') { 
	
	tools{
		def mvnHome = tool 'mvn'
	}
	stages{
		
		stage('Build'){
			checkout scm
			steps{
				 sh "${mvnHome}/bin/mvn versions:set -DnewVersion=${env.BUILD_NUMBER}"
				 sh "${mvnHome}/bin/mvn clean"
			}
		}

		stage('Test'){
			checkout scm
			steps{
				sh "${mvnHome}/bin/mvn test"
				sh "${mvnHome}/bin/mvn cukedoctor:execute"
			}
		}

		stage('Deploy'){
			step{
				sh 'echo write your deploy code here'
			}
		}
	}
	
}
