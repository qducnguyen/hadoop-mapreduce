import os
import sys
import random

N = int(sys.argv[1])
M = int(sys.argv[2])
output_file = sys.argv[3]
if __name__ == "__main__": 
    # current_directory = os.path.dirname(__file__)
    # output_filepath = current_directory + "/input.txt"

    with open(output_file, "w") as f: 
        data = [f"{i}\t{random.randint(0, 100)}" for i in range(N)]
        data = "\n".join(data)
        f.write(data)

