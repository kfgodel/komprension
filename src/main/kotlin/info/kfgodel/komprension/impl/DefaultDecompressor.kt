package info.kfgodel.komprension.impl

import info.kfgodel.komprension.api.Decompressor
import info.kfgodel.komprension.ext.emptyBuffer
import info.kfgodel.komprension.ext.plus
import info.kfgodel.komprension.ext.sliceFrom
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.fold
import java.nio.ByteBuffer

/**
 * This type is the default implementation of a Komprension decompressor
 * Date: 28/6/20 - 19:36
 */
class DefaultDecompressor : Decompressor  {
  @ExperimentalCoroutinesApi
  override fun invoke(input: Flow<ByteBuffer>): Flow<ByteBuffer> {
    return flow {
      // Again, dumb accumulation of everything without knowing its size beforehand
      val receivedBytes = input
        .fold(emptyBuffer()) { previous, next -> previous + next }

      val functionType = receivedBytes[0]
      when(functionType){
        EMPTY_FUNCTION -> doNotEmit()
        UNCOMPRESSED_FUNCTION -> processUncompressed(receivedBytes)
        CONSTANT_FUNCTION -> processConstant(receivedBytes)
        else -> throw IllegalArgumentException("Unknown function type: $functionType")
      }
    }
  }

  private fun doNotEmit() {
    // We don't emit any array
  }

  private suspend fun FlowCollector<ByteBuffer>.processUncompressed(receivedBytes: ByteBuffer) {
    // First byte is the function type, and second the size which we ignore for now
//    receivedBytes.position(2)
//    emit(receivedBytes) // Reuse the buffer
    val originalData = receivedBytes.sliceFrom(2)
    emit(originalData)
  }

  private suspend fun FlowCollector<ByteBuffer>.processConstant(receivedBytes: ByteBuffer) {
    // First byte is function type, second is the
    val byteCount = receivedBytes[1].toInt()
    val buffer = ByteBuffer.allocate(byteCount)
    val constantValue = receivedBytes[2]
    for (i in 1..byteCount) {
      buffer.put(constantValue)
    }
    buffer.flip()
    emit(buffer)
  }
}


