package crawler
import cats.effect.{Concurrent, Sync}
import fs2.Stream
import net.ruippeixotog.scalascraper.browser.JsoupBrowser
import net.ruippeixotog.scalascraper.dsl.DSL.Extract._
import net.ruippeixotog.scalascraper.dsl.DSL._
class SimpleCrawler[F[_]: Sync: Concurrent](config: Config) {
  def getUrls(
      urlList: List[String]
  ): F[List[Title]] =
    Stream
      .emits(urlList)
      .covary[F]
      .mapAsync(config.maxConcurrent)(getTitle)
      .compile
      .toList
  def getTitle(url: String): F[Title] =
    Sync[F].delay {
      JsoupBrowser()
        .get(url)
        .tryExtract(text("head > title"))
        .fold(Title(url, "Can't parse title"))(msg => Title(url, msg))
    }
}
