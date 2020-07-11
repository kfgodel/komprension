package info.kfgodel.komprension.impl.enumeration

import info.kfgodel.komprension.ext.forward
import info.kfgodel.komprension.impl.memory.WorkingMemory
import java.nio.ByteBuffer

/**
 * This type represents the enumeration of elements of a set where the elements are previously memorized
 * Date: 5/7/20 - 17:09
 */
class MemoryChunkStrategy(private val memory: WorkingMemory) : EnumerationStrategy {

  override fun enumerate(): ByteBuffer {
    val input = memory.inputData()
    // First parameter is size, which we ignore for now
    val chunkSize = input.get()
    // Create a view buffer limited to the declared size
    val originalData = input.slice()
    originalData.limit(chunkSize.toInt())
    // Consume the bytes from input buffer
    input.forward(chunkSize.toInt())
    return originalData //
  }
}