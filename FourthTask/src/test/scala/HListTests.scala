import junit.framework.TestCase
import org.junit.Assert._

import HList._

class HListTests extends TestCase {

  def testSplitAt() = {
    val list = 1 :: 2 :: 3 :: "OK" :: HNil

    val (left, right) = splitAt(list, suc(suc(zero)))
    assertEquals(1 :: 2 :: 3 :: HNil, left)
    assertEquals("OK" :: HNil, right)

    val (zleft, zright) = splitAt(list, zero)
    assertEquals(1 :: HNil, zleft)
    assertEquals(2 :: 3 :: "OK" :: HNil, zright)
  }

}
