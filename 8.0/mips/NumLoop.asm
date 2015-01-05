#####
##  NumLoop.asm
##
##  Program to illustrate use of a loop and branch statement
##  CSCI E-50b, Jan's Section
##  Author:	Jan Jackson
##
##  Registers used:
##	$t0  --  Holds user's limit of numbers to print
##	$t1  --  Holds counter and current value


	.text
main:
	la	$a0, request_string	#  Load the message to the user
	li	$v0, 4			#  Load the call to print it
	syscall

	li	$v0, 5			#  Load the call to read an int
	syscall
	move	$t0, $v0		#  Move the input value to $t0

	beqz	$t0, end
	
	li	$t1, 1			#  Load the value to start printing

loop:
	bgt	$t1, $t0, end		#  If we've finished, go to label 'end'

	la	$a0, message		#  Load the text to print
	li	$v0, 4			#  Load the call to print a string
	syscall
		
	move	$a0, $t1		#  Copy the value to $a0 for printing
	li	$v0, 1			#  Load the call to print an int
	syscall

	la	$a0, newline		#  Load the string for the newline
	li	$v0, 4			#  Load the call to print the string
	syscall

	add	$t1, $t1, 1		#  Increment the counter/value
	b	loop			#  Branch to the start of the loop

end:
	la	$a0, newline		#  Load the string for the newline
	li	$v0, 4			#  Load the call to print the string
	syscall				#  Prints one more newline
	
	li	$v0, 10			#  Load call to exit
	syscall

###   Data for the program    ###	
	.data
request_string:	.asciiz		"How many numbers do you want me to print?  "
message:	.asciiz		"  The number is now...  "
newline:	.asciiz		"\n"
	