package chapter3

package object contrvariantFunctor {
  trait Printable[A] {
    def format(value: A): String
    def contramap[B](func: B => A): Printable[B] = {
      val selfRef = this
      new Printable[B] {
        override def format(value: B): String = selfRef.format(func(value))
      }
    }
  }
  def format[A](value: A)(implicit p: Printable[A]): String = p.format(value)


  implicit val stringPrintable =
    new Printable[String] {
      def format(value: String): String =
        "\"" + value + "\""
    }
  implicit val booleanPrintable =
    new Printable[Boolean] {
      def format(value: Boolean): String =
        if(value) "yes" else "no"
    }

  final case class Box[A](value: A)

  implicit def createPrintable[A](implicit pr: Printable[A]) = pr.contramap[Box[A]](_.value)
}
