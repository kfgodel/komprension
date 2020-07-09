package info.kfgodel.komprension.impl.memory

import info.kfgodel.komprension.ext.plus
import java.nio.ByteBuffer

/**
 * This memory is backed by a byte buffer where input is appended
 * Date: 9/7/20 - 17:14
 */
class BufferedMemory : WorkingMemory {
  private var buffer:ByteBuffer = ByteBuffer.allocate(0) //Start empty

  override fun include(inputChunk: ByteBuffer) {
    buffer += inputChunk
  }

  override fun getInput(): ByteBuffer {
    return buffer
  }


}