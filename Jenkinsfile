pipeline {
    agent any

    parameters {
        string(
            name: 'GIT_BRANCH',
            defaultValue: 'exercise-9-ci-pipeline',
            description: 'Use exercise-9-ci-pipeline for Ex 9, 11, 12; use main for Ex 6, 7, 10.'
        )
    }

    environment {
        SONAR_PROJECT_KEY = 'Calculator'
        SONAR_PROJECT_NAME = 'Calculator'
        SONAR_HOST_URL = 'http://localhost:9000'
        SONAR_PLUGIN_VERSION = '5.0.0.4389'
    }

    stages {
        stage('Checkout') {
            steps {
                // Replace this URL with your public GitHub repository URL.
                git branch: "${params.GIT_BRANCH}",
                    url: 'https://github.com/YOUR-USERNAME/YOUR-REPOSITORY.git'
            }
        }

        stage('Compile') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('UnitTest') {
            steps {
                bat 'mvn test'
            }
            post {
                always {
                    junit testResults: '**/target/surefire-reports/*.xml',
                          allowEmptyResults: true
                }
            }
        }

        stage('Coverage') {
            steps {
                // The pom.xml must configure JaCoCo to create jacoco.exec
                // and target/site/jacoco/jacoco.xml.
                bat 'mvn clean test'
            }
            post {
                always {
                    // Record coverage data in Jenkins when the files exist.
                    recordCoverage tools: [[
                        parser: 'JACOCO',
                        pattern: '**/target/site/jacoco/jacoco.xml'
                    ]],
                    sourceCodeRetention: 'EVERY_BUILD',
                    skipSymbolicLinks: true

                    archiveArtifacts artifacts: '**/target/jacoco.exec, **/target/site/jacoco/jacoco.xml',
                                     allowEmptyArchive: true
                }
            }
        }

        stage('StaticAnalysis') {
            steps {
                // Configure a SonarQube server named "SonarQube" under
                // Manage Jenkins -> System -> SonarQube installations.
                // Store the token in Jenkins Credentials as Secret text
                // with ID: sonar-token.
                withSonarQubeEnv('SonarQube') {
                    withCredentials([string(credentialsId: 'sonar-token',
                                            variable: 'SONAR_TOKEN')]) {
                        bat """
                            mvn clean test org.sonarsource.scanner.maven:sonar-maven-plugin:${SONAR_PLUGIN_VERSION}:sonar ^
                            -Dsonar.projectKey=${SONAR_PROJECT_KEY} ^
                            -Dsonar.projectName=${SONAR_PROJECT_NAME} ^
                            -Dsonar.host.url=${SONAR_HOST_URL} ^
                            -Dsonar.token=%SONAR_TOKEN% ^
                            -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml
                        """
                    }
                }
            }
        }
    }

    post {
        always {
            echo 'Calculator CI pipeline finished. Check stage results and SonarQube for analysis.'
        }
    }
}
