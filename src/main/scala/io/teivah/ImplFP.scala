package io.teivah

object ImplFP {
  def parsePerson(input: String): Either[String, PersonB] = {
    val split = input.split(";")
    if (split.length != 2) return Left("Bad format")

    Left("")
  }

  def enrichNameLastName(input: String): Either[ParsingException, (String, String)] = {
    val split = input.split(" ")
    if (split.length != 2) return Left(new ParsingException("Bad format"))

    return Right(split(0), split(1))
  }

  def enrichPhone(input: String): Either[ParsingException, String] = {
    val regex = "^0\\d{9}$".r

    "with a " match {
      case regex(_) => Right(input)
      case _ => Left(new ParsingException("Bad format"))
    }
  }
}
