import org.scalatest._
import org.scalatest.tagobjects.Slow

class IntArrayFunSuite extends FunSuite {

  test("Empty array should have size 0") {
    assert(IntArrayBuffer.empty.size == 0)
  }

  test("Empty array head should throw exception") {
    assertThrows[IndexOutOfBoundsException] {
      IntArrayBuffer.empty.head
    }
  }

}

class IntArrayFunSpec extends FunSpec {

  describe("IntArray") {

    describe("when empty") {
      it("should have size 0") {
        assert(IntArrayBuffer.empty.size == 0)
      }
    }
  }

}

class IntArrayWordSpec extends WordSpec {

  "IntArray" when {
    "empty" should {
      "have size 0" in {
        assert(IntArrayBuffer.empty.size == 0)
      }
    }
  }

}

class IntArrayFlatSpec extends FlatSpec with Matchers {

  "This test" should "always pass" in {
    succeed
  }

  "cancel" should "cancel" in {
    cancel("I can cancel")
  }

  ignore should "fail" in {
    fail
  }

  "Empty IntArray" should "have size 0" in {
    (IntArrayBuffer.empty.size == 0, "your comment")
    IntArrayBuffer.empty.size should be(0)
    IntArrayBuffer.empty should have size 0

    assertResult(0) {
      IntArrayBuffer.empty.size
    }
  }

  "Elements" should "be changed after update" in {
    val buffer = IntArrayBuffer(1, 2, 3)
    buffer.update(0, 0)

    buffer(0) should be(0)
  }

  "IntArray head" should "be first item" in {
    val array = IntArrayBuffer(1, 2, 3)
    array.head should be(1)
    array.head should be(array(0))
  }

  "IntArray" should "throw when IndexOutOfBound" in {
    assertThrows[IndexOutOfBoundsException](IntArrayBuffer().head)
    assertThrows[IndexOutOfBoundsException](IntArrayBuffer().tail)
    assertThrows[IndexOutOfBoundsException] {
      IntArrayBuffer()(30)
    }
    assertThrows[IndexOutOfBoundsException] {
      IntArrayBuffer()(30) = 5
    }
  }

  "Weird test" should "pass" taggedAs Slow in {
    val array = IntArrayBuffer()
    val rand = (x: Int) => (math.random * x) % x
    for (i <- 0 to 100) {
      array += rand(i).toInt
    }

    assume(array.contains(42))

    assert {
      array.filter(_ == 42).size > 0
    }
  }

  it should "compile" in {
    assertDoesNotCompile("val a: String = hohohaha")
    assertCompiles("val a: String = \"hohohaha\"")
    assertDoesNotCompile("val a: String = hohohaha")
  }
}

class MyAwesomeRationalNumber(val m: Int, val n: Int) {
  require(n > 0, "n should be positive")
}

