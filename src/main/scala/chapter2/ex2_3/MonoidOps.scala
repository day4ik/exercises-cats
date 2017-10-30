package chapter2.ex2_3

import chapter2.Monoid

object MonoidOps {
  object OrMonoid extends Monoid[Boolean] {
    override def empty: Boolean = false
    override def combine(x: Boolean, y: Boolean): Boolean = x || y
  }
  object AndMonoid extends Monoid[Boolean] {
    override def empty: Boolean = true
    override def combine(x: Boolean, y: Boolean): Boolean = x && y
  }
  object XorMonoid extends Monoid[Boolean] {
    override def empty: Boolean = false
    override def combine(x: Boolean, y: Boolean): Boolean = x ^ y
  }
  object XnorMonoid extends Monoid[Boolean] {
    override def empty: Boolean = true
    override def combine(x: Boolean, y: Boolean): Boolean = (!x || y) && (x || !y)
  }
}
