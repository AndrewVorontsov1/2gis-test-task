package crawler

import cats.effect.Sync
import pureconfig.ConfigSource
import pureconfig.error.ConfigReaderException
import pureconfig.generic.auto.exportReader

case class ServerConfig(host: String, port: Int)
case class Config(server: ServerConfig, maxConcurrent: Int)
object Config {
  def load[F[_]: Sync]: F[Config] =
    Sync[F].fromEither(
      ConfigSource.default
        .load[Config]
        .left
        .map(err => ConfigReaderException[Config](err))
    )
}
