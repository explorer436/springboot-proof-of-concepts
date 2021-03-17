Commands:

To check if docker is installed, try to check for its version: `docker --version`

To check if Docker Client is talking to Docker Daemon and that Docker Daemon is talking to Docker Hub successfully: `docker run hello-world`

A Docker image in DockerHub is a static template - a set of bytes.

Docker Container - a running version of the image. For the same image, we can have multiple containers running.

Image is like a class and container is like an object based on that class.

```
docker run -p 5000:5000 nameOfTheDockerRepository/nameOfTheApplication
docker run -p {HostPort}:{ContainerPort} nameOfTheDockerRepository/nameOfTheApplication
```
 
(docker run is a shortcut for docker container run)

To pause a container: `docker container pause containerId`
To unpause a container: `docker container unpause containerId`

To inspect a container: `docker container inspect containerId`

To remove all the stopped containers: `docker container prune`

Every container that runs is a part of the bridge network in Docker - it is an internal Docker network. Nobody will be able to access it unless we specifically expose it on to the host system where the container is running.

If we want the container to be running in the background, use the `-d` option.This way, the terminal is not tied to the lifecycle of the container. Detatched mode:`docker run -p 5000:5000 -d nameOfTheDockerRepository/nameOfTheApplication`

To look at logs for a container that is running: `docker logs containerId` (you don't have to type the entire id. A subset of the id will do)

To follow the logs: `docker logs -f containerId`

To list the running containers: `docker container ls`
To list the running and terminated containers: `docker container ls -a`

To list all the images that are pulled into the machine from DockerHub: `docker images`

To stop a container based on containerId : `docker container stop containerId`
This will give the application that is running in the container some time to gracefully shut itself down. e.g. close connection pools, close executorService, etc.
stop -> SIGTERM -> graceful shutdown

To kil a container based on containerId : `docker container kill containerId`
Kill the container and the application that is running in it right away.
kill -> SIGKILL  -> immediately terminate the process

To pull mysql image from DockerHub into our machine: `docker pull mysql`

To view the history of a Docker image: `docker image history imageId`
To inspect the history of a Docker image: `docker image inspect imageId`
To remove a Docker image from local machine: `docker image remove imageId`

To view the events happening in docker: `docker events`

To display the processes running in a container: `docker top containerId`

To display a live stream of container(s) resource usage statistics: `docker stats`

When there is "not enough space" error from Docker: docker system prune -a
