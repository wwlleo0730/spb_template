 pipeline {
     
    agent none
    
    options { 
    	buildDiscarder(logRotator(numToKeepStr: '5'))
    	skipDefaultCheckout true
   	}
    
    environment {
       PROJECT_VERSION = "" // 项目版本，从maven中获得
       IMAGE_NAME = "template"
       SERVICE_NAME = "template"
       PORT = "8888"
    }

  	stages {
 
    	stage('check out') {
    	    
    	    agent { label 'master'}
    	    
    	    steps {
    	    
    	       checkout scm
    	            	       
    	       script {
                    PROJECT_VERSION = readMavenPom().getVersion()
               }
               
               echo "$PROJECT_VERSION"
    	    }
 	    
    	}
    	
    	stage('mvn build & test'){
            
            agent { label 'master'}

            steps {
                sh "docker run -i --rm -v ${env.WORKSPACE}:/usr/src/workspace -v /root/.m2/repository/:/root/.m2/repository -v /opt/maven/settings.xml:/root/.m2/settings.xml -w /usr/src/workspace maven:jdk8 mvn clean package -U -Dmaven.test.skip=true"
            }
        }
        
        stage ('build') {
            
            agent { label 'master'}
            
            steps{
        
            	script {
            	
                    echo 'master branch build & push'
                        
                    docker.build("${IMAGE_NAME}:${PROJECT_VERSION}")
                }
            }
        }
        
         
       stage (' master release ') {
            
            agent { label 'master'}
            
            environment {
                NODEIP = sh(
                    returnStdout: true, 
                    script: 'ip a|grep eth0|grep -w \'inet\'|sed \'s/^.*inet //g\'|sed \'s/\\/[0-9][0-9].*$//g\''
                ).trim()
            }
            
            steps{
            
                sh '''CID=$(docker ps -a | grep $SERVICE_NAME | awk \'{print $1}\')
                    if [ "$CID" != "" ];then
                        docker rm -f $CID
                    fi'''
                            
                echo 'restart'     
                sh "docker run -d --restart always --name $SERVICE_NAME -p $PORT:$PORT -e SPRING_PROFILES_ACTIVE=prod ${IMAGE_NAME}:${PROJECT_VERSION}"  
          }
        }
    }   
}