package info.kfgodel.komprension.impl.enumeration

import java.nio.ByteBuffer

/**
 * This type represents the enumeration of elements in a set with duplicates of only 1 number
 * Date: 5/7/20 - 17:07
 */
class ConstantEnumeration : SetEnumeration {
  override fun enumerate(input: ByteBuffer): ByteBuffer {
    // First byte is function type, second is the repetition count
    val byteCount = input[1].toInt()
    val buffer = ByteBuffer.allocate(byteCount)
    val constantValue = input[2]
    for (i in 1..byteCount) {
      buffer.put(constantValue)
    }
    buffer.flip()
    return buffer
  }
}