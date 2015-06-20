import scalariform.formatter.preferences._

name := "scala-ssh"

version := "0.7.0"

organization := "com.decodified"

organizationHomepage := Some(new URL("http://decodified.com"))

description := "A Scala library providing remote shell access via SSH"

homepage := Some(new URL("https://github.com/sirthias/scala-ssh"))

startYear := Some(2011)

licenses := Seq("Apache 2" -> new URL("http://www.apache.org/licenses/LICENSE-2.0.txt"))

scalaVersion := "2.10.4"

scalacOptions <<= scalaVersion map {
  case x if x startsWith "2.9" =>
    Seq("-unchecked", "-deprecation", "-encoding", "utf8")
  case x if x startsWith "2.1" =>
    Seq("-feature", "-language:implicitConversions", "-unchecked", "-deprecation", "-encoding", "utf8")
}


libraryDependencies ++= Seq(
  "net.schmizz" % "sshj" % "0.10.0",
  "org.slf4j" % "slf4j-api" % "1.7.7",
  "org.bouncycastle" % "bcprov-jdk16" % "1.46" % "provided",
  "com.jcraft" % "jzlib" % "1.1.3" % "provided",
  "ch.qos.logback" % "logback-classic" % "1.1.2" % "test",
  "org.specs2" %% "specs2" % "2.4.6" % "test")

libraryDependencies <++= scalaVersion {
  case x if x startsWith "2.9" =>
    Seq("org.specs2" %% "specs2" % "[1.12.4,)" % "test")
  case x if x startsWith "2.1" =>
    Seq("org.specs2" %% "specs2" % "2.3.11" % "test")
}

resolvers += "Scalaz Bintray Repo" at "http://dl.bintray.com/scalaz/releases"


///////////////
// publishing
///////////////

crossScalaVersions := Seq("2.9.3", "2.10.4", "2.11.0")

scalaBinaryVersion <<= scalaVersion(sV => if (CrossVersion.isStable(sV)) CrossVersion.binaryScalaVersion(sV) else sV)

publishMavenStyle := true

publishTo <<= version { version =>
  Some {
    "spray repo" at {
      // public uri is repo.spray.io, we use an SSH tunnel to the nexus here
      "http://localhost:42424/content/repositories/" + {
        if (version.trim.endsWith("SNAPSHOT")) "snapshots/" else "releases/"
      }
    }
  }
}

pomIncludeRepository := { _ => false }

pomExtra :=
  <scm>
    <url>git@github.com:sirthias/scala-ssh.git</url>
    <connection>scm:git:git@github.com:sirthias/scala-ssh.git</connection>
  </scm>
  <developers>
    <developer>
      <id>sirthias</id>
      <name>Mathias Doenitz</name>
    </developer>
  </developers>
