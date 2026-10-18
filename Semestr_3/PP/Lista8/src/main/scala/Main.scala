object Main extends App {
  val variables: Map[String, Any] = Map(
    "x" -> 10,
    "y" -> 5,
    "cena" -> 99.99,
    "produkt" -> "Kawa"
  )

  val xNode = VariableNode[Int]("x")
  val yNode = VariableNode[Int]("y")
  val addInt = (a: Int, b: Int) => a + b
  val sumNode = BinaryFunctionNode(xNode, yNode, addInt)

  val twoNode = ValueNode(2)
  val multInt = (a: Int, b: Int) => a * b
  val expr1 = BinaryFunctionNode(sumNode, twoNode, multInt)

  println(s"Wyrażenie: (10 + 5) * 2")
  println(s"Wynik: ${expr1.evaluate(variables)}")
  println()


  val prodNode = VariableNode[String]("produkt")
  val countNode = ValueNode(3)

  val repeatFunc = (s: String, i: Int) => s * i

  val expr2 = BinaryFunctionNode(prodNode, countNode, repeatFunc)

  println(s"Wyrażenie: 'Kawa' * 3")
  println(s"Wynik: ${expr2.evaluate(variables)}")
  println()

  val toStringFunc = (a: Any, b: Any) => a.toString + b.toString
  val expr3 = BinaryFunctionNode(prodNode, countNode, toStringFunc)

  println(expr3.evaluate(variables))
}