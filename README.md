### Writing unit tests with Mockito and MockMvc

### Introduction

We have a weather alert service, which allows clients to subscribe.
The service should send out messages to all of its subscribers.

First, let us discuss some requirements for our class - WeatherService:
• It should allow clients to subscribe (which means they start receiving messages),
• It should allow subscribers to unsubscribe (which means they stop receiving messages),
• Every time a new alert comes, it should be sent to all subscribers.

These simple requirements, along with some common sense, already furnish us with a lot of test cases.
We will be implementing the following:
- If the client is not subscribed, it should not receive any messages,
- If client is subscribed, it should receive each incoming message once (and only once),
- If multiple clients are subscribed, each of them should receive each incoming message,
- Consecutive subscribe requests issued by the same client will be ignored (nothing happens),
- If the client unsubscribes, then it should be the case that no more messages are sent to it.

### Mockito
#### Test 1: Single Subscriber Receives a Weather Alert

We will use a TDD approach (Test Driven Development). We will start writing the test and create
the necessary interface and classes when we need them. Therefor we will follow the basic rule
"code to an interface, and not to an implementation".
Start with writing the test class with the test method **subscribedClientShouldReceiveWeatherAlert**.
Create an interface and implementation for the WeatherService.
- Write your test first! Test doubles of the subscriber and weather alert are both created using the Mockito.mock(). Make sure that the subscribed client received the message using
  mockito's Mockito.verify() method.
- Create the interfaces that are needed to solve compile-time errors.
- Run your test (the test should fail)
- Implement the **method under test** in the WeatherService.
- Run you test (the test should - eventually - succeed)

#### Test 2: Send a Weather Alert to Multiple Subscribers

- Write your test **messageShouldBeSentToAllSubscribedClients()** first.
- Run your test and adjust the implementation until the test succeeds.
- Refactor you test class and implementation if necessary.

#### Test 3: Send Weather Alerts to Subscribers Only

- Write the test **notSubscribedClientShouldNotReceiveMessage()**. Mockito is able to check that something has not occurred. This is done using the static never() method.


#### Test 4: Subscribe More Than Once

Let us now verify the behaviour of the WeatherService when subscribers subscribe more than one
time. Which information do we need of our subscribers (email, sms, both)? When are subscribers considered equal?
- Write the test **shouldSendOnlyOneMessageToMultiSubscriber()**.

#### Test 5: Remove a Subscriber

- Write the test **unsubscribedClientShouldNotReceiveMessages()**.
- What should happen if a client that is not subscribed tries to unsubscribe?

### Create a REST API

#### 1. Subscribe to the weather alert service

##### Request

`POST http://{server}:{port}/weatheralert/subscribe`

`{
"nickname": "your nickname",
"phone_number": "+32471676756"
}`

#### Response
    202 ACCEPTED
    400 BAD_REQUEST when the phone number is already registered

### 2. Unsubscribe from the weather alert service

`POST http://{server}:{port}/weatheralert/unsubscribe`

`{
"nickname": "your nickname",
"phone_number": "+32471676756"
}`

#### Response
    202 ACCEPTED
    400 BAD_REQUEST when the phone number is not registered

#### Request

`POST http://{server}:{port}/weatheralert/send`

`{
"title": "Severe Thunderstorm Warning",
"description": "A severe thunderstorm was located near downtown, moving east at 35 mph.",
"time": "2023-07-23T15:00:00"
}`

#### Response

    201 CREATED
    400 BAD_REQUEST if title, description and/or time is missing

### Writing unit tests with MockMvc

Write unit tests for testing all the REST endpoints and different responses.

### Implement and test the WeatherAlertService.

Optional: you can use Twilio to send an SMS (or Whatsapp message). You can try Twilio for
free. Create your account at https://www.twilio.com/en-us. Or you any of the alternatives available.


    









