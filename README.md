### Simple RPC
See method : getRate();  
`rpc getRate(RateRequest) returns (CurrencyRate)`  
Get an instance of RateRequest as an input parameter and generate single result as CurrencyRate

### Server-side Streaming RPC
See method : getRateList();  
`rpc getRateList(RateRequest) returns (stream CurrencyRate)`  
Get an instance of RateRequest as an input parameter and generates a stream of CurrencyRate instances as a result 

### Client-side Streaming RPC
See method: getMonthAverage();  
`rpc getAverageRate(stream RateRequest) returns (CurrencyRate)`  
Method returns Observer, which actually will accept data from the client and do something with it in `onNext` method. 
When all data is received then `onComplete` will be called, and exactly in this method response observer will be populated with result.

### Bidirectional Streaming RPC
Both sides send a sequence of messages using a read-write stream. 
The two streams operate independently, so clients and servers can read and write in whatever order they like: for example,
the server could wait to receive all the client messages before writing its responses, or it could alternately read a
message then write a message, or some other combination of reads and writes. The order of messages in each stream is preserved.
