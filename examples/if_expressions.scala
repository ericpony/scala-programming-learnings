// Scala's idiom for conditional initialization
val filename =
  if (!args.isEmpty) args(0)
  else "default.txt"
