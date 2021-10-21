# assessment
Assessmet

The following API endpoints have been implemented:

GET /teams/ - Returns a list of teams in the database in JSON format
GET /team/{{id}}/ - Returns a detail view of the specified team in JSON format
GET /agents/ - Returns a list of all agents in the database in JSON format
GET /agent/{{id}}/ - Returns a detail view of the specified agent in JSON format. This should include team details.
POST /team/ - Creates a new team with the specified details - Expects a JSON body
POST /agent/ - Creates a new agent with the specified details - Expects a JSON body

GET /agents/ - Implement pagination and query parameters on this request. The agents identity number should no longer be returned in this request.
POST /manager/ - Creates a new manager with the specified details - Expects a JSON body
PUT /team/{{id}}/agent - Assigns an agent to the specified team
api endpoint that returns a list of all empty teams (i.e teams with no agents or managers)
