pipeline{
    agent any

    tools{
        maven "maven"
        jdk "jdk"
    }

    stages{
        stage("git"){
            steps{
                git branch: 'main', credentialsId: 'c45fd740-cb8e-43d9-b5ef-7bdd0cf83021', url: 'https://github.com/vinaygoswami321/devops.git'
            }
        }
        stage("build"){
            steps{
                bat "mvn -B -DskipTests clean package"
            }
        }
        stage("run"){
            steps{
                bat"./mvnw -d spring-boot:run"
            }
        }
        stage("test"){
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