import sys 

# mapper function 
def mapper(): 
    for line in sys.stdin: 
        # read key and value
        i, x_i = map(int, line.strip().split("\t"))
        # reproduce (value, key) pair instead of (key, value)
        print(f"{x_i:d}\t{i:d}")

if __name__ == "__main__": 
    mapper()