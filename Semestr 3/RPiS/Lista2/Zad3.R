setwd("/home/devilian1993/Desktop/PWr/Semestr 3/RPiS/Lista2")
# a) wczytanie danych z pliku
frame <- read.csv("mieszkania.csv", sep=";")

# b) pierwsze sześć wierszy
head(frame)

# c) struktura ramy
str(frame)

# d) średni metraż i cena
avg_area = mean(frame$Metraz)
avg_price = mean(frame$Cena)
print(avg_area)
print(avg_price)

# e) dopisać cene za m^2
frame$PricePerSqMeter <- frame$Cena / frame$Metraz
head(frame)

# f) Oferty na psim polu < 400 000
frame_psie_pole_lt_400000 <- frame[frame$Cena < 400000 & frame$Dzielnica == "Psie Pole",]
head(frame_psie_pole_lt_400000)

# g) Oferty na srodmiesciu metraż > 60
frame_srodmiescie_mt_60 <- frame[frame$Metraz > 60 & frame$Dzielnica == "Śródmieście",]
head(frame_srodmiescie_mt_60)

# h) Ile mieszkań o metrażu > 60 i cenie < 350000
mt_60_lt_350000 <- sum(frame$Metraz > 60 & frame$Cena < 350000)
print(mt_60_lt_350000)

# i) Mieszkanie o najlepszym stosunku metrażu do ceny
ratio <- frame$Metraz / frame$Cena
best_area_to_price <- frame[ratio == max(ratio), ]
print(best_area_to_price)

# j) Która dzielnica charakteryzuje sie najbardziej stabilnymi cenami
dzielnice <- aggregate(Cena ~ Dzielnica, data = frame, FUN = sd)
dzielnice_sorted <- dzielnice[order(dzielnice$Cena),]
print(dzielnice_sorted)
