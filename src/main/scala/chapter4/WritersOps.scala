package chapter4

import cats.data.Writer
import cats.syntax.applicative._
import cats.syntax.writer._
import cats.instances.vector._

import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

object WritersOps extends App {

  type Logged[A] = Writer[Vector[String], A]

  42.pure[Logged]


  def slowly[A](body: => A) =
    try body finally Thread.sleep(100)


  def factorial(n: Int): Logged[Int] =
    for {
      ans <- if(n == 0) 1.pure[Logged] else slowly(factorial(n - 1).map(_ * n))
      _   <- Vector(s"fact $n $ans").tell
    } yield ans

  val (log, result) = factorial(5).run

  val all @ Vector((logA, ansA), (logB, ansB)) =
    Await.result(Future.sequence(Vector(
      Future(factorial(5).run),
      Future(factorial(5).run)
    )), 5.seconds)

  println(factorial(5).run)

  println(all.mkString("\n\t@@@@@@@\n", "\n", "\n\t@@_END_@@"))
}
