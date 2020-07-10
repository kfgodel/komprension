package info.kfgodel.komprension.impl.comprehension

import info.kfgodel.komprension.impl.UNCOMPRESSED_FUNCTION
import info.kfgodel.komprension.impl.memory.WorkingMemory
import java.nio.ByteBuffer

/**
 * This class represents the lack of comprehension where the input is reproduced by memorizing the original bytes
 * instead of using a function to represent it.
 *
 * Date: 5/7/20 - 16:18
 */
class NoComprehensionHeuristic(private val memory: WorkingMemory) : ComprehensionHeuristic {

  override fun comprehend(): ByteBuffer? {
    val receivedBytes =memory.inputData()
    val buffer = ByteBuffer.allocate(2 + receivedBytes.remaining())
    buffer.put(UNCOMPRESSED_FUNCTION)
    buffer.put(receivedBytes.remaining().toByte())
    buffer.put(receivedBytes)
    buffer.flip()
    return buffer
  }

}