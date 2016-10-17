
//Реалзуйте IntArrayBuffer с интерфейсом IntTraversable
trait IntTraversable {
  def isEmpty: Boolean

  def size: Int

  def contains(element: Int): Boolean

  def head: Int

  def tail: IntTraversable

  def ++(traversable: IntTraversable): IntTraversable

  def filter(predicate: Int => Boolean): IntTraversable

  def map(function: Int => Int): IntTraversable

  def flatMap(function: Int => IntTraversable): IntTraversable

  def foreach(function: Int => Unit): Unit
}

class IntArrayBuffer() extends IntTraversable {
  private var buffer = new Array[Int](0)
  private var actualSize = 0

  def apply(index: Int): Int = {
    checkSize(index)
    buffer.apply(index)
  }

  def update(index: Int, element: Int): Unit = {
    checkSize(index)
    buffer.update(index, element)
  }

  def clear(): Unit = {
    buffer = new Array[Int](0)
    actualSize = 0
  }

  def +=(element: Int): IntArrayBuffer = {
    actualSize += 1
    ensureSize(actualSize)

    buffer.update(actualSize - 1, element)
    this
  }

  def ++=(elements: IntTraversable): IntArrayBuffer = {
    elements.foreach(it =>
      this += it
    )

    this
  }

  def remove(index: Int): Int = {
    val result = apply(index)
    Array.copy(buffer, index + 1, buffer, index, size - index - 1)

    result
  }

  override def isEmpty: Boolean = size == 0

  override def size: Int = actualSize

  override def contains(element: Int): Boolean =
    filter(it => element == it).size > 0

  override def head: Int = apply(0)

  override def tail: IntArrayBuffer = {
    val result = new IntArrayBuffer()
    result.ensureSize(size - 1)

    Array.copy(buffer, 1, result.buffer, 0, size - 1)
    result.actualSize = size - 1
    result
  }

  override def ++(traversable: IntTraversable): IntArrayBuffer = {
    val result = new IntArrayBuffer()

    foreach(result += _)
    traversable.foreach(result += _)

    result
  }

  protected def ensureSize(size: Int): Unit = {
    if (actualSize > size) {
      return
    }

    val newBuffer = new Array[Int](size)
    Array.copy(buffer, 0, newBuffer, 0, buffer.size)

    buffer = newBuffer
  }

  override def filter(predicate: (Int) => Boolean): IntTraversable = {
    val _buf = buffer.filter(predicate)
    val result = new IntArrayBuffer()
    result.buffer = _buf
    result.actualSize = _buf.length

    result
  }

  override def map(function: (Int) => Int): IntTraversable = {
    val _buf = buffer.map(function)
    val result = new IntArrayBuffer()
    result.buffer = _buf
    result.actualSize = _buf.length

    result
  }

  override def flatMap(function: (Int) => IntTraversable): IntTraversable = {
    val mapResult = buffer.map(function)
    if (mapResult.size == 0) {
      return IntArrayBuffer()
    }

    var result = mapResult.apply(0)
    for (i <- 1 until buffer.length) {
      result = result ++ mapResult.apply(i)
    }

    result
  }

  override def foreach(function: (Int) => Unit): Unit = {
    buffer.foreach(function)
  }

  private def checkSize(index: Int): Unit = {
    if (index < size && index >= 0) {
      return
    }

    throw new IndexOutOfBoundsException()
  }

  override def equals(o: scala.Any): Boolean = o match {
    case x: IntTraversable =>
      if (x.size == 0 && size == 0) return true
      if (x.size != size) return false

      head == x.head && tail.equals(x.tail)
    case _ => false
  }
}

object IntArrayBuffer {
  def empty: IntArrayBuffer = new IntArrayBuffer()

  def apply(elements: Int*): IntArrayBuffer = {
    val result = new IntArrayBuffer()
    elements.foreach(it => result += it)

    result
  }

  def unapplySeq(buffer: IntArrayBuffer): Option[IntArrayBuffer] = {
    if (buffer.isEmpty) None
    else Some(buffer)
  }
}

IntArrayBuffer(1, 2, 3) ++ IntArrayBuffer(4, 5, 6)