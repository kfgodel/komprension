package info.kfgodel.komprension.impl.comprehension

import info.kfgodel.komprension.ext.byteBufferOf
import info.kfgodel.komprension.impl.CONSTANT_FUNCTION
import java.nio.ByteBuffer

/**
 * This type represents the comprehension for input that can be represents by a constant function
 *
 * Date: 5/7/20 - 16:30
 */
class ConstantComprehension : SetComprehension {

  private var constantValue: Byte? = null
  private var repetitionCount: Byte = 0

  override fun updateWith(input: ByteBuffer) {
    if(!input.hasRemaining()){
      return // No input to take a constant from
    }
    constantValue = input.get(input.position())
    var comparedPosition = input.position() + 1
    while(comparedPosition < input.limit()){
      if(input[comparedPosition] != constantValue){
        break
      }
      comparedPosition++
    }
    // Did we find a different value before reaching the end?
    if(comparedPosition < input.limit()){
      constantValue = null
    }else{
      repetitionCount = comparedPosition.toByte()
    }
  }

  override fun comprehend(): ByteBuffer? {
    if(constantValue == null){
      return null
    }
    return byteBufferOf(CONSTANT_FUNCTION, repetitionCount, constantValue!!)
  }
}