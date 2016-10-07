/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviceCompositionSoa;

/**
 *input: service object
 * todo: get params and requested service name
 * check if requested service exists in the db
 * if exists then pull corresponding basic service info from db
 *      (*)call corresponding basic services to get the results of each basic services
 *      if any service doesn't exist then request for that specific not existing basic service to agent and wait for its reply and when replied then add that to the database for future ref.
 *      else pass these basic service results to the Composer
 * else if the basic services (supplied as the optional parameters defining basic services dependencies) exists in the database then call those and then follow step (*)
 * else send the whole Service object to agent based node for further processing, when the agent based node responses with the new Service and its dependencies, save it in the database for future use. (Here, the exact result will be save here in the db with a TTL or Validity time in order to use it for latest requests And also save the dependencies (URL to the service) in order to dynamically fetch result when TTL of the saved results has expired)
 * 
 * @author arsha
 */
public class ServiceBootstrap {
    
    
}
