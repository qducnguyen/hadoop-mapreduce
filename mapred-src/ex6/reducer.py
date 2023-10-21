#!/usr/bin/env python3
# map function for matrix-vector multiplication
#Input file assumed to have lines of the form "A,i,j,x", where i is the row index, j is the column index, and x is the value in row i, column j of A. Entries of A are followed by lines of the form "B,i,j,x" for the matrix B. 
#It is assumed that the matrix dimensions are such that the product A*B exists. 

#Input arguments:
# M should be set to the number of rows in matrix A
# N should be set to the number of elements in vector V
 
import sys


# #number of rows in matrix A
# M = int(sys.argv[1]) 

# #number of elements in vector V
# N = int(sys.argv[2])

M = 5
N = 5


# input comes from STDIN (stream data that goes to the program)
for line in sys.stdin:
    
    #Remove leading and trailing whitespace
    line = line.strip()

    #Split line into array of entry data
    entry = line.split(",")
        
    # Set row, column, and value for this entry
    id = entry[0]
    row = int(entry[1])
    col = int(entry[2])
    value = int(entry[3])

    #If this is an entry in matrix A
    if (id == "A"):
        #Generate the necessary key-value pairs
        s = int(col / (N**0.5))
        # output result to STDOUT
        print('{0:d},{1:d}\tA,{2:d},{3:d},{4:d}'.format(row, s, row, col, value))

    #Otherwise, if this is an entry in vector V
    else:
        #Generate the necessary key-value pairs
        s = int(row / (N**0.5))
        for i in range(M):
        # output result to STDOUT
            print('{0:d},{1:d}\tV,{2:d},{3:d},{4:d}'.format(i, s, row, col, value))