# Phone Aggregator
This is a personal challenge project, whose purpose is to build a phone information aggregator API. This
system takes a list of phone numbers obtained from user input and returns the
count of valid phones broken down per prefix and per business sector.

For example, given 5 phones, 4 of them valid: one for Apple with prefix `+1`,
one for Bank of America with prefix `+1`, one for Quebramar with prefix
`+3519173`, and one for Salsa with prefix `+3519173`. In this case, the system
should return a count of 1 phone for Technology, and 1 phone for Banking
associated with the `+1` prefix, and a count of 2 phones for Clothing associated
with the `+3519173` prefix.

i.e.
```
$ curl -d '["+1983236248", "+1 7490276403", "001382355A", "+351917382672", "+35191734022"]' "http://challenge.example.com/aggregate"
{
  "1": {
    "Technology": 1
    "Banking": 1
  },
  "3159173": {
    "Clothing": 2,
  }
}
```

The following sections further specify the expected inputs and outputs of the system, as well as of its dependencies.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

* [Docker](https://www.docker.com/)
* API online (https://challenge-business-sector-api.meza.talkdeskstg.com)

## Build/Usage

	1. Clone project

		$ git clone https://github.com/egilsoncabral/td_challenge.git
		
	2. Build the project

	    - For build the project, you will need to execute the instructions
        	
         	$ docker-compose up web
        	
        - Now access the url:
            > http://localhost:8080/swagger-ui.html (If you are using docker CE)    
    
### Exposed endpoints:

```
/aggregate
```

#### Used Technologies

* Java 11
* Lombok 
* SpringBoot 
* Docker
* Swagger

## Versioning

For versioning, i used the gitHub platform. For more details, (https://github.com/)

## Authors

* **Egilson Cabral** - [TDChallenge](https://github.com/egilsoncabral/td_challenge)
