import sys 

def reducer(): 
    current_val = None 

    for line in sys.stdin: 
        # read (value, key) pair 
        x_i, key = map(int, line.strip().split("\t"))
        
        if current_val is None: 
            # set value for `current_val` for the first time
            current_val = x_i 

        if current_val != x_i: 
            # output (0, value) pairs
            print(f"{0:d}\t{current_val:d}")
            current_val = x_i
    
    # output the final (0, value) pair
    print(f"{0:d}\t{current_val:d}")

if __name__ == "__main__": 
    reducer()