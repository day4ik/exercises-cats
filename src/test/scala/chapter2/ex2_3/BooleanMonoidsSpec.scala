package chapter2.ex2_3

import chapter2.Monoid
import org.scalatest.FlatSpec

class BooleanMonoidsSpec extends FlatSpec {

  import BooleanMonoidsSpec._

  "OR Monoid" should "obey associative low" in checkAssociativeLow(MonoidOps.OrMonoid)
  it should "obey identity low" in checkIdentityLow(MonoidOps.OrMonoid)

  "AND Monoid" should "obey associative low" in checkAssociativeLow(MonoidOps.AndMonoid)
  it should "obey identity low" in checkIdentityLow(MonoidOps.AndMonoid)

  "XOR Monoid" should "obey associative low" in checkAssociativeLow(MonoidOps.XorMonoid)
  it should "obey identity low" in checkIdentityLow(MonoidOps.XorMonoid)

  "XNOR Monoid" should "obey associative low" in checkAssociativeLow(MonoidOps.XnorMonoid)
  it should "obey identity low" in checkIdentityLow(MonoidOps.XnorMonoid)
}

object BooleanMonoidsSpec {
  def checkAssociativeLow(implicit m: Monoid[Boolean]) =
    Monoid.associativeLaw(true, false, false) &&
    Monoid.associativeLaw(false, true, true)

  def checkIdentityLow(implicit m: Monoid[Boolean]) =
    Monoid.identityLaw(true) && Monoid.identityLaw(false)
}
