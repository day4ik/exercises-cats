package chapter2.ex2_4

import chapter2.Monoid
import org.scalatest.FlatSpec

class SetMonoidSpec extends FlatSpec {
  import SetMonoidSpec._

  "Set Union Monoid" should "obey associative low" in assert(checkAssociativeLow(MonoidSetOps.createUnionMonoid[Int]))
  it should "obey identity low" in assert(checkIdentityLow(MonoidSetOps.createUnionMonoid[Int]))

}

object SetMonoidSpec {
  def checkAssociativeLow(implicit m: Monoid[Set[Int]]) = Monoid.associativeLaw(Set(1,2,3), Set(3,4,5,6), Set(3,7,8,9))

  def checkIdentityLow(implicit m: Monoid[Set[Int]]) = Monoid.identityLaw(Set(1,2,3))
}