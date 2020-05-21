package crawler.controller

import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller

class HealthCheckController() extends Controller {

  get("/health-check") { _: Request => "Ok" }

}
