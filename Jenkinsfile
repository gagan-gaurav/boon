pipeline {
    agent any

    tools {
        maven "M3"
    }
    

    stages {
        stage('Getting source code.') {
            steps {
                // Get some code from a GitHub repository
                git branch: 'prod', url: 'https://github.com/gagan-gaurav/boon.git'
                
                script{
                    withCredentials([file(credentialsId: 'boon_application_yml', variable: 'APP_YML_FILE')]) {
                 // Check if the secret file exists in the Jenkins workspace
                        if (!fileExists('./src/main/resources/application.yml')) {
                            // Copy the secret file only if 'application.yml' doesn't exist
                            sh 'mkdir ./src/main/resources'
                            sh 'cp $APP_YML_FILE ./src/main/resources/application.yml'
                        } else {
                            echo 'application.yml already exists. Skipping copy.'
                        }
                    }
                }
                
            }

        }
        stage("Maven Building jar"){
            steps{
                // Run Maven on a Unix agent.
                // sh "mvn -Dmaven.test.failure.ignore=true clean package"

                // To run Maven on a Windows agent, use
                bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }
        }
        stage("Building Docker image"){
            steps{
                sh "docker build -t boon:latest ."
            }
        }
        stage("Pushing the image to ECR"){
            steps{
                bat "aws ecr-public get-login-password --region us-east-1 | docker login --username AWS --password-stdin public.ecr.aws/b9h8m9d5"
                bat "docker tag boon:latest public.ecr.aws/b9h8m9d5/boon:latest"
                bat "docker push public.ecr.aws/b9h8m9d5/boon:latest"
            }
        }
        
    }
}
