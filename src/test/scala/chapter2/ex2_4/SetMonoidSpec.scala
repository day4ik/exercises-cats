package chapter2.ex2_4

import chapter2.Monoid
import org.scalatest.FlatSpec

class SetMonoidSpec extends FlatSpec {
  import SetMonoidSpec._

  "Set Union Monoid" should "obey associative low" in checkAssociativeLow(MonoidSetOps.createUnionMonoid[Int])
  it should "obey identity low" in checkIdentityLow(MonoidSetOps.createUnionMonoid[Int])

  "Set Diff Monoid" should "obey associative low" in checkAssociativeLow(MonoidSetOps.createDiffMonoid[Int])
  it should "obey identity low" in checkIdentityLow(MonoidSetOps.createDiffMonoid[Int])
}

object SetMonoidSpec {
  def checkAssociativeLow(implicit m: Monoid[Set[Int]]) = Monoid.associativeLaw(Set(1,2,3), Set(4,5,6), Set(7,8,9))

  def checkIdentityLow(implicit m: Monoid[Set[Int]]) = Monoid.identityLaw(Set(1,2,3))
}