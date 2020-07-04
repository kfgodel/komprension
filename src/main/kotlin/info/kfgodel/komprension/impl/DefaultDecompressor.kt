package info.kfgodel.komprension.impl

import info.kfgodel.komprension.api.Decompressor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.fold

/**
 * This type is the default implementation of a Komprension decompressor
 * Date: 28/6/20 - 19:36
 */
class DefaultDecompressor : Decompressor  {
  override fun invoke(input: Flow<ByteArray>): Flow<ByteArray> {
    return flow {
      // Again, dumb accumulation of everything without knowing its size beforehand
      val receivedBytes = input
        .fold(ByteArray(0)) { previous, next -> previous + next }

      val functionType = receivedBytes[0]
      when(functionType){
        EMPTY_FUNCTION -> doNotEmit()
        UNCOMPRESSED_FUNCTION -> processUncompressed(receivedBytes)
        CONSTANT_FUNCTION -> processConstant(receivedBytes)
        else -> throw IllegalArgumentException("Unknown function type: $functionType")
      }
    }
  }

  private suspend fun <T> FlowCollector<T>.doNotEmit() {
    // We don't emit any array
  }

  private suspend fun FlowCollector<ByteArray>.processUncompressed(receivedBytes: ByteArray) {
    // First byte is the function type, and second the size which we ignore for now
    val originalData = receivedBytes.copyOfRange(2, receivedBytes.size)
    emit(originalData)
  }

  private suspend fun FlowCollector<ByteArray>.processConstant(receivedBytes: ByteArray) {
    // First byte is function type, second is the
    val count = receivedBytes[1].toInt()
    val constantByte = ByteArray(count)
    val constantValue = receivedBytes[2]
    constantByte.fill(constantValue)
    emit(constantByte)
  }
}

