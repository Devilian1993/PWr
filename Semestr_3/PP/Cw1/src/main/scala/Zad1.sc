def flatten[A](ll: List[List[A]]): List[A] =
  if (ll.isEmpty) List.empty[A]
  else ll.head ::: flatten(ll.tail)

val list = flatten(List(List(1, 2), List(3, 4)))
println(list)