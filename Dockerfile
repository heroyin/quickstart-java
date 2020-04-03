FROM maven:3.6.3-jdk-8 

# Create project directory (workdir)
RUN mkdir -p /java/src/app
WORKDIR /java/src/app

# Add source code files to WORKDIR
ADD . .
RUN chmod +x CompileDaemon && mv ./CompileDaemon /usr/bin/

# Install dependencies
#RUN mvn compile 

# Build application (test if everything works)
RUN mvn package -U -Dmaven.test.skip=true

EXPOSE 8080

# Start CompileDaemon for hot reloading (will watch for file changes and then rebuild & restart the application)
ENTRYPOINT CompileDaemon -log-prefix=false -color=true -pattern="(.+\.java|.+\.c)$" -build="mvn package -U -D maven.test.skip=true" -command="java -jar ./target/quickstart-1.0-shaded.jar"

