import sbt._
object Dependencies {
  object Versions {
    val http4s = "0.23.4"
    val circe = "0.14.1"
    val cats = "2.6.1"
    val catsEffect = "3.2.9"
    val pureConfig = "0.16.0"
    val scalaScrapper = "2.2.1"
    val scalaTest = "3.1.2"
  }
  lazy val http4s = Seq(
    "org.http4s" %% "http4s-blaze-server" % Versions.http4s,
    "org.http4s" %% "http4s-dsl" % Versions.http4s,
    "org.http4s" %% "http4s-circe" % Versions.http4s,
    "org.http4s" %% "http4s-blaze-client" % Versions.http4s
  )
  lazy val cats = Seq("org.typelevel" %% "cats-core" % Versions.cats)
  lazy val catsEffect = Seq(
    "org.typelevel" %% "cats-effect" % Versions.catsEffect
  )
  lazy val pureConfig = Seq(
    "com.github.pureconfig" %% "pureconfig" % Versions.pureConfig
  )
  lazy val scalaScraper = Seq(
    "net.ruippeixotog" %% "scala-scraper" % Versions.scalaScrapper
  )
  lazy val circe = Seq(
    "io.circe" %% "circe-generic" % Versions.circe,
    "io.circe" %% "circe-literal" % Versions.circe
  )
  lazy val scalaTest = Seq(
    "org.scalatest" %% "scalatest" % Versions.scalaTest % "test"
  )
}
