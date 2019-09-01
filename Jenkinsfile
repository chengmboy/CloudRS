pipeline {
    agent {
        docker {
            image 'maven:3-alpine'
            args '-v /root/.m2:/root/.m2'
        }
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }
        stage('Test') {
            steps {
                 echo 'Testing'
            }
        }
       stage('Deploy - Staging') {
           steps {
               sh './deploy/staging.sh'
           }
       }

       stage('Sanity check') {
           steps {
               input "Does the staging environment look ok?"
           }
       }

       stage('Deploy - Production') {
           steps {
               sh './deploy/production.sh'
           }
       }
    }
}