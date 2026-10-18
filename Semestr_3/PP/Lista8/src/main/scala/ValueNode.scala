class ValueNode[+T](value: T) extends Node[T] {
  override def evaluate(map: Map[String, Any]): T = value
}
