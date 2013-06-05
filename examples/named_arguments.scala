// Now let's talk about named arguments.
// In normal function call, the arguments in the call are matched one by one
// in the order of the parameters of the called function:
def speed(distance: Float, time: Float): Float =
  distance / time

// You could then call the speed function like this:
speed(100, 10)

// The 100 is matched to distance and the 10 to time.
//  The 100 and 10 are matched in the same order as the formal parameters are listed. Named arguments allow you
// to pass arguments to a function in a different order. The syntax is simply that each argument is preceded by a
// parameter name and an equals sign. For example, the following call to speed is equivalent to speed(100,10):
speed(distance = 100, time = 10)

// Called with named arguments, the arguments can be reversed without changing the meaning:
speed(time = 10, distance = 100)

// It is also possible to mix positional and named arguments. In that case, the positional arguments come first.
// Named arguments are most frequently used in combination with default parameter values.
