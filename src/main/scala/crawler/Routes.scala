package crawler

import cats.effect.kernel.Concurrent
import cats.implicits._
import org.http4s.{EntityDecoder, EntityEncoder, HttpRoutes}
import org.http4s.dsl.Http4sDsl
import org.http4s.circe._
import io.circe.generic.auto._

class Routes[F[_]: Concurrent](crawler: SimpleCrawler[F]) extends Http4sDsl[F] {
  implicit val decoder: EntityDecoder[F, UrlList] = jsonOf[F, UrlList]
  implicit val encoder: EntityEncoder[F, UrlList] = jsonEncoderOf[F, UrlList]
  implicit val titlesEncoder: EntityEncoder[F, List[Title]] =
    jsonEncoderOf[F, List[Title]]
  val routes: HttpRoutes[F] =
    HttpRoutes.of[F] {
      case req @ POST -> Root =>
        for {
          urlList <- req.as[UrlList]
          titles <- crawler.getUrls(urlList.list)
          result <- Ok(titles)
        } yield result
      case GET -> Root => Ok("{}")
    }
}
