package items

import visitor.KitchenVisitor


class Spoon(id: Int, val brand: String) extends KitchenItem(id) with EatingItem {
  override def eat(): Unit = println("Jem łyżką")
  override def toString: String = String.format("Jestem lyzka marki %s o id: %d", brand, id)
  override def accept(visitor: KitchenVisitor): Unit = visitor.visit(this)
}