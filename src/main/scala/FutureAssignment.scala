package com.knoldus

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.io.Source

class StudentsData(filePath: String) {
  def csvFileReader(): Future[List[Map[String, String]]] = {
    try {
      // Read the contents of the file as a list of lines
      val fileContents: List[String] = Source.fromFile(filePath).getLines.toList

      // Split each line into a list of values
      val rowsContent: List[List[String]] = fileContents.map(_.split(",").toList)

      // Extract the headerRowData row
      val headersData: List[String] = rowsContent.head

      // Convert the remaining rowsContent into a list of maps
      val studentsData: List[Map[String, String]] = rowsContent.tail.map(row => headersData.zip(row).toMap)
      
      // Return the studentsData wrapped in a Future
      Future.successful(studentsData)
    } catch {
      case exception: Exception => Future.failed(new Exception(s"Error in reading the file: ${exception.getMessage}"))
    }
  }


  def calculateStudentAverages(data: Future[List[Map[String, String]]]): Future[List[(String, Double, String)]] = {
    data.map { studentsDataList =>
      studentsDataList.map { studentData =>
        val studentId = studentData("StudentID")
        val marks = List(studentData("English"), studentData("Physics"), studentData("Chemistry"), studentData("Maths")).map(_.toDouble)
        val averageMarks = marks.sum / marks.length
        val letterGrade = if (averageMarks >= 90) "Grade-A"
        else if (averageMarks >= 80) "Grade-B"
        else if (averageMarks >= 70) "Grade-C"
        else if (averageMarks >= 60) "Grade-D"
        else "F"
        (studentId, averageMarks, letterGrade)
      }
    }
  }

  def calculateClassAverage(studentAverages: Future[List[(String, Double, String)]]): Future[Double] = {
    studentAverages.map { averages =>
      val totalMarks = averages.map(_._2).sum
      val countOfStudents = averages.length
      totalMarks / countOfStudents
    }
  }

  def calculateGrades(data: Future[List[Map[String, String]]]): Future[Double] = {
    val studentAverages = calculateStudentAverages(data)
    calculateClassAverage(studentAverages).recover {
      case exception: Exception => throw new Exception(s"Error in calculating class average: ${exception.getMessage}")
    }
  }

}

