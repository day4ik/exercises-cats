package chapter1.ex1

object Main extends App {

  import PrintableInstances._
  import PrintableSyntax._

  Cat("new one", 1, "green").print

}
