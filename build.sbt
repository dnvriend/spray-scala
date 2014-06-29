name := "spay-scala"

version := "1.0.0"

scalaVersion := "2.10.4"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.3.3",
  "com.typesafe.akka" %% "akka-slf4j" % "2.3.3",
  "com.typesafe" % "config" % "1.2.0",
  "io.spray" % "spray-http" % "1.3.1",
  "io.spray" % "spray-httpx" % "1.3.1",
  "io.spray" % "spray-routing" % "1.3.1",
  "io.spray" % "spray-util" % "1.3.1",
  "io.spray" % "spray-io" % "1.3.1",
  "io.spray" % "spray-can" % "1.3.1",
  "com.chuusai" %% "shapeless" % "1.2.4"
)

com.github.retronym.SbtOneJar.oneJarSettings