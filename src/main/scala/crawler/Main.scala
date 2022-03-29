package crawler

import cats.effect.kernel.{Async, Concurrent}
import cats.effect.{ExitCode, IO, IOApp}
import org.http4s.blaze.server.BlazeServerBuilder
import cats.implicits._

import scala.concurrent.ExecutionContext.global
object Main extends IOApp {
  def startApp[F[_]: Async: Concurrent]: F[Unit] =
    for {
      config <- Config.load[F]
      simpleCrawler = new SimpleCrawler[F](config)
      routes = new Routes[F](simpleCrawler)
      server <-
        BlazeServerBuilder[F](global)
          .bindHttp(config.server.port, config.server.host)
          .withHttpApp(routes.routes.orNotFound)
          .serve
          .compile
          .drain
    } yield server
  override def run(args: List[String]): IO[ExitCode] =
    startApp[IO].as(ExitCode.Success)

}
