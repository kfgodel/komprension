package info.kfgodel.komprension.impl.enumeration

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
 * This class verifies that constant enumeration algorithm handles some cases correctly
 * Date: 9/7/20 - 17:17
 */
@ExperimentalCoroutinesApi
@RunWith(JavaSpecRunner::class)
class ConstantEnumerationTest : KotlinSpec() {
  override fun define() {
    describe("a constant enumeration") {
      val decompressor by let { Komprenser().decompressor() }

      it("can handle a header split into different input chunks") {
        val input = flowOf(byteBufferOf(CONSTANT_FUNCTION, 10 /*count*/), byteBufferOf(5))
        val output = decompressor().invoke(input)
        Assertions.assertThat(output.collectToByteArray()).containsExactly(5, 5, 5, 5, 5,  5, 5, 5, 5, 5)
      }
    }
  }
}