# Wczytanie danych
data <- read.csv("waga1.csv", sep = ";")
head(data)
n <- nrow(data)

# Zad 2, 3
# H0 - średni wzrost wszystkich studentów wynosi 170 cm
# H1 - średni wzrost wszystkich studentów nie wynosi 170 cm
p_2 <- t.test(data$Wzrost, , mu = 170, conf.level = 0.9)
print(p_2)

# p-value = 0.0003244
# więc odrzucamy hipotezę zerową z dużą pewnością
# 90 percent confidence interval:
# 162.6116 167.1662

# Zad 4, 5
# H0 - średni wzrost studentek wynosi 160 cm
# H1 - średni wzrost studentek nie wynosi 160 cm
wzrost_studentek <- data[data$plec == 1, ]$Wzrost
p_4 <- t.test(wzrost_studentek, mu = 160, conf.level = 0.98)
print(p_4)

# p-value = 0.3574
# czyli brak podstaw do odrzucenia H0
# 98 percent confidence interval:
# 154.0376 162.6432

# Zad 6, 7
# H0 - proporcja wzrostu studentów o wzroście > 180 wynosi 0.25
# H1 - proporcja wzrostu studentów o wzroście > 180 nie wynosi 0.25
wzrost_studentow <- data[data$plec == 0, ]$Wzrost
n <- length(wzrost_studentow)       
k <- sum(wzrost_studentow > 180)
p_6 <- prop.test(x = k, n = n, p = 0.25, conf.level = 0.96)
print(p_6)

# p-value = 0.631
# czyli brak podstaw do odrzucenia H0
# 96 percent confidence interval:
# 0.1120782 0.3576638
