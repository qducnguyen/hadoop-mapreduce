python3 /root/ex2/data_gen.py 20

cat data/sample_data_20.txt | python3 /root/ex2/mapper.py 20 | sort -n | python3 /root/ex2/reducer_round1.py | sort -n | python3 /root/ex2/reducer_round2.py 20
