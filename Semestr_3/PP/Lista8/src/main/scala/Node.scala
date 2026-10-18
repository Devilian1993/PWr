trait Node[+T] {
  def evaluate(map: Map[String, Any]): T
}