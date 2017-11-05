package chapter4

import cats.Monad
import scala.annotation.tailrec
import cats.syntax.monad._

object TreeMonadOps {

  sealed trait Tree[+A]
  final case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]
  final case class Leaf[A](value: A) extends Tree[A]

  def branch[A](left: Tree[A], right: Tree[A]): Tree[A] =
    Branch(left, right)
  def leaf[A](value: A): Tree[A] =
    Leaf(value)


  val treeMonad = new Monad[Tree] {

    def flatMap[A, B](tree: Tree[A])(func: A => Tree[B]): Tree[B] = tree match {
      case Branch(l, r) => branch(flatMap(l)(func), flatMap(r)(func))
      case Leaf(leaf) => func(leaf)
    }

    def pure[A](t: A): Tree[A] = leaf(t)

    def tailRecM[A, B](a: A)(fn: A => Tree[Either[A, B]]): Tree[B] = fn(a) match {
      case Branch(l, r) =>
        Branch(
          flatMap(l) {
            case Left(l)  => tailRecM(l)(fn)
            case Right(l) => pure(l)
          },
          flatMap(r) {
            case Left(r)  => tailRecM(r)(fn)
            case Right(r) => pure(r)
          } )
      case Leaf(Left(value)) =>
        tailRecM(value)(fn)
      case Leaf(Right(value)) =>
        Leaf(value)
    }
  }
}
