package net.miladinov.currenciesCaseStudy

abstract class CurrencyZone {
  type Currency <: AbstractCurrency
  val CurrencyUnit: Currency
  def make(x: Long): Currency

  abstract class AbstractCurrency {
    val amount: Long
    def designation: String
    override def toString = s"$amount $designation"
    def + (that: Currency): Currency =
      make(this.amount + that.amount)
    def * (x: Double): Currency =
      make((this.amount * x).toLong)
  }
}
