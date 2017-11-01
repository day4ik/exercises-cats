package chapter3

import chapter3.contrvariantFunctor.Box
import org.scalatest.FlatSpec

class CodecSpec extends FlatSpec {
  "Codec" should "work properly" in {
    import Codec._
    implicit val intCodec =
      new Codec[Int] {
        def encode(value: Int): String =
          value.toString
        def decode(value: String): Option[Int] =
          scala.util.Try(value.toInt).toOption

      }
    implicit val stringBoxCodec = intCodec.imap[Box[Int]](Box(_), _.value)
    encode(Box(123)) === "123"
    decode[Box[Int]]("123") === Some(Box(123))
  }
}
