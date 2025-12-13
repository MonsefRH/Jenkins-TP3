pipeline {
    agent any
    stages {
        stage('Build & Test') {
            steps {
                sh 'mvn clean verify'
            }
        }
    }
    post {
        always {
            junit '**/target/surefire-reports/*.xml'
            jacoco()
            publishHTML([
                allowMissing: false,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'target/site/jacoco',
                reportFiles: 'index.html',
                reportName: 'JaCoCo Coverage Report'
            ])
        }
        success {
            echo 'Build réussi !'
        }
        failure {
            echo 'Build échoué !'
        }
    }
}
