package chapter2.ex2_5_5

import cats.syntax.semigroup._
import cats.Monoid

object SuperAdder {

  def add[T: Monoid](list: List[T]) = list.foldLeft(Monoid[T].empty)( _ |+| _ )
}
