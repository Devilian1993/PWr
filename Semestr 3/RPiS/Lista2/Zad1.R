#Wczytanie danych

imiona <- c("Krzysztof", "Maria", "Henryk", "Anna")
plec <- c("M", "K", "M", "K")
oceny_analiza <- c(3.5 , 4.5, 5.0, 4.5)
oceny_algebra <- c(4.0, 5.0, 4.0, 3.5)

# a) tworzenie ramki
frame <- data.frame(imiona, plec, oceny_analiza, oceny_algebra)

# b) pierwsze dwa wiersze
head(frame, 2)

# c) str()
str(frame)

# d) średnia ocen z analizy
print(mean(frame$oceny_analiza))

# e) nowa kolumna z średnią ocen
frame$srednia <- rowMeans(frame[,c("oceny_analiza", "oceny_algebra")])
print(frame)

# f) rama z wynikami kobiet
frame_female <- frame[frame$plec == "K",]
print(frame_female)

# g) rama z wynikami, gdzie student ma >= 4.5 z któregoś przedmiotu
frame_good_grades = frame[frame$oceny_algebra >= 4.5 | frame$oceny_analiza >= 4.5,]
print(frame_good_grades)

# h) ile osób >= 4.5 z analizy
n_o_good_grades_calculus = sum(frame$oceny_analiza >= 4.5)
print(n_o_good_grades_calculus)
