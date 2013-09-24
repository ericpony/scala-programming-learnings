package net.miladinov.currenciesCaseStudy

object US extends CurrencyZone {

  abstract class Dollar extends AbstractCurrency {
    def designation = "USD"
  }

  type Currency = Dollar

  def make(cents: Long) = new Dollar {
    val amount = cents
  }

  val Cent = make(1)
  val Dollar = make(1)
  val CurrencyUnit = Dollar
}
