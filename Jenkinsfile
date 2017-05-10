node ('master') { 
		
		def mvnHome = tool 'mvn'
				
		stage('Build'){
			checkout scm
			sh "${mvnHome}/bin/mvn versions:set -DnewVersion=${env.BUILD_NUMBER}"
			sh "${mvnHome}/bin/mvn install"
			println 'Running Sonar analysis';
    		sh "mvn sonar:sonar'"
			
		}

		stage('Acceptance Test'){
				sh "${mvnHome}/bin/mvn cukedoctor:execute"
		}

		stage('Archive'){
				sh 'echo write your deploy code here'
		}
	
}
