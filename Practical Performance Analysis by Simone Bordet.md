## Introduction to Jetty and Webtide
- Simone works for Webtide, a company that offers services and support around the open-source project Jetty, which is now 29 years old and probably the oldest open-source project in the Java sphere
- Jetty was initially started by Greg Wilkins as an issue-tracking tool, but it evolved into a web server and became one of the most used Java services
- Jetty version 12 is a new rewrite of the core engine, allowing it to run Java 8 applications, Jakarta EE applications, and the new Java 10 ones, with plans to support the upcoming Java 11 specifications
- This allows users to deploy old Java applications without porting them to the Jakarta space, while also developing and deploying new Jakarta applications in the same instance of the Jetty server
- Webtide is working on new stuff coming up with HTTP/3 and is always trying to stay on top of new problems, even after 29 years
- This performance analysis session came about due to the development of Jetty 12, which had many performance-related problems to solve, and the company's consulting work with clients to understand the performance behavior of their systems
- The session will share war stories and examples of performance-related issues encountered by Webtide and its clients

## The Importance of Performance Measurement
- When it comes to performance, the answer to how well a web application performs is "it depends," and the right questions need to be asked, such as what is the end goal and why measure performance
- Measuring performance is not useful per se, but it is useful when it provides answers to questions like how many machines are needed to sustain a certain load or whether the system can handle a significant increase in usage
- To analyze the performance of a web application, it's essential to define an end goal, such as minimal latency or efficiency, and then conduct load testing to test the application's performance under various loads

## Load Testing Methodologies
- A common mistake in load testing is using a flawed test, such as a single-threaded client sending blocking requests to the server, which can limit the test results and provide inaccurate information about the server's capabilities
- There are two types of load testing: steady state load testing and limit load testing, both of which are useful but provide different metrics and data
- Steady state load testing involves running the load test at the nominal pace or nominal load, which is the expected production load, and provides information about the system's performance under normal conditions
- Limit load testing involves maximizing or saturating one of the resources, such as CPU, network, or heap memory, to test the system's performance under extreme conditions and identify potential bottlenecks
- Steady state load testing is useful for capacity planning and scaling strategies, while limit load testing provides a different set of problems and answers, such as identifying the maximum capacity of a resource
- Load testing is used to analyze the behavior of a system when one of its resources becomes saturated, which can help identify common errors that occur in such situations
- Limit load testing is also useful in determining if a system can recover and return to its normal state after the load is removed
- Some systems may not be able to recover from a spike in load and may require a restart or removal from the load balancer

## Setting Up for Load Testing
- To perform load testing, a series of steps are recommended, starting with the setup of a particular hardware configuration
- The typical setup includes one server machine, one or more client machines, and a dedicated probe machine to monitor the system from the outside
- The probe machine is used to measure the response time of the system from the perspective of a normal user, as opposed to the client machines which are busy sending requests
- The operative system needs to be configured, including increasing the number of open files, which is often forgotten and can cause problems if not set correctly
- The ephemeral port range also needs to be tuned, especially for the client, to allow for a large number of connections to be opened
- When conducting load testing, it's essential to configure the server and client settings, including the port and sockets, to accurately simulate real-world scenarios
- Modern CPUs can scale their frequency, so it's crucial to configure them to run at a fixed frequency during load testing to ensure accurate results
- When tuning the JVM, it's vital to choose the same JVM that will be used in production to avoid surprises, and to select the appropriate garbage collector, such as generational ZGC, which is recommended for low-latency applications
- The G1 garbage collector is not the only option, and other collectors may be more suitable for specific use cases, so it's essential to try different options
- The -XX:+UseG1GC flag is not the only flag that needs to be set, and other flags, such as -XX:+UseStringDeduplication, may also be necessary

## Creating Load Test Scenarios and Gathering Metrics
- When creating a load test scenario, it's essential to simulate real-world user behavior, such as landing on the homepage, downloading resources, logging in, and navigating to product pages
- There are various tools available for simulating user behavior, including JRY L Generator, Gatling, Hyperfoil, and JMeter, each with its own strengths and weaknesses
- Cloud providers also offer their own load testing solutions, which can be used to simulate user behavior and test application performance
- There are many solutions available for writing workflows and taking metrics, such as Gatling and Hyperfoil, which can be used to measure real times on the exchange between the client and the server
- When doing load testing, it is essential to do resource tracking at every possible level, including CPU, network, disk, and operative systems, to identify potential bottlenecks
- Resource tracking should include monitoring hiccups on the operative systems, garbage collector logs, threads used by the server, connections, and response times
- Gathering response times, types, and errors is also crucial in load testing, and this can be done using tools like JMeter, which has a built-in system to report response times
- However, JMeter's built-in system may not provide enough information, and additional instrumentation may be needed to measure the time taken by each microservice

