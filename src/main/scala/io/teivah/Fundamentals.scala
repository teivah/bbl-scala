package io.teivah

object Fundamentals {
  def sum(a: Int, b: Int): Int =
    a + b

  def foo(a: Int, b: Int, f: (Int, Int) => Int): Int =
    f(a, b)

  def bar(name: String)(age: Int): String =
    s"Hello $name $age"

  def baz(name: String)(implicit age: Int): String =
    s"How are you $name $age"

  def main(args: Array[String]): Unit = {
    // First class support for function
    var d: (Int, Int) => Int = sum

    // Higher-order function
    foo(5, 7, sum)
    foo(5, 7, (x, y) => x * y)

    // Partial function
    val partialFunction: Int => Int = sum(5, _: Int)
    val a = partialFunction(1)
    val b = partialFunction(1)
    println(s"$a $b")

    // Currying function
    val s = bar("john")(30)
    println(s"$s")
    val f: Int => String = bar("jack")
    println(f(40))

    implicit var implicitAge = 50
    val bob: String = baz("bob")
    println(s"$bob")

    // Closure
    val pi = 3.1415926
    val nPi = (n: Int) => {
      n * pi
    }
  }
}
