package ru.spbau.mit.scala.currency

import scala.xml.XML

object details {
  def getCurrency(name: String, date: Date): Double = {
    var request = name
    if (date != null) {
      request += s"?date_req=${date.day}/${date.month}/${date.year}"
    }

    var currency = XML.load(request)
    val currencyList = currency
      .child
      .toList
      .filter(_.isInstanceOf[scala.xml.Elem])

    def getCurrency(name: String) =
      currencyList
        .filter(_.child.exists(_.text == name))
        .head
        .child
        .filter(_.label == "Value")
        .head.text.replace(',', '.').toDouble

    getCurrency(name)
  }
}

sealed trait Currency {
  def convert(value: Double, to: Currency, date: Date): Double

  def convert(value: Double, to: Currency): Double = {
    convert(value, to, null)
  }
}

object usd extends Currency {
  override def convert(value: Double, to: Currency, date: Date): Double = to match {
    case `usd` => value
    case `rub` => details.getCurrency("USD", date) * value
    case `eur` => value * details.getCurrency("USD", date)  / details.getCurrency("EUR", date)
  }
}

object rub extends Currency {
  override def convert(value: Double, to: Currency, date: Date): Double = to match {
    case `usd` => value / details.getCurrency("USD", date)
    case `rub` => value
    case `eur` => value / details.getCurrency("EUR", date)
  }
}

object eur extends Currency {
  override def convert(value: Double, to: Currency, date: Date): Double = to match {
    case `usd` => value * details.getCurrency("EUR", date) / details.getCurrency("USD", date)
    case `rub` => value * details.getCurrency("EUR", date)
    case `eur` => value
  }
}

