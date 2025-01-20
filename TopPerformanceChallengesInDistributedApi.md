## Introduction and Audience Identification
- The discussion begins with a brief introduction and a show of hands to identify the audience's countries of origin, including Poland, Austria, Australia, and Germany
- The speaker mentions the World Cup and makes light of the situation, acknowledging the tough day for some countries
- The speaker's wife is Colombian, and they have a personal connection to the World Cup, making Sunday a potentially tough day
- The keynote speaker from earlier in the day got the audience exercised, and the speaker suggests doing a similar physical exercise with the audience

## Performance Challenges in Distributed Architectures
- The main topic of discussion is performance challenges in distributed architectures, with the speaker sharing personal examples and experiences from companies they've worked with and their customers
- The speaker introduces a concept learned from Google, Facebook, and others on how to avoid introducing performance and architectural issues in distributed architectures through automation in the CI/CD pipeline
- An example from Dynatrace is shared, where the company implemented a feature to monitor AWS using CloudWatch, pulling in metrics from the external API
- The speaker explains that using CloudWatch led to complaints about costs, as AWS charges for every API call, highlighting the importance of considering costs in performance
- The initial iteration of the monitoring solution used the single fetch API, resulting in 907 calls to the AWS CloudWatch API, taking 41 seconds and consuming 97 threads
- The speaker acknowledges that this approach is not efficient and will discuss better solutions

## Efficient API Usage in Distributed Architectures
- When designing distributed architectures that rely on third-party APIs, it is crucial to use these APIs in the most efficient way possible, considering factors such as the number of calls, data transformation, and costs, as excessive API calls can lead to increased expenses, especially for growing businesses
- AWS recently introduced a bulk fetch API, which can reduce the number of round trips to APIs by a factor of nine, making it more efficient and cost-effective
- When moving to a more distributed architecture, it is essential to be aware of the growing dependencies, as this can lead to problems if not properly managed, and having a good strategy for failover is critical

## Failover Strategies in Distributed Systems
- Breaking down a bigger system into smaller, connected pieces can be challenging, and it is crucial to have a strategy in place for dealing with failing components to prevent cascading effects
- Neiman Marcus, a US-based e-commerce store, faced challenges when moving to a microservices architecture on ECS, highlighting the importance of having a good strategy for failover and dealing with dependencies
- When designing distributed systems, it is essential to keep an eye on dependencies and have a plan in place for dealing with failover, including having a failover mechanism to prevent cascading failures
- Real-world examples, such as a pharmacy's credit card transaction system, demonstrate the importance of having a system in place to deal with outages and prevent cascading failures, ensuring that critical services remain available
- When building distributed systems, it's crucial to consider the potential consequences of denying a critical service, such as a person's medication, and instead find ways to process calls to the next system later on

## Anti-patterns in Distributed Systems
- Distributed systems often involve anti-patterns, which are common issues that arise when analyzing distributed applications, many of which are familiar to software engineers
- The "M+1 call pattern" is a classical anti-pattern where monolithic code is distributed, leading to multiple calls to microservices, resulting in performance issues and overhead
- A customer example is an insurance company that extracted their monolithic code base into smaller services, resulting in 44 calls to product services for every customer quote request, highlighting the need for end-to-end analysis
- Another example is Landbay, a financial institution in the UK, which realized that a single message entering their system on AWS translates into 74 database entries, 24 messages in a queue, and multiple listeners, demonstrating the complexity of distributed systems

## Service Communication and API Design
- It's essential to reevaluate how services communicate with each other, define the right API, and consider use cases over time to avoid performance challenges in distributed architectures
- In distributed architectures, the consumer of a service may change their pattern, requiring a more bulky version of an API, so it's essential to reevaluate the system's performance regularly

## Performance Anti-Patterns: M+1 Call, N+1 Query, and Payload Flood
- The M+1 call pattern, where one call spreads out into multiple calls, can be excessive and costly, especially when using cloud services like Amazon
- The N+1 query pattern, where a monolithic app makes multiple calls to the database, can also be inefficient, especially when using tools like Hibernate incorrectly
- Breaking up a monolith into smaller pieces can lead to duplicated calls to the database, making it essential to analyze and optimize data access
- The payload flood pattern, where large amounts of data are transferred between services, can be inefficient and costly, especially when only a small piece of the data is needed
- Analyzing the amount of data transferred between services can help identify areas for optimization, such as only sending the necessary pieces of data
- A solution to the payload flood pattern is to only send the necessary pieces of data to each service, rather than the entire document
- Optimizing data transfer can lead to significant improvements, such as a 50% reduction in bytes sent over the network
- It's essential to consider the network and data transfer when designing distributed architectures, as it can have a significant impact on performance and cost
- Analyzing and optimizing data access and transfer can help improve the performance and efficiency of distributed architectures

