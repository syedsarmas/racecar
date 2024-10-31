pipeline {
    agent none
    tools {
        maven "maven"
    }
    stages {
        stage('Git Checkout') {
            agent {
                label "admin"
            }
            steps {
                git branch: 'main', url: 'https://github.com/syedsarmas/racecar.git'
            }
        }
        stage('Build Artifact') {
            agent {
                label "admin"
            }
            steps {
                sh "mvn clean install"
            }
        }
        stage('Deploy Artifact') {
            agent {
                label "admin"
            }
            steps {
                sshagent(['tomcat']) {
                    // Add the host to known_hosts
                    sh "ssh-keyscan -H 3.110.153.216 >> ~/.ssh/known_hosts"
                    // Run rsync
                    sh "rsync -avz /var/lib/jenkins/workspace/assign/target/petclinic.war ubuntu@3.110.153.216:/opt/apache-tomcat-9.0.65/webapps"
                }
            }
        }
        stage('restart the tomcat') {
            agent {
                label "tomcat"
            }
            steps {
                sh "sudo startTomcat"
            }
        }
    }
}
