package net.miladinov.collection

import collection._

class PrefixMap [T] extends mutable.Map[String, T] with mutable.MapLike[String, T, PrefixMap[T]] {

  var suffixes: immutable.Map[Char, PrefixMap[T]] = Map.empty
  var value: Option[T] = None

  def get (key: String): Option[T] =
    if (key.isEmpty) value
    else suffixes get key(0) flatMap (_.get(key substring 1))

  def withPrefix (prefix: String): PrefixMap[T] =
    if (prefix.isEmpty) this
    else {
      val leading = prefix(0)

      suffixes get leading match {
        case None =>
          suffixes = suffixes + (leading -> empty)
        case _ =>
      }

      suffixes(leading) withPrefix (prefix substring 1)
    }

  override def update (s: String, elem: T) =
    withPrefix(s).value = Some(elem)

  override def remove (key: String): Option[T] =
    if (key.isEmpty) { val prev = value; value = None; prev }
    else suffixes get key(0) flatMap (_.remove(key substring 1))

  def iterator: Iterator[(String, T)] =
    (for (v <- value.iterator) yield ("", v)) ++
    (for ((chr, m) <- suffixes.iterator;
          (s, v) <- m.iterator) yield (chr +: s, v))

  def +=(kv: (String, T)): this.type = { update(kv._1, kv._2); this }

  def -=(key: String): this.type = { remove(key); this }

  override def empty = new PrefixMap[T]
}
