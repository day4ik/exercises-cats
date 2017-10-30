package chapter2.ex2_5_5

import cats.Monoid


case class Order(totalCost: Double, quantity: Double)

object OrderOps {
  implicit val monoid = new Monoid[Order]{
    override def empty: Order = Order(0.0, 0.0)

    override def combine(x: Order, y: Order): Order = Order(
      totalCost = x.totalCost + y.totalCost,
      quantity = x.quantity + y.quantity
    )
  }
}