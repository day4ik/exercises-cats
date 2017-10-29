package ex1_2

import scala.io.StdIn

import cats.Show
import cats.syntax.show._
import cats.instances.string._, cats.instances.int._

object Main extends App {
  println("Exercise 1_2\n")

  implicit val catShow = Show.show { cat: Cat =>
    s"${cat.name.show} is a ${cat.age.show} year-old ${cat.color.show} cat."
  }

  println(Cat("John", 12, "grey").show)

  StdIn.readLine()
  println("\nShut down...\n")
}
