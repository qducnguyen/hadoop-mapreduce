#!/usr/bin/env python
# Reduce phase in Round 1
# for each key (i,s) independently (where 0 <= i <= M, and 0 <= s < sqrt(N))
# 1. gather all pairs with key (i,s): key, value of A[i,s], value of V[i,s]
# 2. calculate the inner product: W[i,s] = sum(A[i,s][i][j] * V[i,s][j])

import sys


def calculateRound1(current_key, a_value, v_value):
    # load a pair with key (i,s)
    i, s = current_key[0], current_key[1]
    W_is = 0
    for col in a_value[i].keys():
        W_is += a_value[i][col] * v_value[col]
    # compute/output result to STDOUT
    print('{0:d}\t{1:d},{2:d}'.format(i, s, W_is))


#Process input of matrix A
def gatherMatrixA(id, row, col, a_value, val):
    if row not in a_value:
      a_value[row] = dict()
    a_value[row][col] = val
    return a_value


#Process input of vector V
def gatherVectorV(id, row, v_value, val):
    if row not in v_value:
        v_value[row] = dict()
    v_value[row] = val
    return v_value


def main():
    #Create data structures to hold the current row/column values
    current_key = None
    a_value, v_value = dict(), dict()

    # input comes from STDIN (stream data that goes to the program)
    for line in sys.stdin:

        #Remove leading and trailing whitespace
        line = line.strip()
        
        #Get key/value
        key, value = line.split('\t',1)
        
        #Parse key/value input
        try:
            key = tuple(map(int, key.split(',')))
            value = value.split(',')
            id = value[0]
            row, col, val = int(value[1]), int(value[2]), int(value[3])
        except:
            continue

        #If we are still on the same key...
        if key == current_key:
            #Process key/value pair
            if id == 'A':
                a_value = gatherMatrixA(id, row, col, a_value, val)
            else:
                v_value = gatherVectorV(id, row, v_value, val)

            #Otherwise, if this is a new key...
        else:
        #If this is a new key and not the first key we've seen
            if current_key:
                calculateRound1(current_key, a_value, v_value)

            current_key = key
            a_value, v_value = dict(), dict()
            
            #Process input for new key
            if id == 'A':
                a_value = gatherMatrixA(id, row, col, a_value, val)
            else:
                v_value = gatherVectorV(id, row, v_value, val)
    
    #Compute/output result for the last key
    if current_key:
        calculateRound1(current_key, a_value, v_value)


if __name__ == "__main__":
    main()