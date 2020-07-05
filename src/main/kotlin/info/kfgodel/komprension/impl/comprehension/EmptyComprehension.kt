package info.kfgodel.komprension.impl.comprehension

import info.kfgodel.komprension.ext.byteBufferOf
import info.kfgodel.komprension.impl.EMPTY_FUNCTION
import java.nio.ByteBuffer

/**
 * This type represents the comprehension that can describe the empty set
 * Date: 5/7/20 - 16:14
 */
class EmptyComprehension : SetComprehension {

  private var buffer: ByteBuffer? = byteBufferOf(EMPTY_FUNCTION)

  override fun updateWith(input: ByteBuffer) {
    if(input.remaining() > 0){
      // Is not empty, cannot represent input
      buffer = null
    }
  }

  override fun comprehend(): ByteBuffer? {
    return buffer
  }
}