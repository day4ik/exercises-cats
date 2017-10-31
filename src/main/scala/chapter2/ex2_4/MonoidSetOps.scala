package chapter2.ex2_4

import chapter2.Monoid

object MonoidSetOps {
  def createUnionMonoid[T]: Monoid[Set[T]] = Monoid.create[Set[T]](_ ++ _)(Set.empty)
}
