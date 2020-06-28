package info.kfgodel.komprension

import info.kfgodel.jspek.api.JavaSpecRunner
import info.kfgodel.jspek.api.KotlinSpec
import info.kfgodel.komprension.ext.collectToByteArray
import info.kfgodel.komprension.impl.EMPTY_ARRAY_FUNCTION
import info.kfgodel.komprension.impl.Komprenser
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

      it("returns en empty flow when the empty array function is passed") {
        val input = flowOf(byteArrayOf(EMPTY_ARRAY_FUNCTION))
        val output = decompressor().invoke(input);
        assertThat(output.collectToByteArray()).isEmpty()
      }

    }
  }
}