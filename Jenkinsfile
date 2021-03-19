
def projects = [[name: "demo-store-auth", jdk: "jdk-11", maven: "maven-3.6.3"]]
def changedProjects = getChangedProjects(projects)

pipeline {
    agent any

    stages {
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
