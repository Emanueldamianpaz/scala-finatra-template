name := "scala-crawler"
version := "0.1"
scalaVersion := "2.12.11"

resolvers += Resolver.mavenLocal

libraryDependencies ++= Seq(
  "com.twitter" %% "finatra-http" % "20.4.1",
  "org.slf4j" % "slf4j-api" % "1.7.16",
  "org.slf4j" % "log4j-over-slf4j" % "1.7.16",
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "com.squareup.okhttp3" % "okhttp" % "4.2.2",

  "org.junit.jupiter" % "junit-jupiter-engine" % "5.2.0" % Test,
  "org.junit.jupiter" % "junit-jupiter-params" % "5.5.2" % Test
)
