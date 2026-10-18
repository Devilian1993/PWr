package items

import visitor.KitchenVisitor

class Fork(id: Int, val brand: String) extends KitchenItem(id) with EatingItem  {
  override def eat(): Unit = println("Jem widelcem")
  override def toString: String = String.format("Jestem widelcem marki %s o id: %d", brand, id)
  override def accept(visitor: KitchenVisitor): Unit = visitor.visit(this)
}
