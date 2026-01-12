package items

import visitor.KitchenVisitor

abstract class KitchenItem(val id: Int) {
  def toString: String
  def accept(visitor: KitchenVisitor): Unit 
}
