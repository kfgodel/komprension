package info.kfgodel.komprension

import info.kfgodel.jspek.api.JavaSpecRunner
import info.kfgodel.jspek.api.KotlinSpec
import info.kfgodel.komprension.impl.EMPTY_ARRAY_FUNCTION
import info.kfgodel.komprension.impl.Komprenser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
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
        runBlocking {
          val onlyByteArray = ByteArray(1)
          onlyByteArray[0] = EMPTY_ARRAY_FUNCTION
          val output: Flow<ByteArray> = decompressor().invoke(mutableListOf(onlyByteArray).asFlow());
          val byteArrays = output.toList(mutableListOf())
          assertThat(byteArrays).isEmpty()
        }
      }

    }
  }
}