#!/usr/bin/env python
"""reducer.py"""

from operator import itemgetter
import sys
import math


def reduce2(N):
    curr_idx = "0"
    curr_val = None
    current_mean = 0

    # input comes from STDIN
    for line in sys.stdin:
        # remove leading and trailing whitespace
        line = line.strip()
        # parse the input we got from mapper.py
        idx, val = line.split("\t", 1)

        # convert count (currently a string) to int
        try:
            val = int(val)
        except ValueError:
            # count was not a number, so silently
            # ignore/discard this line
            continue

        current_mean += val

    # do not forget to output the last word if needed!
    print(f"0\t{current_mean/N}")


def main():
    try:
        N = int(sys.argv[1])
    except:
        print("Need to get N")

    reduce2(N)


if __name__ == "__main__":
    main()
