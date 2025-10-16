print("### ii) Analiza wektorów")

a <- seq(300, 0, -1)
b <- c("one", "two", "three", "four", 5)
c <- c("one", "two", "three", "four", "5")
d <- rep(c(3, 1, 6), each = 4)
e <- rep(c(3, 1, 6), times = 4)
f <- c(5, 1, 4, 7)

# --- Wektor a ---
print("--- Wektor a ---")
print(paste("Długość:", length(a)))
print(paste("Typ danych:", typeof(a)))
print(paste("Min:", min(a)))
print(paste("Max:", max(a)))
print(paste("Suma:", sum(a)))

# --- Wektor b ---
print("--- Wektor b ---")
print(paste("Długość:", length(b)))
print(paste("Typ danych:", typeof(b)))
print(paste("Min:", min(b)))
print(paste("Max:", max(b)))
print("Suma: Nie dotyczy (typ character)")

# --- Wektor c ---
print("--- Wektor c ---")
print(paste("Długość:", length(c)))
print(paste("Typ danych:", typeof(c)))
print(paste("Min:", min(c)))
print(paste("Max:", max(c)))
print("Suma: Nie dotyczy (typ character)")

# --- Wektor d ---
print("--- Wektor d ---")
print(paste("Długość:", length(d)))
print(paste("Typ danych:", typeof(d)))
print(paste("Min:", min(d)))
print(paste("Max:", max(d)))
print(paste("Suma:", sum(d)))

# --- Wektor e ---
print("--- Wektor e ---")
print(paste("Długość:", length(e)))
print(paste("Typ danych:", typeof(e)))
print(paste("Min:", min(e)))
print(paste("Max:", max(e)))
print(paste("Suma:", sum(e)))

# --- Wektor f ---
print("--- Wektor f ---")
print(paste("Długość:", length(f)))
print(paste("Typ danych:", typeof(f)))
print(paste("Min:", min(f)))
print(paste("Max:", max(f)))
print(paste("Suma:", sum(f)))

# --- Sortowanie ---
print("### iii) Sortowanie wektorów b i e")
print("Posortowany b:")
print(sort(b))
print("Posortowany e:")
print(sort(e))

# --- Operacje ---
print("### iv) Operacje na wektorach")
print("a) d + f:")
print(d + f)
print("b) Iloczyn skalarny d i f:")
print(sum(d * f))
print("c) 35-ty element a:")
print(a[35])
print("d) Elementy 67-85 a:")
print(a[67:85])

# --- Filtracja ---
print("### v) Elementy wektora a mniejsze niż 100")
print("Elementy mniejsze niż 100:")
print(a[a < 100])
print("Liczba elementów mniejszych niż 100:")
print(sum(a < 100))


