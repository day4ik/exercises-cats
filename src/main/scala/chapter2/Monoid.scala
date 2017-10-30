package chapter2

trait Semigroup[A] {
  def combine(x: A, y: A): A
}

trait Monoid[A] extends Semigroup[A] {
  def empty: A
}

object Monoid {

  def apply[A](implicit monoid: Monoid[A]) = monoid

  def create[T](addition: (T,T) => T)(zero: T): Monoid[T] = new Monoid[T] {
    override def empty: T = zero
    override def combine(x: T, y: T): T = addition(x,y)
  }

  def associativeLaw[A](x: A, y: A, z: A)
                       (implicit m: Monoid[A]): Boolean =
    m.combine(x, m.combine(y, z)) == m.combine(m.combine(x, y), z)

  def identityLaw[A](x: A)
                    (implicit m: Monoid[A]): Boolean = {
    (m.combine(x, m.empty) == x) &&
      (m.combine(m.empty, x) == x)
  }
}
