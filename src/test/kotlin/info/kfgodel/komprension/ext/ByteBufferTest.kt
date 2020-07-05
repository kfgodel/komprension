package info.kfgodel.komprension.ext

import info.kfgodel.jspek.api.JavaSpecRunner
import info.kfgodel.jspek.api.KotlinSpec
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.assertj.core.api.Assertions.assertThat
import org.junit.runner.RunWith

/**
 * This test verifies extensions added to ByteBuffer
 * Date: 6/5/20 - 00:49
 */
@ExperimentalCoroutinesApi
@RunWith(JavaSpecRunner::class)
class ByteBufferTest : KotlinSpec() {
  override fun define() {
    describe("a ByteBuffer") {
      val buffer by let { byteBufferOf(1, 2 ,3) }

      it("can be summed to other buffer to be joined") {
        val result = buffer() + byteBufferOf(4, 5, 6)
        assertThat(result.position()).isEqualTo(0)
        assertThat(result.limit()).isEqualTo(6)
        assertThat(result).isEqualTo(byteBufferOf(1, 2, 3, 4 ,5, 6))
      }

      it("allows getting remaining bytes as an array") {
        buffer().position(1)
        assertThat(buffer().getByteArray()).isEqualTo(byteArrayOf(2, 3))
      }

    }
  }
}