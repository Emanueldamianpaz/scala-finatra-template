package infrastructure


object Errors {

  // ------------------------------------------ INTERNAL SERVER ERROR - 500
  sealed abstract class MainException(message: String, cause: Option[Throwable]) extends Exception {
    override def getMessage: String = message
  }

  case class UnexpectedErrorException(message: String, cause: Option[Throwable] = None) extends MainException(message, cause)

  // ----------------------------------------------------- BAD REQUEST - 400
  sealed abstract class ValidationException(message: String, cause: Option[Throwable] = None) extends MainException(message, cause)


}
