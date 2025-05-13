.data
mode_prompt: .asciiz "Podaj tryb dzialania (0 - szyfrowanie, 1 - deszyfrowanie): "
string_prompt: .asciiz "\nPodaj string do zaszyfrowania/odszyfrowania: "
input_buffer: .space 17
.text
.globl main
main:
#prompt o trybie dzia³ania programu
li $v0, 4
la $a0, mode_prompt
syscall

#wczytywanie trybu dzia³ania programu
li $v0, 5
syscall
move $t0, $v0

#prompt o wpisaniu stringa
li $v0, 4
la $a0, string_prompt
syscall

#wczytywanie stringa
li $v0, 8
la $a0, input_buffer
li $a1, 17
syscall

beq $t0, 0, cipher
beq $t0, 1, decipher

cipher:
decipher: