print("Zadanie 2")

a_vector <- c(3, 4, 1, 5, 2, 3)
a <- matrix(a_vector, nrow = 2, ncol = 3)
print(a)

b_vector_1 <- c(-1, 3, -5)
b_vector_2 <- c(2, -4, 6)
b <- cbind(b_vector_1, b_vector_2)
print(b)

c_vector_1 <- c(7, 3)
c_vector_2 <- c(2, 1)
c <- rbind(c_vector_1, c_vector_2)
print(c)

d_vector <- c(1, 3, 5, 2, 5, 7, 4, 7, 11)
d <- matrix(d_vector, nrow = 3)
print(d)

### ii) Wyznaczyć a) A+B, b) A+BT, c) AB d) A*A, e) D-1, f) DD-1

# a) A + B
print("ii) a) A + B:")
print(a + b) # Spowoduje błąd w R ze względu na niezgodność wymiarów

# b) A + B^T
print("ii) b) A + B^T:")
print(a + t(b))

# c) A %*% B (mnożenie macierzowe)
print("ii) c) A %*% B:")
print(a %*% b)

# d) A * A (mnożenie element-po-elemencie)
print("ii) d) A * A:")
print(a * a)

# e) D^-1 (macierz odwrotna do D)
print("ii) e) D^-1:")
print(solve(d))

# f) D %*% D^-1 (mnożenie macierzowe D * D^-1)
print("ii) f) D %*% D^-1:")
print(d %*% solve(d))

### iii) Rozwiązać równania a) CX = A, b) XD=A

# a) cX = a  => X = c^-1 %*% a
print("iii) a) cX = a:")
print(solve(c) %*% a)

# b) Xd = a  => X = a %*% d^-1
print("iii) b) Xd = a:")
print(a %*% solve(d))
