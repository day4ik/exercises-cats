import scala.language.higherKinds
import cats.Monad
import cats.syntax.flatMap._
import cats.syntax.functor._
import cats.instances.list._
import cats.instances.either._

import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

def product[M[_]: Monad, A, B](
  fa: M[A],
  fb: M[B]
): M[(A, B)] = for {
  a <- fa
  b <- fb
} yield (a, b)

product(List(1, 2), List(3, 4))

type ErrorOr[A] = Either[Vector[String], A]

product[ErrorOr, Int, Int](
  Left(Vector("Error 1")),
  Left(Vector("Error 2"))
)

val a = Future("Future 1")
val b = Future("Future 2")
val fres = for {
  x <- a
  y <- b
} yield (x, y)

Await.result(fres, 1.second)