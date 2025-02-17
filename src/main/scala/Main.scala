import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.filters.CommonFilters
import com.twitter.finatra.http.routing.HttpRouter
import crawler.controller.HealthCheckController


object MainServerMain extends MainServer

class MainServer extends HttpServer {
  override def configureHttp(router: HttpRouter): Unit = {
    router
      .filter[CommonFilters]
      .add[HealthCheckController]
  }
}
