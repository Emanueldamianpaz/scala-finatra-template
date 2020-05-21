package infrastructure

import infrastructure.Errors.UnexpectedErrorException
import okhttp3._
import org.apache.http.conn.ssl.NoopHostnameVerifier

import scala.util.{Failure, Success, Try}

object HttpClient extends Logging with Json {

  private val httpClient = new OkHttpClient.Builder().hostnameVerifier(new NoopHostnameVerifier()).build()
  private val JSON = MediaType.get("application/json; charset=utf-8")

  def get[Res](endpoint: String)(implicit req: Manifest[Res]) = {
    val request = new Request.Builder()
      .url(s"$endpoint")
      .build()

    doRequest[Res](request)
  }

  def post[Req, Res](endpoint: String, entity: Req)(implicit req: Manifest[Req], res: Manifest[Res]) = {
    val body = RequestBody.create(JSON, toJson(entity))

    val request = new Request.Builder()
      .url(s"$endpoint")
      .post(body)
      .addHeader("Content-Type", "application/json")
      .build()

    doRequest[Res](request)
  }

  private def doRequest[Res](request: Request)(implicit res: Manifest[Res]) = {
    Try(httpClient.newCall(request).execute()) match {
      case Success(response: Response) => handleResponse[Res](response, request)
      case Failure(exception) => {
        log.error("Error in HTTP Request", exception)
        Failure(UnexpectedErrorException(s"Request Http failure", Some(exception)))
      }
    }
  }

  private def handleResponse[Res](response: Response, request: Request)(implicit res: Manifest[Res]) = {
    if (response.isSuccessful) {
      Success(fromJson[Res](response.body().string()))
    } else {
      log.debug(response.body().string())
      log.error("Request failure and body cannot parsed", response.body().string())
    }
  }
}
