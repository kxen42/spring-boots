# async-producer-consumer

These are supposed to be a pair aysnc producer and it's consumer.
It runs on http://localhost:8089.

Each producer endpoint has a differenct time delay before responding.
The consumer sends out requests to the /a, /b, and /c endpoints, and expects to 
order the messages such that the end results is the message "Hello World !".

It uses the deprecated AsyncRestTemplate, but that's not the point of the exercise.


Uses Spring Boot 2.
