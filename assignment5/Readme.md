**functionality left(Todos):**

1> Develop protected API

2> Manually make auth token non functional(Have DB of no longer active tokens that still have some time to live
. Query provided token against The Blacklist on every authorized request)

3> User signup using Phone No and OTP instead of username and password.

4> Rate limit sign up api(using redis or guvava or netflix zull)




### User signup:

Post: localhost:8080/api/v1/users/signup

Body:
`{`
	`"name": "Rishabh",`
	`"username": "rishabh7",`
	`"password": "rishabh123"`
`}`

Header: Content-Type  ->   application/json

### User login:

Post: localhost:8080/login

Body:
`{`
	`"username": "rishabh7",`
	`"password": "rishabh123"`
`}`

Header: Content-Type  ->   application/json

Response header: **Get the auth token**

### Get all the users:

Get: localhost:8080/api/v1/users

Header: Authorization  ->   Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJyaXNoYWJoNyIsImV4cCI6MTU0NjI3NjQ1M30.YUR9-VGh1N75Y3T0HZ6vhFp3IwrS-FstHNl9OmqRZBthYbKhF71BR49oNsPQ6vGR0Yvp0OXngsjvq-g2uwzd9A

### Get user self detail:

Get: localhost:8080/api/v1/users/details

Header: Authorization  ->   Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJyaXNoYWJoNyIsImV4cCI6MTU0NjI3NjQ1M30.YUR9-VGh1N75Y3T0HZ6vhFp3IwrS-FstHNl9OmqRZBthYbKhF71BR49oNsPQ6vGR0Yvp0OXngsjvq-g2uwzd9A

### Logout user:

Delete: localhost:8080/api/v1/logout

Header: Authorization  ->   Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJyaXNoYWJoNyIsImV4cCI6MTU0NjI3NjQ1M30.YUR9-VGh1N75Y3T0HZ6vhFp3IwrS-FstHNl9OmqRZBthYbKhF71BR49oNsPQ6vGR0Yvp0OXngsjvq-g2uwzd9A

### Update name or password:

Put: localhost:8080/api/v1/users/update

Body:
`{`
	`"name": "Rishabh",`
	`"password": "rishabh123"`
`}`

Header: 

Authorization  ->   Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJyaXNoYWJoNyIsImV4cCI6MTU0NjI3NjQ1M30.YUR9-VGh1N75Y3T0HZ6vhFp3IwrS-FstHNl9OmqRZBthYbKhF71BR49oNsPQ6vGR0Yvp0OXngsjvq-g2uwzd9A

Content-Type  ->   application/json

### Delete your account:

Delete: localhost:8080/api/v1/users/delete

Header: Authorization  ->   Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJyaXNoYWJoNyIsImV4cCI6MTU0NjI3NjQ1M30.YUR9-VGh1N75Y3T0HZ6vhFp3IwrS-FstHNl9OmqRZBthYbKhF71BR49oNsPQ6vGR0Yvp0OXngsjvq-g2uwzd9A


