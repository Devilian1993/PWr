.data
sequence_number_prompt: .asciiz "Podaj liczbe ciagow (1-10): "
endl: .asciiz "\n"
space: .asciiz " "
sequence_prompt: .asciiz "Podaj ciag: "
error_prompt: .asciiz "Podaj poprawna liczbe ciagow!\n"
input_buffer: .space 256
stack: .space 2048

.text
.globl main

main:
	li $v0, 4
	la $a0, sequence_number_prompt
	syscall
	
	# $s0 - liczba ciagow 
	li $v0, 5
	syscall 
	move $s0, $v0
	
	blt $s0, 0, wrong_sequence_number
	bgt $s0, 10, wrong_sequence_number
	
	# $s1 - pocz¹tek stosu
	la $sp, stack
	la $s1, stack
	
	# $t0 - iterator dla ci¹gu
	li $t0, 1
	
	j sequence_input
	
wrong_sequence_number:
	li $v0, 4
	la $a0, error_prompt
	syscall
	
	j main
	
sequence_input:
	bgt $t0, $s0, first_output
	
	# wprowadzanie ci¹gu
	li $v0, 4
	la $a0, sequence_prompt
	syscall
	
	li $v0, 8
	la $a0, input_buffer
	li $a1, 256
	syscall
	
	# $t1 - iterator dla pojedynczego ci¹gu
	li $t1, 0
	
next_word:
	# $s2 - indeks pocz¹tku s³owa
	move $s2, $t1
	
load_char:
	# $t2 - aktualny znak
	lb $t2, input_buffer($t1)
	# 10 - "\n" 32 - " "
	# load_word - ³adujemy s³owo na stos
	beq $t2, 10, load_word
	beq $t2, 32, load_word
	
	# zwiekszamy iterator dla ciagu o 1
	addi $t1, $t1, 1
	j load_char
	
load_word:
	# $s3 - indeks koñca s³owa
	move $s3, $t1
	# odejmujemy 1 ¿eby wskazywaæ na ostatni znak przed \n lub " "
	subi $s3, $s3, 1
	
	# wstawiamy s³owo na stos
	jal push_on_stack
	
	# zwiêkszamy iterator dla ci¹gu o 1
	addi $t1, $t1, 1
	# jak dojdziemy do "\n" to przechodzimy do kolejnego ci¹gu
	beq $t2, 10, next_sequence
	
	j next_word

push_on_stack:
	# jeœli koniec s³owa dojdzie do pocz¹tku - koñczyny
	bgt $s2, $s3, end_of_word
	# ³adujemy koniec s³owa do $t3
	lb $t3, input_buffer($s3)
	# zmniejszamy wskaŸnik na koniec
	subi $s3, $s3, 1
	# ³adujemy $t3 na stos
	sb $t3, 0($sp)
	# zwiêkszamy wskaŸnik stosu
	addi $sp, $sp, 1
	j push_on_stack
	
next_sequence:
	# zwiêkszamy iterator globalny (ci¹gów, nie s³ów)
	addi $t0, $t0, 1
	j sequence_input
	
end_of_word:
	# ³adujemy spacjê na stos
	lb $t3, space
    	sb $t3, 0($sp)
    	addi $sp, $sp, 1
    	j return
    	
output:
	# jak wskaŸnik na szczyt stosu zrówna siê z wskaŸnikiem na koniec - wychodzimy
	blt $sp, $s1, exit
	# ³adujemy do $a0 szczyt stosu i wypisujemy
	lb $a0, 0($sp)
	li $v0, 11
	syscall
	# zmniejszamy szczyt stosu
	subi $sp, $sp, 1
	j output
	
first_output:
	# usuwamy ostatnie 2 znaki - nadmiarowe spacje
	subi $sp, $sp, 2
	j output	

exit:
	li $v0, 10
    	syscall

return:
	jr $ra
	
	