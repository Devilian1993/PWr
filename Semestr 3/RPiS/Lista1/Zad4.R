print("Zadanie 4")

a_vector <- c(-3, 4, 1, -5, -22, 3)
a <- matrix(a_vector, nrow = 2, ncol = 3)
print(a)

b_vector_1 <- c(1, 3, 5)
b_vector_2 <- c(2, 4, 6)
b <- cbind(b_vector_1, b_vector_2)
print(b)

c_vector_1 <- c(7, -3)
c_vector_2 <- c(-2, 1)
c <- rbind(c_vector_1, c_vector_2)
print(c)

d_vector <- c(1, 3, 2, 2, 5, 3, 4, 7, 2)
d <- matrix(d_vector, nrow = 3)
print(d)

cat("\n### ii) Operacje na macierzach:\n")

# a) A + B
cat("\nii) a) A + B:\n")
print(a + b)

# b) A^T + B
cat("\nii) b) A^T + B:\n")
print(t(a) + b)

# c) B %*% A
cat("\nii) c) B %*% A:\n")
print(b %*% a)

# d) B * B 
cat("\nii) d) B * B:\n")
print(b * b)

# e) C^-1 
cat("\nii) e) C^-1:\n")
print(solve(c))

# f) C %*% C^-1 
cat("\nii) f) C %*% C^-1:\n")
print(c %*% solve(c))

cat("\n### iii) Rozwiązywanie równań macierzowych:\n")

# a) X * C = B  => X = B %*% C^-1
cat("\niii) a) X * C = B:\n")
print(b %*% solve(c))

# b) D * X = B  => X = D^-1 %*% B
cat("\niii) b) D * X = B:\n")
print(solve(d) %*% b)