package info.kfgodel.komprension.ext

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.fold
import kotlinx.coroutines.runBlocking

/**
 * Extension methods to ease testing of Flows
 * Date: 28/6/20 - 18:55
 */

/**
 * Collects all the byte arrays in the flow to a single joined array with the contents of each.<br>
 * Because the nature of this method, calling it may consume all available memory if the underlying flow
 * is not limited
 */
fun Flow<ByteArray>.collectToByteArray() = runBlocking {
  fold(ByteArray(0)) { buffer, chunk ->
    buffer.plus(chunk)
  }
}