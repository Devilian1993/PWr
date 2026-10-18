import items._
import sorter.Sorter
import dishwasher.Dishwasher
import scala.jdk.CollectionConverters._

object Main extends App {

  println("--- Start symulacji ---")

  val dirtyPile = (1 to 12).map(i => new Fork(i, s"F$i")).toList ++
    (1 to 10).map(i => new Spoon(i, s"S$i")).toList ++
    (1 to 8).map(i => new Glass(i, 250)).toList

  println(s"Brudy: ${dirtyPile.size} szt.")

  val sorter = new Sorter()
  sorter.sort(dirtyPile.asJava)

  println(sorter)

  val standardCapacity = Map[Class[_], Int](
    classOf[Fork] -> 4,
    classOf[Spoon] -> 3,
    classOf[Glass] -> 3
  )

  val dishwashers = List(
    new Dishwasher(1, standardCapacity, sorter),
    new Dishwasher(2, standardCapacity, sorter),
    new Dishwasher(3, standardCapacity, sorter)
  )

  println(s"\n--- Ładowanie sekwencyjne (${dishwashers.size} maszyny) ---")

  for (dw <- dishwashers) {
    dw.load()
    println(dw)
    println(sorter)
  }

  println("\n--- Koniec cyklu - Rozładunek ---")

  val cleanShelf = new Array[KitchenItem](50)
  var shelfIndex = 0

  def addToShelf(items: Array[KitchenItem]): Unit = {
    val count = items.count(_ != null)
    Array.copy(items, 0, cleanShelf, shelfIndex, count)
    shelfIndex += count
  }

  dishwashers.foreach { dw =>
    val buffer = new Array[KitchenItem](20)
    dw.unload(buffer)
    addToShelf(buffer)
  }

  println(s"Na półce: $shelfIndex czystych naczyń.")
}