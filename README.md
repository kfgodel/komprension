# komprension
Kotlin POC for compressing data by comprehension

- Status: Experimental
- Phase: Ideation

## Brief description
Komprension is a test project to implement a compression algorithm using
comprehension to describe the contents of a byte stream.  
Instead of storing compressed data, Komprension tries to encode
different functions that can reproduce the original compressed data.  

## Longer description
Imagine having to store an infinite file of alternating '1' and '0',
and I mean literally infinite. Like in never ending stream of bytes.
Common compression methods try to have a single smart algorithm that
can compress data into a compact format. However, they fail at having to
compress infinite streams of bytes.

While not a real case scenario, infinite sized files begs for a
different approach. Solving that problem may
help on compression really large files which are more common nowadays. 

Instead of using a single smart algorithm, Komprenssion uses 
as many as it needs to describe the original data as a sequence of 
functions. Each reproducing a range of bytes of the original data.
Then, it only needs to store the ID of the function to use, and whatever
parameters that function needs. In our example, it could represent
an infinite stream of alternating 0/1 bytes with only 3 bytes. The first 
to identify the function that repeats the rest of bytes forever and
the next 2 bytes with the starting pair.  

Based on human communication where common knowledge is shared
between parties allowing more economic data transfers, Komprension 
is designed to be open and extended, so more algorithms can be added 
as part of the available repertoire of functions to use.
This, combined with the ability to share a common server to store
functions allows Komprension to be used as an efficient data 
transfer method.    

### About the project
This is a proof of concept to test possible implementations of the 
underlying ideas that make Komprension a feasible compression method.
Because its early stage it cannot be used yet and the implementation
is still in the phase of ideation. A lot can and will change during 
development until a stable implementation can be used.

When that happens, probably a new project will replace this POC
with a user friendlier version
