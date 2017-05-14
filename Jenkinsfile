node ('master') { 

		def mvnHome = tool 'mvn'
		
		stage('Build'){
			checkout scm	
			
			try {
     			notifyBuild('STARTED')
     			//sh "${mvnHome}/bin/mvn install"
     			
 		   	} catch (e) {
     			currentBuild.result = "FAILED"
	     		throw e
   		    } finally {
     			notifyBuild(currentBuild.result)
   		    }
   
		 }

		stage('Acceptance Test Report'){
				 
		}
	
}

def notifyBuild(String buildStatus) {
  				buildStatus =  buildStatus ?: 'SUCCESSFUL'
  				def subject = "${buildStatus}: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'"
   				def summary = "${subject} (${env.BUILD_URL})"
   				
   				if (buildStatus == 'STARTED') {
     					color = 'YELLOW'
     			} else if (buildStatus == 'SUCCESSFUL') {
     					color = 'GREEN'
  				} else {
     					color = 'RED'
   				}

  				hipchatSend (color: color, notify: true, message: summary)
 }