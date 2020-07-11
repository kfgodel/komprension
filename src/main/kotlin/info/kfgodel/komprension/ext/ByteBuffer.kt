package info.kfgodel.komprension.ext

import java.nio.ByteBuffer

/**
 * Extensions to ByteBuffer in order to reduce verbosity
 * Date: 5/7/20 - 01:27
 */

/**
 * Creates an empty byte buffer
 */
fun emptyBuffer(): ByteBuffer {
  return ByteBuffer.allocate(0)
}

/**
 * Creates a byte buffer from the bytes that make its content
 */
fun byteBufferOf(vararg bytes: Byte): ByteBuffer {
  return ByteBuffer.wrap(bytes)
}

/**
 * Creates a slice of this buffer starting at the given position
 */
fun ByteBuffer.sliceFrom(position: Int) : ByteBuffer {
  this.position(position)
  return this.slice()
}

/**
 * Creates a buffer with the contents of this with the other passed
 */
operator fun ByteBuffer.plus(other: ByteBuffer): ByteBuffer {
  val buffer = ByteBuffer.allocate(this.remaining() + other.remaining())
    .put(this)
    .put(other)
  buffer.flip() // Let it ready to be read
  return buffer
}

/**
 * Reads the remaining bytes in this buffer into a byte array
 * (from current position to the limit)
 */
fun ByteBuffer.getByteArray(): ByteArray {
  val extracted = ByteArray(this.remaining())
  this.get(extracted)
  return extracted
}

/**
 * Moves the position forward (positively) as many steps as indicated
 */
fun ByteBuffer.forward(positions: Int) : ByteBuffer {
  this.position(this.position() + positions)
  return this
}

/**
 * Gets the byte on the current position without changing it
 */
fun ByteBuffer.getByte() : Byte {
  return this.get(this.position())
}