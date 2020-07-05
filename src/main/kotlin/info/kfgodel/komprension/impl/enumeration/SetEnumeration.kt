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
   * Generates a buffer with the elements of the set enumerated as bytes, taking the input as parameters
   * for the function to enumerate.<br>
   */
  fun enumerate(input: ByteBuffer) : ByteBuffer
}