pipeline {
    agent any

    tools {
        maven 'maven' // Jenkins tool name for Maven
    }

    environment {
        IMAGE_NAME = 'bsavanth/cloud-capstone-backend-image'
        DOCKER_USER = 'bsavanth'
        DOCKER_PASS = 'Abhijeeth6'
    }

    stages {
        stage('Clone GitHub Repo') {
            steps {
                checkout([
                    $class: 'GitSCM',
                    branches: [[name: '*/master']],
                    extensions: [],
                    userRemoteConfigs: [[url: 'https://github.com/bsavanth/Cloud_Capstone_Backend']]
                ])
            }
        }

        stage('Build with Maven') {
            steps {
                echo 'Running Maven build...'
                bat 'mvn clean install'
            }
        }

       stage('Build Docker Image') {
           steps {
               echo 'Building Docker image...'
               bat """
                   dir
                   docker build -t ${IMAGE_NAME} .
               """
           }
       }

        stage('Push Docker Image to Hub') {
            steps {
                script {
                    bat "docker login -u ${DOCKER_USER} -p ${DOCKER_PASS} docker.io"
                    bat "docker push ${IMAGE_NAME}"
                    bat "docker logout"
                }
            }
        }
    }

    post {
        success {
            echo '✅ Pipeline completed successfully!'
        }
        failure {
            echo '❌ Pipeline failed.'
        }
    }
}
