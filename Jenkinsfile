pipeline {
  agent any
  stages {
    stage('build') {
     steps {
                     sh 'ls'
                     dir ('GBT_4'){
                     sh "chmod +x gradlew"
                                     sh "./gradlew clean assemble" // 일반 APK 빌드
                                     // sh "./gradlew clean app:assembleDebug" // DEBUG APK 빌드
                                     // sh "./gradlew clean app:assembleRelease" // RELEASE APK 빌드
                                     sh "find $WORKSPACE -name '*.apk'"

                     }
                  }

  }
}