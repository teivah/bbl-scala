package io.teivah

import scalaz.Scalaz._
import scalaz._

import scala.io.Source

object ImplFP {

  private def getConfigUsers(config: Config)(threshold: Int): Seq[String] = {
    // Get iterator on file lines
    val it = Source.fromFile(config.usersFile).getLines()

    // Get n first lines
    val lines =
      for {
        i <- 0 until threshold
      } yield it.next()

    lines
      .toList
  }

  private def getConfigUsers(threshold: Int, f: (Int) => Seq[String]) =
    f.apply(threshold)

  def parseUser(input: String): ParsingException \/ UserB = {
    val split = input.split(";")
    if (split.length != 2) return -\/(new ParsingException("Bad format"))

    val person = parsePerson(split(0))
    val phone = parsePhone(split(1))

    // Applicative pattern
    (person |@| phone) (UserB)
  }

  private def parsePerson(input: String): ParsingException \/ PersonB = {
    val split = input.split(" ")
    if (split.length != 2) return -\/(new ParsingException("Bad format"))

    \/-(PersonB(split(0), split(1)))
  }

  private def parsePhone(input: String): ParsingException \/ PhoneB = {
    val regex = "^\\+(\\d{2})(\\d{9})$".r

    input match {
      case regex(indicative, phoneNumber) => \/-(PhoneB(indicative, phoneNumber))
      case _ => -\/(new ParsingException("Bad format"))
    }
  }


  def main(args: Array[String]) = {
    //    val p = parseUser("bob ross;+33620220221")
    val config = Config("src/main/resources/users.csv")
    val partialConfigFunction: Int => Seq[String] = getConfigUsers(config)

    val strings = getConfigUsers(5, partialConfigFunction)
    println(s"$strings")
  }
}
