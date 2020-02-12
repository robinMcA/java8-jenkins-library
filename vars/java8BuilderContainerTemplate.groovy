def call(String path) {
  return [
    containerTemplate(
      name: 'java8-builder',
      image: "agiledigital/java8-builder",
      alwaysPullImage: true,
      command: 'cat',
      ttyEnabled: true
    )
  ]
}