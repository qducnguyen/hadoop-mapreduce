import sys
import random

N = int(sys.argv[1])

with open(f'data/sample_data_{N}.txt', 'w') as f:
    x = '\n'.join([f'{i} {random.randint(0, 100)}' for i in range(N)])
    f.write(x)
    f.close()