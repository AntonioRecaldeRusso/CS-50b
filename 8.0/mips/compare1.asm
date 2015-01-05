##  compare1.asm
##  Author:	Jan Jackson
##  Date:	5/05/04
##  Compare two numbers and output the larger
# # Registers used:
	# $t0  Holds initial input integer value
	# $t1  Holds second input integer value
	# $t2  Holds the larger integer
		
	.text
	
main:
	## Get first number from memory, put into $t0.
	lw	$t0, num1	# load the word (integer) into a register
	lw	$t1, num2	# load the other integer into a register
	
	## Test the two, and put the bigger one in $t2
	bgt	$t0, $t1, isBigger
	
	move	$t2, $t1
	
	## Print the results
back:	la	$a0, result	# Print the result string
	li	$v0, 4		# Load the system call to do it
	syscall
	
	move	$a0, $t2	# Print the result number
	li	$v0, 1		# Load the system call to do it
	syscall

	la	$a0, newline	# Print the result string
	li	$v0, 4		# Load the system call to do it
	syscall

	li	$v0, 10		# Load call to exit program
	syscall
	
isBigger:
        move	$t2, $t0
	b	back

	##  Data for the program to print.
	.data
num1:		.word		4
num2:		.word		8
newline:	.asciiz		"\n"
result:		.asciiz		" The larger value is... "

