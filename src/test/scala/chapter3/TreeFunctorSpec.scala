package chapter3

import org.scalatest.{FlatSpec, MustMatchers}

import cats.syntax.functor._

class TreeFunctorSpec extends FlatSpec with MustMatchers {
  import TreeFunctorSpec._
  import TreeOps.treeFunctor

  "Tree functor" should  "obey identity flow" in {
    data.map(identity) mustBe data
  }

  it should "obey composition low" in {
    data.map(f).map(g) must equal (data.map(x => g(f(x))))
  }
}

object TreeFunctorSpec {
  val data: Tree[Int] = Branch(Branch(Branch(Leaf(1), Leaf(2)), Leaf(3)), Leaf(4))
  def f(i: Int): Long = i * i * i.toLong
  def g(i: Long): String = s"<$i>"
}