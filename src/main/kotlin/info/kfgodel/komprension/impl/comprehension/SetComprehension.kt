package info.kfgodel.komprension.impl.comprehension

import java.nio.ByteBuffer

/**
 * This type represents an ongoing comprehension where the input data is analyzed to be expressible as a set
 * of elements defined by a particular function and its parameters.<br>
 * The result of the comprehension is a buffer of bytes representing the function and its parameters.
 * Using a SetEnumeration the buffer will reproduce the original input.<br>
 * Because no every function can reproduce any input, this comprehension may yield no result if the
 * underlying function cannot be parameterized to reproduce the input data.
 *
 * Date: 5/7/20 - 15:45
 */
interface SetComprehension{

  /**
   * Updates the parameters of the underlying function so it can reproduce the received input
   * as part of its expansion output
   */
  fun updateWith(input: ByteBuffer)

  /**
   * Tries to generate a buffer representation of the underlying function and its parameters (if possible).
   * If not, it returns null
   */
  fun comprehend() : ByteBuffer?
}