import scala.annotation.tailrec

def find[A](list: List[A], element: A): List[Int] = {
  def findIter(list: List[A], element: A, index: Int): List[Int] = {
    list match {
      case Nil => Nil
      case head :: tail =>
        if (element == head) {
          index :: findIter(tail, element, index + 1)
        } else {
          findIter(tail, element, index + 1)
        }
    }
  }
  findIter(list, element, 0)
}

def findGenerator[A](elementsList: List[A]): A => List[Int] = {
  (x: A) => find(elementsList, x)
}

val find1232 = findGenerator(List(1, 2, 3, 2))
find1232(2)

def splitNRec[A](list: List[A], n: Int): List[List[A]] = {

  def generateEmpty(k: Int): List[List[A]] = {
    k match {
      case 0 => Nil
      case _ => List.empty[A] :: generateEmpty(k - 1)
    }
  }

  def appendOnIndex(list: List[List[A]], index: Int, item: A): List[List[A]] = {
    list match {
      case Nil => Nil
      case head :: tail =>
        index match {
          case 0 => (item :: head) :: tail
          case _ => head :: appendOnIndex(tail, index - 1, item)
        }
    }
  }

  def helper(list: List[A], index: Int): List[List[A]] = {
    list match {
      case Nil => generateEmpty(n)
      case head :: tail =>
        val subLists = helper(tail, (index + 1) % n)
        appendOnIndex(subLists, index, head)
    }
  }

  helper(list, 0)
}

def splitNTail[A](l: List[A], n: Int): List[List[A]] = {

  def generateEmpty(k: Int): List[List[A]] = {
    k match {
      case 0 => Nil
      case _ => List.empty[A] :: generateEmpty(k - 1)
    }
  }

  def appendOnIndex(list: List[List[A]], index: Int, item: A): List[List[A]] = {
    list match {
      case Nil => Nil
      case head :: tail =>
        index match {
          case 0 => (item :: head) :: tail
          case _ => head :: appendOnIndex(tail, index - 1, item)
        }
    }
  }

  @tailrec
  def tail_helper(l: List[A], acc: List[List[A]], i: Int): List[List[A]] = {
    l match {
      case Nil => acc
      case h :: t =>
        val next_acc = appendOnIndex(acc, i, h)
        val next_i = (i + 1) % n
        tail_helper(t, next_acc, next_i)
    }
  }

  val initial_acc = generateEmpty(n)
  tail_helper(l, initial_acc, 0)
}

splitNTail(List(1, 2, 3, 4, 5), 3)

def priorityAB[A](list: List[A], a: Int, b: Int): List[A] = {
  @tailrec
  def joinReverse(list: List[A], listRev: List[A]): List[A] = {
    listRev match {
      case Nil => list
      case head :: tail => joinReverse(head :: list, tail)
    }
  }

  def helper(list: List[A], a: Int, b: Int, index: Int, accHead: List[A]): List[A] = {
    list match {
      case Nil => joinReverse(list, accHead)
      case head :: tail =>
        if (index > b) {
          joinReverse(list, accHead)
        } else if (index >= a) {
          head :: helper(tail, a, b, index + 1, accHead)
        } else {
          helper(tail, a, b, index + 1, head :: accHead)
        }
    }
  }
  helper(list, a, b, 0, List.empty[A])
}


priorityAB(Nil, 0, 1)
priorityAB(List(1, 2, 3, 4, 5, 6, 7, 8, 9), 2, 4)
priorityAB(List(1, 2, 3, 4), 1, 3)
priorityAB(List(1, 2, 3, 4, 5), 1, 3)