.data
mode_prompt: .asciiz "Podaj tryb dzialania (0 - szyfrowanie, 1 - deszyfrowanie): "
shift_prompt: .asciiz "\nPodaj przesuniecie: "
string_prompt: .asciiz "\nPodaj string do zaszyfrowania/odszyfrowania: "
result_message: .asciiz "\nTekst po przesunieciu: "
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

	#prompt o podaniu przesuniecia
	li $v0, 4
	la $a0, shift_prompt
	syscall

	#wczytywanie przesuniecia
	li $v0, 5
	syscall
	move $t1, $v0

	#obliczanie efektywnego przesuniecia (przesuniecie%26)
	div $t1, $t1, 26
	mfhi $t1

	#prompt o wpisaniu stringa
	li $v0, 4
	la $a0, string_prompt
	syscall

	#wczytywanie stringa
	li $v0, 8
	la $a0, input_buffer
	li $a1, 17
	syscall

#Kod który konwertuje ma³e litery na wielkie
la $s6, input_buffer   
to_uppercase_loop:
    lb $t5, 0($s6)          
    beq $t5, $zero, to_uppercase_done 
    #97 - ascii a
    blt $t5, 97, not_lowercase_char 
    #32 - sta³a ró¿nica miedzy ma³ymi a wielkimi literami
    subi $t5, $t5, 32       

not_lowercase_char:
    sb $t5, 0($s6)          
    addi $s6, $s6, 1        
    j to_uppercase_loop

to_uppercase_done:

#kod majacy za zadanie zastapienie znaku \n na $zero, aby uniknac bledu przy wyswietlaniu wyniku koncowego
la $s7, input_buffer   
find_newline_loop:
	#ladujemy bajt z $s7 do $t7
	lb $t7, 0($s7)   
	#sprawdzamy czy doszlismy do konca bufora      
	beq $t7, $zero, newline_not_found_or_done 
	#10 - kod ASCII \n           
	beq $t7, 10, replace_found_newline 
	#inkrementujemy adres trzymany w rejestrze $s7
	addi $s7, $s7, 1       
	j find_newline_loop

replace_found_newline:
    sb $zero, 0($s7)  

newline_not_found_or_done:
	#³adujemy adres bufora do $s0
	la $s0, input_buffer
	beq $t0, 0, cipher
	beq $t0, 1, decipher

cipher:
	#³adujemy bajt z adresu $s0 do $t2
	lb $t2, 0($s0)
	#jeœli w $t2 znajduje sie znak koñcowy to opuszczamy petle
	beq $t2, $zero, print_result
	#dodajemy do wartoœci w $t2 (kod ASCII) wartoœæ przesuniecia
	add $t2, $t2, $t1
	bgt $t2, 90, wrap_around_cipher_sub
	blt $t2, 65, wrap_around_cipher_add
	j load_char_cipher

wrap_around_cipher_sub:
	#odejmujemy 26 od wyniku je¿eli wykracza on poza zakres du¿ych liter ASCII
	subi $t2, $t2, 26
	j load_char_cipher
	
wrap_around_cipher_add:
	#odejmujemy 26 od wyniku je¿eli wykracza on poza zakres du¿ych liter ASCII od dolu
	addi $t2, $t2, 26
	j load_char_cipher 
	
load_char_cipher:
	#³adujemy bajt znajduj¹cy siê w $t2 z powrotem do $s0
	sb $t2, 0($s0)
	#inkrementujemy $s0 ¿eby przesunac sie do kolejnego bajtu
	addi $s0, $s0, 1
	#wracamy do pocz¹tku pêtli
	j cipher

decipher:
	#analogicznie jak cypher tylko odejmujemy przesuniecie zamiast dodac
	lb $t2, 0($s0)
	beq $t2, $zero, print_result
	sub $t2, $t2, $t1
	blt $t2, 65, wrap_around_decipher_add
	bgt $t2, 90, wrap_around_decipher_sub
	j load_char_decipher
	
wrap_around_decipher_add:
	#dodajemy 26 do wyniku je¿eli wykracza on poza zakres du¿ych liter ASCII
	addi $t2, $t2, 26
	j load_char_decipher
	
wrap_around_decipher_sub:
	#odejmujemy 26 do wyniku je¿eli wykracza on poza zakres du¿ych liter ASCII od gory
	subi $t2, $t2, 26
	j load_char_decipher

load_char_decipher:
	#³adujemy bajt znajduj¹cy siê w $t2 z powrotem do $s0
	sb $t2, 0($s0)
	#inkrementujemy $s0 ¿eby przesunac sie do kolejnego bajtu
	addi $s0, $s0, 1
	#wracamy do pocz¹tku pêtli
	j decipher

print_result:
	#wiadomoœæ o wyniku
	li $v0, 4
	la $a0, result_message
	syscall

	#wypisanie wyniku
	li $v0, 4
	la $a0, input_buffer
	syscall
