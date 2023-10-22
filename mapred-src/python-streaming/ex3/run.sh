python3 data_gen.py 100 100 ./input.txt
cat ./input.txt | python3 mapper.py | sort -n | python3 reducer.py | python3 reducer2.py