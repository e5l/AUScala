import ru.spbau.mit.scala.currency._
import ru.spbau.mit.scala.currency.CurrencyConverters._
import ru.spbau.mit.scala.currency.DateImplicits._


object Application extends App {
  val fiveUsdToRub: Int = 5.usd to rub
  val fiveEurToRub: Int = 5.eur to rub on 21--10--2014

  println(fiveUsdToRub)
  println(fiveEurToRub)
}
