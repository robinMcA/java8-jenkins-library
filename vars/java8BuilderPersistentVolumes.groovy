def call(Map config) {
  return [
    [
      path: '/home/root/.m2/repository',
      claimName: "${config.project}-home-jenkins-m2-repository",
      sizeGiB: 1
    ]
  ]
}
