a <- c(1, 4, 6, 13, -10, 8)
b <- seq(1, 101, 2)
c <- rep(c(4, 7, 9), each = 5)
d <- c("czy", "to", "jest", "wektor", NA)
e <- c("czy", "to", "jest", "wektor", "NA")
f <- rep(c(4, 7, 9), times = 6)

cat("### ii) Analiza wektorów:\n")

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
print(paste("Suma elementów:", sum(b)))

# Wektor 'c'
cat("\n--- Wektor 'c' ---\n")
print(paste("Długość:", length(c)))
print(paste("Typ danych:", typeof(c)))
print(paste("Najmniejszy element:", min(c)))
print(paste("Największy element:", max(c)))
print(paste("Suma elementów:", sum(c)))

cat("\n--- Wektor 'd' ---\n")
print(paste("Długość:", length(d)))
print(paste("Typ danych:", typeof(d)))
print(paste("Najmniejszy element:", min(d)))
print(paste("Największy element:", max(d)))  
print(paste("Suma elementów:", sum(d)))

cat("\n--- Wektor 'e' ---\n")
print(paste("Długość:", length(e)))
print(paste("Typ danych:", typeof(e)))
print(paste("Najmniejszy element:", min(e)))
print(paste("Największy element:", max(e)))
print(paste("Suma elementów:", sum(e)))

cat("\n--- Wektor 'f' ---\n")
print(paste("Długość:", length(f)))
print(paste("Typ danych:", typeof(f)))
print(paste("Najmniejszy element:", min(f)))
print(paste("Największy element:", max(f)))
print(paste("Suma elementów:", sum(f)))

cat("\n\n### iii) Sortowanie wektorów d) oraz e):\n")

cat("\n--- Posortowany wektor 'd' ---\n")
print(sort(d))

cat("\n--- Posortowany wektor 'e' ---\n")
print(sort(e))

cat("\n\n### iv) Operacje na wektorach:\n")

cat("\na) a + f:\n")
result_a_plus_f <- a + f
print(result_a_plus_f)

cat("\nb) a * f:\n")
result_a_times_f <- a * f
print(result_a_times_f)

cat("\nc) a + c:\n")
result_a_plus_c <- a + c
print(result_a_plus_c)

cat("\nd) a + 10:\n")
result_a_plus_10 <- a + 10
print(result_a_plus_10)

cat("\ne) 15 * a:\n")
result_15_times_a <- 15 * a
print(result_15_times_a)

cat("\nf) 26-ty element wektora 'b':\n")
element_b_26 <- b[26]
print(element_b_26)

cat("\ng) 6-ty do 10-tego elementu (włącznie) wektora 'f':\n")
elements_f_6_to_10 <- f[6:10]
print(elements_f_6_to_10)


cat("\n\n### v) Które elementy w wektorze 'b', oraz ile, jest większe niż 50?\n")

elements_greater_than_50_b <- b[b > 50]
count_greater_than_50_b <- sum(b > 50) 

cat("\nElementy w wektorze 'b' większe niż 50:\n")
print(elements_greater_than_50_b)

cat("\nLiczba elementów w wektorze 'b' większych niż 50:\n")
print(count_greater_than_50_b)

