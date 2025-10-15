a <- seq(300, 0, -1)
b <- c("one", "two", "three", "four", 5)
c <- c("one", "two", "three", "four", "5")
d <- rep(c(3, 1, 6), each = 4)
e <- rep(c(3, 1, 6), times = 4)
f <- c(5, 1, 4, 7)

cat("\n### ii) Analiza wektorów:\n")

# Wektor 'a'
cat("\n--- Wektor 'a' ---\n")
print(paste("Długość:", length(a)))
print(paste("Typ danych:", typeof(a)))
print(paste("Najmniejszy element:", min(a)))
print(paste("Największy element:", max(a)))
print(paste("Suma elementów:", sum(a)))

# Wektor 'b'
cat("\n--- Wektor 'b' ---\n")
print(paste("Długość:", length(b)))
print(paste("Typ danych:", typeof(b)))
print(paste("Najmniejszy element:", min(b)))
print(paste("Największy element:", max(b)))
print(paste("Suma elementów: Nie dotyczy (typ character)"))

# Wektor 'c'
cat("\n--- Wektor 'c' ---\n")
print(paste("Długość:", length(c)))
print(paste("Typ danych:", typeof(c)))
print(paste("Najmniejszy element:", min(c)))
print(paste("Największy element:", max(c)))
print(paste("Suma elementów: Nie dotyczy (typ character)"))

# Wektor 'd'
cat("\n--- Wektor 'd' ---\n")
print(paste("Długość:", length(d)))
print(paste("Typ danych:", typeof(d)))
print(paste("Najmniejszy element:", min(d)))
print(paste("Największy element:", max(d)))
print(paste("Suma elementów:", sum(d)))

# Wektor 'e'
cat("\n--- Wektor 'e' ---\n")
print(paste("Długość:", length(e)))
print(paste("Typ danych:", typeof(e)))
print(paste("Najmniejszy element:", min(e)))
print(paste("Największy element:", max(e)))
print(paste("Suma elementów:", sum(e)))

# Wektor 'f'
cat("\n--- Wektor 'f' ---\n")
print(paste("Długość:", length(f)))
print(paste("Typ danych:", typeof(f)))
print(paste("Najmniejszy element:", min(f)))
print(paste("Największy element:", max(f)))
print(paste("Suma elementów:", sum(f)))


cat("\n\n### iii) Sortowanie wektorów b) oraz e):\n")

cat("\nPosortowany wektor 'b':\n")
print(sort(b))

cat("\nPosortowany wektor 'e':\n")
print(sort(e))


cat("\n\n### iv) Operacje na wektorach:\n")

# a) d + f
cat("\na) d + f:\n")
print(d + f)

# b) iloczyn skalarny d i f
cat("\nb) Iloczyn skalarny d i f:\n")
print(sum(d * f))

# c) 35-ty element wektora a
cat("\nc) 35-ty element wektora a:\n")
print(a[35])

# d) 67-ty do 85-tego elementu wektora a
cat("\nd) 67-ty do 85-tego elementu wektora a:\n")
print(a[67:85])

# e*) iloczyn wektorowy d x e
cat("\ne*) Iloczyn 'wektorowy' (iloczyn element-po-elemencie d*e):\n")
print(d * e)


cat("\n\n### v) Elementy w wektorze 'a' mniejsze niż 100:\n")

# Elementy, które są mniejsze niż 100
elements_less_than_100_a <- a[a < 100]
cat("\nElementy mniejsze niż 100:\n")
print(elements_less_than_100_a)

# Liczba elementów mniejszych niż 100
count_less_than_100_a <- sum(a < 100)
cat("\nLiczba elementów mniejszych niż 100:\n")
print(count_less_than_100_a)

