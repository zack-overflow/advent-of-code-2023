def main(input_file):
	# set counter to store amount of winning cards
    # we do this all together because
    with open(input_file, 'r') as file:
        for line in file:
            process_line(line)

def process_line(line):
    data = line.split(": ")[1]
    winners = clean_numbers(data[0])
    our_numbers = clean_numbers(data[1])

main("inputs/testinput4.1")