# callcenter

A call center consists of below members 
X Junior executives at level 1 who attends the call first. 
Y Senior executives at level 2 who attends calls escalated from Level 1
1 Manager who attends the calls escalated from Level 2
X > Y always
Call center receives Z calls a day

Calls should be assigned to the executives in such a way that the total time taken to finish ALL the calls is optimized.  

System Rules
Any calls which stays more than 7 minutes with a JE will be escalated to SE
Any calls which stays more than 10 minutes with a SE will be escalated to next level (Manager)
Any calls which stays more than 15 minutes with Manager will be treated as unresolved
Any executive cannot not handle more than Z/X+Y calls. Once the limit is reached, he/she should not receive any further calls.

Sample Request Json :
{
	
	"callEntry":"9,12,20,13,20,3,3,3,9,2,7,1,7,11,10,5,4,14,14,12"

}

Sample Response JSON :
{
    "num_of_calls": 20,
    "resolved": 18,
    "unresolved": 2,
    "totalTimeTakenInMinutes": 326,
    "performance": {
        "senior_executives": [
            {
                "id": "se1",
                "timeTakenInMinutes": 40,
                "callsAttended": 4,
                "resolved": 1,
                "unresolvedOrEscalated": 3
            },
            {
                "id": "se2",
                "timeTakenInMinutes": 40,
                "callsAttended": 4,
                "resolved": 0,
                "unresolvedOrEscalated": 4
            },
            {
                "id": "se3",
                "timeTakenInMinutes": 28,
                "callsAttended": 3,
                "resolved": 2,
                "unresolvedOrEscalated": 1
            }
        ],
        "junior_executives": [
            {
                "id": "je1",
                "timeTakenInMinutes": 16,
                "callsAttended": 4,
                "resolved": 3,
                "unresolvedOrEscalated": 1
            },
            {
                "id": "je2",
                "timeTakenInMinutes": 24,
                "callsAttended": 4,
                "resolved": 2,
                "unresolvedOrEscalated": 2
            },
            {
                "id": "je3",
                "timeTakenInMinutes": 23,
                "callsAttended": 5,
                "resolved": 3,
                "unresolvedOrEscalated": 2
            },
            {
                "id": "je4",
                "timeTakenInMinutes": 21,
                "callsAttended": 3,
                "resolved": 1,
                "unresolvedOrEscalated": 2
            },
            {
                "id": "je5",
                "timeTakenInMinutes": 28,
                "callsAttended": 4,
                "resolved": 0,
                "unresolvedOrEscalated": 4
            }
        ],
        "manager": [
            {
                "id": "mgr1",
                "timeTakenInMinutes": 106,
                "callsAttended": 8,
                "resolved": 6,
                "unresolvedOrEscalated": 2
            }
        ]
    }
}