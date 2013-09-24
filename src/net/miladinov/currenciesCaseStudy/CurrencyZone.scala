package net.miladinov.currenciesCaseStudy

abstract class CurrencyZone {
  type Currency <: AbstractCurrency
  val CurrencyUnit: Currency
  def make(x: Long): Currency

  abstract class AbstractCurrency {
    val amount: Long
    def designation: String
    override def toString: String = {
      ((amount.toDouble / CurrencyUnit.amount.toDouble)
        formatted ("%." + decimals(CurrencyUnit.amount) + "f") + " " + designation)
    }
    def + (that: Currency): Currency =
      make(this.amount + that.amount)
    def * (x: Double): Currency =
      make((this.amount * x).toLong)
    private def decimals (n: Long): Int =
      if (n == 1) 0 else 1 + decimals(n / 10)
  }
}
