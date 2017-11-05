package chapter4

object StateExrc extends App {
  import cats.data.State
  import cats.syntax.applicative._

  type CalcState[A] = State[List[Int], A]

  def operand(num: Int): CalcState[Int] =
    State[List[Int], Int] { stack =>
      (num :: stack, num)
    }

  def operator(func: (Int, Int) => Int): CalcState[Int] = State[List[Int], Int] {
    case a :: b :: tail =>
      val ans = func(a, b)
      (ans :: tail, ans)
    case _ =>
      sys.error("Fail!")
  }

  def evalOne(sym: String): CalcState[Int] =
    sym match {
      case "+" => operator(_ + _)
      case "-" => operator(_ - _)
      case "*" => operator(_ * _)
      case "/" => operator(_ / _)
      case num => operand(num.toInt)
    }

  def evalAll(input: List[String]): CalcState[Int] = {
    input.foldLeft(0.pure[CalcState]) { (a, b) =>
      a flatMap (_ => evalOne(b))
    }
  }


  val program = evalAll(List("1", "2", "+", "3", "*"))

  println(program.runA(Nil).value)
}
