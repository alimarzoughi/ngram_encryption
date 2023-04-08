# Ngram Encryption

As it currently stands, there are only so many secure
encryption systems, and as technology gets exponentially
better, these systems can become obsolete very quickly. If we
want to continue living in this world and evolving by
increasing our capabilities through technological innovation,
we need to make sure we investigate every possible
improvement that could be made to today’s cryptosystems.
One such improvement is the use of multiple encryption, or
cascading. To test how far multiple encryption can go and how
much the security of encryption algorithms can benefit from it,
the multiplication of cyclic permutations is used to make two
variants of an encryption system that is cascaded n times, for
any number n, depending on the length of the plaintext being
encrypted. The two variants, NgramA and NgramNull, were tested 
and analyzed in Java. The Null variant hardly functions
properly, while the A variant works a vast majority of the time.
The security of the A variant is also astoundingly
improved, suggesting that cascading to the nth level is worth
further investigation on a larger scale.
