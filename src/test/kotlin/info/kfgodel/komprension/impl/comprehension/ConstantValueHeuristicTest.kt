package info.kfgodel.komprension.impl.comprehension

import info.kfgodel.jspek.api.JavaSpecRunner
import info.kfgodel.jspek.api.KotlinSpec
import info.kfgodel.komprension.ext.byteBufferOf
import info.kfgodel.komprension.ext.collectToByteArray
import info.kfgodel.komprension.impl.CONSTANT_FUNCTION
import info.kfgodel.komprension.impl.Komprenser
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.assertj.core.api.Assertions
import org.junit.runner.RunWith

/**
 * This class verifies that constant comprehension algorithm handles some cases correctly
 * Date: 9/7/20 - 17:17
 */
@ExperimentalCoroutinesApi
@RunWith(JavaSpecRunner::class)
class ConstantValueHeuristicTest : KotlinSpec() {
  override fun define() {
    describe("a constant value heuristic") {
      val compressor by let { Komprenser().compressor() }

      it("joins input chunk with same constant value") {
        val input = flowOf(byteBufferOf(5,5,5,5,5), byteBufferOf(5,5,5,5,5))
        val output = compressor().invoke(input)
        Assertions.assertThat(output.collectToByteArray())
          .containsExactly(CONSTANT_FUNCTION, 10 /*count*/, 5)
      }
    }
  }
}