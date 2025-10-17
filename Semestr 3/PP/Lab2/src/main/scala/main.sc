//Zadanie 1
def fiddle22(tuple1: (Int, Int), tuple2: (Int, Int)): ((Int, Int), (Int, Int)) = ((tuple2._2, tuple1._1), (tuple1._2, tuple2._1))

println(fiddle22((1, 2), (3, 4)))

// Zadanie 2
def hits(list1: List[Int], list2: List[Int]): List[Int] = {
  def checker(l1: List[Int], l2: List[Int], index: Int): List[Int] = {
    if (l1.isEmpty || l2.isEmpty) Nil
    else if (l1.head == l2.head)
      index :: checker(l1.tail, l2.tail, index + 1)
    else
      checker(l1.tail, l2.tail, index + 1)
  }

  checker(list1, list2, 0)
}

println(hits(List(1, 2, 3, 4), List(1, 3, 4, 4)))


// Zadanie 3
def insert[A](list: List[(A, Int)], element: A): List[(A, Int)] = {
  if (list.isEmpty) List((element, 1))
  else {
    val pair = list.head
    if (pair._1 == element)
      (element, pair._2 + 1) :: list.tail
    else
      pair :: insert(list.tail, element)
  }
}

val list1 = insert(List(), 10)
val list2 = insert(List(("q", 10), ("b", 15)), "q")

println(list1)
println(list2)


// Zadanie 4
def theVeryNextDay(day: Int, month: Int, year: Int): (Int, Int, Int) = {

  def leapYear(y: Int): Boolean =
    (y % 4 == 0 && y % 100 != 0) || (y % 400 == 0)

  def daysInMonth(y: Int, m: Int): Int = {
    if (m == 1 || m == 3 || m == 5 || m == 7 || m == 8 || m == 10 || m == 12) 31
    else if (m == 4 || m == 6 || m == 9 || m == 11) 30
    else if (m == 2) { if (leapYear(y)) 29 else 28 }
    else 0
  }

  def checkDay(d: Int, m: Int, y: Int): Boolean = d > 0 && d <= daysInMonth(y, m)
  def checkMonth(m: Int): Boolean = m > 0 && m <= 12
  def checkYear(y: Int): Boolean = y > 0

  if (!checkDay(day, month, year) || !checkMonth(month) || !checkYear(year))
    throw new IllegalArgumentException("Nieprawidłowa data!")
  else if (day == daysInMonth(year, month))
    if (month == 12) (1, 1, year + 1)
    else (1, month + 1, year)
  else
    (day + 1, month, year)
}

println(theVeryNextDay(28, 2, 2024))
println(theVeryNextDay(31, 12, 2024))
