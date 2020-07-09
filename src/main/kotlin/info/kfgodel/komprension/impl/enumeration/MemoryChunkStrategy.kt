package info.kfgodel.komprension.impl.enumeration

import info.kfgodel.komprension.impl.memory.WorkingMemory
import java.nio.ByteBuffer

/**
 * This type represents the enumeration of elements of a set where the elements are previously memorized
 * Date: 5/7/20 - 17:09
 */
class MemoryChunkStrategy(private val memory: WorkingMemory) : EnumerationStrategy {

  override fun enumerate(): ByteBuffer {
    val input = memory.getInput()
    // First byte is the function type, and second the size which we ignore for now
    input.position(2)
    return input // Reuse the buffer
  }
}