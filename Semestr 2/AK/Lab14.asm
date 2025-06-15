.data
	.eqv PLAYER_X 1
	.eqv PLAYER_O 2
	.eqv EMPTY 0
	.eqv MIN_VAL 0x80000000
	.eqv MAX_VAL 0x7FFFFFFF
	
	board: .byte EMPTY, EMPTY, EMPTY
	       .byte EMPTY, EMPTY, EMPTY
	       .byte EMPTY, EMPTY, EMPTY
	       
	msg_welcome: .asciiz "Welcome to tick-tac-toe!\n"
	msg_choose_mode: .asciiz "\nChoose the game mode (0 - PvC 1 - CvC): "
	msg_set_number_of_games: .asciiz "\nInput the number of games to play "
	msg_board:   .asciiz "\nBoard:\n"
    	msg_human_turn: .asciiz "Your move (1-9): "
    	msg_invalid_move: .asciiz "Incorrect move!.\n"
    	msg_computer_move: .asciiz "Computer's move: "
    	msg_human_win: .asciiz "\nYou won!\n"
    	msg_computer_win: .asciiz "\nComputer won!\n"
    	msg_draw: .asciiz "\nDraw!\n"
    	msg_newline: .asciiz "\n"
    	msg_space: .asciiz " " 
    	msg_pipe: .asciiz "|"
    	msg_hline: .asciiz "---+---+---\n"
    	
    	game_mode:      .word 0        
    	current_player: .word PLAYER_X
    	
    	msg_computer_move_done: .asciiz "Computer move done\n"
    	msg_computer_turn: .asciiz "Computer turn\n"
    	msg_computer_X_turn: .asciiz "Computer's move (X):\n"
    	msg_computer_O_turn: .asciiz "Computer's move (O):\n"
    	msg_computer_X_win: .asciiz "\n*** COMPUTER (X) WON! ***\n"
    	msg_computer_O_win: .asciiz "\n*** COMPUTER (O) WON! ***\n"
    	msg_play_again:     .asciiz "Play again? (1=Yes, 0=No): "
    	msg_goodbye:        .asciiz "Thanks for playing! Goodbye.\n"
    	
    	player_X_score: .word 0 
    	player_O_score: .word 0 
    	msg_X_points: .asciiz "Player X points: "
    	msg_O_points: .asciiz "Player O points: "