## Executing and Analyzing Load Tests
- A typical setup for load testing includes one server, many loaders, and one probe, with multiple loaders used to avoid being limited by the clients
- Using multiple loaders can help identify the server's capacity, and in one example, four loaders were used to achieve 240,000 requests per second, with the server running at about 10% CPU
- Establishing a baseline is crucial as it helps determine if the system is configured correctly and meets expectations, which is often overlooked when people jump straight into load testing to find maximum throughput
- To establish a baseline, apply a small load to the system and check if the expected numbers are met, such as the number of open connections and concurrent threads serving requests
- If the baseline matches expectations, proceed to run the load test, gathering information on the imposed load and resource tracking on both the client and server sides
- The USE method, introduced by Brendan Gregg, is suggested for analyzing the data, which stands for utilization, saturation, and errors; however, it's best used in the reverse order
- When analyzing the data, first look for errors, such as 200s from requests, as they can indicate issues with the system, even if the connections are established
- Check for saturation to test the maximum capacity of every resource, paying attention to the fact that most metrics are averaged over a period of time
- Distinguish between saturation and utilization, as utilization is an average of resource usage over time, and a high utilization percentage doesn't necessarily mean the resource is fully utilized
- When conducting load testing, it's essential to revisit the initial questions and goals, such as capacity planning, resource checking, and ensuring the application behaves properly, to determine if the numbers obtained are good or not

## Profiling and Identifying Performance Bottlenecks
- If the numbers are not good, the next step is to figure out how to improve them, which involves identifying the bottlenecks in the system
- Profiling is a crucial step in identifying performance issues, and it's recommended to use an asynchronous profiler that can sample threads in an asynchronous way
- Many profilers do not have this capability by default and can introduce a huge bias in the results, making them unreliable
- Some profilers, like async-profiler, have an option to use a special call, such as Gest trace, to enable asynchronous profiling
- Once the profiler is configured, it can be attached to the server, and a load test can be run to gather information, typically in the form of a flame graph
- Flame graphs are useful for pinpointing performance issues and are the work of Brandon Gregg
- The process of profiling, identifying issues, fixing them, and re-running the load test is a typical cycle of performance fixing

## Real-world Examples of Performance Analysis
- A real-world example of load testing and profiling is shown, where the goal was to increment the load rate and find the maximum throughput achievable on the server
- The example shows that even with high request rates, the CPU usage can be relatively low, with 75% of CPUs idle, indicating that there may be room for improvement
- A performance analysis was conducted on a system where the client was supposed to send 120,000 requests per second to the server, but the throughput was wobbly and not a flat line as expected
- The system seemed to be working fine with no errors, no CPU saturation, and nominal parameters, but the wobbly throughput figure for the client was investigated
- The investigation found that one thread, the sender thread on the client, was spinning as fast as possible and could not do more than 120,000 requests per second, causing the wobbly throughput
- The sender thread would sometimes slow down, allowing it to catch up and sleep for a little while, which made it seem like one of the CPUs was at 100% utilization while the others were idle
- The solution to this problem was to use two threads to send requests to the server instead of just one
- Another performance analysis was conducted to test the difference between two thread configuration modes in Jetty, with the goal of determining if there was a difference between the two configurations
- The analysis found that the maximum response times for the two configurations were significantly different, with the second configuration having higher maximums
- The difference in maximum response times was unexpected, as the time it takes to execute Java code and stop the recording should not be affected by the configuration of the trading system or Jetty
- Enabling perf stat revealed that the CPU Governor was not set to Performance, resulting in one test running at 2.6 GHz and the other at 1.3 GHz, causing the second test to run slower due to the lower CPU speed
- Setting the CPU Governor to Performance is a good choice, as demonstrated by an example where it solved a performance issue
- A flame graph analysis showed two unusual towers on the left, indicating time spent allocating an exception, which was resolved by removing the allocation and using a static exception instead
- The optimized flame graph showed the two towers were completely gone, and the right side of the top flame graph became slightly larger, indicating the freed time was now available for other tasks
- Profiling can help identify performance issues, such as the allocation of IO exceptions, and provide easy fixes

## Performance-Driven API Design
- A performance-driven design of APIs in Jerry involved comparing two proposals, one returning a Handler class and the other a Boolean, with the latter being consistently faster
- The decision was made to use the faster API, even if it was slightly less intuitive, as the goal was to write a fast web server

## Conclusion and Q&A
- Load testing is hard, but tools are available to help, and it's essential to set up a baseline, run load tests, and eventually profile and analyze the results
- Analyzing results requires identifying the saturated resource and applying critical thinking to resolve issues
- A configuration step recommended in the setup phase was overlooked
- The recommended configuration was mentioned earlier in the setup process
- The presenter invites the audience to ask questions and will be available to answer them
