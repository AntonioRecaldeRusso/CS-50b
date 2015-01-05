#####
##  CountVowels.asm
##
##  Program to illustrate use of a loop, branch, and char comparison
##  CSCI E-50b, Jan's Section
##  Author:	Jan Jackson
##
##  Registers used:
##	$t0  --  Holds the location of the char we're working on in the String
##	$t1  --  Holds the char at that location
##	$t2  --  Holds counter for the number of vowels


	.text
main:
	la	$a0, request_string	#  Load the message to the user
	li	$v0, 4			#  Load the call to print it
	syscall

	la	$a0, string		#  Load the space for the user's string
	li	$a1, 1024		#  Load the number of bytes allocated
	li	$v0, 8			#  Load the call to read a string
	syscall

	la	$t0, string		#  Load the address of the input String
	li	$t2, 0			#  Start the counter register at 0

loop:	
	lb	$t1, ($t0)		#  Load the character at that address
	beqz	$t1, end		#  Check for the final null char to see if we're done

	beq	$t1, 'a', count		#  Begin the checks for vowels..
	beq	$t1, 'e', count		#  NOTE: because the vowels are spread out, we have
	beq	$t1, 'i', count		#   to test individually - ranges can be tested as well!
	beq	$t1, 'o', count		#  But we can't combine conditions, so this listing
	beq	$t1, 'u', count		#  is the only way for separated items
					#  When we find a vowel, go to 'count' to count it
	b	increment		#  If no vowel, just increment the pointer for the string
	
count:
	add	$t2, $t2, 1		#  We found a vowel so count it
	
increment:	
	add	$t0, $t0, 1		#  Move to the next char in the string
	b	loop			#  Branch to the start of the loop

end:
	la	$a0, newline		#  Load the string for the newline
	li	$v0, 4			#  Load the call to print the string
	syscall				#  Prints one more newline
	
	la	$a0, message		#  Print the result message to the user
	li	$v0, 4			#  Load the call to print the string
	syscall				#  Print it 

	move	$a0, $t2		#  Move the counter value so we can print it
	li	$v0, 1			#  Load the call to print an int
	syscall				#  Print it

	la	$a0, newline		#  Load the string for the newline
	li	$v0, 4			#  Load the call to print the string
	syscall				#  Prints one more newline	

	li	$v0, 10			#  Load call to exit
	syscall

###   Data for the program    ###	
	.data
request_string:	.asciiz		"Enter a string and I'll count the vowels:   "
string:		.space	 	1024    #  Space for the string to be entered
message:	.asciiz		"  The number of vowels is/are...  "
newline:	.asciiz		"\n"
	