.text
.globl main
	main:
    		li $v0, 4
    		la $a0, msg_welcome
    		syscall    

	main_choose_mode:
    		# 1 - PvC 2 - CvC
    		li $v0, 4
    		la $a0, msg_choose_mode
    		syscall

    		li $v0, 5                
    		syscall
    		sw $v0, game_mode      
    		
    		li $v0, 4
    		la $a0, msg_set_number_of_games
    		syscall  
    		
    		# $t8 - liczba gier
    		li $v0, 5                
    		syscall
    		move $t8, $v0 
    		
    		
    		# $t9 - iterator liczby gier
    		li $t9, 0

	main_game_start:
		beq $t9, $t8, exit
		# $t0 - adres planszy
		# $t1 - indeks pola na planszy
    		la $t0, board            
    		li $t1, 0                
	reset_board_loop:
		# iterujemy do 9 (plansza indeksowana 0-8)
		# na ka¿de pole z planszy ³adujemy EMPTY
    		bge $t1, 9, reset_board_end
    		li $t2, EMPTY            
    		sb $t2, ($t0)            
    		addi $t0, $t0, 1         
    		addi $t1, $t1, 1         
    		j reset_board_loop
	reset_board_end:
		# $t0 - obecny gracz
		# $t1 - znak obecnego gracza
    		lw $t0, current_player   
    		li $t1, PLAYER_X         
    		sw $t1, current_player   

	game_loop:
    		# wyœwietlanie planszy
    		la $a0, board            
    		jal print_board
		
		# sprawdzanie warunków zwyciêstwa
		# $a0 - plansza
		# $a1 - znak gracza do sprawdzenia
    		la $a0, board
    		li $a1, PLAYER_O
    		jal _check_win
    		# $v0 - wynik (1 - wygrana, 0 - przegrana)
   		bnez $v0, game_end_computer_O_win 

    		la $a0, board
    		li $a1, PLAYER_X
    		jal _check_win
    		bnez $v0, game_end_player_X_win 

    		la $a0, board
   		jal _check_draw
   		# $v0 - remis (1 - remis, 0 - brak remisu)
    		bnez $v0, game_end_draw     

		# $t0 - obecny gracz (X/O) 
		# $t1 - tryb gry
    		lw $t0, current_player   
    		lw $t1, game_mode        

    		beq $t0, PLAYER_X, handle_player_X_turn
    		beq $t0, PLAYER_O, handle_player_O_turn

	handle_player_X_turn:
    		beq $t1, 0, handle_human_turn 
    		j handle_computer_X_turn      

	handle_human_turn:
    		# $a0 - adres planszy
    		la $a0, board
    		jal _get_human_move   
    		# $v0 = indeks ruchu przenoszony potem do $s5
    		move $s5, $v0            
    
    		# Wykonaj ruch
    		la $a0, board
    		move $a1, $s5            
    		li $a2, PLAYER_X         
    		jal _make_move

    		# Zamiana gracza
    		li $t0, PLAYER_O
    		sw $t0, current_player
    		j game_loop              

	handle_computer_X_turn:
    		li $v0, 4
    		la $a0, msg_computer_X_turn
    		syscall

    		# $a0 - adres planszy
    		la $a0, board
    		jal _find_best_computer_move 
    		# $v0 = indeks ruchu przenoszony potem do $s5
    		move $s5, $v0                

    		# Wyœwietl ruch komputera
    		li $v0, 4
    		la $a0, msg_computer_move_done
    		syscall
    		li $v0, 1                  
    		addi $a0, $s5, 1           
    		syscall
    		li $v0, 4
    		la $a0, msg_newline
    		syscall

    		# Wykonaj ruch
    		la $a0, board
    		move $a1, $s5              
    		li $a2, PLAYER_X           
    		jal _make_move

    		# Zamiana gracza
    		li $t0, PLAYER_O
    		sw $t0, current_player
    		j game_loop

	handle_player_O_turn:
    		li $v0, 4
    		la $a0, msg_computer_turn
    		syscall

    		# $a0 - adres planszy
    		la $a0, board
    		jal _find_best_computer_move 
    		# $v0 = indeks ruchu przenoszony potem do $s5
    		move $s5, $v0               

    		# Wyœwietl ruch komputera
    		li $v0, 4
    		la $a0, msg_computer_move_done
    		syscall
    		li $v0, 1                  
    		addi $a0, $s5, 1           
    		syscall
    		li $v0, 4
    		la $a0, msg_newline
    		syscall

    		# Wykonaj ruch
    		la $a0, board
    		move $a1, $s5   
    	        li $a2, PLAYER_O   
    		jal _make_move

    		# Zamiana gracza
    		li $t0, PLAYER_X
    		sw $t0, current_player
    		j game_loop

	game_end_computer_O_win:
    		li $v0, 4
    		lw $t0, game_mode
    		beq $t0, 0, print_comp_win 
    		la $a0, msg_computer_O_win 
    		lw $t6, player_O_score     
    		addi $t6, $t6, 1           
    		sw $t6, player_O_score
    		j print_win_message
    		
	print_comp_win:
    		la $a0, msg_computer_win
    		
	print_win_message:
    		syscall
    		j game_end_prompt

	game_end_player_X_win:
    		li $v0, 4
    		lw $t0, game_mode
    		beq $t0, 0, print_human_win 
    		la $a0, msg_computer_X_win  
    		lw $t7, player_X_score     
    		addi $t7, $t7, 1           
    		sw $t7, player_X_score 
    		j print_win_message
    		
	print_human_win:
    		la $a0, msg_human_win
    		j print_win_message

	game_end_draw:
    		li $v0, 4
    		la $a0, msg_draw
    		syscall
    		j game_end_prompt

	game_end_prompt:
    		la $a0, board
    		jal print_board
    		
    		#li $v0, 4
        	#la $a0, msg_X_points
        	#syscall
        
        	#li $v0, 1
        	#lw $a0, player_X_score 
        	#syscall
        
        	#li $v0, 4
        	#la $a0, msg_newline
        	#syscall
        
        	#li $v0, 4
        	#la $a0, msg_O_points
        	#syscall
        
        	#li $v0, 1
        	#lw $a0, player_O_score 
        	#syscall
        
        	#li $v0, 4
        	#la $a0, msg_newline
       	 	#syscall

    		addi $t9, $t9, 1
    		j main_game_start    

	print_board:
        	addi $sp, $sp, -4 
        	sw $s0, 0($sp)    

        	move $s0, $a0          

    		li $v0, 4                
   		la $a0, msg_board       
    		syscall                   

    		li $t0, 0              

	board_loop:
    		bge $t0, 9, board_loop_end 

    		add $t1, $s0, $t0         
    		lb $t2, ($t1)             

    		beq $t2, PLAYER_X, print_X
    		beq $t2, PLAYER_O, print_O
    		
    		li $v0, 11
    		li $a0, ' '               
    		syscall
    		
    		j print_char_done

	print_X:
    		li $v0, 11               
    		li $a0, 'X'              
    		syscall
    		j print_char_done

	print_O:
    		li $v0, 11        
    		li $a0, 'O'   
    		syscall
    		j print_char_done

	print_char_done:
   		li $t3, 3                
    		rem $t1, $t0, $t3        

    		beq $t1, 2, end_of_row
    
    		li $v0, 4                 
    		la $a0, msg_pipe          
    		syscall
    		j next_iteration          

	end_of_row:
    		li $v0, 4                 
    		la $a0, msg_newline     
    		syscall

    		bne $t0, 8, print_hline   
    		j next_iteration          

	print_hline:
    		li $v0, 4                
    		la $a0, msg_hline         
    		syscall

	next_iteration:
    		addi $t0, $t0, 1          
    		j board_loop

	board_loop_end:
        	lw $s0, 0($sp)    
        	addi $sp, $sp, 4  
    		jr $ra               
    		
    	exit:
    		li $v0, 10           
    		syscall  
    		
    	_check_win:
    		# na stosie: $ra $s0 - adres planszy $s1 - symbol gracza
    		addi $sp, $sp, -12
    		sw $ra, 8($sp)  
    		sw $s0, 4($sp)  
    		sw $s1, 0($sp)  

    		move $s0, $a0  
   		move $s1, $a1   

    		lb $t0, 0($s0)  
    		bne $t0, $s1, check_row1 
    		lb $t1, 1($s0)  
    		bne $t1, $s1, check_row1
    		lb $t2, 2($s0)  
    		beq $t2, $s1, check_win_found 

	check_row1:
    		lb $t0, 3($s0)  
    		bne $t0, $s1, check_row2
    		lb $t1, 4($s0)  
    		bne $t1, $s1, check_row2
    		lb $t2, 5($s0)  
    		beq $t2, $s1, check_win_found

	check_row2:
    		lb $t0, 6($s0) 
    		bne $t0, $s1, check_col0
    		lb $t1, 7($s0)  
    		bne $t1, $s1, check_col0
    		lb $t2, 8($s0)  
    		beq $t2, $s1, check_win_found

	check_col0:
    		lb $t0, 0($s0) 
    		bne $t0, $s1, check_col1
    		lb $t1, 3($s0)  
    		bne $t1, $s1, check_col1
    		lb $t2, 6($s0)  
    		beq $t2, $s1, check_win_found

	check_col1:
    		lb $t0, 1($s0)  
    		bne $t0, $s1, check_col2
    		lb $t1, 4($s0) 
    		bne $t1, $s1, check_col2
    		lb $t2, 7($s0) 
    		beq $t2, $s1, check_win_found

	check_col2:
    		lb $t0, 2($s0)  
    		bne $t0, $s1, check_diag0
    		lb $t1, 5($s0)  
    		bne $t1, $s1, check_diag0
    		lb $t2, 8($s0) 
    		beq $t2, $s1, check_win_found

	check_diag0:
    		lb $t0, 0($s0)  
    		bne $t0, $s1, check_diag1
    		lb $t1, 4($s0)  
    		bne $t1, $s1, check_diag1
    		lb $t2, 8($s0) 
    		beq $t2, $s1, check_win_found

	check_diag1:
    		lb $t0, 2($s0)  
    		bne $t0, $s1, check_win_not_found 
    		lb $t1, 4($s0)  
    		bne $t1, $s1, check_win_not_found
    		lb $t2, 6($s0)  
    		beq $t2, $s1, check_win_found

	check_win_not_found:
    		li $v0, 0 
    		j _check_win_exit 

	check_win_found:
    		li $v0, 1 

	_check_win_exit:
    		lw $ra, 8($sp)  
    		lw $s0, 4($sp)  
    		lw $s1, 0($sp)  
    		addi $sp, $sp, 12 
    		jr $ra         
    
    	_check_draw:
    		addi $sp, $sp, -12
    		sw $ra, 8($sp)  
    		sw $s0, 4($sp)  
    		sw $s1, 0($sp) 

    		move $s0, $a0   

    		li $s1, 0       

	check_draw_loop:
   		 bge $s1, 9, check_draw_end_loop

    		add $t0, $s0, $s1         
    		lb $t1, ($t0)            

    		beq $t1, EMPTY, check_draw_not_found

    		addi $s1, $s1, 1          
    		j check_draw_loop         

	check_draw_not_found:
    		li $v0, 0                
    		j _check_draw_exit        

	check_draw_end_loop:
    		li $v0, 1                 

	_check_draw_exit:
    		lw $ra, 8($sp)  
    		lw $s0, 4($sp)  
    		lw $s1, 0($sp)  
    		addi $sp, $sp, 12 
    		jr $ra         
    
    	_get_human_move:
    		addi $sp, $sp, -8
    		sw $ra, 4($sp)  
    		sw $s0, 0($sp)  

    		move $s0, $a0   

	get_input_loop:
    		li $v0, 4                 
    		la $a0, msg_human_turn    
    		syscall

		# $v0 - ruch u¿ytkownika (nr pola na planszy)
		li $v0, 5                
    		syscall                   

		# sprawdzanie poprawnoœci inputu
    		slti $t1, $v0, 1          
    		bnez $t1, invalid_move_range
    		slti $t1, $v0, 10        
    		beqz $t1, invalid_move_range 

		# [1, 9] -> [0, 8]
    		addi $v0, $v0, -1         

    		add $t1, $s0, $v0         
    		lb $t2, ($t1)             

		# sprawdzamy czy wybra³ puste pole
    		beq $t2, EMPTY, valid_move 

		# jeœli doszed³ tutaj - niepoprawny
    		li $v0, 4                 
    		la $a0, msg_invalid_move  
    		syscall
    		j get_input_loop          

	invalid_move_range:
    		li $v0, 4                 
    		la $a0, msg_invalid_move  
    		syscall
    		j get_input_loop          

	valid_move:
    		j _get_human_move_exit
    		
	_get_human_move_exit:
    		# Przywracanie rejestrów ze stosu
    		lw $ra, 4($sp)  
    		lw $s0, 0($sp)  
    		addi $sp, $sp, 8 
    		jr $ra         
    
	_make_move:
    		add $t0, $a0, $a1        
    		sb $a2, ($t0)             
    		jr $ra                   
    
	_undo_move:
    		# Cofanie ruchu - konieczne do minmax
    		add $t0, $a0, $a1         
    		li $t1, EMPTY             
    		sb $t1, ($t0)             
    		jr $ra                   
    
   	 _minimax:
    		# $ra, $s0 - najlepszy wynik, $s1 - indeks, $s2 - znak gracza, $s3 - adres planszy
    		addi $sp, $sp, -20
   		sw $ra, 16($sp)  
    		sw $s0, 12($sp) 
    		sw $s1, 8($sp)  
    		sw $s2, 4($sp)  
    		sw $s3, 0($sp)   

    		# $s4 - g³êbokoœæ rekurencji
    		move $s3, $a0    
    		move $s2, $a1   
    		move $s4, $a2   

    		# Sprawdzanie O
    		move $a0, $s3            
    		li $a1, PLAYER_O          
    		jal _check_win
    		bnez $v0, minimax_O_wins  

    		# Sprawdzanie X
    		move $a0, $s3           
    		li $a1, PLAYER_X         
    		jal _check_win
    		bnez $v0, minimax_X_wins  

    		# Sprawdzanie remisu
    		move $a0, $s3             
    		jal _check_draw
    		bnez $v0, minimax_draw   

    		beq $s2, PLAYER_O, minimax_maximizer_turn

	minimax_minimizer_turn:
		# %s0 - MAX_VAL, $s1 - indeks
    		li $s0, MAX_VAL           
    		li $s1, 0   
    		              
	minimax_min_loop:
    		bge $s1, 9, minimax_min_loop_end # Jeœli loop_idx >= 9, koniec pêtli

    		add $t0, $s3, $s1         
    		lb $t1, ($t0)            
    		bne $t1, EMPTY, minimax_min_next_iter 

    		# Ruch obecnego gracza
    		move $a0, $s3             
    		move $a1, $s1             
    		li $a2, PLAYER_X          
    		jal _make_move

    		# Rekurencyjny ruch przeciwnika
    		move $a0, $s3            
    		li $a1, PLAYER_O          
    		addi $a2, $s4, 1          
    		jal _minimax
    		move $t0, $v0             

    		move $a0, $s3          
    		move $a1, $s1           
    		jal _undo_move

    		# Aktualizacja wyniku
    		slt $t1, $t0, $s0         
    		bnez $t1, minimax_min_update_score 
    		j minimax_min_next_iter

	minimax_min_update_score:
    		move $s0, $t0             

	minimax_min_next_iter:
    		addi $s1, $s1, 1        
    		j minimax_min_loop

	minimax_min_loop_end:
    		move $v0, $s0             
    		j _minimax_exit


	minimax_maximizer_turn:
    		li $s0, MIN_VAL           

    		li $s1, 0         
    		        
	minimax_max_loop:
    		bge $s1, 9, minimax_max_loop_end
    		add $t0, $s3, $s1         
    		lb $t1, ($t0)            
    		bne $t1, EMPTY, minimax_max_next_iter 

    		# Ruch obecnego gracza
    		move $a0, $s3             
    		move $a1, $s1             
    		li $a2, PLAYER_O          
    		jal _make_move

    		# Rekurencyjny ruch przeciwnika
    		move $a0, $s3             
    		li $a1, PLAYER_X          
    		addi $a2, $s4, 1          
    		jal _minimax
    		move $t0, $v0             

    		move $a0, $s3             
    		move $a1, $s1             
    		jal _undo_move

    		# Aktualizacja
    		slt $t1, $s0, $t0         
    		bnez $t1, minimax_max_update_score 
    		j minimax_max_next_iter

	minimax_max_update_score:
   		 move $s0, $t0             

	minimax_max_next_iter:
    		addi $s1, $s1, 1         
    		j minimax_max_loop

	minimax_max_loop_end:
    		move $v0, $s0             
    		j _minimax_exit

	minimax_O_wins:
    		li $v0, 1                
    		j _minimax_exit

	minimax_X_wins:
    		li $v0, -1               
    		j _minimax_exit

	minimax_draw:
    		li $v0, 0                 
    		j _minimax_exit

	_minimax_exit:
    		# Przywracanie rejestrów
    		lw $ra, 16($sp)  
    		lw $s0, 12($sp)  
    		lw $s1, 8($sp)   
    		lw $s2, 4($sp)   
    		lw $s3, 0($sp)   
    		addi $sp, $sp, 20 
    		jr $ra           
    
	_find_best_computer_move:
    		addi $sp, $sp, -24
    		sw $ra, 20($sp)  
    		sw $s0, 16($sp)  
    		sw $s1, 12($sp)  
    		sw $s2, 8($sp)   
    		sw $s3, 4($sp)   
    		sw $s4, 0($sp)   

    		move $s3, $a0  


    		lw $s4, current_player 

    		li $s0, MIN_VAL          
    		li $s1, -1              

    		li $s2, 0               

	find_move_loop:
    		bge $s2, 9, find_move_loop_end 

    		add $t0, $s3, $s2        
    		lb $t1, ($t0)             
    		bne $t1, EMPTY, find_move_next_iter


		# ruch do sprawdzenia
    		move $a0, $s3            
    		move $a1, $s2             
    		move $a2, $s4             
    		jal _make_move

    		move $a0, $s3            
    
   		# minmax
   		beq $s4, PLAYER_O, set_next_player_X 
    		li $a1, PLAYER_O          
    		j continue_minimax_call
    		
	set_next_player_X:
    		li $a1, PLAYER_X          

	continue_minimax_call:
    		li $a2, 0                 
    		jal _minimax
    		move $t0, $v0             

    		# cofniecie ruchu
    		move $a0, $s3             
    		move $a1, $s2            
    		jal _undo_move

   		# porównanie z najlepszym wynikiem
    		slt $t1, $s0, $t0        
    		bnez $t1, find_move_update_best 

	find_move_next_iter:
    		addi $s2, $s2, 1          
    		j find_move_loop

	find_move_update_best:
    		move $s0, $t0             
    		move $s1, $s2             
    		j find_move_next_iter

	find_move_loop_end:
		# $v0 - zwracany najlepszy ruch do wykonania
    		move $v0, $s1             

	_find_best_computer_move_exit:
    		lw $ra, 20($sp)  
    		lw $s0, 16($sp)  
    		lw $s1, 12($sp)  
    		lw $s2, 8($sp)   
    		lw $s3, 4($sp)   
    		lw $s4, 0($sp)   
    		addi $sp, $sp, 24 
    		jr $ra                
