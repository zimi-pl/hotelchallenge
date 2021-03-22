# Intro

Project created with Gradle and Spring Boot. So pretty straightforward to use.
Convention over configuration ;)
Package are splitted according to hexagonal architecture.
Due to small number of classes in domain I put that in one package.
External stuff as for example controller in "plugins".

# Run
`gradlew bootRun`

# Test

`gradlew test`

# Test case 1

`curl --location --request POST 'http://localhost:8080/reserve' --header 'Content-Type: application/json' --data-raw '{
"emptyPremiumRooms" : 3,
"emptyEconomyRooms" : 3,
"offers" : [23, 45, 155, 374, 22, 99, 100, 101, 115, 209]
}'`

# Test case 2

`curl --location --request POST 'http://localhost:8080/reserve' --header 'Content-Type: application/json' --data-raw '{
"emptyPremiumRooms" : 7,
"emptyEconomyRooms" : 5,
"offers" : [23, 45, 155, 374, 22, 99, 100, 101, 115, 209]
}'`

# Test case 3

`curl --location --request POST 'http://localhost:8080/reserve' --header 'Content-Type: application/json' --data-raw '{
"emptyPremiumRooms" : 2,
"emptyEconomyRooms" : 7,
"offers" : [23, 45, 155, 374, 22, 99, 100, 101, 115, 209]
}'`

# Test case 4

`curl --location --request POST 'http://localhost:8080/reserve' --header 'Content-Type: application/json' --data-raw '{
"emptyPremiumRooms" : 7,
"emptyEconomyRooms" : 1,
"offers" : [23, 45, 155, 374, 22, 99, 100, 101, 115, 209]
}'`