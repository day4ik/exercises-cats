package ex1

trait Printable[T] {
  def format(value: T): String
}

object PrintableInstances {
  implicit val createPrintableString = new Printable[String] {
    override def format(value: String): String = value
  }
  implicit val createPrintableInt = new Printable[Int] {
    override def format(value: Int): String = value.toString
  }
  implicit def catPrintable(implicit intPr: Printable[Int], strPr: Printable[String]) = new Printable[Cat] {
    override def format(value: Cat): String =
      s"${strPr.format(value.name)} is a ${intPr.format(value.age)} year-old ${strPr.format(value.color)} cat."
  }
}

object PrintableSyntax {
  implicit class PrintOps[T] (val value: T) extends AnyVal {

    def format(implicit printable: Printable[T]): String =
      printable.format(value)

    def print(implicit printable: Printable[T]): Unit =
      println(format)
  }
}