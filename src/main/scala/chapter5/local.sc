import cats.data.EitherT
import cats.instances.future._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Await, Future}
import scala.concurrent.duration._

type Response[A] = EitherT[Future, String, A]

val powerLevels = Map(
  "Jazz"      -> 6,
  "Bumblebee" -> 8,
  "Hot Rod"   -> 10
)

def getPowerLevel(autobot: String): Response[Int] =
  powerLevels.get(autobot) match {
    case Some(avg) => EitherT.right(Future.successful(avg))
    case None      => EitherT.left(Future.successful(s"$autobot is unreachable"))
  }

 Await.result(getPowerLevel("Jazz").value, 1 second)


def canSpecialMove(
  ally1: String,
  ally2: String
): Response[Boolean] = {
  for {
    response1 <- getPowerLevel(ally1)
    response2 <- getPowerLevel(ally2)
  } yield response1 + response2 > 15
}

Await.result(canSpecialMove("Bumblebee", "Jazz").value, 1 second)

def tacticalReport(
  ally1: String,
  ally2: String
): String = Await.result(
  canSpecialMove(ally1, ally2).value,
  1.second
) match {
  case Left(msg) =>
    s"Comms error: $msg"
  case Right(true)  =>
    s"$ally1 and $ally2 are ready to roll out!"
  case Right(false) =>
    s"$ally1 and $ally2 need a recharge."
}

tacticalReport("Jazz", "Bumblebee")

tacticalReport("Bumblebee", "Hot Rod")

tacticalReport("Jazz", "Ironhide")