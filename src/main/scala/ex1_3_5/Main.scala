package ex1_3_5

import cats.Eq
import cats.instances.int._
import cats.instances.string._
import cats.instances.option._
import cats.syntax.eq._

import scala.io.StdIn

object Main extends App {
  println("Exercise 1_3_5\n")

  implicit val catEq = Eq.instance[Cat]{ (cat1, cat2) =>
    cat1.name === cat2.name &&
    cat1.age === cat2.age &&
    cat1.color === cat2.color
  }

  val cat1 = Cat("Garfield",   35, "orange and black")
  val cat2 = Cat("Heathcliff", 30, "orange and black")
  val optionCat1 = Option(cat1)
  val optionCat2 = Option.empty[Cat]

  println("1) " + (cat1 === cat2))
  println("2) " + (optionCat1 === optionCat2))


  StdIn.readLine()
  println("\nShut down...\n")
}
