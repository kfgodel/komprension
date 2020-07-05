package info.kfgodel.komprension.impl

import info.kfgodel.komprension.api.Compressor
import info.kfgodel.komprension.ext.emptyBuffer
import info.kfgodel.komprension.ext.plus
import info.kfgodel.komprension.impl.comprehension.ConstantComprehension
import info.kfgodel.komprension.impl.comprehension.EmptyComprehension
import info.kfgodel.komprension.impl.comprehension.NoComprehension
import info.kfgodel.komprension.impl.comprehension.SetComprehension
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
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

      val smallestComprehension = getAvailableComprehensions()
        .onEach { comp -> comp.updateWith(receivedBytes.slice()) }
        .map { comp -> comp.comprehend() }
        .filterNotNull()
        .reduce { aResult, otherResult -> if (aResult.remaining() < otherResult.remaining()) aResult else otherResult }
      emit(smallestComprehension)
    }
  }

  private fun getAvailableComprehensions(): Sequence<SetComprehension> {
    return sequenceOf(EmptyComprehension(), ConstantComprehension(), NoComprehension())
  }

}

