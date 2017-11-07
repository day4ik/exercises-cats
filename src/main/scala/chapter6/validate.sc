import cats.data.Validated
import cats.syntax.either._
import cats.syntax.cartesian._
import cats.instances.either._
import cats.instances.list._
import cats.data.Validated._
import scala.language.higherKinds

type FormData = Map[String, String]
type ErrorsOr[A] = Either[List[String], A]
type AllErrorsOr[A] = Validated[List[String], A]

final case class User(name: String, age: Int)

def getValue(name: String)(data: FormData): ErrorsOr[String] =
  data.get(name).toRight(List(s"$name field not specified"))

type NumFmtExn = NumberFormatException

def parseInt(fieldName: String)(data: String): ErrorsOr[Int] =
  Right(data).
    flatMap(s => Either.catchOnly[NumFmtExn](s.toInt)).
    leftMap(_ => List(s"$fieldName must be an integer"))

def nonBlank(fieldName: String)(str: String): ErrorsOr[String] =
  Right(str)
    .ensure(List(s"$fieldName shouldn't be blank"))(_.trim.nonEmpty)

def nonNegative(fieldName: String)(value: Int): ErrorsOr[Int] =
  Right(value)
    .ensure(List(s"$fieldName should be positive"))(_ >= 0)

def readName(data: FormData): ErrorsOr[String] =
  getValue("name")(data).flatMap(nonBlank("name"))

def readAge(data: FormData): ErrorsOr[Int] =
  getValue("age")(data).
    flatMap(nonBlank("age")).
    flatMap(parseInt("age")).
    flatMap(nonNegative("age"))

def readUser(data: FormData): AllErrorsOr[User] =
  (
    readName(data).toValidated |@|
      readAge(data).toValidated
    ).map(User.apply)