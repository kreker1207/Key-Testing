# Key-Testing (Тестування ключів на випадковість за стандартом FIPS-140)

FIPS-140 (Federal Information Processing Standards Publication 140) randomness key testing is used to assess the quality and randomness of generated cryptographic keys. This standard was developed by the US National Institute of Standards and Technology (NIST) and is widely used in the field of information security.

## Task

To perform the practical task, you need to programmatically implement the standard for testing bit sequences for randomness (FIPS-140 third version). The input data to the test functions must be a sequence of length 20,000 bits. The output data of each test function must be a logical value (true / false), test passed and failed, respectively. If the sequence passes all four test functions, the program should report that the 20,000 bits are sufficiently random. Otherwise, the bit sequence is rejected. Tests: Monobit Test, Maximum Streak Length Test, Poker Test and Streak Length Test.

## How to Use

1. Clone the repository to your local machine
2. Navigate to the project directory
3. Compile the Java code: `javac Main.java`
4. Run the application:`java Main` - it would automatically generate and pass tests.
