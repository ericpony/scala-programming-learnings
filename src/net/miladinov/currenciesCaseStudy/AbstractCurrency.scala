package net.miladinov.currenciesCaseStudy

// The amount of a currency is the number of currency units it represents. This is a field
// of type Long so that very large amounts of money such as the market capitalization of Google
// or Microsoft can be represented. It's left abstract here, waiting to be defined when a subclass
// talks about concrete amounts of money. The designation of a currency is a string that identifies
// it. The toString method of class Currency indicates an amount and a designation. It would yield
// results such as:

// 79 USD
// 11000 Yen
// 99 Euro

// Finally, there are methods +, for adding currencies, and *, for multiplying a currency with
// a floating-point number. You can create a concrete currency value by supplying concrete amount
// and designation values, like this:

// new Currency {
//   val amount = 79L
//   def designation = "USD"
// }

// This design would be OK if all we wanted to model was a single currency such as only dollars
// or only euros. But it fails once we need to deal with several currencies. Assume you model
// dollars and euros as two subclasses of class currency:

// abstract class Dollar extends Currency {
//   def designation = "USD"
// }
// abstract class Euro extends Currency {
//   def designation = "Euro"
// }

// At first glance this looks reasonable. But it would let you add dollars to euros. The result of
// such an addition would be of type Currency. But it would be a funny currency that was made up of
// a mix of euros and dollars. What you want instead is a more specialized version of the + method:
// when implemented in class Dollar, it should take Dollar arguments and yield a Dollar result;
// when implemented in class Euro, it should take Euro arguments and yield a Euro result. So the
// type of the addition method would change depending on which class you are in. Nonetheless, you
// would like to write the addition method just once, not each time a new currency is defined.

// In Scala, there’s a simple technique to deal with situations like this: if something is not
// known at the point where a class is defined, make it abstract in the class. This applies to
// both values and types. In the case of currencies, the exact argument and result type of the
// addition method are not known, so it is a good candidate for an abstract type. This would lead
// to the following sketch of class AbstractCurrency:
// A second (still imperfect) design of the Currency class
abstract class AbstractCurrency {
  type Currency <: AbstractCurrency
  val amount: Long
  def designation: String
  override def toString = s"$amount $designation"
  def + (that: Currency): Currency
  def * (x: Double): Currency
}

// The only differences from the previous situation are that the class is now called
// AbstractCurrency, and that it contains an abstract type Currency, which represents the
// real currency in question. Each concrete subclass of AbstractCurrency would need to fix the
// Currency type to refer to the concrete subclass itself, thereby "tying the knot."
