# Wczytanie danych
dane <- read.csv("waga1.csv", sep = ";")
head(dane)


# Zadanie 2: Czy studenci średnio przytyli o 2kg
dane$Roznica_wagi <- dane$Waga_po - dane$Waga_przed
t.test(dane$Roznica_wagi, mu = 2)

# Zadanie 3: Proporcja osób > 70kg (Kobiety vs Mężczyźni)
dane$Ponad70 <- ifelse(dane$Waga_po > 70, 1, 0)
tabela_70 <- table(dane$plec, dane$Ponad70)
prop.test(tabela_70)

# Zadanie 4: Czy mężczyźni są średnio o 5cm wyżsi
wzrost_m <- dane$Wzrost[dane$plec == 0]
wzrost_k <- dane$Wzrost[dane$plec == 1]
t.test(wzrost_m, wzrost_k, mu = 5)

# Zadanie 5: Czy 80% studentów przybiera na wadze
czy_przytyl <- ifelse(dane$Waga_po > dane$Waga_przed, 1, 0)
prop.test(sum(czy_przytyl), length(czy_przytyl), p = 0.8)

# Zadanie 6: Czy mężczyźni średnio przytyli o 4kg
dane_m <- subset(dane, plec == 0)
roznica_m <- dane_m$Waga_po - dane_m$Waga_przed
t.test(roznica_m, mu = 4)

