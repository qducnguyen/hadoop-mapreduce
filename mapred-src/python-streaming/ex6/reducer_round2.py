#!/usr/bin/env python
# Reduce phase in Round 2
# for each key i independently
# 1. gather all pairs with key i and value W[i,s]
# 2. calculate final results: W[i] = sum(W[i,s]) for 0 <= s <= sqrt(N)

import sys


def calculateRound2(current_key, w_value):
    # load each key i independently
    i = int(current_key)
    W_i = 0
    for s in w_value.keys():
        W_i += w_value[s]
    # compute/output result to STDOUT
    print('{0:d}\t{1:d}'.format(i, W_i))


#Process input of vector W
def gatherMatrixW(s, w_value, val):
    if s not in w_value:
        w_value[s] = dict()
    w_value[s] = val
    return w_value


def main():
    #Create data structures to hold the current row/column values
    current_key = None
    w_value = dict()

    # input comes from STDIN (stream data that goes to the program)
    for line in sys.stdin:

        #Remove leading and trailing whitespace
        line = line.strip()
            
        #Get key/value
        key, value = line.split('\t',1)

        #Parse key/value input
        try:
            value = value.split(',')
            s = value[0]
            val = int(value[1])
        except:
            continue

        #If we are still on the same key...
        if key == current_key:
            #Process key/value pair
            w_value = gatherMatrixW(s, w_value, val)

        #Otherwise, if this is a new key...
        else:
            #If this is a new key and not the first key we've seen
            if current_key:
                calculateRound2(current_key, w_value)

            current_key = key
            w_value = dict()
            
            #Process input for new key
            w_value = gatherMatrixW(s, w_value, val)

    #Compute/output result for the last key
    if current_key:
        calculateRound2(current_key, w_value)


if __name__ == "__main__":
    main()