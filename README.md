# Financial Aggregation System
Welcome to the my Financial Aggregation System! 

This project entails 3 microservices in order to achieve the goal of aggregating data from different sources.

1. request-service
2. fetch
3. redis db 

### Request Service
This service provides us with the rest API needed to get on demand updated data.

If the data is updated (within less then 4 hours) we shall read it from our redis db.

Please use port 8080 for this service

It has 2 controllers:

/ondemand request structure
````
/ondemand??userName={user_name}}&userId={user_id}&channel={channel}&lsad={timestamp}
````
where:
user_name : shall be the name of the users' account we wish to update.
user_id : shall be the id of the users' account we wish to update.
channel: shall be one of the sources (API, WEB or STATEMENT).
lsad: shall be the last successful aggregation date.


/data request structure 
````
/data?userName={user_name}}&userId={user_id}&channel={channel}
````
user_name : shall be the name of the users' account we wish to update.
user_id : shall be the id of the users' account we wish to update.
channel: shall be one of the sources (API, WEB or STATEMENT).

### Fetch service
This service provides an API to interact with the Request-service.

It also saves the new data retrieved from the different sources to our redis db.

This service saves all requests received from Request-service in a queue to be managed concurrently and to not overflow the different sources we should fetch the data from. 

Please use port 8081 for this service

The API for this service is as follows:
```
/fetch?userName={user_name}}&userId={user_id}&channel={channel}
```

###Install
Please make sure you have redis db installed before running
```
brew install redis
```

