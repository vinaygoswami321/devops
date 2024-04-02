pipeline{
    agent any

    tools{
        maven "maven"
        jdk "jdk"
    }

    stages{
        stage("Git"){
            steps{
                git branch: 'main', credentialsId: 'c45fd740-cb8e-43d9-b5ef-7bdd0cf83021', url: 'https://github.com/vinaygoswami321/devops.git'
            }
        }
        stage("Build"){
            steps{
                bat "mvn -B -DskipTests clean package"
            }
        }
        stage("Test"){
            steps{
                bat "mvn test"
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
    }
}