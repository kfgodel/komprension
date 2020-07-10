package info.kfgodel.komprension.impl.enumeration

import info.kfgodel.komprension.impl.memory.WorkingMemory
import java.nio.ByteBuffer

/**
 * This type represents the enumeration of elements in a set with duplicates of only 1 number
 * Date: 5/7/20 - 17:07
 */
class ConstantValueStrategy(private val memory: WorkingMemory) : EnumerationStrategy {

  override fun enumerate(): ByteBuffer {
    val input = memory.inputData()
    // First parameter is repetition count
    val byteCount = input.get().toInt()
    val buffer = ByteBuffer.allocate(byteCount)
    // 2nd parameter is the value
    val constantValue = input.get()
    for (i in 1..byteCount) {
      buffer.put(constantValue)
    }
    buffer.flip()
    return buffer
  }
}