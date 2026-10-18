a <- c(1, 4, 6, 13, -10, 8)
b <- seq(1, 101, 2)
c <- rep(c(4, 7, 9), each = 5)
d <- c("czy", "to", "jest", "wektor", NA)
e <- c("czy", "to", "jest", "wektor", "NA")
f <- rep(c(4, 7, 9), times = 6)

### ii) Analiza wektorów:

# Wektor 'a'
print("Wektor 'a'")
print(length(a))
print(typeof(a))
print(min(a))
print(max(a))
print(sum(a))

# Wektor 'b'
print("Wektor 'b'")
print(length(b))
print(typeof(b))
print(min(b))
print(max(b))
print(sum(b))

# Wektor 'c'
print("Wektor 'c'")
print(length(c))
print(typeof(c))
print(min(c))
print(max(c))
print(sum(c))

# Wektor 'd'
print("Wektor 'd'")
print(length(d))
print(typeof(d))
print(min(d))
print(max(d))
print(sum(d))

# Wektor 'e'
print("Wektor 'e'")
print(length(e))
print(typeof(e))
print(min(e))
print(max(e))
print(sum(e))

# Wektor 'f'
print("Wektor 'f'")
print(length(f))
print(typeof(f))
print(min(f))
print(max(f))
print(sum(f))

### iii) Sortowanie wektorów d) oraz e):

print("Posortowany wektor 'd'")
print(sort(d))

print("Posortowany wektor 'e'")
print(sort(e))

### iv) Operacje na wektorach:

print("a + f:")
print(a + f)

print("a * f:")
print(a * f)

print("a + c:")
print(a + c)

print("a + 10:")
print(a + 10)

print("15 * a:")
print(15 * a)

print("26-ty element wektora 'b':")
print(b[26])

print("6-10 element wektora 'f':")
print(f[6:10])

### v) Które elementy w wektorze 'b', oraz ile, jest większe niż 50?

print("Elementy w wektorze 'b' większe niż 50:")
print(b[b > 50])

print("Liczba elementów w wektorze 'b' większych niż 50:")
print(sum(b > 50))


