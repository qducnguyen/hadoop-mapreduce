import sys
import random

# Check for the correct number of command-line arguments
if len(sys.argv) != 4:
    print("Usage: python generate_matrix.py <width> <height> <output_file>")
    sys.exit(1)

# Read the necessary numbers from arguments
M = int(sys.argv[1])
N = int(sys.argv[2])
output_file = sys.argv[3]

data = []
# Generate the random matrix A
for i in range(M):
    for j in range(N):
        value = random.randint(0, 20)
        data.append(("A", i, j, value))

# Generate the random vector
for j in range(N):
    value = random.randint(0, 20)
    data.append(("V", j, -1, value))

# Write the matrix to the output file
with open(output_file, "w") as file:
    for item in data:
        file.write(f"{item[0]},{item[1]},{item[2]},{item[3]}\n")

print(
    f"Random {M}x{N} matrix and {N}-vector has been generated and saved to {output_file}."
)
