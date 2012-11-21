namespace java org.sandbox.myservice.thrift

/**
 * It's considered good form to declare an exception type for your service.
 * Thrift will serialize and transmit them transparently.
 */
exception MyServiceException {
  1: string description
}

/**
 * A simple service, which stores strings by key/value.
 * You should replace this with your actual service.
 */
service MyService {

  string myGet(1: string key) throws(1: MyServiceException ex)

  void myPut(1: string key, 2: string value)

}