## Breaking Down Monolithic Systems into Microservices
- When breaking down a monolithic system into microservices, it's essential to determine what services should be separate and what should remain close to where they're needed, as excessive calls between services can be costly and inefficient, such as a system that made 316 calls to an encryption service for a single document
- A monolithic system can be broken down into smaller services, but it's crucial to understand the internal structure and how components interact with each other before doing so
- A well-structured monolith may have modules, classes, and components that can be broken down into separate services, such as a journey service, booking service, and configuration service
- Before breaking down a monolithic system, it's essential to analyze how individual components and services interact with each other to avoid inefficient service flows and unnecessary refactoring
- Tools can provide valuable information on how components interact, helping architects make informed decisions about what services to break out and what to leave intact

## Analyzing and Optimizing Service Flows
- Inefficient service flows can occur when too many things are loaded, nothing is cached, too many round trips are made, and data is not brought close to the end users
- Analyzing transaction flows or service flows with dependencies is crucial in understanding how services interact and identifying potential performance challenges
- When breaking down a monolithic application into smaller pieces, it's essential to analyze end-to-end flows from individual business features and optimize them, rather than just focusing on breaking the monolith
- A bank recently worked on breaking down their Java middleware into smaller pieces and moving it to OpenShift, and they started by analyzing key features of their banking software, such as logging in, looking at accounts, making transactions, and buying stocks

## Managing System Complexity and Dependencies
- It's crucial to keep an eye on the complexity of the system to avoid it becoming too complicated, as it can happen when dealing with other systems, configurations, and changes made by different people in the organization
- In production, the system can become a big mess if not managed properly, leading to classical patterns such as individual services calling other services recursively, the "M+1" pattern, and no caching layer
- To avoid this, it's essential to keep an eye on the system's architecture and make sure that it doesn't become too complicated, and to enforce dependencies for every single code check-in
- When writing a service, it's essential to define the dependencies and what the service should do, and to make sure to enforce these dependencies for every single code check-in
- This can be done by validating dynamically if the discussed dependencies are still the case, and by running tests through a delivery pipeline
- In a distributed architecture, a single call to the database can suddenly become multiple calls to different microservices, which can cause issues if not properly validated and monitored
- Monitoring code is essential to catch these issues early on, and this concept can be implemented in a pipeline to prevent problems from arising
- A common challenge in distributed architectures is the cascading effect of microservices calling each other recursively, leading to inefficiencies and potential problems
- Another example of a challenge is when the same microservice is called recursively, resulting in repeated database queries and inefficient use of resources
- Dependencies between services can also cause issues, and it's essential to understand the full chain of events and the reverse stack trace to identify and resolve these problems
- In large organizations, it's crucial to have a clear understanding of the overall architecture and how different services interact with each other to avoid problems and maintain scalability
- To address these challenges, it's recommended to always look at the big picture, validate dependencies, and use tools that can provide a full chain of events and reverse stack trace across the entire architecture
- By doing so, developers can focus on specific services and understand how they interact with other services, making it easier to identify and resolve issues
- Understanding dependencies is crucial in distributed architectures, as changes to one service can impact others, and it's essential to know who depends on your changes to avoid breaking something

## Automating Checks in CI/CD Pipelines
- Even basic patterns and principles are often overlooked, and examples of these oversights occur on a day-to-day basis, despite being well-known issues, such as the N+1 query pattern
- To address these challenges, automating checks in CI/CD pipelines is proposed, using a concept called "monitoring as code" or more broadly "architecture validation as code"
- This approach involves constantly validating dependencies and performance on individual patterns or service endpoints before and after changes
- Google's approach to dealing with distributed architectures involves defining specific types for each service, which automatically determines what the service is allowed to do and who can use it
- For example, a front-end service is not allowed to access the database directly, and this is enforced in the CI/CD pipeline, causing the pipeline to fail if the service attempts to make a call to the database
- The concept of "monitoring as code" or "architecture validation as code" can be implemented using various formats, such as JSON or YAML, to define and enforce architecture rules
- Akamai Cruz, the head of SRE at Google, has discussed Google's approach to load testing and distributed architectures, and his talks and podcast appearances are recommended for further learning

## Example Implementation of Monitoring as Code
- A Node.js-based microservice is used as an example, with the code and cloud formation template available on GitHub as part of the AWS DevOps tutorial, which can be completed using the AWS free tier and a free trial of Dynatrace
- The microservice has a pipeline that includes a staging environment with dedicated EC2 instances for staging and production, deployment of tests, and a step to validate the build using a lambda function
- The lambda function checks the deployed version against a "monspec" file, which defines the expected behavior and performance of the service
- The monspec file allows users to specify the expected metrics, such as response time and failure rate, and relationships between services, including the number of calls made to other services
- Users can define a contract for their service, specifying which services it is allowed to call, and also define the expected number of nodes the service should run on
- The monspec file can be used to validate the service's performance and behavior in different environments, including staging and production, and can be used to simulate load and test the service's scalability
- A distributed architecture can have multiple nodes, and the number of nodes can change from build to build, making it essential to monitor and track these changes
- A staging environment can be set up with a Linux machine tagged as "staging" to monitor a service and its dependencies, including response time, failure rate, and the number of nodes it runs on
- Dependency information can be automatically obtained and validated through a pipeline, which can also be configured to wait for a certain amount of time before proceeding with the deployment
- A pipeline can be set up to run load tests and validate metrics such as average response time, response 90th percentile, failure rate, and service request load, with each column representing a build
- The pipeline can be configured to automatically stop if any of the metrics exceed a certain threshold or if a new dependency is introduced, ensuring that the deployment meets the required standards
- A "monspec" file, or a JSON file, can be used to guide the pipeline and ensure that it enforces basic architectural rules, avoiding situations where the deployment deviates from the expected architecture over time
- The goal is to avoid situations where the pipeline is not enforcing architectural rules, leading to deviations from the expected architecture, and to ensure that all colleagues are doing their job correctly

