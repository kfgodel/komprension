package info.kfgodel.komprension.impl.enumeration

import java.nio.ByteBuffer

/**
 * This type represents a set enumeration based on a particular function. It generates the elements of the set as bytes
 * using the received function parameters.
 *
 * Date: 5/7/20 - 16:07
 */
interface SetEnumeration {

  /**
   * Updates the parameters of the underlying function so it can reproduce the elements of the set as bytes
   */
  fun updateWith(input: ByteBuffer)

  /**
   * Generates a buffer with the elements of the set enumerated as bytes
   */
  fun enumerate() : ByteBuffer
}