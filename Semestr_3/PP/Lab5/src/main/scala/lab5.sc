enum LLTree[A]:
  case Leaf(value: A)
  case Node(value: A, left: LLTree[A], right: Option[A])

enum Quantity:
  case QUnit(value: Int)
  case QWeight(value: Double)

//enum Product:
//  case UnitProduct(id: Int, name: String, price: Double)
//  case WeightProduct(id: Int, name: String, price: Double)

sealed trait Product
case class UnitProduct(id: Int, name: String, price: Double) extends Product
case class WeightProduct(id: Int, name: String, price: Double) extends Product

type Database = List[Product]
type ReceiptItem = (Int, Quantity)
type Receipt = List[ReceiptItem]

val db: Database = List(
  Product.UnitProduct(1, "Ołówek", 2.0),
  Product.WeightProduct(2, "Jabłka", 4.0)
)

val myReceipt: Receipt = List(
  (1, Quantity.QUnit(3)),
  (2, Quantity.QWeight(1.5))
)