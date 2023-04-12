package com.knoldus

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

object Driver extends App {
  private val csvFilePath = new StudentsData("src/main/scala/StudentsData.csv")
  private val futureData = csvFilePath.csvFileReader()

  private val studentAverageData = csvFilePath.calculateStudentAverages(futureData)
  private val classAverageData = csvFilePath.calculateClassAverage(studentAverageData)
  private val gradesData = csvFilePath.calculateGrades(futureData)

  studentAverageData.onComplete {
    case Failure(exception) => println(exception.getMessage)
    case Success(value) => println(s"Student Average Data: $value")
  }
  classAverageData.onComplete {
    case Failure(exception) => println(exception.getMessage)
    case Success(value) => println(s"Class Average Data: $value")
  }
  gradesData.onComplete {
    case Failure(exception) => println(exception.getMessage)
    case Success(value) => println(s"Grades Data: $value")
  }

}
