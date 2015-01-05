##
#  compare.asm
#  Jan Jackson,  Last_modified 4/30/13	
#	
#  Requests 2 values from the user and reads them from the keyboard,
#  then prints the larger of the two back.
#
# Registers used:
	# $t0  Holds initial input integer value
	# $t1  Holds second input integer value
	# $t2  Holds the larger integer
		
	.text
	
main:
	## Get first number from user, put into $t0.
	la	$a0, request_msg  	# load user request message (the arg for the syscall)
	li	$v0, 4			# load the syscall print_string into $v0
	syscall				# make the syscall

        li      $v0, 5          	# load syscall read_int into $v0.
        syscall                 	# make the syscall.
        move    $t0, $v0        	# move the number read from $v0 into $t0.

        ## Get second number from user, put into $t1.
	la	$a0, request_msg  	# load user request message 
	li	$v0, 4			# load the syscall print_string into $v0
	syscall

        li      $v0, 5          	# load syscall read_int into $v0.
        syscall                 	# make the syscall.
        move    $t1, $v0        	# move the number read from $v0 into $t1.

	## Test the two, and put the bigger one in $t2
	bgt	$t0, $t1, isBigger  	# if #t0 > $t1, jump to isBigger
	
	move	$t2, $t1		# if not, we come here and save $t1
	b	back			# Now we jump across the move to print the result
	
isBigger:
        move	$t2, $t0		# We came here because $t0 is bigger - so store it!

	## Print the results
back:	la	$a0, result		# Print the result string
	li	$v0, 4			# Load the system call to print_int
	syscall
	
	move	$a0, $t2		# Print the result number
	li	$v0, 1			# Load the system call to print_int
	syscall

	la	$a0, newline		# Print the result string
	li	$v0, 4			# Load the system call to print_string
	syscall

	li	$v0, 10			# Load call to exit program
	syscall
	
	##  Data for the program to print.
	.data
request_msg:	.asciiz		" Enter an integer... "
newline:	.asciiz		"\n"
result:		.asciiz		" The larger value is... "

