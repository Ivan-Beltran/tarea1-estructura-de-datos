package org.example.modules

import java.util.stream.IntStream.range

interface IBaseNumber{
    val value:Int
    fun printValue()
}
class PrimeNumber(override val value: Int):IBaseNumber{
    override fun printValue(){
        println("numero primo:${value}")
    }
}

class OddNumber(override val value: Int):IBaseNumber{
    private val divisors: List<Int> = ShowDivisors(value)
    override fun printValue() {
        println("numero impar:${value} ")
        println("Divisores: $divisors")
    }

    private fun ShowDivisors(num:Int): List<Int>{
        return (1..num).filter { num % it ==0 }
    }
}

class EvenNumber(override val value: Int):IBaseNumber{
    private val divisors: List<Int> = ShowDivisors(value)
    override fun printValue() {
        print("numero par:${value} ")
        println("Divisores: $divisors")
    }

    private fun ShowDivisors(num:Int): List<Int>{
        return (1..num).filter { num % it ==0 }
    }
}


class PrimeNumberProcessor(val range:IntRange){
    data class EvaluationResult(
        val primes: List<PrimeNumber>,
        val odds: List<OddNumber>,
        val evens: List<EvenNumber>
    )

    private fun isPrime(number:Int):Boolean{
        if(number<2){
            return false
        }
        for(i in 2 until  number){
            if(number%i==0) return false
        }
        return true
    }

    private fun ValidateNumber(number: Int):List<NumberType>{
        val types= mutableListOf<NumberType>()
        if(isPrime(number)){
            types.add(NumberType.PRIME)
        }
        if(number%2==0){
            types.add(NumberType.EVEN)
        }
        if(number %2 !=0){
            types.add((NumberType.ODD))
        }
        return types
    }

    fun process():EvaluationResult{
        val primes = mutableListOf<PrimeNumber>()
        val odds = mutableListOf<OddNumber>()
        val evens = mutableListOf<EvenNumber>()

        for (number in range){
            val types=ValidateNumber(number)
            if(NumberType.PRIME in types){
                primes.add(PrimeNumber(number))
            }
            if (NumberType.ODD in types) {
                odds.add(OddNumber(number))
            }
            if (NumberType.EVEN in types) {
                evens.add(EvenNumber(number))
            }

        }
        return EvaluationResult(primes,odds,evens)
    }

    private enum class NumberType{
        PRIME,
        ODD,
        EVEN
    }
}

