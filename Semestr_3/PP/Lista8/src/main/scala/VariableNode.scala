case class VariableNode[+T](name: String) extends Node[T] {
  override def evaluate(map: Map[String, Any]): T = {
    map.get(name) match {
      case Some(value: T) => value
      case Some(wrongValue) =>
        throw new IllegalArgumentException(
          s"Zmienna '$name' ma typ ${wrongValue.getClass.getSimpleName}, a oczekiwano typu węzła."
        )
      case None =>
        throw new NoSuchElementException(s"Brak definicji dla zmiennej: '$name'")
    }
  }
}