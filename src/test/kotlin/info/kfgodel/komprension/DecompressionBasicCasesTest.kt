package info.kfgodel.komprension

import info.kfgodel.jspek.api.JavaSpecRunner
import info.kfgodel.jspek.api.KotlinSpec
import info.kfgodel.komprension.ext.collectToByteArray
import info.kfgodel.komprension.impl.EMPTY_FUNCTION
import info.kfgodel.komprension.impl.Komprenser
import info.kfgodel.komprension.impl.UNCOMPRESSED_FUNCTION
import kotlinx.coroutines.flow.flowOf
import org.assertj.core.api.Assertions.assertThat
import org.junit.runner.RunWith

/**
 * This test verifies on an integration level the basic cases a Komprenser must support
 * Date: 6/5/20 - 00:49
 */
@RunWith(JavaSpecRunner::class)
class DecompressionBasicCasesTest : KotlinSpec() {
  override fun define() {
    describe("a decompressor") {
      val decompressor by let { Komprenser().decompressor() }

      it("returns en empty flow when the empty function is passed") {
        val input = flowOf(byteArrayOf(EMPTY_FUNCTION))
        val output = decompressor().invoke(input);
        assertThat(output.collectToByteArray()).isEmpty()
      }

      it("returns a flow with the uncompressed data when uncompressed function is passed") {
        val input = flowOf(byteArrayOf(UNCOMPRESSED_FUNCTION, 10 /*size*/, 2, 6, 3, 10, 23, 110, 84, 91, -44, -43))
        val output = decompressor().invoke(input);
        assertThat(output.collectToByteArray()).containsExactly(2, 6, 3, 10, 23, 110, 84, 91, -44, -43)
      }

    }
  }
}