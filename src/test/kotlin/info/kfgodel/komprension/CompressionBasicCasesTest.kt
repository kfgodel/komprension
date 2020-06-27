package info.kfgodel.komprension

import info.kfgodel.jspek.api.JavaSpecRunner
import info.kfgodel.jspek.api.KotlinSpec
import info.kfgodel.komprension.impl.EMPTY_ARRAY_FUNCTION
import info.kfgodel.komprension.impl.Komprenser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions
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
        runBlocking {
          val output: Flow<ByteArray> = compressor().invoke(emptyFlow());
          val byteArrays = output.toList(mutableListOf())
          Assertions.assertThat(byteArrays).hasSize(1)
          val onlyByteArray = byteArrays[0]
          Assertions.assertThat(onlyByteArray.size).isEqualTo(1)
          val onlyByte = onlyByteArray.get(0)
          Assertions.assertThat(onlyByte).isEqualTo(EMPTY_ARRAY_FUNCTION)
        }
      }

      xit("uses the empty array function type when a flow with no bytes is passed") {

      }

    }
  }
}