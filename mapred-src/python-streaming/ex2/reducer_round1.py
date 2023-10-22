#!/usr/bin/env python
"""reducer.py"""

from operator import itemgetter
import sys
import math


def reduce1():
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

        # this IF-switch only works because Hadoop sorts map output
        # by key (here: word) before it is passed to the reducer
        if idx == curr_idx:
            current_mean += val
        else:
            print(f"{curr_idx}\t{current_mean}")
            curr_idx = idx
            current_mean = val

    # do not forget to output the last word if needed!
    print(f"{curr_idx}\t{current_mean}")


def main():
    # try:
    #     n = math.floor(int(sys.argv[1])**.5)
    # except:
    #     print("Need to get N")

    reduce1()


if __name__ == "__main__":
    main()
