import Dependencies._
lazy val commonSettings = Seq(
  name := "2gis-test-task",
  version := "0.1",
  scalaVersion := "2.13.8"
)
lazy val main = (project in file("."))
  .settings(
    commonSettings,
    Test / fork := true,
    libraryDependencies ++= http4s ++ cats ++ pureConfig ++ scalaScraper ++ circe ++ catsEffect ++ scalaTest
  )
