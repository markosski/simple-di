import Dependencies._

ThisBuild / scalaVersion     := "2.13.4"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "simple-di"

lazy val root = (project in file("."))
  .settings(
    name := "simple-di",
    libraryDependencies ++= Seq(
      Dependencies.scalaTest,
    ),
    scalacOptions ++= Seq(
      "-deprecation"
    )
  )
