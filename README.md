# song-service

## Implements REST API CRUD service for Song record (metadata) management. Provides managing capabilities for maintaining some metadata about songs (artist, album, etc). It uses `postgresql` database for storing data.

### Startup

  1. Since it is a maven project, first build it using your local installed maven or using maven wrapper inside the project
     ```bash
      mvn clean package
     ```
      or using maven wrapper
      ```bash
      mvnw clean package
      ```
     NOTE! - **prerequiste is that you install docker engine on your machine, as well as *docker-compose* **
  
  2. Inside project root, build local docker image of the project(project already contains prepared `Dockerfile`
     ```bash
     docker build -t songs-service-local-image .
     ```
     
  3. After checking that the image is properly built and ready, start `songs-service` and it's corresponding `postgresql` database container.
     ```bash
     docker-compose up -d
     ```
    
  4. Finally you can chech if the containers are running and check logs to if they are running properly
     - check are they running
       ```bash
       docker ps
       ```
     - check are they running properly by checking logs (**container_id** - value listed after running previous command)
       ```bash
       docker logs -f *container_id* 
       ```
       
