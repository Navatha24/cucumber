node ('master') { 
	
		stage('Build'){
			checkout scm
			step{
				 def mvnHome = tool 'mvn'
				 sh "${mvnHome}/bin/mvn versions:set -DnewVersion=${env.BUILD_NUMBER}"
				 sh "${mvnHome}/bin/mvn clean"
			}
		}

		stage('Test'){
			checkout scm
			step{
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
