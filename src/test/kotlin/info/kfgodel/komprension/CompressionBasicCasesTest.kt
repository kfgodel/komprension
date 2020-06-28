package info.kfgodel.komprension

import info.kfgodel.jspek.api.JavaSpecRunner
import info.kfgodel.jspek.api.KotlinSpec
import info.kfgodel.komprension.ext.collectToByteArray
import info.kfgodel.komprension.impl.EMPTY_ARRAY_FUNCTION
import info.kfgodel.komprension.impl.Komprenser
import kotlinx.coroutines.flow.emptyFlow
import org.assertj.core.api.Assertions.assertThat
import org.junit.runner.RunWith

/**
 * This test verifies on an integration level the basic cases a Komprenser must support
 * Date: 6/5/20 - 00:49
 */
@RunWith(JavaSpecRunner::class)
class CompressionBasicCasesTest : KotlinSpec() {
  override fun define() {
    describe("a compressor") {
      val compressor by let { Komprenser().compressor() }

      it("uses the empty array function type when an empty flow is passed") {
        val input = emptyFlow<ByteArray>()
        val output = compressor().invoke(input);
        assertThat(output.collectToByteArray()).containsExactly(EMPTY_ARRAY_FUNCTION)
      }

      xit("uses the empty array function type when a flow with no bytes is passed") {

      }

    }
  }
}