#!/usr/bin/env python3
"""mapper.py"""

import sys
import math

def read_input(file):
    # Ignore line start with >
    for line in file:
        line_clean = line.strip()
        if len(line_clean) > 0:
            try:
                yield line_clean
            except: 
                continue

def main(separator='\t'):

    try:
        n = math.floor(int(sys.argv[1])**.5)
    except:
        print("Need to get N")

    # input comes from STDIN (standard input)
    data = read_input(sys.stdin)
    for words in data:
        if len(words.split()) == 1:
            continue
        idx, val = map(int, words.split())
        # write the results to STDOUT (standard output);
        # what we output here will be the input for the
        # Reduce step, i.e. the input for reducer.py
        #
        # tab-delimited; the trivial    word count is 1
        print(f"{idx % n}{separator}{val}")

if __name__ == "__main__":
    main()