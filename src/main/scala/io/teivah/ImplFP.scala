package io.teivah

import scalaz.Scalaz._
import scalaz._

object ImplFP {
  def users = List(
    "bob ross;+33620220221",
    "foo bar;+31620220220"
  )

  private def emit(): Seq[String] = users

  private def go(emit: () => Seq[String]) = emit.apply().foreach(println)

  def parseUser(input: String): ParsingException \/ UserB = {
    val split = input.split(";")
    if (split.length != 2) return -\/(new ParsingException("Bad format"))

    val person = parsePerson(split(0))
    val phone = parsePhone(split(1))

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


  def main(args: Array[String]): Unit = {
    val p = parseUser("bob ross;+33620220221")
    println(p)
    //    go(emit)
  }
}
