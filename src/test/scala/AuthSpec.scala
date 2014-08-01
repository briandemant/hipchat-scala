import com.imadethatcow.hipchat._
import com.typesafe.config.ConfigFactory
import org.scalatest._
import scala.util.Try
import com.imadethatcow.hipchat.common.enums.Scope._
import scala.concurrent.ExecutionContext.Implicits.global

class AuthSpec extends FlatSpec with Matchers {

  val config = ConfigFactory.load
  val API_TOKEN_KEY = "com.imadethatcow.hipchat.auth_token"
  val USERNAME = "com.imadethatcow.hipchat.test_username"
  val PASSWORD = "com.imadethatcow.hipchat.test_password"
  println(config.getString(API_TOKEN_KEY))
  val apiTokenTry = Try(config.getString(API_TOKEN_KEY))
  if (apiTokenTry.isFailure) fail("Could not find auth_token in config")

  for (apiToken <- apiTokenTry) {
    val auth = new Auth(apiToken)

    // this can work, but it is probably not good to test external API authentication
    /*
    "Auth create personal request" should "return a valid JSON response" in {
      for (authResponse <- auth.genPersonalToken()) {
        println(authResponse)
        authResponse.access_token should not equal apiTokenTry.get
      }
    }
    */
    /*
    "Auth create personal request" should "return a valid JSON response" in {
      val usernameTry = Try(config.getString(USERNAME))
      val passwordTry = Try(config.getString(PASSWORD))
      if (usernameTry.isFailure) fail("Could not find username in config")
      if (passwordTry.isFailure) fail("Could not find password in config")

      val scopes = Seq(manage_rooms, admin_group, view_group, admin_room, send_message, send_notification)

      for (username <- usernameTry; password <- passwordTry) {
        for (authResponse <- auth.genPasswordToken(username, password, scopes)) {
          println(authResponse)
        }
      }
    }
    */
    "Auth get session" should "return a valid JSON response" in {
      for (sessionResponse <- auth.getSession(apiToken)) {
        println(sessionResponse)
      }
    }
    /*
    "Auth delete session" should "return true" in {
      auth.deleteSession(apiToken) shouldEqual true
    }
    */
  }
}
