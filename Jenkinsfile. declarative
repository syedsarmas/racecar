node {
    // Define tools
    tools {
        maven "maven"
    }
    
    // Git Checkout Stage
    stage('Git Checkout') {
        node('admin') {
            checkout scm: [
                $class: 'GitSCM', 
                branches: [[name: '*/main']], 
                userRemoteConfigs: [[url: 'https://github.com/syedsarmas/racecar.git']]
            ]
        }
    }
    
    // Build Artifact Stage
    stage('Build Artifact') {
        node('admin') {
            sh "mvn clean install"
        }
    }
    
    // Deploy Artifact Stage
    stage('Deploy Artifact') {
        node('admin') {
            sshagent(['tomcat']) {
                // Add the host to known_hosts
                sh "ssh-keyscan -H 3.110.153.216 >> ~/.ssh/known_hosts"
                // Run rsync
                sh "rsync -avz /var/lib/jenkins/workspace/assign/target/petclinic.war ubuntu@3.110.153.216:/opt/apache-tomcat-9.0.65/webapps"
            }
        }
    }

    // Restart Tomcat Stage
    stage('restart the tomcat') {
        node('tomcat') {
            sh "sudo startTomcat"
        }
    }
}
