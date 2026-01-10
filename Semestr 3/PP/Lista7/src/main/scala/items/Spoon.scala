package items

class Spoon(val brand: String) extends KitchenItem with EatingItem {
  override def eat(): Unit = println("Jem łyżką")
}