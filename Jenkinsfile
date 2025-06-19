pipeline {
    agent any

    tools {
        maven 'maven' // Jenkins tool name for Maven
    }

    environment {
        IMAGE_NAME = 'bsavanth/cloud-capstone-backend-image'
        DOCKER_USER = 'bsavanth'
        DOCKER_PASS = 'Abhijeeth6'

        AWS_REGION = 'us-east-2'
        AWS_ACCOUNT_ID = '605383994027'
        ECR_REGISTRY = "${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_REGION}.amazonaws.com"
        ECR_IMAGE_NAME = "${ECR_REGISTRY}/cloud-capstone-backend-image"
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
                    docker build -t ${IMAGE_NAME}:latest .
                """
            }
        }

        stage('Push to Docker Hub') {
            steps {
                echo 'Pushing Docker image to Docker Hub...'
                bat """
                    docker login -u ${DOCKER_USER} -p ${DOCKER_PASS}
                    docker push ${IMAGE_NAME}:latest
                    docker logout
                """
            }
        }

        stage('Check AWS Access') {
            steps {
                bat 'aws sts get-caller-identity'
            }
        }


        stage('Authenticate with AWS ECR') {
            steps {
                echo 'Authenticating Jenkins to AWS ECR...'
                bat """
                    aws ecr get-login-password --region ${AWS_REGION} | docker login --username AWS --password-stdin ${ECR_REGISTRY}
                """
            }
        }

        stage('Tag & Push to AWS ECR') {
            steps {
                echo 'Tagging and pushing image to AWS ECR...'
                bat """
                    docker tag ${IMAGE_NAME}:latest ${ECR_IMAGE_NAME}:latest
                    docker push ${ECR_IMAGE_NAME}:latest
                    docker logout ${ECR_REGISTRY}
                """
            }
        }
    }

    post {
        success {
            echo '✅ Docker image pushed to Docker Hub and AWS ECR successfully!'
        }
        failure {
            echo '❌ Pipeline failed. Check logs.'
        }
    }
}
