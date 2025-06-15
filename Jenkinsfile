pipeline {
    agent any

    environment {
        IMAGE_NAME = "imedhamami/springboot-app"
        TAG = "latest"
    }

    stages {
        stage('Build with Maven') {
            steps {
                script {
                    docker.image('maven:3.8.6-openjdk-17').inside {
                        sh 'mvn clean package -DskipTests'
                    }
                }
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t $IMAGE_NAME:$TAG .'
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                sh 'kubectl apply -f k8s/deployment.yaml'
            }
        }
    }
}
