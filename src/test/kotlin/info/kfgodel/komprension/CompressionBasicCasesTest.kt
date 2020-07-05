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
import kotlinx.coroutines.flow.emptyFlow
import org.assertj.core.api.Assertions.assertThat
import org.junit.runner.RunWith
import java.nio.ByteBuffer

/**
 * This test verifies on an integration level the basic cases a Komprenser must support
 * Date: 6/5/20 - 00:49
 */
@ExperimentalCoroutinesApi
@RunWith(JavaSpecRunner::class)
class CompressionBasicCasesTest : KotlinSpec() {
  override fun define() {
    describe("a compressor") {
      val compressor by let { Komprenser().compressor() }

      it("uses the empty function type when an empty flow is passed") {
        val input = emptyFlow<ByteBuffer>()
        val output = compressor().invoke(input)
        assertThat(output.collectToByteArray()).containsExactly(EMPTY_FUNCTION)
      }

      it("uses the empty function type when a flow with no bytes is passed") {
        val input = flowOfByteBuffer()
        val output = compressor().invoke(input)
        assertThat(output.collectToByteArray()).containsExactly(EMPTY_FUNCTION)
      }

      it("uses the uncompressed function type when a flow of random bytes is passed") {
        val input = flowOfByteBuffer(2, 6, 3, 10, 23, 110, 84, 91, -44, -43)
        val output = compressor().invoke(input)
        assertThat(output.collectToByteArray())
          .containsExactly(UNCOMPRESSED_FUNCTION, 10 /*size*/, 2, 6, 3, 10, 23, 110, 84, 91, -44, -43)
      }

      it("uses the constant function type when a flow with repetitions of a number is passed") {
        val input = flowOfByteBuffer(5,5,5,5,5, 5,5,5,5,5)
        val output = compressor().invoke(input)
        assertThat(output.collectToByteArray())
          .containsExactly(CONSTANT_FUNCTION, 10 /*count*/, 5)
      }
    }
  }
}