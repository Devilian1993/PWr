package dishwasher

import items.KitchenItem
import sorter.Sorter
import scala.jdk.CollectionConverters._
import scala.collection.mutable.Buffer

class Dishwasher(val id: Int, val capacityMap: Map[Class[_], Int], val sorter: Sorter) {

  private var items: Array[KitchenItem] = Array.empty
  private var isLocked: Boolean = false

  private def countByType(clazz: Class[_]): Int = {
    items.count(item => item.getClass == clazz)
  }

  private def loadSpecificType(sourceBuffer: Buffer[KitchenItem], clazz: Class[_]): Unit = {
    val limit = capacityMap.getOrElse(clazz, 0)
    var count = countByType(clazz)

    while (count < limit && sourceBuffer.nonEmpty) {
      items = items :+ sourceBuffer.remove(0)
      count += 1
    }
  }

  def load(): Unit = {
    if (isLocked) {
      println(s"[#$id] Zablokowana.")
      return
    }

    capacityMap.keys.foreach { clazz =>
      val javaList = sorter.getLine(clazz.asInstanceOf[Class[KitchenItem]])
      val scalaBuffer: Buffer[KitchenItem] = javaList.asScala
      loadSpecificType(scalaBuffer, clazz)
    }

    if (items.nonEmpty) {
      isLocked = true
      println(s"[#$id] Start mycia. Wsad: ${items.length}.")
    } else {
      println(s"[#$id] Brak pasujących elementów w sortowni.")
    }
  }

  def unload(array: Array[KitchenItem]): Unit = {
    if (isLocked) {
      items.copyToArray(array)
      items = Array.empty
      isLocked = false
      println(s"[#$id] Rozładowana.")
    }
  }

  override def toString: String = {
    val info = items.groupBy(_.getClass.getSimpleName).map { case (k, v) => s"$k:${v.length}" }.mkString(", ")
    s"Zmywarka #$id [$info] Locked:$isLocked"
  }
}