# Dane 
n_k <- 520   # Kobiety 
x_k <- 220   # Kobiety z wyższym
n_m <- 480   # Mężczyźni 
x_m <- 165   # Mężczyźni z wyższym

#1.	a) 220 z 520 kobiet ma wyższe wykształcenie,
# a 165 z 480 mężczyzn. Przetestować hipotezę,
# iż prawdopodobieństwo że osoba kończy studia nie zależy od płci
# za pomocą i) testu Z, ii) polecenia „prop.test”.
p1 <- x_k / n_k
p2 <- x_m / n_m
p_wspolne <- (x_k + x_m) / (n_k + n_m)
SE <- sqrt(p_wspolne * (1 - p_wspolne) * (1/n_k + 1/n_m))
z_wynik <- (p1 - p2) / SE
p_value_z <- 2 * (1 - pnorm(abs(z_wynik)))

print(z_wynik)      # Wyświetl statystykę Z
print(p_value_z)    # Wyświetl p-value

prop.test(x = c(x_k, x_m), n = c(n_k, n_m), correct = FALSE)

# p-value = 0.01001 < 0.05 więc odrzucamy na poziomie istotności 5%

# 1b. Tablica rozdzielcza
bez_edu_k <- n_k - x_k
bez_edu_m <- n_m - x_m
tabela <- matrix(c(bez_edu_k, bez_edu_m, x_k, x_m), nrow = 2)
colnames(tabela) <- c("Brak", "Wyzsze")
rownames(tabela) <- c("Kobiety", "Mezczyzni")
tabela       

# 1c. Test Chi-kwadrat i Fishera
chisq.test(tabela, correct = FALSE)
fisher.test(tabela)

# 1d. Ręczny test Z dla różnicy średnich (wzrost)
sr_k <- 166;
var_k <- 100
sr_m <- 174
var_m <- 121

SE_wzrost <- sqrt(var_k/n_k + var_m/n_m)
z_wzrost <- (sr_k - sr_m) / SE_wzrost
p_value_wzrost <- 2 * (1 - pnorm(abs(z_wzrost)))

print(z_wzrost)         
print(p_value_wzrost)  
