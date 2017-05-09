node ('master') {

   stage 'checkout'
   
   def mvnHome = tool 'mvn'

   stage 'build'
   // set the version of the build artifact to the Jenkins BUILD_NUMBER so you can
   // map artifacts to Jenkins builds
   sh "${mvnHome}/bin/mvn versions:set -DnewVersion=${env.BUILD_NUMBER}"
   sh "${mvnHome}/bin/mvn package"

   stage 'test'
   parallel 'test': {
     sh "${mvnHome}/bin/mvn test; sleep 2;"
   }, 'verify': {
     sh "${mvnHome}/bin/mvn verify; sleep 3"
   }

   stage 'archive'
   archive 'target/*.pdf'
}


node {
   stage 'deploy Canary'
   sh 'echo "write your deploy code here"; sleep 5;'

   stage 'deploy Production'
   input 'Proceed?'
   sh 'echo "write your deploy code here"; sleep 6;'
   archive 'target/*.jar'
}

