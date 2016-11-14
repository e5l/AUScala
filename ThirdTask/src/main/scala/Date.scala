package ru.spbau.mit.scala.currency

class Date(val day: Int, val month: Int, val year: Int)
class DayMonth(day: Int, month: Int) {
  def --(year: Int): Date = new Date(day, month, year)
}

object DateImplicits {
  implicit class IntExt3(i2: Int) {
    def --(i: Int): DayMonth = new DayMonth(i2, i)
  }
}

