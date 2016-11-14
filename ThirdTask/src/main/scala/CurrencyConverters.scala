package ru.spbau.mit.scala.currency

case class DateConverterHolder(value: Double, from: Currency, to: Currency, date: Date)

case class ConverterHolder(value: Double, from: Currency, to: Currency) {
  def on(date: Date): DateConverterHolder = new DateConverterHolder(value, from, to, date)
}

class CurrencyHolder(value: Double, currency: Currency) {
  def to(target: Currency) = new ConverterHolder(value, currency, target)
}

object CurrencyConverters {

  implicit class DoubleToCurrencyHolder(val value: Double) {
    def usd = new CurrencyHolder(value, ru.spbau.mit.scala.currency.usd)

    def rub = new CurrencyHolder(value, ru.spbau.mit.scala.currency.rub)

    def eur = new CurrencyHolder(value, ru.spbau.mit.scala.currency.eur)
  }

  implicit class IntToCurrencyHolder(val value: Int) {
    def usd = new CurrencyHolder(value, ru.spbau.mit.scala.currency.usd)

    def rub = new CurrencyHolder(value, ru.spbau.mit.scala.currency.rub)

    def eur = new CurrencyHolder(value, ru.spbau.mit.scala.currency.eur)
  }

  implicit def ConverterHolderToDouble(holder: ConverterHolder): Double =
    holder.from.convert(holder.value, holder.to)

  implicit def DateConverterHolderToDouble(holder: DateConverterHolder): Double =
    holder.from.convert(holder.value, holder.to, holder.date)

  implicit def ConverterHolderToInt(holder: ConverterHolder): Int =
    holder.from.convert(holder.value, holder.to).toInt

  implicit def DateConverterHolderToInt(holder: DateConverterHolder): Int =
    holder.from.convert(holder.value, holder.to, holder.date).toInt
}
