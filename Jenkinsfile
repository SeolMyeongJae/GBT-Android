pipeline {
  agent any
  options {
        skipStagesAfterUnstable()
  }
//   tools {
//         jdk("JAVA11")
//   }
//   environment {
//         BUILD_VARIANT = "Debug" // "DevAlpha"
//   }
  stages {
     stage('build') {
              steps {
                  sh 'ls'
                  dir ('GBT_4'){
                  sh 'ls'
                  sh './gradlew assembleDebug'
                  sh 'ls'
                  }
               }
          }
//     stage("Environment") {
//      steps {
//         script{
//                 //withCredentials([string(credentialsId : "build-pwd", variable: "PWD")]) {
//                   //      env.DevKeyPassword = "${PWD}"
//                     //    env.DevStorePassword = "${PWD}"
//                       //  env.ProductKeyPassword = "${PWD}"
//                         //env.ProductStorePassword = "${PWD}"
//                 //}
//         }
//      }
//}

//     stage("Unit Test"){
//          steps{
//             sh "./gradlew test${env.BUILD_VARIANT}Unittest --stacktrace"
//             junit "**/TEST-*.xml"
//          }
//     }
//     stage("Assemble"){
//         steps{
//             sh "./gradlew assemble${env.BUILD_VARIANT} --stacktrace"
//             archiveArtifacts artifacts: "**/*.apk, **/mapping.txt", fingerprint: true
//     }
//    }
  }
}

