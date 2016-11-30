package ru.spbau.mit.scala.currency

import ru.spbau.mit.scala.currency.CurrencyConverters._
import ru.spbau.mit.scala.currency.DateImplicits._

import org.junit.Assert._
import junit.framework.TestCase

class CurrencyConvertersTest extends TestCase {

  def testConverters() = {
    val same: Int = 300.rub to rub
    assertEquals(300, same)
  }

  def testDatedConverters() = {
    val expectedEur = 260
    val expectedDollar = 204

    val EurInRub: Int = 5.eur to rub on 21 -- 10 -- 2014
    val UsdInRub: Int = 5.usd to rub on 21 -- 10 -- 2014

    assertEquals(expectedEur, EurInRub)
    assertEquals(expectedDollar, UsdInRub)
  }
}
