class UnaryFunctionNode[S <: A, A, +T](child: Node[S],func: A => T) extends Node[T] {
  override def evaluate(map: Map[String, Any]): T =
    val value = child.evaluate(map)
    func(value)
}