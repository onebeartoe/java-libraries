pipeline {
  agent any
  stages {
    stage('Maven Clean') {
      steps {
        echo 'Cleaning...'
        sh '''
                    mvn clean
                '''
      }
    }
    stage('Verify onebeartoe.com') {
      steps {
        echo 'Verifying...'
        sh '''
                    mvn install
                '''
      }
    }
  }
}