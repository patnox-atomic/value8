# Simple Reorder Module

## Scenario

Kefis is a simple store that deals in FMCG. They desire a simple application that tracks inventory and makes automated reorders to their off-site warehouse when the stock level hits a predefined quantity.
The warehouse attendant can see all reorders coming in and dispatch them to the store. Processed reorders are marked as such.
Both the retailer and the warehouse can see pending and processed reorders.

## Requirements

**Hint:** In addition to other technical areas of interest, we are very keen on your thought process. Feel free to be creative. Do not complicate your solution in a bid to impress only to find it unfinished in the stipulated period.
**You are allowed a maximum of 168 Hours to complete. The last commit created in that time is what we take into account**.

### Client Requirements

- Seed 5 test products into the database with a default amount of inventory and reorder level
- In the product listing for the store, add a button that reduces inventory to simulate a sale
- When inventory hits the predefined reorder level, create an automated reorder in an unprocessed state
- The warehouse actor should have a view to see unprocessed reorders
- On the reorder listing for the warehouse, have a dispatch button that simulates a dispatch to the store
- The above dispatch action should increment inventory in the store
- The store actor should also have a view to see unprocessed and processed reorders

### Technical Requirements

- Write at least one unit test for each of the functions that simulate the sale and the dispatch
- Provide the command necessary to run your tests
- Create a private repository on GitHub and add [info@valuechainfactory.com](mailto:info@valuechainfactory.com) and [thomasjgx@gmail.com](mailto:thomasjgx@gmail.com) as a collaborator
- Push your code to that repoitory in as many commits as neccessary - preferably more than one.
- Host the solution on [Heroku](https://www.heroku.com/) or an alternative hosting provider and share the link in the section below.

### Languages allowed

Use any programming language you're comfortable with.

The following languages are what we currently use in our company and will be given special consideration:

- Elixir/Phoenix
- HTML/CSS/JS

#### Resources

- https://elixir-lang.org/getting-started/introduction.html
- https://hexdocs.pm/ecto/Ecto.html
- https://hexdocs.pm/phoenix/overview.html

---

## Applicant Section

### Hosted App Link

    https://value8.herokuapp.com/  -- frontend
    https://value8backend.herokuapp.com/ -- backend

### Comments

    Used SpringBoot for the backend
    Used VueJs for the frontend
    
    To Login, Use these credentials:
    
    Store Attendant:
    username: "john.doe@yahoo.com",
    password: "123456"
    
    Warehouse Attendant:
    username: "peter.mash@gmail.com",
    password: "123456"
    
    System Admin:
    username: "elon.masker@mailinator.com",
    password: "123456"
    
    Note that the system admin (superuser) can perform all actions, the warehouse attendant can only fulfil orders while the retail/store attendant can view products, perform sales, and create orders
    Note that orders can be manualy created or triggered by the system when reorder levels are reached
    Note that creating a sale reduces the number of products available
    
    For Testing:
    Use the provided Postman Script to send API requests to the backend i.e https://value8backend.herokuapp.com/
    
    backend endpoints:
    
    api/v1/product -- products
    api/v1/order -- orders
    api/v1/sale -- sales
    api/v1/user -- users
    
    Security Endpoints:
    
    login/ -- login into the app
    api/refreshtoken -- refresh JWT token
    
    FrontEnd:
    The front end is at https://value8.herokuapp.com/
    Login before first use
    
    Running Tests:
    	Backend (2 tests available): Run command on terminal: $ mvn clean package
    	Frontend: Run command on terminal: $ npm run test:unit
    
    Client Requirements:
    
    	- Seed 5 test products into the database with a default amount of inventory and reorder level -- done
	- In the product listing for the store, add a button that reduces inventory to simulate a sale -- done
	- When inventory hits the predefined reorder level, create an automated reorder in an unprocessed state -- done
	- The warehouse actor should have a view to see unprocessed reorders -- done
	- On the reorder listing for the warehouse, have a dispatch button that simulates a dispatch to the store -- done
	- The above dispatch action should increment inventory in the store -- done
	- The store actor should also have a view to see unprocessed and processed reorders -- done
    
    Tech Requirements:
    
    	- Write at least one unit test for each of the functions that simulate the sale and the dispatch -- done
	- Provide the command necessary to run your tests -- done
	- Create a private repository on GitHub and add [info@valuechainfactory.com](mailto:info@valuechainfactory.com) and [thomasjgx@gmail.com](mailto:thomasjgx@gmail.com) as a collaborator -- done
	- Push your code to that repoitory in as many commits as neccessary - preferably more than one. -- done
	- Host the solution on [Heroku](https://www.heroku.com/) or an alternative hosting provider and share the link in the section below. -- done
    
    
    
    
    
    
    
    
