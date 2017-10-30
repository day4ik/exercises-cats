package chapter2.ex2_3

import scala.io.StdIn

object Main extends App {
  println("Exercise 1_3_5\n")

  println(s"check associative low in OR monoid")
  require(chapter2.Monoid.associativeLaw(false, false, true)(MonoidOps.OrMonoid))
  require(chapter2.Monoid.associativeLaw(true, false, true)(MonoidOps.OrMonoid))
  println("-----------------------------------")
  println(s"check identity low in OR monoid")
  require(chapter2.Monoid.identityLaw(true)(MonoidOps.OrMonoid))
  require(chapter2.Monoid.identityLaw(false)(MonoidOps.OrMonoid))
  println("-----------------------------------")


  println(s"check associative low in AND monoid")
  require(chapter2.Monoid.associativeLaw(false, false, true)(MonoidOps.AndMonoid))
  require(chapter2.Monoid.associativeLaw(true, false, true)(MonoidOps.AndMonoid))
  println("-----------------------------------")
  println(s"check identity low in AND monoid")
  require(chapter2.Monoid.identityLaw(true)(MonoidOps.AndMonoid))
  require(chapter2.Monoid.identityLaw(false)(MonoidOps.AndMonoid))
  println("-----------------------------------")



  println(s"check associative low in XOR monoid")
  require(chapter2.Monoid.associativeLaw(false, false, true)(MonoidOps.XorMonoid))
  require(chapter2.Monoid.associativeLaw(true, false, true)(MonoidOps.XorMonoid))
  println("-----------------------------------")
  println(s"check identity low in XOR monoid")
  require(chapter2.Monoid.identityLaw(true)(MonoidOps.XorMonoid))
  require(chapter2.Monoid.identityLaw(false)(MonoidOps.XorMonoid))
  println("-----------------------------------")



  println(s"check associative low in XNOR monoid")
  require(chapter2.Monoid.associativeLaw(false, false, true)(MonoidOps.XnorMonoid))
  require(chapter2.Monoid.associativeLaw(true, false, true)(MonoidOps.XnorMonoid))
  println("-----------------------------------")
  println(s"check identity low in XNOR monoid")
  require(chapter2.Monoid.identityLaw(true)(MonoidOps.XnorMonoid))
  require(chapter2.Monoid.identityLaw(false)(MonoidOps.XnorMonoid))
  println("-----------------------------------")

  StdIn.readLine()
  println("\nShut down...\n")
}
