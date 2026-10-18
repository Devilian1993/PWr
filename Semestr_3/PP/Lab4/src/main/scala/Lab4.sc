def coprimes(a: Int, n: Int): List[Int] = {
  def areCoprime(a: Int, b: Int): Boolean = {
    val commonDivisors = for {
      d <- List.range(2, Math.min(a, b) + 1)
      if a % d == 0 && b % d == 0
    } yield d
    commonDivisors.isEmpty
  }

  for (i <- List.range(1, n+1); if areCoprime(a, i)) yield i
}

coprimes(60, 50)