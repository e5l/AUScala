import Nat._

sealed trait HList {
  def ::[H](h: H): HCons[H, this.type] = HCons(h, this)
}

case object HNil extends HList

case class HCons[+H, +T <: HList](head: H, tail: T) extends HList

object HList {
  type ::[+H, +T <: HList] = HCons[H, T]
  type HNil = HNil.type

  trait Appendable[L <: HList, R <: HList, Res <: HList] {
    def apply(l: L, r: R): Res
  }

  object Appendable {
    implicit def base[R <: HList]: Appendable[HNil, R, R] = (l: HNil, r: R) => r

    implicit def step[H, L <: HList, R <: HList, Res <: HList](implicit appendable: Appendable[L, R, Res]): Appendable[H :: L, R, H :: Res] =
      (l: H :: L, r: R) => l.head :: appendable(l.tail, r)
  }

  trait Splittable[Left <: HList, Right <: HList, RLeft <: HList, RRight <: HList, N <: Nat] {
    def apply(left: Left, right: Right, n: N): (RLeft, RRight)
  }

  object Splittable {
    implicit def base[Left <: HList, Right <: HList]: Splittable[Left, Right, Left, Right, zero] = (left: Left, right: Right, n: zero) => (left, right)

    implicit def step[Left <: HList, H, Right <: HList, N <: Nat, RLeft <: HList, RRight <: HList]
    (implicit splittable: Splittable[H :: Left, Right, RLeft, RRight, N]): Splittable[Left, H :: Right, RLeft, RRight, suc[N]] = (left: Left, right: H :: Right, n: suc[N]) => {
      splittable.apply(right.head :: left, right.tail, n.pred)
    }
  }

  def append[L <: HList, R <: HList, Res <: HList](l: L, r: R)(implicit appendable: Appendable[L, R, Res]): Res = appendable(l, r)

  def splitAt[H, List <: HList, Left <: HList, Right <: HList, N <: Nat, RLeft <: HList, RRight <: HList, RRLeft <: HList]
  (list: H :: List, n: N)
  (implicit splittable: Splittable[H :: HNil, List, RLeft, RRight, N], reversible: Reversible[RLeft, HNil, RRLeft]): (RRLeft, RRight) = {
    val (l, r) = splittable(list.head :: HNil, list.tail, n)
    (reversible.apply(l, HNil), r)
  }

  trait Reversible[L <: HList, Acc <: HList, R <: HList] {
    def apply(list: L, acc: Acc): R
  }

  object Reversible {
    implicit def base[H <: HNil, Acc <: HList]: Reversible[HNil, Acc, Acc] = (_: HNil, acc: Acc) => acc

    implicit def step[H, T <: HList, Acc <: HList, Res <: HList]
    (implicit reversibile: Reversible[T, H :: Acc, Res]): Reversible[H :: T, Acc, Res] = (list: H :: T, acc: Acc) => reversibile.apply(list.tail, list.head :: acc)
  }

}

