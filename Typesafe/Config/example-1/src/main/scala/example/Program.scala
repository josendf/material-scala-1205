package org.example
import com.typesafe.config._

object MyApp extends App {

  run()

  def run() {
    val conf = ConfigFactory.load()

    val valueOne = conf.getInt("myapp.value_one")
    println("value_one:   " + valueOne)

    val valueTwo = conf.getString("myapp.value_two")
    println("value_two:   " + valueTwo)

    val valueThree = conf.getList("myapp.value_three")
    println("value_three: " + valueThree)
  }
}
