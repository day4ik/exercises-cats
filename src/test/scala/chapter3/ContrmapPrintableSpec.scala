package chapter3

import org.scalatest.{FlatSpec, MustMatchers}

import contrvariantFunctor._

class ContrmapPrintableSpec extends FlatSpec with MustMatchers {

  "contramap mathod in Printable" should "work correctly" in {
    format(Box("hello world")) mustEqual format("hello world")
    format(Box(true)) mustEqual format(true)
  }
}