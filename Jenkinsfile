
def projects = [[name: "demo-store-auth", jdk: "jdk-11", maven: "maven-3.6.3"]]
def changedProjects = []

pipeline {
    agent any

    stages {
        stage("Check project changes") {
            steps {
                script {
                    changedProjects = getChangedProjects(projects)
                }
            }
        }
        stage("Build") {
            steps {
                echo 'Building projects ...'
                script {
                    changedProjects.each {
                        echo 'The project ${it.name} need to build.'
                        dir (it.name) {
                            withMaven(jdk: it.jdk, maven: it.maven) {
                                sh 'mvn clean package -Dmaven.test.skip=true'
                            }
                        }
                    }
                }
                echo 'Building finished'
            }
        }
        stage('Unit testing') {
            steps {
                echo 'Executing unit tests ...'
                script {
                    changedProjects.each {
                        dir (it.name) {
                            withMaven(jdk: it.jdk, maven: it.maven) {
                                sh 'mvn test'
                            }
                        }
                    }
                }
            }
        }
        stage('Integration testing') {
            steps {
                echo 'Executing unit tests ...'
                script {
                    changedProjects.each {
                        dir (it.name) {
                            withMaven(jdk: it.jdk, maven: it.maven) {
                                sh 'mvn failsafe:integration-test'
                            }
                        }
                    }
                }
            }
        }
        stage('Analyse') {
            steps {
                echo 'Verifying style ...'
                script {
                    changedProjects.each {
                        dir (it.name) {
                            withMaven(jdk: it.jdk, maven: it.maven) {
                                withSonarQubeEnv() {
                                    sh 'mvn checkstyle:check sonar:sonar'
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    post {
        always {
            recordIssues enabledForFailure: true, tool: checkStyle()
        }
    }
}

def getChangedProjects(projects) {
    def result = new HashSet<String>()
    currentBuild.changeSets.each {
        it.items.each {
            it.affectedFiles.each {
                path = it.path
                projects.each {
                    if (path.startsWith(it.name)) {
                        result.add(it)
                    }   
                }
            }
        }
    }
    return result
}
