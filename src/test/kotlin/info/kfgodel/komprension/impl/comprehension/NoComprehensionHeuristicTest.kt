package info.kfgodel.komprension.impl.comprehension

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
 * This class verifies that no comprehension algorithm handles some cases correctly
 * Date: 9/7/20 - 17:17
 */
@ExperimentalCoroutinesApi
@RunWith(JavaSpecRunner::class)
class NoComprehensionHeuristicTest : KotlinSpec() {
  override fun define() {
    describe("a no comprehension heuristic") {
      val compressor by let { Komprenser().compressor() }

      it("joins input chunks into a single output chunk") {
        val input = flowOf(byteBufferOf(2, 6, 3, 10, 23), byteBufferOf(110, 84, 91, -44, -43))
        val output = compressor().invoke(input)
        Assertions.assertThat(output.collectToByteArray())
          .containsExactly(UNCOMPRESSED_FUNCTION, 10 /*size*/, 2, 6, 3, 10, 23, 110, 84, 91, -44, -43)
      }
    }
  }
}