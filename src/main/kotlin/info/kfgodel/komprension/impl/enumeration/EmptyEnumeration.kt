package info.kfgodel.komprension.impl.enumeration

import java.nio.ByteBuffer

/**
 * This type represents the element enumeration for the empty set (no bytes)
 * Date: 5/7/20 - 17:04
 */
class EmptyEnumeration : SetEnumeration  {
  override fun enumerate(input: ByteBuffer): ByteBuffer {
    return ByteBuffer.allocate(0)
  }
}