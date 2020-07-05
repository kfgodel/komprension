package info.kfgodel.komprension.impl

import info.kfgodel.komprension.api.Compressor
import info.kfgodel.komprension.ext.byteBufferOf
import info.kfgodel.komprension.ext.emptyBuffer
import info.kfgodel.komprension.ext.plus
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.fold
import java.nio.ByteBuffer

/**
 * This type is the default implementation of a Komprension compressor
 * Date: 28/6/20 - 19:35
 */
class DefaultCompressor: Compressor {

  @ExperimentalCoroutinesApi
  override fun invoke(input: Flow<ByteBuffer>): Flow<ByteBuffer> {
    return flow {
      // Dumb impl that accumulates all bytes, decides output later
      val receivedBytes: ByteBuffer = input
        .fold(emptyBuffer()){ previous, next -> previous + next }

      when {
        !receivedBytes.hasRemaining() -> emit(byteBufferOf(EMPTY_FUNCTION)) // No byte was received
        repeatsSameNumber(receivedBytes) -> processConstant(receivedBytes)
        else -> processUncompressed(receivedBytes)
      }
    }
  }


  private suspend fun FlowCollector<ByteBuffer>.processUncompressed(receivedBytes: ByteBuffer) {
    val buffer = ByteBuffer.allocate(2 + receivedBytes.remaining())
    buffer.put(UNCOMPRESSED_FUNCTION)
    buffer.put(receivedBytes.remaining().toByte())
    buffer.put(receivedBytes)
    buffer.flip()
    emit(buffer)
  }

  private fun repeatsSameNumber(receivedBytes: ByteBuffer): Boolean {
    val firstByte = receivedBytes.get(receivedBytes.position())
    var comparedPosition = receivedBytes.position() + 1
    while(comparedPosition < receivedBytes.limit()){
      if(receivedBytes[comparedPosition] != firstByte){
        break
      }
      comparedPosition++
    }
    // Did we find a different value before reaching the end?
    return comparedPosition == receivedBytes.limit()
  }

  private suspend fun FlowCollector<ByteBuffer>.processConstant(receivedBytes: ByteBuffer) {
    val constantValue = receivedBytes[receivedBytes.position()]
    val count = receivedBytes.remaining().toByte()
    val parameterizedFunction = byteBufferOf(CONSTANT_FUNCTION, count, constantValue)
    emit(parameterizedFunction)
  }
}

