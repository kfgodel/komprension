package info.kfgodel.komprension

import info.kfgodel.jspek.api.JavaSpecRunner
import info.kfgodel.jspek.api.KotlinSpec
import info.kfgodel.komprension.ext.byteBufferOf
import info.kfgodel.komprension.ext.collectToByteArray
import info.kfgodel.komprension.impl.CONSTANT_FUNCTION
import info.kfgodel.komprension.impl.Komprenser
import info.kfgodel.komprension.impl.UNCOMPRESSED_FUNCTION
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.assertj.core.api.Assertions.assertThat
import org.junit.runner.RunWith

/**
 * This test verifies on an integration level the basic cases a Komprenser must support
 * Date: 6/5/20 - 00:49
 */
@ExperimentalCoroutinesApi
@RunWith(JavaSpecRunner::class)
class SplitDataCompressionTest : KotlinSpec() {
  override fun define() {
    describe("a compressor") {
      val compressor by let { Komprenser().compressor() }

      it("can process split input input for the uncompressed function type") {
        val input = flowOf(byteBufferOf(2, 6, 3, 10, 23), byteBufferOf(110, 84, 91, -44, -43))
        val output = compressor().invoke(input)
        assertThat(output.collectToByteArray())
          .containsExactly(UNCOMPRESSED_FUNCTION, 10 /*size*/, 2, 6, 3, 10, 23, 110, 84, 91, -44, -43)
      }

      it("can process split input for the constant function type") {
        val input = flowOf(byteBufferOf(5,5,5,5,5), byteBufferOf(5,5,5,5,5))
        val output = compressor().invoke(input)
        assertThat(output.collectToByteArray())
          .containsExactly(CONSTANT_FUNCTION, 10 /*count*/, 5)
      }
    }
  }
}