package items

class Fork(val brand: String) extends KitchenItem with EatingItem {
  override def eat(): Unit = println("Jem widelcem")
}
