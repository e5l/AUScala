
sealed trait Nat {
  def toInt: Int
}

case object zero extends Nat {
  override def toInt: Int = 0
}
case class suc[N <: Nat](n : N) extends Nat {
  def pred: N = n

  override def toInt: Int = 1 + n.toInt
}

object Nat {
  type zero = zero.type
}

