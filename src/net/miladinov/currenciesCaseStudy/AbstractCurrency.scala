package net.miladinov.currenciesCaseStudy

// This package is a case study that explains how abstract types can be used in Scala.
// The task is to design a class Currency. A typical instance of Currency would represent
// an amount of money in dollars, euros, yen, or some other currency. It should be possible
// to do some arithmetic on currencies. For instance, you should be able to add two amounts
// of the same currency. Or you should be able to multiply a currency amount by a factor
// representing an interest rate.

// These thoughts lead to the following first design for a currency class:
// A first (faulty) design of the Currency class
abstract class AbstractCurrency {
  val amount: Long
  def designation: String
  override def toString = s"$amount $designation"
  def + (that: AbstractCurrency): AbstractCurrency
  def * (x: Double): AbstractCurrency
}