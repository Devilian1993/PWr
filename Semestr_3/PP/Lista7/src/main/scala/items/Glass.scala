package items

import visitor.KitchenVisitor


class Glass(id: Int, val size: Int) extends KitchenItem(id) with DrinkingItem {
  override def drink(): Unit = println("Pije ze szklanki")
  override def toString: String = String.format("Jestem szklanka o pojemnosci %d o id: %d", size, id)
  override def accept(visitor: KitchenVisitor): Unit = visitor.visit(this)
}
