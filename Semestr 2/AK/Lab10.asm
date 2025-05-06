.data
first_num: .asciiz "Podaj pierwsza liczbe: "
operation: .asciiz "\nPodaj kod dzialania (0: +; 1: -; 2: /; 3: *): "
second_num: .asciiz "\nPodaj druga liczbe: "
endline: .asciiz "\n"
new_prompt: .asciiz "\nCzy chcesz kontynuowac? (0 nie 1 tak): "

.text
.globl main
main:
#wczytanie 1. argumentu (w rejestrze $t0)
li $v0, 4
la $a0, first_num 
syscall

li $v0, 5
syscall

move $t0, $v0

#wczytanie kodu dzia³ania (w rejestrze $t1)
li $v0, 4
la $a0, operation 
syscall

li $v0, 5
syscall

move $t1, $v0

#wczytanie 2. argumentu (w rejestrze $t2)
li $v0, 4
la $a0, second_num 
syscall

li $v0, 5
syscall

move $t2, $v0

beq $t1, 0, addition
beq $t1, 1, subtraction
beq $t1, 2, division
beq $t1, 3, multiplication


#koniec	
li $v0, 10
syscall

addition:
add $v0, $t0, $t2
j end

subtraction:
sub $v0, $t0, $t2
j end

division:
div $t0, $t2
mflo $v0
j end

multiplication:
mult $t0, $t2
mflo $v0
j end

end:
move $a0, $v0 
li $v0, 1
syscall

li $v0, 4
la $a0, new_prompt 
syscall

li $v0, 5
syscall

beq $v0, 1, main


