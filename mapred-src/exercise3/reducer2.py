import sys 

def reducer2(): 
    i = 0   # initiate number of distinct element = 0 
    current_x = None
    for line in sys.stdin: 
        _, x = map(int, line.strip().split("\t"))

        # IF-ELSE statement: just for checking correctness 
        if current_x is None: 
            current_x = x

        elif current_x != x: 
            i += 1
            current_x = x
        else: 
            print(f"{current_x}\t{x}")
            # this line is printed if I am wrong. 
    # Adding one last value to i 
    i += 1 
    print(f"Number of distince elements: {i}")

if __name__ == "__main__": 
    reducer2()
