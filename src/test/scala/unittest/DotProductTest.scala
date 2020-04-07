package unittest

import chisel3._
import chisel3.util._
import chisel3.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}

import scala.util.Random
import unittest.util._
import vta.core._

import scala.math.pow

class DotProductTest(c: DotProduct) extends PeekPokeTester(c) {

  def dotproduct(inp: Array[Int], wgt: Array[Int]) : Int = {
    var dot:Int = 0
    for (i <- 0 until inp.size) {
      dot += wgt(i) * inp(i)
    }
    return dot
  }
  //val a = new RandomArray(4,32)
  //val aa = a.any
  //val bb = a.any

  val aa = Array(1,2,3,4,5,6,7,8)
  val bb = Array(1,2,3,4,5,6,7,8)
  for(i <- 0 until aa.size) {
    poke(c.io.a(i), aa(i))
    poke(c.io.b(i), bb(i))
  }
  val res = dotproduct(aa,bb)
  step(1)
  step(1)
  expect(c.io.y,res)

}

