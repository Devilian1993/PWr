n <- 100              
k <- 30               
p_hat <- k / n        
p0 <- 0.35             

# a) a)	Przetestować hipotezę,
# że 35% wszystkich studentów ma IQ wyższy niż 115 
# i) Z-test
SE <- sqrt((p0 * (1 - p0)) / n)
Z_stat <- (p_hat - p0) / SE
p_val <- 2 * (1 - pnorm(abs(Z_stat)))

print(p_val)

# ii) prop.test
prop_test_a <- prop.test(n_over_115, n, p = 0.35)
print(prop_test_a)

# b) b)	Wyznaczyć przedział ufności dla proporcji wszystkich studentów mającej
# IQ wyższy niż 115 na poziomie ufności 99% za pomocą 
# i) Przybliżenie do rozkładu normalnego
alpha_b <- 0.01
z_crit_b <- qnorm(1 - alpha_b / 2)
SE_prop_sample <- sqrt((p_hat * (1 - p_hat)) / n)

ci_prop_lower <- p_hat - z_crit_b * SE_prop_sample
ci_prop_upper <- p_hat + z_crit_b * SE_prop_sample

print(c(ci_prop_lower, ci_prop_upper))

# ii) prop.test
prop_test_b <- prop.test(k, n, conf.level = 0.99)
print(prop_test_b$conf.int)

# c) Przedział ufności dla średniego IQ (90%) - wzór dla dużej próby (Z)
alpha_c <- 0.10
z_crit_c <- qnorm(1 - alpha_c / 2)
SE_mean <- sd_iq / sqrt(n)

ci_mean_Z_lower <- mean_iq - z_crit_c * SE_mean
ci_mean_Z_upper <- mean_iq + z_crit_c * SE_mean

print(c(ci_mean_Z_lower, ci_mean_Z_upper))


# d) Przedział ufności dla średniego IQ (90%) - rozkład Studenta
t_crit_d <- qt(1 - alpha_c / 2, df = n - 1)

ci_mean_T_lower <- mean_iq - t_crit_d * SE_mean
ci_mean_T_upper <- mean_iq + t_crit_d * SE_mean

print(c(ci_mean_T_lower, ci_mean_T_upper))


# e) Test hipotezy, że średnie IQ wynosi 115 (H0: mu = 115)
T_stat <- (mean_iq - mu0) / SE_mean

# i) Test Z
p_val_Z_mean <- 2 * (1 - pnorm(abs(T_stat)))

print(p_val_Z_mean)

# ii) Test T (studenta)
p_val_T_mean <- 2 * (1 - pt(abs(T_stat), df = n - 1))

print(p_val_T_mean)