## Automating Deployment Validation and Monitoring
- A pipeline can be set up using AWS CodePipeline, which can deploy code to an EC2 instance, run tests, and have a manual approval stage
- Manual processes are time-consuming and inefficient, which is why a lambda function was created to automate the process of determining if enough data is available and if specifications are being met
- When deploying into production, monitoring tools are used to validate the deployment and ensure that users are happy with the result
- The goal is to automate the process of validating metrics, such as response time, failure rate, CPU consumption, and memory, by comparing different time frames and builds
- A monspec file can be used to fully automate the validation of these metrics, allowing for automatic comparison of different time frames and builds
- Google takes this concept a step further by specifying which metrics to validate per endpoint and generating tests based on this file
- By using a monspec file, tests can be generated based on the service's endpoints and workload distribution, allowing for more accurate testing
- The pipeline can be improved by automating the approval process, which involves pulling in metrics and comparing them to defined values
- This automated approval process can be used for both staging and production environments, with different metrics being used for each
- In production, additional metrics such as site accessibility, checkout process, and sales can be used to validate the deployment
- Distributed apps are composed of smaller services, and when deployed to production, they can be monitored to ensure everything is working as expected, allowing for celebrations when issues are resolved
- Monitoring tools can detect problems and trigger automated actions, such as calling other tools, lambda functions, or Ansible Tower scripts, to fix issues, and this approach is advocated for, with examples available on GitHub

## Key Performance Challenges and Solutions
- Key performance challenges in distributed architectures include too many calls coming into a service, too many dependencies, and sending too much data over the wire, which can arise from code changes and lack of constant monitoring
- Automating checks in the pipeline is essential to address these challenges, and companies should strive to figure out how to do so

## Continuous Performance Environment and Testing
- A continuous performance environment, which is a partial replica of the production environment, can be used to test performance characteristics of the system and its parts, and this approach is used internally
- In this environment, every build is automatically deployed and tested under constant load, and the first thing validated is how the deployment works, followed by the validation of the behavior change compared to the previous build
- Performance testing can be done on individual services, setting them under load to figure out if code changes affect memory behavior, and this approach is used in integration environments and production
- Performance testing involves analyzing the behavior of incoming and outgoing calls, transferred bytes, JVM configuration changes, memory settings, and garbage collection behavior to identify potential issues
- Testing on small environments can be done much faster, providing developers with quicker feedback on performance regressions
- A regression is not just a functional issue, but also a performance problem, such as increased memory consumption, which needs to be justified or fixed
- A combination of unit tests, integration tests, functional tests, and load tests is necessary to ensure comprehensive testing
- Load testing with production-like data is ideal, but often not possible due to confidentiality concerns, so data generators or transformation processes can be used to create similar data
- Mark Tomlinson, a performance engineer at PayPal, uses a process called "Swiss cheesing" to create a production replica with manipulated data that maintains the volume and semantics of the original data
- Automatic performance tests can fail frequently, but running them continuously can help reduce performance regressions by providing immediate feedback to engineers
- Continuous performance testing can be integrated into the CI environment, allowing engineers to receive feedback on their code changes within minutes and fix issues before they reach the nightly build
- Thomas Steinbauer, chief performance architect, has also discussed performance testing and its importance in a separate session
- Continuous performance validation is a crucial process that involves simulating a large load, running numerous tests, and analyzing the results to ensure optimal system performance
- Thomas Steinbauer has given a talk on continuous performance validation, which includes statistics on the load simulated, the frequency of tests, and the number of tests found
- Flaky results can be handled by constantly testing hardware, using the same hardware configuration in production and pre-production, and calibrating the test environment
- The test environment is calibrated by running warm-up tests to ensure that there are no side effects from other processes or JVM not being warmed up enough
- Stefan Frantel has written a blog post on calibrating the test environment, which explains how unit tests and integration tests are analyzed for performance
- Pure performance tests are less prone to flakiness due to the use of beefy hardware and simple configurations, but unit and integration tests can be more flaky due to factors like execution time
- To address flakiness, tests are run with different combinations of system startup and cache priming to ensure that the system behaves as expected in various scenarios
- The continuous performance environment is designed to mimic the production environment, with constant load and deployment of new builds every two weeks
- The system is tested for deployment, as well as for other use cases like bringing up new cluster nodes or regions in AWS, to ensure optimal performance in different scenarios
- The main tests focus on a system that is under constant load, but also test the system's behavior when caches are not primed, such as during a promotion or when bringing up new clusters
