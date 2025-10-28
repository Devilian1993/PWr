setwd("/home/devilian1993/Desktop/PWr/Semestr 3/RPiS/Lista2")
# a) wczytanie danych z pliku
frame <- read.csv("waga1.csv", sep=";")

# b) pierwsze 5 wierszy (bez nazw kolumn)
head(frame, 5)

# c) wypisać strukturę ramki 
str(frame)

# d) średni wzrost i waga przed
avg_height <- mean(frame$Wzrost)
avg_weight_before <- mean(frame$Waga_przed)
print(avg_height)
print(avg_weight_before)

# e) nowa kolumna BMI przed studiami
frame$BMI_before <- frame$Waga_przed / (frame$Wzrost * frame$Wzrost / 10000)
head(frame)

# f) nowa rama kobiety o BMI > 25
frame_female_overweight <- frame[frame$plec == 1 & frame$BMI_before > 25,]
print(frame_female_overweight)

# g) rama wszyscy mężczyźni
frame_male <- frame[frame$plec == 0,]
print(frame_male)

# h) ile osób wyższe niż 175
height_over_175 = sum(frame$Wzrost > 175)
print(height_over_175)
