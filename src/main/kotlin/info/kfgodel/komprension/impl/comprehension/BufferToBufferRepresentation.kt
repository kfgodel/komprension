package info.kfgodel.komprension.impl.comprehension

import java.nio.ByteBuffer

/**
 * This type is a representation where a buffer of bytes represents another bigger buffer
 * Date: 10/7/20 - 22:08
 */
class BufferToBufferRepresentation(
  private val inputData: ByteBuffer,
  private val outputData: ByteBuffer
) : ByteRepresentation {

  override fun outputData(): ByteBuffer {
    return outputData
  }

  override fun inputSize(): Int {
    return inputData.position()
  }


}