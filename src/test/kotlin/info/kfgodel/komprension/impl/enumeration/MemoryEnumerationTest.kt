package info.kfgodel.komprension.impl.enumeration

import info.kfgodel.jspek.api.JavaSpecRunner
import info.kfgodel.jspek.api.KotlinSpec
import info.kfgodel.komprension.ext.byteBufferOf
import info.kfgodel.komprension.ext.collectToByteArray
import info.kfgodel.komprension.impl.Komprenser
import info.kfgodel.komprension.impl.UNCOMPRESSED_FUNCTION
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.assertj.core.api.Assertions
import org.junit.runner.RunWith

/**
 * This class verifies that memory enumeration algorithm handles some cases correctly
 * Date: 9/7/20 - 17:17
 */
@ExperimentalCoroutinesApi
@RunWith(JavaSpecRunner::class)
class MemoryEnumerationTest : KotlinSpec() {
  override fun define() {
    describe("a memory enumeration") {
      val decompressor by let { Komprenser().decompressor() }

      it("joins its input chunks into a single output chunk") {
        val input = flowOf(byteBufferOf(UNCOMPRESSED_FUNCTION, 10 /*size*/, 2, 6, 3), byteBufferOf(10, 23, 110, 84, 91, -44, -43))
        val output = decompressor().invoke(input)
        Assertions.assertThat(output.collectToByteArray()).containsExactly(2, 6, 3, 10, 23, 110, 84, 91, -44, -43)
      }
    }
  }
}