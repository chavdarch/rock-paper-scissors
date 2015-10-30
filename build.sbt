lazy val root = (project in file("."))

name := "rock-paper-scissor"

version := "1.0.0"

scalaVersion := "2.11.7"

javacOptions ++= Seq("-source", "1.8", "-target", "1.8")

scalacOptions ++= Seq(
 "-deprecation" //Emit warning of usage of deprecated APIs
 , "-feature" //Emit warning for usages of features that should be imported explicitly
 , "-unchecked" // Enable additional warning when generated code depends on assumptions
 , "-Xfatal-warnings" //Fail the compilation if there are any warnings
 , "-Xlint:_" // Enable specific warnings
)

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.2.5" % "test",
  "org.scalacheck" %% "scalacheck" % "1.12.5" % "test"
)

coverageEnabled := true
