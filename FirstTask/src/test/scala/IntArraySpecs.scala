import org.scalacheck.Gen
import org.specs2.mutable._
import org.specs2.ScalaCheck
import org.scalacheck.Prop.forAll
import org.specs2.mock.Mockito


class IntArraySpecs extends Specification with BeforeAfter with ScalaCheck with Mockito {

  def before() = {
    println("Before")
  }

  def after() = {
    println("After")
  }

  "Size" >> {
    IntArrayBuffer.empty.size must_== 0
    IntArrayBuffer(1, 2, 3).size must_== 3
    IntArrayBuffer.empty.isEmpty must_== true
    IntArrayBuffer(1, 2, 3).isEmpty must_== false
  }

  "Append" >> {
    IntArrayBuffer(1, 2, 3) ++ IntArrayBuffer(1, 2, 3) must_== IntArrayBuffer(1, 2, 3, 1, 2, 3)
    (IntArrayBuffer(1, 2, 3) += 4) must_== IntArrayBuffer(1, 2, 3, 4)
  }

  "Clear" >> {
    val data = IntArrayBuffer(1, 2, 3)
    data.clear()

    data must_== IntArrayBuffer()
  }

  "Remove" >> {
    IntArrayBuffer(1, 2, 3).remove(0) must_== 1
  }

  "Head&Tail" >> {
    IntArrayBuffer(1).head must_== 1
    IntArrayBuffer(1, 2, 3, 4).tail must_== IntArrayBuffer(2, 3, 4)
  }

  def randomBuffer(size: Int): Gen[IntArrayBuffer] = {
    for {
      numbers <- Gen.listOfN[Int](size, Gen.choose(0, 100))
    } yield IntArrayBuffer(numbers: _*)
  }

  "Traversable" >> {
    forAll(randomBuffer(100), randomBuffer(100)) {
      (first, second) => {
        first ++ second must_== first ++ second
        (first ++ second).size must_== first.size + second.size
        first.flatMap(IntArrayBuffer(_)) must_== first
      }
    }
  }

  "call" >> {
    forAll(randomBuffer(100)) { first =>
      val teapot = mock[IntArrayBuffer]
      teapot.size

      there was one(teapot).size
      there was no(teapot).isEmpty

      teapot += 10
      teapot.size must_== 0

      (first ++ teapot) must_== first
      there was exactly(0)(teapot).apply(0)
    }


  }

}
