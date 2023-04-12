ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"

lazy val root = (project in file("."))
  .settings(
    name := "Session5Assignment2",
    idePackagePrefix := Some("com.knoldus")
  )
libraryDependencies += "com.github.tototoshi" %% "scala-csv" % "1.3.8"
