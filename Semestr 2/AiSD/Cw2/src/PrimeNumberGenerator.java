import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class PrimeNumberGenerator implements Iterator<Integer> {
    boolean[] isElementPrime;
    List<Integer> primes;
    Iterator<Integer> primesIterator;
    int N;

    public PrimeNumberGenerator(int N) {
        this.N = N;
        isElementPrime = new boolean[N];
        primes = new ArrayList<>();
        sieve();
        primesIterator = primes.iterator();
    }

    private void sieve() {
        for (int i = 2; i < N; i++) {
            isElementPrime[i] = true;
        }

        for (int i = 2; i < N; i++) {
            if (isElementPrime[i]) {
                primes.add(i);
                for (int j = i * i; j < N; j += i) {
                    isElementPrime[j] = false;
                }
            }
        }
    }

    @Override
    public boolean hasNext() {
        return primesIterator.hasNext();
    }

    @Override
    public Integer next() {
        if (hasNext()) {
            return primesIterator.next();
        } else {
            throw new NoSuchElementException();
        }
    }
}
