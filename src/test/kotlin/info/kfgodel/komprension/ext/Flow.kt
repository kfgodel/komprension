package info.kfgodel.komprension.ext

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.fold
import kotlinx.coroutines.runBlocking
import java.nio.ByteBuffer

/**
 * Extension methods to ease testing of Flows
 * Date: 28/6/20 - 18:55
 */

/**
 * Collects all the byte arrays in the flow to a single joined array with the contents of each.<br>
 * Because the nature of this method, calling it may consume all available memory if the underlying flow
 * is not limited
 */
@ExperimentalCoroutinesApi
fun Flow<ByteBuffer>.collectToByteArray(): ByteArray = runBlocking {
  fold(ByteArray(0)) { array, buffer ->
    array.plus(buffer.getByteArray())
  }
}

/**
 * Creates a flow of bytebuffer describing each byte.<br>
 *   Using this function the flow will contain only 1 buffer
 */
fun flowOfByteBuffer(vararg bytes: Byte): Flow<ByteBuffer> {
  return flowOf(byteBufferOf(*bytes))
}