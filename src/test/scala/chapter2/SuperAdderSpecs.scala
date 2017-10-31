package chapter2

import ex2_5_5.{Order, SuperAdder}
import ex2_5_5.OrderOps._
import org.scalatest.FlatSpec
import org.scalatest.MustMatchers
import cats.instances.int._
import cats.instances.option._

import scala.util.Random

class SuperAdderSpecs extends FlatSpec with MustMatchers {
  "SuperAdder" should "sum elements from list [int]" in {
    val list = List.fill(100)(Random.nextInt(10))
    list.sum mustBe SuperAdder.add(list)
  }
  it should "sum elements from list [Option[int]]" in {
    val list = List.fill(100)(Random.nextInt(10)).map(Option(_)).map(_.filter( _ < 4 ))
    list.flatten.sum mustBe SuperAdder.add(list).get
  }
  it should "summurize Order models correctly" in {
    val list = List(Order(1,2), Order(4,3), Order(4,9))
    list.reduce((f,s) => Order(f.totalCost+s.totalCost, f.quantity+s.quantity)) mustBe SuperAdder.add(list)
  }
}
