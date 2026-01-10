package items

class Glass(id: Int, val size: Int) extends KitchenItem(id) with DrinkingItem {
  override def drink(): Unit = println("Pije ze szklanki")
}
