# spray-scala
This is basically the same example as spark-scala. The main differences are in the routing. Spray is also pretty 
awesome once you know Scala, Akka and of course the Spray API.

# Starting
This project uses the Typesafe Activator Launcher, only a Java 6 or higher must be installed on your computer and 
the activator-laucher will do the rest:

    $ ./activator 'run-main com.example.Main'

# Example configuration
The com.example.Main class has the configuration for the routing:

    startServer(interface = "localhost", port = 8080) {
        pathPrefix("hello") {
          pathEnd {
            get {
              complete("Got a get")
            } ~
            post {
              complete("Got a post")
            } ~
            put {
              complete("Got a put")
            } ~
            delete {
              complete("Got a delete")
            }
          } ~
          path(Segment) { name =>
            complete(s"Hello: $name")
          }
        } ~
        path("ping") {
          get {
            complete {
              (pingActor ? "ping").mapTo[String]
            }
          }
        }
      }
          
# Actors
Spray works on top of Akka, so you can communicate with Actors, and of course, that is what we want.

# Httpie
Install [httpie](https://github.com/jakubroztocil/httpie), a great tool by [Jakub Roztočil](https://github.com/jakubroztocil) 
for testing your REST services, like spray or in this case, SparkJava! 

    http GET http://localhost:8080/ping
    
# Conclusion
The API of Spray is more complex than the Java variant, but both use the same concepts, a route, directives, filters,
and the number of lines are the same. Spray uses Akka for its non-blocking nature, and therefor I would advice, also
for simple projects, to use Spray. Its one-jar footprint is 14MB to start with, but now a days, whats 14MB.
    
Have fun!