python generate_data.py 5 5 ./data/input.txt
# cat ./data/input.txt | python mapper.py 5 10 | sort -n | python reducer_round1.py | sort -n | python reducer_round2.py 
cat ./data/input.txt | python mapper.py | sort -n | python reducer.py