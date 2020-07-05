package info.kfgodel.komprension

import info.kfgodel.jspek.api.JavaSpecRunner
import info.kfgodel.jspek.api.KotlinSpec
import info.kfgodel.komprension.ext.collectToByteArray
import info.kfgodel.komprension.ext.flowOfByteBuffer
import info.kfgodel.komprension.impl.CONSTANT_FUNCTION
import info.kfgodel.komprension.impl.EMPTY_FUNCTION
import info.kfgodel.komprension.impl.Komprenser
import info.kfgodel.komprension.impl.UNCOMPRESSED_FUNCTION
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.assertj.core.api.Assertions.assertThat
import org.junit.runner.RunWith

/**
 * This test verifies on an integration level the basic cases a Komprenser must support
 * Date: 6/5/20 - 00:49
 */
@ExperimentalCoroutinesApi
@RunWith(JavaSpecRunner::class)
class DecompressionBasicCasesTest : KotlinSpec() {
  override fun define() {
    describe("a decompressor") {
      val decompressor by let { Komprenser().decompressor() }

      it("returns en empty flow when the empty function is passed") {
        val input = flowOfByteBuffer(EMPTY_FUNCTION)
        val output = decompressor().invoke(input)
        assertThat(output.collectToByteArray()).isEmpty()
      }

      it("returns a flow with a chunk of uncompressed data when uncompressed function is passed") {
        val input = flowOfByteBuffer(UNCOMPRESSED_FUNCTION, 10 /*size*/, 2, 6, 3, 10, 23, 110, 84, 91, -44, -43)
        val output = decompressor().invoke(input)
        assertThat(output.collectToByteArray()).containsExactly(2, 6, 3, 10, 23, 110, 84, 91, -44, -43)
      }

      it("returns a flow with repetitions of a single number when constant function is passed ") {
        val input = flowOfByteBuffer(CONSTANT_FUNCTION, 10 /*count*/, 5)
        val output = decompressor().invoke(input)
        assertThat(output.collectToByteArray()).containsExactly(5, 5, 5, 5, 5,  5, 5, 5, 5, 5)
      }

    }
  }
}