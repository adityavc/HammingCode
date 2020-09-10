# HammingCode

Crypto.java:
Though Hamming Code is already a very efficient error-correcting method, we were trying to develop our own network by programming in Java. First the compiler would ask the user to type in the total number of digits (data+parity digits). If we type in 10, the computer will automatically generate all possibilities of combination of subsets in which each subset must satisfy the following conditions:
        1. At least one element belongs to another set
        2. At most one element that belongs only to this subset
        3. Every element except for the one in (2), except for at most
           one, has to be the only intersection of other subsets
Then we will take the intersection of every 2 subsets in each combination(among {1,2},{1,2,3},{3} the intersection will be 1,2,3) and make the intersection digits the parity bits for that combination, and then we will check which combination can have the parity bits checking with all data like the hamming-code diagram shown in the lecture. Eventually we will take the combination with the highest data efficiency, which would become the maximum efficiency for 10 digits in total.

hammingCode.R
Type in a number in the user interface in binary and the encrypted message will be shown using the Hamming Code algorithm. 
The key of encrypting binary numbers using Hamming Code is using extra parity bits to allow the
identification of a single error.
  1. Mark all bit positions that are powers of two as parity bits. (positions 1, 2, 4, 8, 16, 32, 64, etc.)
     All other bit positions are for the data to be encoded (data). (positions 3, 5, 6, 7, 9, 10, 11, 12, 13,
     14, 15, 17, etc.)
     Each parity bit calculates the parity for some of the bits in the code word. The position of the
     parity bit determines the sequence of bits that it alternately checks and skips.
     Parity 1: check 1 bit, skip 1 bit, etc. (1,3,5,7,9,11,13,15,...)
     Parity 2: check 2 bits, skip 2 bits, etc. (2,3,6,7,10,11,14,15,...)

  2. Then set a parity bit to 1 if the total number of ones in the positions it checks is odd. Set a parity
     bit to 0 if the total number of ones in the positions it checks is even.
     Example: A byte of data: 10011010
     Create the data word, leaving spaces for the parity bits: _ _ 1 _ 0 0 1 _ 1 0 1 0
  3. Calculate the parity for each parity bit (? represents the bit position being set):
  
         ❏ Position 1 checks bits 1,3,5,7,9,11:
            ? _ 1 _ 0 0 1 _ 1 0 1 0. Even parity so set position 1 to a 0: 0 _ 1 _ 0 0 1 _ 1 0 1 0
            
         ❏ Position 2 checks bits 2,3,6,7,10,11:
            0 ? 1 _ 0 0 1 _ 1 0 1 0. Odd parity so set position 2 to a 1: 0 1 1 _ 0 0 1 _ 1 0 1 0
            
         ❏ Position 4 checks bits 4,5,6,7,12:
            0 1 1 ? 0 0 1 _ 1 0 1 0. Odd parity so set position 4 to a 1: 0 1 1 1 0 0 1 _ 1 0 1 0
            
         ❏ Position 8 checks bits 8,9,10,11,12:
            0 1 1 1 0 0 1 ? 1 0 1 0. Even parity so set position 8 to a 0: 0 1 1 1 0 0 1 0 1 0 1 0
            
         ❏ Encrypted number: 011100101010.


Suppose the word that was received was 011100101110, instead of 011100101010. The method is to
verify each check bit. Write down all the incorrect parity bits. Doing so, you will discover that parity bits
2 and 8 are incorrect. It is not an accident that 2 + 8 = 10, and that bit position 10 is the location of the
error bit. In general, check each parity bit, and add the positions that are wrong, this will give you the location of the error bit.



