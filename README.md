# Future-Assignment
This file contains the assignment of session 5 assignment 2

# Program to calculate average grades for each student and class using Future API

This program takes a path to a CSV file and returns a Future[Double] representing the class average. The program uses the Future API to perform calculations asynchronously.

The program includes the following functions:

## parseCsv

This function takes a path to the CSV file and returns a Future[List[Map[String, String]]] representing the parsed CSV data. The Map object contains the column name as the key and the cell value as the value. If the file does not exist or cannot be read, the Future fails with an appropriate error message.

## calculateStudentAverages

This function takes a Future[List[Map[String, String]]] representing the parsed CSV data and returns a Future[List[(String, Double)]] representing the average grade for each student. The List object contains a tuple for each student, where the first element is the student ID and the second element is the average grade. If the input Future fails, the calculateStudentAverages function returns a failed Future with the same error.

## calculateClassAverage

This function takes a Future[List[(String, Double)]] representing the student averages and returns a Future[Double] representing the class average. The class average is calculated as the arithmetic mean of the student averages. If the input Future fails, the calculateClassAverage function returns a failed Future with the same error.

## calculateGrades

This function uses the first three functions to calculate the class average. It takes a path to the CSV file and returns a Future[Double] representing the class average. The function uses the map, flatMap, and recover callbacks of the Future API to chain the asynchronous operations together and handle errors. Alternatively, it can also use for-comprehension to chain together multiple map and flatMap calls.
