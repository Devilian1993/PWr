class BinaryFunctionNode[S1 <: A, S2 <: B, A, B, +T](
                                                      left: Node[S1],
                                                      right: Node[S2],
                                                      func: (A, B) => T
                                                    ) extends Node[T] {

  override def evaluate(map: Map[String, Any]): T = {
    val leftValue = left.evaluate(map)

    val rightValue = right.evaluate(map)

    func(leftValue, rightValue)
  }
}