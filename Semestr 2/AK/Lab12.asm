.data
endl: .asciiz "\n"
sequence: .space 11

.text
.globl main
main:
	#ustawianie ziarna generatora
	li $v0, 40
	li $a0, 0
	#w adresie $a1 znajduje sie ziarno - jest ono sta³e wiec bez jego zmiany wyniki bêd¹ takie same
	li $a1, 11
	syscall
	
	#ustaw licznik
	li $t0, 0
	#skacz do funkcji generatora
	j generator
	
generator:
	#przy 11 iteracji - exit od razu
	beq $t0, 10, exit
	#³adujemy adres ciagu
	la $t3, sequence
	#iterator dla dlugosci ciagu
	li $t1, 0
	jal sequence_generator
	
	#wypisz ciag
	li $v0, 4
	la $a0, sequence
	syscall
	
	#wypisz endline
	li $v0, 4
	la $a0, endl
	syscall
	
	addi $t0, $t0, 1
	j generator
	
sequence_generator:
	#przy 11 iteracji - wracamy do generatora
	beq $t1, 10, add_nullchar
	
	#musimy na chwile zmieniæ $ra, ¿ebyœmy mogli wróciæ z powrotem do generatora na koñcu iteracji
	move $t6, $ra
	jal get_random_alphanumeric_char
	move $ra, $t6
	
	sb $t7, 0($t3)
	
	addi $t1, $t1, 1
	addi $t3, $t3, 1
	j sequence_generator
	
add_nullchar:
	sb $zero, 0($t3)
	j return	
	
get_random_alphanumeric_char:
	#0 - liczba 1 - litera
	li $v0, 42
	li $a0, 0
	li $a1, 2
	syscall
	
	beq $a0, 0, get_random_number
	beq $a0, 1, get_random_letter
	
get_random_number:
	#ustawia losowa liczbe w rejestrze $t7
	li $v0, 42
	li $a0, 0
	li $a1, 10
	syscall
	
	li $t7, 48
	add $t7, $t7, $a0
	
	j return
	
get_random_letter:
	#ustawia losowa litere  w rejestrze $t7
	li $v0, 42
	li $a0, 0
	li $a1, 26
	syscall
	
	li $t7, 65
	add $t7, $t7, $a0
	
	j return
	
return:
	jr $ra																											
	
exit:
	#syscall zamykaj¹cy program
	li $v0, 10
    	syscall