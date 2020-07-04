package info.kfgodel.komprension.impl

import info.kfgodel.komprension.api.Compressor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.fold

/**
 * This type is the default implementation of a Komprension compressor
 * Date: 28/6/20 - 19:35
 */
class DefaultCompressor: Compressor {
  override fun invoke(input: Flow<ByteArray>): Flow<ByteArray> {
    return flow {
      // Dumb impl that accumulates all bytes, decides output later
      val receivedBytes: ByteArray = input
        .fold(ByteArray(0)){ previous, next -> previous + next }

      when {
        receivedBytes.isEmpty() -> emit(byteArrayOf(EMPTY_FUNCTION)) // No byte was received
        repeatsSameNumber(receivedBytes) -> processConstant(receivedBytes)
        else -> processUncompressed(receivedBytes)
      }
    }
  }


  private suspend fun FlowCollector<ByteArray>.processUncompressed(receivedBytes: ByteArray) {
    val header = byteArrayOf(UNCOMPRESSED_FUNCTION, receivedBytes.size.toByte())
    emit(header + receivedBytes)
  }

  private fun repeatsSameNumber(receivedBytes: ByteArray): Boolean {
    val firstByte = receivedBytes[0]
    return receivedBytes.all { byte -> byte == firstByte }
  }

  private suspend fun FlowCollector<ByteArray>.processConstant(receivedBytes: ByteArray) {
    val constantValue = receivedBytes[0]
    val count = receivedBytes.size.toByte()
    val parameterizedFunction = byteArrayOf(CONSTANT_FUNCTION, count, constantValue)
    emit(parameterizedFunction)
  }
}

