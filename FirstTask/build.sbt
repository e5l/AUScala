name := "FirstTask"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.0"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.0" % "test"
libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.12.5" % "test"
libraryDependencies += "org.specs2" %% "specs2-core" % "3.8.5" % "test"
libraryDependencies += "org.specs2" %% "specs2-scalacheck" % "3.8.5" % "test"
libraryDependencies += "org.specs2" %% "specs2-mock" % "3.8.5" % "test"

scalacOptions in Test ++= Seq("-Yrangepos")


